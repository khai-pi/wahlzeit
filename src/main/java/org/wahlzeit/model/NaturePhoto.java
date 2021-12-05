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

    private String country;
    private int daytime = -1;

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
        assert rset != null;
        readFrom(rset);
    }

    /**
     *
     */
    @Override
    public void readFrom(ResultSet rset) throws SQLException {
        assert rset != null;
        country = rset.getString("country");
        daytime = rset.getInt("daytime");
        super.readFrom(rset);
    }

    /**
     *
     */
    @Override
    public void writeOn(ResultSet rset) throws SQLException {
        assert rset != null;
        rset.updateString("country", country);
        rset.updateInt("daytime", daytime);
        super.readFrom(rset);
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getDaytime() {
        return daytime;
    }

    public void setDaytime(int daytime) {
        this.daytime = daytime;
    }
}
