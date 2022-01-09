package org.wahlzeit.model;

import org.wahlzeit.services.SysLog;

import java.sql.ResultSet;
import java.sql.SQLException;

@PatternInstance(
        patternName = "Singleton",
        participants = {
                "NaturePhotoFactory"
        }
)
public class NaturePhotoFactory extends PhotoFactory {

    /**
     * Hidden singleton instance; needs to be initialized from the outside.
     */
    private static boolean isInitialized = false;

    /**
     * Public singleton access method.
     */
    public static synchronized NaturePhotoFactory getInstance() throws IllegalStateException {
        if (!isInitialized) {
            SysLog.logSysInfo("setting generic NaturePhotoFactory");
            PhotoFactory.setInstance(new NaturePhotoFactory());
            isInitialized = true;
        }

        return (NaturePhotoFactory) PhotoFactory.getInstance();
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
        assert id != null;
        return new NaturePhoto(id);
    }

    /**
     *
     */
    public NaturePhoto createPhoto(ResultSet rs) throws SQLException {
        assert rs != null;
        return new NaturePhoto(rs);
    }
}
