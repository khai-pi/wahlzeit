package org.wahlzeit.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class LocationTest {

    private Location location;
    private Coordinate coordinate;

    @Before
    public void initLocation() {
        coordinate = new Coordinate(1,2,3);
        location = new Location(coordinate);
    }

    @Test
    public void testConstructor() {
        assertNotNull(location);
    }

    @Test
    public void testGet() {
        assertEquals(coordinate, location.getCoordinate());
    }

    @Test
    public void testSet() {
        Coordinate newCoordinate = new Coordinate(4,5,6);
        location.setCoordinate(newCoordinate);
        assertEquals(newCoordinate, location.getCoordinate());
    }
}
