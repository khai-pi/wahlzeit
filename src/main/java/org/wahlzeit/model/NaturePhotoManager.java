package org.wahlzeit.model;

import org.wahlzeit.main.ServiceMain;
import org.wahlzeit.services.Persistent;
import org.wahlzeit.services.SysLog;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class NaturePhotoManager extends PhotoManager{

    private static boolean isInitialized = false;

    /**
     * In-memory cache for photos
     */
    protected Map<PhotoId, NaturePhoto> photoCache = new HashMap<PhotoId, NaturePhoto>();

    public static NaturePhotoManager getInstance() {
        if (!isInitialized) {
            PhotoManager.setInstance(new NaturePhotoManager());
            isInitialized = true;
        }
        return (NaturePhotoManager) PhotoManager.getInstance();
    }

    /**
     *
     */
    public NaturePhotoManager() {
        photoTagCollector = NaturePhotoFactory.getInstance().createPhotoTagCollector();
    }

    /**
     * @methodtype boolean-query
     * @methodproperties primitive
     */
    protected boolean doHasPhoto(PhotoId id) {
        return photoCache.containsKey(id);
    }

    /**
     *
     */
    public NaturePhoto getPhotoFromId(PhotoId id) {
        if (id.isNullId()) {
            return null;
        }

        NaturePhoto result = doGetPhotoFromId(id);

        if (result == null) {
            try {
                PreparedStatement stmt = getReadingStatement("SELECT * FROM photos WHERE id = ?");
                result = (NaturePhoto) readObject(stmt, id.asInt());
            } catch (SQLException sex) {
                SysLog.logThrowable(sex);
            }
            if (result != null) {
                doAddPhoto(result);
            }
        }

        return result;
    }

    /**
     * @methodtype get
     * @methodproperties primitive
     */
    protected NaturePhoto doGetPhotoFromId(PhotoId id) {
        return photoCache.get(id);
    }

    /**
     *
     */
    protected NaturePhoto createObject(ResultSet rset) throws SQLException {
        return NaturePhotoFactory.getInstance().createPhoto(rset);
    }

    /**
     * @methodtype command
     *
     * Load all persisted photos. Executed when Wahlzeit is restarted.
     */
    public void addPhoto(NaturePhoto photo) {
        PhotoId id = photo.getId();
        assertIsNewPhoto(id);
        doAddPhoto(photo);

        try {
            PreparedStatement stmt = getReadingStatement("INSERT INTO photos(id) VALUES(?)");
            createObject(photo, stmt, id.asInt());
            ServiceMain.getInstance().saveGlobals();
        } catch (SQLException sex) {
            SysLog.logThrowable(sex);
        }
    }

    /**
     * @methodtype command
     * @methodproperties primitive
     */
    protected void doAddPhoto(NaturePhoto myPhoto) {
        photoCache.put(myPhoto.getId(), myPhoto);
    }

    /**
     * @methodtype command
     */
    public void loadNaturePhotos(Collection<NaturePhoto> result) {
        try {
            PreparedStatement stmt = getReadingStatement("SELECT * FROM photos");
            readObjects(result, stmt);
            for (Iterator<NaturePhoto> i = result.iterator(); i.hasNext(); ) {
                Photo photo = i.next();
                if (!doHasPhoto(photo.getId())) {
                    doAddPhoto(photo);
                } else {
                    SysLog.logSysInfo("photo", photo.getId().asString(), "photo had already been loaded");
                }
            }
        } catch (SQLException sex) {
            SysLog.logThrowable(sex);
        }

        SysLog.logSysInfo("loaded all photos");
    }

    /**
     *
     */
    public void savePhoto(NaturePhoto photo) {
        try {
            PreparedStatement stmt = getUpdatingStatement("SELECT * FROM photos WHERE id = ?");
            updateObject(photo, stmt);
        } catch (SQLException sex) {
            SysLog.logThrowable(sex);
        }
    }

    /**
     *
     */
    public void savePhotos() {
        try {
            PreparedStatement stmt = getUpdatingStatement("SELECT * FROM photos WHERE id = ?");
            updateObjects(photoCache.values(), stmt);
        } catch (SQLException sex) {
            SysLog.logThrowable(sex);
        }
    }

    /**
     * @methodtype command
     *
     * Persists all available sizes of the Photo. If one size exceeds the limit of the persistence layer, e.g. > 1MB for
     * the Datastore, it is simply not persisted.
     */
    public Set<Photo> findPhotosByOwner(String ownerName) {
        Set<Photo> result = new HashSet<Photo>();
        try {
            PreparedStatement stmt = getReadingStatement("SELECT * FROM photos WHERE owner_name = ?");
            readObjects(result, stmt, ownerName);
        } catch (SQLException sex) {
            SysLog.logThrowable(sex);
        }

        for (Iterator<Photo> i = result.iterator(); i.hasNext(); ) {
            doAddPhoto(i.next());
        }

        return result;
    }

    /**
     *
     */
    public NaturePhoto getVisiblePhoto(PhotoFilter filter) {
        NaturePhoto result = getPhotoFromFilter(filter);

        if(result == null) {
            java.util.List<PhotoId> list = getFilteredPhotoIds(filter);
            filter.setDisplayablePhotoIds(list);
            result = getPhotoFromFilter(filter);
        }

        return result;
    }

    /**
     *
     */
    protected NaturePhoto getPhotoFromFilter(PhotoFilter filter) {
        PhotoId id = filter.getRandomDisplayablePhotoId();
        NaturePhoto result = getPhotoFromId(id);
        while((result != null) && !result.isVisible()) {
            id = filter.getRandomDisplayablePhotoId();
            result = getPhotoFromId(id);
            if ((result != null) && !result.isVisible()) {
                filter.addProcessedPhoto(result);
            }
        }

        return result;
    }

    /**
     *
     */
    protected void updateDependents(Persistent obj) throws SQLException {
        NaturePhoto photo = (NaturePhoto) obj;

        PreparedStatement stmt = getReadingStatement("DELETE FROM tags WHERE photo_id = ?");
        deleteObject(obj, stmt);

        stmt = getReadingStatement("INSERT INTO tags VALUES(?, ?)");
        Set<String> tags = new HashSet<String>();
        photoTagCollector.collect(tags, photo);
        for (Iterator<String> i = tags.iterator(); i.hasNext(); ) {
            String tag = i.next();
            stmt.setString(1, tag);
            stmt.setInt(2, photo.getId().asInt());
            SysLog.logQuery(stmt);
            stmt.executeUpdate();
        }
    }

    /**
     *
     */
    public NaturePhoto createPhoto(File file) throws Exception {
        PhotoId id = PhotoId.getNextId();
        NaturePhoto result = (NaturePhoto) PhotoUtil.createPhoto(file, id);
        addPhoto(result);
        return result;
    }

}

