package org.wahlzeit.model;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;

public class CartesianCoordinateTest {

    private CartesianCoordinate cartesianCoordinate;
    private final double delta = 0.0001;

    @Before
    public void initCoordinate() {
        cartesianCoordinate = CartesianCoordinate.getCartesianCoorinate(1,2,3);
    }

    @Test
    public void testConstructor() {
        assertNotNull(cartesianCoordinate);
    }

    @Test
    public void testGet() {
        assertEquals(1, cartesianCoordinate.getX(), delta);
        assertEquals(2, cartesianCoordinate.getY(), delta);
        assertEquals(3, cartesianCoordinate.getZ(), delta);
    }

    @Test
    public void testSet() {
        cartesianCoordinate = cartesianCoordinate.setX(4);
        cartesianCoordinate = cartesianCoordinate.setY(5);
        cartesianCoordinate = cartesianCoordinate.setZ(6);
        assertEquals(4, cartesianCoordinate.getX(), delta);
        assertEquals(5, cartesianCoordinate.getY(), delta);
        assertEquals(6, cartesianCoordinate.getZ(), delta);
    }

    @Test
    public void testGetCartesianDistance() {
        double expect = Math.sqrt(14);
        double actual = cartesianCoordinate.getCartesianDistance(CartesianCoordinate.getCartesianCoorinate(0,0,0));
        assertEquals(expect,actual,delta);
    }

    @Test
    public void testIsEqualCartesian() {
        assertTrue(cartesianCoordinate.isEqual(CartesianCoordinate.getCartesianCoorinate(1,2,3)));
        assertTrue(cartesianCoordinate.equals(CartesianCoordinate.getCartesianCoorinate(1,2,3)));
        assertFalse(cartesianCoordinate.isEqual(CartesianCoordinate.getCartesianCoorinate(4,5,6)));
    }

    @Test
    public void testIsEqualSpheric() {
        cartesianCoordinate = CartesianCoordinate.getCartesianCoorinate(1,0,0);
        SphericCoordinate sphericCoordinate = SphericCoordinate.getSphericCoordinate(0,Math.PI/2,1);
        assertTrue(cartesianCoordinate.isEqual(sphericCoordinate));
        assertTrue(cartesianCoordinate.equals(sphericCoordinate));
    }

    @Test
    public void testSerialization() throws SQLException {
        // arrange
        CartesianCoordinate coordinate = CartesianCoordinate.getCartesianCoorinate(1,2,3);
        ResultSet rset = Mockito.mock(ResultSet.class);

        // act
        coordinate.writeOn(rset);

        // assert
        verify(rset, Mockito.times(1)).updateDouble("coordinate_x", coordinate.getX());
        verify(rset, Mockito.times(1)).updateDouble("coordinate_y", coordinate.getY());
        verify(rset, Mockito.times(1)).updateDouble("coordinate_z", coordinate.getZ());

    }

    @Test
    public void testThisAsCartesianCoordinate() {
        assertEquals(cartesianCoordinate, cartesianCoordinate.asCartesianCoordinate());
    }

    @Test
    public void testGetCartesianDistanceWithCartesianCoordinate() {
        double expect = Math.sqrt(14);
        double actual = cartesianCoordinate.getCartesianDistance(CartesianCoordinate.getCartesianCoorinate(0,0,0));
        assertEquals(expect,actual,delta);
    }

    @Test
    public void testGetCartesianDistanceWithSphericCoordinate() {
        cartesianCoordinate = CartesianCoordinate.getCartesianCoorinate(1,0,0);
        SphericCoordinate sphericCoordinate = SphericCoordinate.getSphericCoordinate(0,Math.PI/2,1);
        double expect = 0.0;
        double actual = cartesianCoordinate.getCartesianDistance(sphericCoordinate);
        assertEquals(expect,actual,delta);
    }

    @Test
    public void testThisAsSphericCoordinate() {
        cartesianCoordinate = CartesianCoordinate.getCartesianCoorinate(1,0,0);
        SphericCoordinate sphericCoordinate = SphericCoordinate.getSphericCoordinate(0,Math.PI/2,1);
        SphericCoordinate convertSphericCoordinate = cartesianCoordinate.asSphericCoordinate();
        assertEquals(sphericCoordinate.getPhi(), convertSphericCoordinate.getPhi(),delta);
        assertEquals(sphericCoordinate.getPhi(), convertSphericCoordinate.getPhi(),delta);
        assertEquals(sphericCoordinate.getPhi(), convertSphericCoordinate.getPhi(),delta);
    }

    @Test
    public void testGetCentralAngleWithCartesianCoordinate() {
        cartesianCoordinate = CartesianCoordinate.getCartesianCoorinate(1,0,0);
        CartesianCoordinate coordinate = CartesianCoordinate.getCartesianCoorinate(1,0,0);
        double expect = 0;
        double actual = cartesianCoordinate.getCentralAngle(coordinate);
        assertEquals(expect, actual, delta);
    }

    @Test
    public void testGetCentralAngleWithSphericCoordinate() {
        cartesianCoordinate = CartesianCoordinate.getCartesianCoorinate(1,0,0);
        SphericCoordinate coordinate = SphericCoordinate.getSphericCoordinate(0, Math.PI/2, 1);
        double expect = 0;
        double actual = cartesianCoordinate.getCentralAngle(coordinate);
        assertEquals(expect, actual, delta);
    }

    @Test(expected = IllegalStateException.class)
    public void testAssertionClassStructure() {
        CartesianCoordinate coordinate = CartesianCoordinate.getCartesianCoorinate(-1,-1,-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDistanceWithNullObject() {
        cartesianCoordinate.getCartesianDistance(null);
    }
}
