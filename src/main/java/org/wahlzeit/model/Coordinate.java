package org.wahlzeit.model;

import java.lang.Math;

public class Coordinate {

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

    public boolean isEqual(Coordinate coordinate) {
        return (this.getX() == coordinate.getX())
                && (this.getY() == coordinate.getY())
                && (this.getZ() == coordinate.getZ());
    }
}
