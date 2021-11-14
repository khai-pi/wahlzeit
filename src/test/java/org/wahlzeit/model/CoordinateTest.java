package org.wahlzeit.model;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;

public class CoordinateTest {

    private Coordinate coordinate;
    private final double delta = 0.0001;

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
        assertEquals(1, coordinate.getX(), delta);
        assertEquals(2, coordinate.getY(), delta);
        assertEquals(3, coordinate.getZ(), delta);
    }

    @Test
    public void testSet() {
        coordinate.setX(4);
        coordinate.setY(5);
        coordinate.setZ(6);
        assertEquals(4, coordinate.getX(), delta);
        assertEquals(5, coordinate.getY(), delta);
        assertEquals(6, coordinate.getZ(), delta);
    }

    @Test
    public void testGetDistance() {
        double expect = Math.sqrt(14);
        double actual = coordinate.getDistance(new Coordinate(0,0,0));
        assertEquals(expect,actual,delta);
    }

    @Test
    public void testIsEqual() {
        assertTrue(coordinate.isEqual(new Coordinate(1,2,3)));
        assertFalse(coordinate.isEqual(new Coordinate(4,5,6)));
    }

    @Test
    public void testSerialization() throws SQLException {
        // arrange
        Coordinate coordinate = new Coordinate(1,2,3);
        ResultSet rset = Mockito.mock(ResultSet.class);

        // act
        coordinate.writeOn(rset);

        // assert
        verify(rset, Mockito.times(1)).updateDouble("coordinate_x", coordinate.getX());
        verify(rset, Mockito.times(1)).updateDouble("coordinate_y", coordinate.getY());
        verify(rset, Mockito.times(1)).updateDouble("coordinate_z", coordinate.getZ());

    }

}
