package org.wahlzeit.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NaturePhoto extends Photo {

    /**
     *
     * @methodtype constructor
     */
    public NaturePhoto() {
        super();
    }

    /**
     *
     * @methodtype constructor
     */
    public NaturePhoto(PhotoId myId) {
        super(myId);
    }

    /**
     *
     * @methodtype constructor
     */
    public NaturePhoto(ResultSet rset) throws SQLException {
        super(rset);
    }
}
