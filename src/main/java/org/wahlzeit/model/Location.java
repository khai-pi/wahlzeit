package org.wahlzeit.model;

import org.wahlzeit.services.DataObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Location extends DataObject {

    /**
     *
     */
    private CartesianCoordinate cartesianCoordinate;
    private SphericCoordinate sphericCoordinate;

    public Location(Coordinate coordinate) {
        this.cartesianCoordinate = coordinate.asCartesianCoordinate();
        this.sphericCoordinate = coordinate.asSphericCoordinate();
    }

    public void setCoordinate(Coordinate coordinate) {
        this.cartesianCoordinate = coordinate.asCartesianCoordinate();
        this.sphericCoordinate = coordinate.asSphericCoordinate();
    }

    public CartesianCoordinate getCartesianCoordinate() {
        return cartesianCoordinate;
    }

    public SphericCoordinate getSphericCoordinate() {
        return getSphericCoordinate();
    }

    @Override
    public String getIdAsString() {
        return null;
    }

    @Override
    public void readFrom(ResultSet rset) throws SQLException {
        this.cartesianCoordinate.readFrom(rset);
        this.sphericCoordinate.readFrom(rset);
    }

    @Override
    public void writeOn(ResultSet rset) throws SQLException {
        this.cartesianCoordinate.writeOn(rset);
        this.sphericCoordinate.writeOn(rset);
    }

    @Override
    public void writeId(PreparedStatement stmt, int pos) throws SQLException {

    }
}
