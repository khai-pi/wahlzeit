package org.wahlzeit.model;

import org.wahlzeit.services.DataObject;

import java.lang.Math;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class CartesianCoordinate extends DataObject implements Coordinate {

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
    public CartesianCoordinate(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        return this;
    }

    /**
     *
     * @methodtype get
     */
    @Override
    public double getCartesianDistance(Coordinate coordinate) {
        CartesianCoordinate cartesianCoordinate = coordinate.asCartesianCoordinate();
        double distanceSquare =
                  Math.pow(this.getX()-cartesianCoordinate.getX(),2)
                + Math.pow(this.getY()-cartesianCoordinate.getY(),2)
                + Math.pow(this.getZ()-cartesianCoordinate.getZ(),2);
        return Math.sqrt(distanceSquare);
    }

    @Override
    public SphericCoordinate asSphericCoordinate() {
        double radius = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
        double phi = Math.atan(y / x);
        double theta = Math.acos(z / radius);

        return new SphericCoordinate(phi, theta, radius);
    }

    @Override
    public double getCentralAngle(Coordinate coordinate) {
        return this.asSphericCoordinate()
                .getCentralAngle(coordinate.asSphericCoordinate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }

    /**
     *
     * @methodtype boolean-query
     * return true if coordinate has the same x,y,z values,
     * even if they are 2 different object
     */
    public boolean isEqual(Coordinate coordinate) {
//        if (coordinate instanceof CartesianCoordinate) return doIsEqualCartesian(coordinate.asCartesianCoordinate());
//        else if (coordinate instanceof SphericCoordinate) return doIsEqualSpheric(coordinate.asSphericCoordinate());
//        else return false;
        return doIsEqualCartesian(coordinate.asCartesianCoordinate());
    }

    @Override
    public boolean equals(Object o) {
        if (o==this) return true;
        if (!(o instanceof  Coordinate)) return false;
        Coordinate other = (Coordinate) o;
        return isEqual(other);
    }

    public boolean doIsEqualCartesian(CartesianCoordinate cartesianCoordinate) {
        double delta = 0.0001;
        return (Math.abs(this.getX() - cartesianCoordinate.getX()) <= delta)
                && (Math.abs(this.getY() - cartesianCoordinate.getY()) <= delta)
                && (Math.abs(this.getZ() - cartesianCoordinate.getZ()) <= delta);
    }

    public boolean doIsEqualSpheric(SphericCoordinate sphericCoordinate) {
        CartesianCoordinate cartesianCoordinate = sphericCoordinate.asCartesianCoordinate();
        return doIsEqualCartesian(cartesianCoordinate);
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

}
