package org.wahlzeit.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class NaturePhotoTest {

    @Test
    public void testGetCountry() {
        NaturePhoto np = new NaturePhoto();
        String country = np.getCountry();
        assertNull(country);
    }

    @Test
    public void testSetCountry() {
        NaturePhoto np = new NaturePhoto();
        np.setCountry("Germany");
        assertEquals("Germany", np.getCountry());
    }

    @Test
    public void testGetDaytime() {
        NaturePhoto np = new NaturePhoto();
        int daytime = np.getDaytime();
        assertEquals(-1, daytime);
    }

    @Test
    public void testSetDaytime() {
        NaturePhoto np = new NaturePhoto();
        np.setDaytime(12);
        assertEquals(12, np.getDaytime());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetCountryNull() {
        NaturePhoto np = new NaturePhoto();
        np.setCountry(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetCountryNameWithNumber() {
        NaturePhoto np = new NaturePhoto();
        np.setCountry("abd123");
    }
}
