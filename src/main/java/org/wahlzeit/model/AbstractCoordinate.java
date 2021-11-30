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
        return coordinate.asCartesianCoordinate().getCartesianDistance(this);
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
        return coordinate.asSphericCoordinate().getCentralAngle(this);
    }

    // convert coordinate co cartesian coordinate to compare
    @Override
    public boolean isEqual(Coordinate coordinate) {
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
    public int hashCode() {
        return this.asCartesianCoordinate().hashCode();
    }

    // compare coordinates base on its Cartesian Coordinate
    public boolean doIsEqualCartesian(CartesianCoordinate cartesianCoordinate) {
        double delta = 0.0001;
        return (Math.abs(this.asCartesianCoordinate().getX() - cartesianCoordinate.getX()) <= delta)
                && (Math.abs(this.asCartesianCoordinate().getY() - cartesianCoordinate.getY()) <= delta)
                && (Math.abs(this.asCartesianCoordinate().getZ() - cartesianCoordinate.getZ()) <= delta);
    }

}
