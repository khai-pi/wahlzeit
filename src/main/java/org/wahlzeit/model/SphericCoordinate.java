package org.wahlzeit.model;

import org.wahlzeit.services.DataObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class SphericCoordinate extends AbstractCoordinate {

    private double phi;
    private double theta;
    private double radius;

    public SphericCoordinate(double phi, double theta, double radius) throws IllegalStateException {
        this.phi = phi;
        this.theta = theta;
        this.radius = radius;

        assertClassInvariants();
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
        assert radius >= 0;
        this.radius = radius;
    }

    @Override
    public CartesianCoordinate asCartesianCoordinate() throws IllegalStateException{

        assertClassInvariants();

        double x = radius * Math.sin(theta) * Math.cos(phi);
        double y = radius * Math.sin(theta) * Math.sin(phi);
        double z = radius * Math.cos(theta);

        assertClassInvariants();

        return new CartesianCoordinate(x,y,z);
    }

    @Override
    public SphericCoordinate asSphericCoordinate() {
        return this;
    }

    /**
     *
     * @param coordinate
     * @return double
     * this method turn the coordinate, which called it, to SphericCoordinate
     * and turn the Coordinate parameter to SphericCoordinate
     * then it calculates the central angle between 2 coordinates
     */
    @Override
    public double getCentralAngle(Coordinate coordinate) throws IllegalArgumentException {
        try {
            assertClassInvariants();
            assertCoordinateIsNotNull(coordinate);

            SphericCoordinate sphericCoordinate = coordinate.asSphericCoordinate();

            double centralAngle = Math.acos(Math.sin(this.getPhi()) * Math.sin(sphericCoordinate.getPhi())
                    + Math.cos(this.getPhi())
                    * Math.cos(sphericCoordinate.asSphericCoordinate().getPhi())
                    * Math.cos(Math.abs(this.getRadius() - sphericCoordinate.asSphericCoordinate().getRadius())));

            assert centralAngle >= 0;
            assertClassInvariants();

            return centralAngle;
        } catch (IllegalStateException | NullPointerException exception) {
            throw new IllegalArgumentException("Something is wrong with your coordinate"
                    + exception);
        }
    }

//    @Override
//    public int hashCode() {
//        return Objects.hash(phi,theta,radius);
//    }

    @Override
    public void readFrom(ResultSet rset) throws SQLException {
        assert rset != null;
        phi = rset.getDouble("coordinate_phi");
        theta = rset.getDouble("coordinate_theta");
        radius = rset.getDouble("coordinate_radius");
    }

    @Override
    public void writeOn(ResultSet rset) throws SQLException {
        assert rset != null;
        rset.updateDouble("coordinate_phi", this.phi);
        rset.updateDouble("coordinate_theta", this.theta);
        rset.updateDouble("coordinate_radius", this.radius);
    }

    /**
     *  Ensure a correct Spherical Coordinate, all coordinate must not null
     *  and radius greater than 0.
     */
    @Override
    public void assertClassInvariants() throws IllegalStateException{
//        assert !Double.isNaN(phi);
//        assert phi != Double.POSITIVE_INFINITY;
//        assert phi != Double.NEGATIVE_INFINITY;
//        assert !Double.isNaN(theta);
//        assert theta != Double.POSITIVE_INFINITY;
//        assert theta != Double.NEGATIVE_INFINITY;
//        assert !Double.isNaN(radius);
//        assert radius >= 0;
//        assert radius != Double.POSITIVE_INFINITY;
        if (Double.isNaN(phi) || Double.isNaN(theta) || Double.isNaN(radius)) {
            throw new IllegalStateException("Coordinate must not isNan");
        }
        if (phi == Double.POSITIVE_INFINITY || theta == Double.POSITIVE_INFINITY || radius == Double.POSITIVE_INFINITY
            || phi == Double.NEGATIVE_INFINITY || theta == Double.NEGATIVE_INFINITY) {
            throw new IllegalStateException("phi, theta, radius must not Infinity");
        }
        if (radius < 0) {
            throw new IllegalStateException("radius must greater than 0");
        }
    }
}
