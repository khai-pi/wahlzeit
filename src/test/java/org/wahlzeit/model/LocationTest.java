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

    @Test
    public void testSerialization() throws SQLException {
        // arrange
        Coordinate coordinate = Mockito.mock(Coordinate.class);
        ResultSet rset = Mockito.mock(ResultSet.class);
        Location location = new Location(coordinate);

        // act
        location.writeOn(rset);

        // assert
        verify(coordinate, Mockito.times(1)).writeOn(rset);
    }
}
