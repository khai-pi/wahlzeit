package org.wahlzeit.model;

import org.wahlzeit.services.DataObject;

import java.lang.Math;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class Coordinate extends DataObject {

    /**
     *
     */
    private double x;
    private double y;
    private double z;

    /**
     *
     * @methodtype constructor
     */
    public Coordinate(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     *
     * @methodtype set
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     *
     * @methodtype set
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     *
     * @methodtype set
     */
    public void setZ(double z) {
        this.z = z;
    }

    /**
     *
     * @methodtype get
     */
    public double getX() {
        return x;
    }

    /**
     *
     * @methodtype get
     */
    public double getY() {
        return y;
    }

    /**
     *
     * @methodtype get
     */
    public double getZ() {
        return z;
    }

    /**
     *
     * @methodtype get
     */
    public double getDistance(Coordinate coordinate) {
        double distanceSquare =
                  Math.pow(this.getX()-coordinate.getX(),2)
                + Math.pow(this.getY()-coordinate.getY(),2)
                + Math.pow(this.getZ()-coordinate.getZ(),2);
        return Math.sqrt(distanceSquare);
    }

    /**
     *
     * @methodtype boolean-query
     * return true if coordinate has the same x,y,z values,
     * even if they are 2 different object
     */
    public boolean isEqual(Coordinate coordinate) {
        return (this.getX() == coordinate.getX())
                && (this.getY() == coordinate.getY())
                && (this.getZ() == coordinate.getZ());
    }

    @Override
    public String getIdAsString() {
        return null;
    }

    @Override
    public void readFrom(ResultSet rset) throws SQLException {
        x = rset.getDouble("coordinate_x");
        y = rset.getDouble("coordinate_y");
        z = rset.getDouble("coordinate_z");
    }

    @Override
    public void writeOn(ResultSet rset) throws SQLException {
        rset.updateDouble("coordinate_x", this.x);
        rset.updateDouble("coordinate_y", this.y);
        rset.updateDouble("coordinate_z", this.z);
    }

    @Override
    public void writeId(PreparedStatement stmt, int pos) throws SQLException {

    }

    @Override
    public boolean equals(Object o) {
        if (o==this) return true;
        if (!(o instanceof  Coordinate)) return false;
        Coordinate other = (Coordinate) o;
        return isEqual(other);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }
}
