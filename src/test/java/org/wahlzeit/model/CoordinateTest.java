package org.wahlzeit.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;

public class CoordinateTest {

    private Coordinate coordinate;

    @Before
    public void initCoordinate() {
        coordinate = new Coordinate(1,2,3);
    }

    @Test
    public void testConstructor() {
        assertNotNull(coordinate);
    }

    @Test
    public void testGet() {
        assertEquals(1, coordinate.getX(), 0);
        assertEquals(2, coordinate.getY(), 0);
        assertEquals(3, coordinate.getZ(), 0);
    }

    @Test
    public void testSet() {
        coordinate.setX(4);
        coordinate.setY(5);
        coordinate.setZ(6);
        assertEquals(4, coordinate.getX(), 0);
        assertEquals(5, coordinate.getY(), 0);
        assertEquals(6, coordinate.getZ(), 0);
    }

    @Test
    public void testGetDistance() {
        double expect = Math.sqrt(14);
        double actual = coordinate.getDistance(new Coordinate(0,0,0));
        assertEquals(expect,actual,0);
    }

    @Test
    public void testIsEqual() {
        assertTrue(coordinate.isEqual(new Coordinate(1,2,3)));
        assertFalse(coordinate.isEqual(new Coordinate(4,5,6)));
    }

}
