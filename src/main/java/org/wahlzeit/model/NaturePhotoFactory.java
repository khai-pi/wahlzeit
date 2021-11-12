package org.wahlzeit.model;

import org.wahlzeit.services.SysLog;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NaturePhotoFactory extends PhotoFactory {

    /**
     * Hidden singleton instance; needs to be initialized from the outside.
     */
    private static NaturePhotoFactory instance = null;

    /**
     * Public singleton access method.
     */
    public static synchronized NaturePhotoFactory getInstance() {
        if (instance == null) {
            SysLog.logSysInfo("setting generic NaturePhotoFactory");
            setInstance(new NaturePhotoFactory());
        }

        return instance;
    }

    /**
     * Method to set the singleton instance of PhotoFactory.
     */
    protected static synchronized void setInstance(NaturePhotoFactory naturePhotoFactory) {
        if (instance != null) {
            throw new IllegalStateException("attempt to initialize PhotoFactory twice");
        }

        instance = naturePhotoFactory;
    }

    /**
     * Hidden singleton instance; needs to be initialized from the outside.
     */
    public static void initialize() {
        getInstance(); // drops result due to getInstance() side-effects
    }

    /**
     *
     */
    protected NaturePhotoFactory() {
        // do nothing
    }

    /**
     * @methodtype factory
     */
    public NaturePhoto createPhoto() {
        return new NaturePhoto();
    }

    /**
     *
     */
    public NaturePhoto createPhoto(PhotoId id) {
        return new NaturePhoto(id);
    }

    /**
     *
     */
    public NaturePhoto createPhoto(ResultSet rs) throws SQLException {
        return new NaturePhoto(rs);
    }
}
