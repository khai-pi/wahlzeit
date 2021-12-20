package org.wahlzeit.model;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;

public class LocationTest {

    private Location location;
    CartesianCoordinate cartesianCoordinate;
    SphericCoordinate sphericCoordinate;
    double delta = 0.0001;

    @Before
    public void initLocation() {
        cartesianCoordinate = CartesianCoordinate.getCartesianCoorinate(1,2,3);
        sphericCoordinate = cartesianCoordinate.asSphericCoordinate();
        location = new Location(cartesianCoordinate);
    }

    @Test
    public void testConstructor() {
        assertNotNull(location);
    }

    @Test
    public void testGet() {
        CartesianCoordinate cartesianCoordinate = CartesianCoordinate.getCartesianCoorinate(1,2,3);
        assertEquals(cartesianCoordinate, location.getCartesianCoordinate());
    }

    @Test
    public void testSet() {
        CartesianCoordinate newCoordinate = CartesianCoordinate.getCartesianCoorinate(4,5,6);
        location.setCoordinate(newCoordinate);
        assertEquals(newCoordinate, location.getCartesianCoordinate());
    }

    @Test(expected = NullPointerException.class)
    public void testNullCoordinate() {
        Location location = new Location(null);
    }

//    @Test
//    public void testCoordinateAreConvertEqualy() {
//        CartesianCoordinate cartesianCoordinate = new CartesianCoordinate(1,0,0);
//        SphericCoordinate sphericCoordinateExpect = new SphericCoordinate(0,Math.PI/2,1);
//        location.setCoordinate(cartesianCoordinate);
//        SphericCoordinate sphericCoordinateActual = location.getSphericCoordinate();
//        assertEquals(sphericCoordinateExpect.getPhi(), sphericCoordinateActual.getPhi(),delta);
//        assertEquals(sphericCoordinateExpect.getTheta(), sphericCoordinateActual.getTheta(),delta);
//        assertEquals(sphericCoordinateExpect.getRadius(), sphericCoordinateActual.getRadius(),delta);
//    }

//    @Test
//    public void testSerialization() throws SQLException {
//        // arrange
//        CartesianCoordinate cartesianCoordinate = Mockito.mock(CartesianCoordinate.class);
//        ResultSet rset = Mockito.mock(ResultSet.class);
//        Location location = new Location(cartesianCoordinate);
//
//        // act
//        location.writeOn(rset);
//
//        // assert
//        verify(cartesianCoordinate, Mockito.times(1)).writeOn(rset);
//    }
}
