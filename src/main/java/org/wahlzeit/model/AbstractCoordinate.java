package org.wahlzeit.model;

import org.wahlzeit.services.DataObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AbstractCoordinate extends DataObject implements Coordinate {
    @Override
    public String getIdAsString() {
        return null;
    }

    @Override
    public abstract void readFrom(ResultSet rset) throws SQLException;

    @Override
    public abstract void writeOn(ResultSet rset) throws SQLException;

    @Override
    public void writeId(PreparedStatement stmt, int pos) throws SQLException {

    }

    @Override
    public abstract CartesianCoordinate asCartesianCoordinate();

    /**
     *
     * @param coordinate
     * @return double
     * this method turn the coordinate, which called it, to CartesianCoordinate
     * and turn the Coordinate parameter to CartesianCoordinate
     * then it calculates the distance between 2 coordinates
     */
    @Override
    public double getCartesianDistance(Coordinate coordinate) {
        CartesianCoordinate cartesianCoordinate = coordinate.asCartesianCoordinate();
        double distanceSquare =
                Math.pow(this.asCartesianCoordinate().getX()-cartesianCoordinate.getX(),2)
                        + Math.pow(this.asCartesianCoordinate().getY()-cartesianCoordinate.getY(),2)
                        + Math.pow(this.asCartesianCoordinate().getZ()-cartesianCoordinate.getZ(),2);
        return Math.sqrt(distanceSquare);
    }

    @Override
    public abstract SphericCoordinate asSphericCoordinate();

    /**
     *
     * @param coordinate
     * @return double
     * this method turn the coordinate, which called it, to SphericCoordinate
     * and turn the Coordinate parameter to SphericCoordinate
     * then it calculates the central angle between 2 coordinates
     */
    @Override
    public double getCentralAngle(Coordinate coordinate) {
        SphericCoordinate sphericCoordinate = coordinate.asSphericCoordinate();
        return Math.acos(Math.sin(this.asSphericCoordinate().getPhi()) * Math.sin(sphericCoordinate.getPhi())
                + Math.cos(this.asSphericCoordinate().getPhi())
                * Math.cos(sphericCoordinate.asSphericCoordinate().getPhi())
                * Math.cos(Math.abs(this.asSphericCoordinate().getRadius() - sphericCoordinate.asSphericCoordinate().getRadius())));

    }

    @Override
    public boolean isEqual(Coordinate coordinate) {
        /**
        if (coordinate instanceof CartesianCoordinate) return doIsEqualCartesian(coordinate.asCartesianCoordinate());
        else if (coordinate instanceof SphericCoordinate) return doIsEqualSpheric(coordinate.asSphericCoordinate());
        else return false;
         */
        return doIsEqualCartesian(coordinate.asCartesianCoordinate());
    };

    @Override
    public boolean equals(Object o) {
        if (o==this) return true;
        if (!(o instanceof AbstractCoordinate)) return false;
        AbstractCoordinate other = (AbstractCoordinate) o;
        return isEqual(other);
    }

    @Override
    public abstract int hashCode();

    // compare coordinates base on its Cartesian Coordinate
    public boolean doIsEqualCartesian(CartesianCoordinate cartesianCoordinate) {
        double delta = 0.0001;
        return (Math.abs(this.asCartesianCoordinate().getX() - cartesianCoordinate.getX()) <= delta)
                && (Math.abs(this.asCartesianCoordinate().getY() - cartesianCoordinate.getY()) <= delta)
                && (Math.abs(this.asCartesianCoordinate().getZ() - cartesianCoordinate.getZ()) <= delta);
    }

//    public boolean doIsEqualSpheric(SphericCoordinate sphericCoordinate) {
//        CartesianCoordinate cartesianCoordinate = sphericCoordinate.asCartesianCoordinate();
//        return doIsEqualCartesian(cartesianCoordinate);
//    }
}
