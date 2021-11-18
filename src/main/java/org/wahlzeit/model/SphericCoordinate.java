package org.wahlzeit.model;

import org.wahlzeit.services.DataObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class SphericCoordinate extends DataObject implements Coordinate {

    private double phi;
    private double theta;
    private double radius;

    public SphericCoordinate(double phi, double theta, double radius) {
        this.phi = phi;
        this.theta = theta;
        this.radius = radius;
    }

    public double getPhi() {
        return this.phi;
    }

    public double getTheta() {
        return this.theta;
    }

    public double getRadius() {
        return this.radius;
    }

    public void setPhi(double phi) {
        this.phi = phi;
    }

    public void setTheta(double theta) {
        this.theta = theta;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        double x = radius * Math.sin(theta) * Math.cos(phi);
        double y = radius * Math.sin(theta) * Math.sin(phi);
        double z = radius * Math.cos(theta);
        return new CartesianCoordinate(x,y,z);
    }

    @Override
    public double getCartesianDistance(Coordinate coordinate) {
        return this.asCartesianCoordinate().
                getCartesianDistance(coordinate.asCartesianCoordinate());
    }

    @Override
    public SphericCoordinate asSphericCoordinate() {
        return this;
    }

    @Override
    public double getCentralAngle(Coordinate coordinate) {
        SphericCoordinate sphericCoordinate = coordinate.asSphericCoordinate();
        return Math.acos(Math.sin(this.phi) * Math.sin(sphericCoordinate.phi)
                + Math.cos(this.phi) * Math.cos(sphericCoordinate.phi) * Math.cos(Math.abs(this.radius - sphericCoordinate.radius)));

    }

    @Override
    public int hashCode() {
        return Objects.hash(phi,theta,radius);
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
        return doIsEqualSpheric(coordinate.asSphericCoordinate());
    }

    @Override
    public boolean equals(Object o) {
        if (o==this) return true;
        if (!(o instanceof  Coordinate)) return false;
        Coordinate other = (Coordinate) o;
        return isEqual(other);
    }

    public boolean doIsEqualCartesian(CartesianCoordinate cartesianCoordinate) {
        CartesianCoordinate thisCartesianCoordinate = this.asCartesianCoordinate();
        double delta = 0.0001;
        return (Math.abs(thisCartesianCoordinate.getX() - cartesianCoordinate.getX()) <= delta)
                && (Math.abs(thisCartesianCoordinate.getY() - cartesianCoordinate.getY()) <= delta)
                && (Math.abs(thisCartesianCoordinate.getZ() - cartesianCoordinate.getZ()) <= delta);
    }

    public boolean doIsEqualSpheric(SphericCoordinate sphericCoordinate) {
//        CartesianCoordinate cartesianCoordinate = sphericCoordinate.asCartesianCoordinate();
//        return doIsEqualCartesian(cartesianCoordinate);
        double delta = 0.0001;
        return (Math.abs(this.getPhi() - sphericCoordinate.getPhi()) <= delta)
                && (Math.abs(this.getTheta() - sphericCoordinate.getTheta()) <= delta)
                && (Math.abs(this.getRadius() - sphericCoordinate.getRadius()) <= delta);
    }

    @Override
    public String getIdAsString() {
        return null;
    }

    @Override
    public void readFrom(ResultSet rset) throws SQLException {
        phi = rset.getDouble("coordinate_phi");
        theta = rset.getDouble("coordinate_theta");
        radius = rset.getDouble("coordinate_radius");
    }

    @Override
    public void writeOn(ResultSet rset) throws SQLException {
        rset.updateDouble("coordinate_phi", this.phi);
        rset.updateDouble("coordinate_theta", this.theta);
        rset.updateDouble("coordinate_radius", this.radius);
    }

    @Override
    public void writeId(PreparedStatement stmt, int pos) throws SQLException {

    }
}
