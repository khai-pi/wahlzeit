package org.wahlzeit.model;

public class Location {

    /**
     *
     */
    private Coordinate coordinate;

    /**
     *
     * @methodtype constructor
     */
    public Location(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    /**
     *
     *
     * @methodtype set
     */
    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    /**
     *
     *
     * @methodtype get
     */
    public Coordinate getCoordinate() {
        return this.coordinate;
    }

}
