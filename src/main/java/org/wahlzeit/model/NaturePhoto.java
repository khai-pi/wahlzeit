package org.wahlzeit.model;

import org.wahlzeit.services.SysLog;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import java.io.*;
import java.sql.*;
import java.util.*;

import org.wahlzeit.main.*;
import org.wahlzeit.services.*;

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
