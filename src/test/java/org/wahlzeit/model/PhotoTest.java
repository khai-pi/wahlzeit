package org.wahlzeit.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PhotoTest {

    private Photo photo;

    @Before
    public void initPhoto() {
        photo = new Photo();
    }

    @Test
    public void testConstructor() {
        assertNotNull(photo);
    }

    @Test
    public void testGetLocation() {
        assertNull(photo.getLocation());
    }

    @Test
    public void testSetLocation() {
        CartesianCoordinate coordinate = new CartesianCoordinate(1,2,3);
        Location location = new Location(coordinate);
        photo.setLocation(location);
        assertEquals(location, photo.getLocation());
    }
}
