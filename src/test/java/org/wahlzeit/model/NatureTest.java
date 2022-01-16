package org.wahlzeit.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NatureTest {

    @Test
    public void testIdIncrement() {
        Nature nature1 = new Nature(new NatureType("Plants"));
        Nature nature2 = new Nature(new NatureType("Animals"));
        assertEquals(nature1.getId()+1, nature2.getId());
    }

    @Test
    public void testSetNatureType() {
        Nature nature = new Nature(new NatureType("Plants"));
        nature.setNatureType(new NatureType("Animals"));
        assertEquals("Animals", nature.getNatureType().name);
    }
}
