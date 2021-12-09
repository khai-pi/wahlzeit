package org.wahlzeit.model;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

public class SphericCoordinateTest {

    private SphericCoordinate sphericCoordinate;
    private final double delta = 0.0001;

    @Before
    public void initCoordinate() {
        sphericCoordinate = new SphericCoordinate(0, Math.PI/2,1);
    }

    @Test
    public void testConstructor() {
        assertNotNull(sphericCoordinate);
    }

    @Test
    public void testGet() {
        assertEquals(0, sphericCoordinate.getPhi(), delta);
        assertEquals(Math.PI/2, sphericCoordinate.getTheta(), delta);
        assertEquals(1, sphericCoordinate.getRadius(), delta);
    }

    @Test
    public void testSet() {
        sphericCoordinate.setPhi(Math.PI);
        sphericCoordinate.setTheta(0);
        sphericCoordinate.setRadius(2);
        assertEquals(Math.PI, sphericCoordinate.getPhi(),delta);
        assertEquals(0, sphericCoordinate.getTheta(),delta);
        assertEquals(2, sphericCoordinate.getRadius(),delta);
    }

    @Test
    public void testGetCartesianDistance() {
        double expect = 1;
        double actual = sphericCoordinate.getCartesianDistance(new CartesianCoordinate(0,0,0));
        assertEquals(expect,actual,delta);
    }

    @Test
    public void testIsEqualCartesian() {
        CartesianCoordinate cartesianCoordinate = new CartesianCoordinate(1,0,0);
        assertTrue(sphericCoordinate.isEqual(cartesianCoordinate));
        assertTrue(sphericCoordinate.equals(cartesianCoordinate));
    }

    @Test
    public void testIsEqualSpheric() {
        assertTrue(sphericCoordinate.isEqual(new SphericCoordinate(0, Math.PI/2,1)));
        assertTrue(sphericCoordinate.equals(new SphericCoordinate(0, Math.PI/2,1)));
    }

    @Test
    public void testSerialization() throws SQLException {
        ResultSet rset = Mockito.mock(ResultSet.class);

        sphericCoordinate.writeOn(rset);

        verify(rset, Mockito.times(1)).updateDouble("coordinate_phi", sphericCoordinate.getPhi());
        verify(rset, Mockito.times(1)).updateDouble("coordinate_theta", sphericCoordinate.getTheta());
        verify(rset, Mockito.times(1)).updateDouble("coordinate_radius", sphericCoordinate.getRadius());
    }

    @Test
    public void testThisAsCartesianCoordinate() {
        CartesianCoordinate cartesianCoordinate = new CartesianCoordinate(1,0,0);
        CartesianCoordinate convertCartesianCoordinate = this.sphericCoordinate.asCartesianCoordinate();
        assertEquals(cartesianCoordinate.getX(), convertCartesianCoordinate.getX(),delta);
        assertEquals(cartesianCoordinate.getY(), convertCartesianCoordinate.getY(),delta);
        assertEquals(cartesianCoordinate.getZ(), convertCartesianCoordinate.getZ(),delta);
    }

    @Test
    public void testGetCartesianDistanceWithCartesianCoordinate() {
        double expect = 1;
        double actual = sphericCoordinate.getCartesianDistance(new CartesianCoordinate(0,0,0));
        assertEquals(expect,actual,delta);
    }

    @Test
    public void testGetCartesianDistanceWithSphericCoordinate() {
        SphericCoordinate createSphericCoordinate = new SphericCoordinate(0,Math.PI/2,1);
        double expect = 0.0;
        double actual = sphericCoordinate.getCartesianDistance(sphericCoordinate);
        assertEquals(expect,actual,delta);
    }

    @Test
    public void testThisAsSphericCoordinate() {
        assertEquals(sphericCoordinate, sphericCoordinate.asSphericCoordinate());
    }

    @Test
    public void testGetCentralAngleWithCartesianCoordinate() {
        CartesianCoordinate coordinate = new CartesianCoordinate(1,0,0);
        double expect = 0;
        double actual = sphericCoordinate.getCentralAngle(coordinate);
        assertEquals(expect, actual, delta);
    }

    @Test
    public void testGetCentralAngleWithSphericCoordinate() {
        SphericCoordinate coordinate = new SphericCoordinate(0, Math.PI/2, 1);
        double expect = 0;
        double actual = sphericCoordinate.getCentralAngle(coordinate);
        assertEquals(expect, actual, delta);
    }

    @Test(expected = IllegalStateException.class)
    public void testAssertionClassStructure() {
        SphericCoordinate coordinate = new SphericCoordinate(0,0,-1);
    }

    @Test(expected =NullPointerException.class)
    public void testDistanceWithNullObject() {
        sphericCoordinate.getCentralAngle(null);
    }
}
