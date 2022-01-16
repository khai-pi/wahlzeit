package org.wahlzeit.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class NatureManagerTest {

    NatureManager instance;

    @Before
    public void initInstance() {
        instance = NatureManager.getInstance();
    }

    @Test
    public void testGetInstance() {
        assertNotNull(instance);
    }

    @Test
    public void testCreateNature() {
        Nature actualNatureType = instance.createNature("Ocean");
        assertEquals("Ocean", actualNatureType.natureType.getName());
    }

    @Test
    public void testGetNatureType() {
        NatureType expect = new NatureType("Mountain");
        NatureType actual = instance.getNatureType("Mountain");
        assertEquals(expect.name, actual.name);
    }

    @Test(expected = IllegalStateException.class)
    public void testAssertTypeNameNotNull() {
        instance.assertIsValidNatureTypeName(null);
    }
}
