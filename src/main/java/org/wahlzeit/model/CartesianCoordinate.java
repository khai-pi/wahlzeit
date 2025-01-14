package org.wahlzeit.model;

import java.lang.Math;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Objects;

@PatternInstance(
        patternName = "Value Objects",
        participants = {
                "CartesianCoordinate"
    }
)
public class CartesianCoordinate extends AbstractCoordinate {

    protected static HashMap<Integer, CartesianCoordinate> map = new HashMap<>();

    /**
     *
     */
    private final double x;
    private final double y;
    private final double z;

    /**
     *
     * @methodtype constructor
     */
    private CartesianCoordinate(double x, double y, double z) throws IllegalStateException {
        this.x = x;
        this.y = y;
        this.z = z;

        assertClassInvariants();
    }

    /**
     * get CartesianCoordinate in map
     * if there is no such coordinate, create a new one
     *
     */
    public static CartesianCoordinate getCartesianCoorinate(double x, double y, double z) {
        int hash = hashCode(x,y,z);
        CartesianCoordinate cartesianCoordinate = map.get(hash);
        if (cartesianCoordinate == null) {
            cartesianCoordinate = new CartesianCoordinate(x, y, z);
            map.put(hash, cartesianCoordinate);
        }
        return cartesianCoordinate;
    }

    public CartesianCoordinate setX(double x) {
        assert x >= 0;
        return new CartesianCoordinate(x, this.getY(), this.getZ());
    }

    public CartesianCoordinate setY(double y) {
        assert y >= 0;
        return new CartesianCoordinate(this.getX(), y, this.getZ());
    }

    public CartesianCoordinate setZ(double z) {
        assert z >= 0;
        return new CartesianCoordinate(this.getX(), this.getY(), z);
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

    @Override
    public SphericCoordinate asSphericCoordinate() throws ArithmeticException{
        assertClassInvariants();

        double radius = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
        if (radius == 0) {
            return SphericCoordinate.getSphericCoordinate(0,0,0);
        }
        double phi = Math.atan(y / x);
        double theta = Math.acos(z / radius);

        assertClassInvariants();

        return SphericCoordinate.getSphericCoordinate(phi, theta, radius);
    }

    /**
     *
     * @param coordinate
     * @return double
     * this method turn the coordinate, which called it, to CartesianCoordinate
     * and turn the Coordinate parameter to CartesianCoordinate
     * then it calculates the distance between 2 coordinates
     */
    @Override
    public double getCartesianDistance(Coordinate coordinate) throws IllegalArgumentException {
        try {
            assertClassInvariants();
            assertCoordinateIsNotNull(coordinate);

            CartesianCoordinate cartesianCoordinate = coordinate.asCartesianCoordinate();
            double distanceSquare =
                    Math.pow(this.getX() - cartesianCoordinate.getX(), 2)
                            + Math.pow(this.getY() - cartesianCoordinate.getY(), 2)
                            + Math.pow(this.getZ() - cartesianCoordinate.getZ(), 2);

            assert distanceSquare >= 0;
            assertClassInvariants();

            return Math.sqrt(distanceSquare);
        } catch (IllegalStateException | NullPointerException exception) {
            throw new IllegalArgumentException("Something is wrong with your coordinate"
                    + exception);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }

    private static int hashCode(double x, double y, double z) {
        return Objects.hash(x,y,z);
    }

    // TODO How to create new object here
    @Override
    public void readFrom(ResultSet rset) throws SQLException {
        assert rset != null;
//        x = rset.getDouble("coordinate_x");
//        y = rset.getDouble("coordinate_y");
//        z = rset.getDouble("coordinate_z");
//        double xRset = rset.getDouble("coordinate_x");
//        double yRset = rset.getDouble("coordinate_y");
//        double zRset = rset.getDouble("coordinate_z");
//        getCartesianCoorinate(x,y,z)
    }

    /**
     *
     * @param rset
     * @return CartesianCoordinate
     * @throws SQLException
     *
     * this method is used instead of old readFrom since is complicated to change readFrom signature
     */
    public static CartesianCoordinate readFromValue(ResultSet rset) throws SQLException {
        double xRset = rset.getDouble("coordinate_x");
        double yRset = rset.getDouble("coordinate_y");
        double zRset = rset.getDouble("coordinate_z");
        return getCartesianCoorinate(xRset,yRset,zRset);
    }

    @Override
    public void writeOn(ResultSet rset) throws SQLException {
        assert rset != null;
        rset.updateDouble("coordinate_x", this.x);
        rset.updateDouble("coordinate_y", this.y);
        rset.updateDouble("coordinate_z", this.z);
    }

    /**
     *  Ensure a correct Cartesian Coordinate, all coordinate must not null,
     *  greater than 0 and not infinity
     */
    @Override
    public void assertClassInvariants() throws IllegalStateException{
//        assert !Double.isNaN(x);
//        assert x != Double.POSITIVE_INFINITY;
//        assert x >= 0;
//        assert !Double.isNaN(y);
//        assert y != Double.POSITIVE_INFINITY;
//        assert y >= 0;
//        assert !Double.isNaN(z);
//        assert z != Double.POSITIVE_INFINITY;
//        assert z >= 0;
        if (Double.isNaN(x) || Double.isNaN(y) || Double.isNaN(z)) {
            throw new IllegalStateException("Coordinate must not isNan");
        }
        if (x < 0 || y < 0 || z < 0) {
            throw new IllegalStateException("x,y,z must greater than 0");
        }
        if (x == Double.POSITIVE_INFINITY || y == Double.POSITIVE_INFINITY || z == Double.POSITIVE_INFINITY) {
            throw new IllegalStateException("x,y,z must not Infinity");
        }
    }

    @Override
    public Object clone() {
        return this;
    }
}
