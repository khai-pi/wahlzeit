package org.wahlzeit.model;

import org.junit.Ignore;
import org.junit.Test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import static org.junit.Assert.*;

public class NatureTypeTest {

//    NatureType plants = new NatureType("Plants");
//    NatureType trees = new NatureType("Trees");
//    NatureType flowers = new NatureType("Flowers");

    @Test
    public void testGetSuperType() {
        NatureType plants = new NatureType("Plants");
        NatureType trees = new NatureType("Trees");
        trees.setSuperType(plants);
        assertEquals(plants.name, trees.getSuperType().name);
    }

    @Ignore
    @Test
    public void testGetSubTypesIterator() {
        NatureType plants = new NatureType("Plants");
        NatureType trees = new NatureType("Trees");
        NatureType flowers = new NatureType("Flowers");
        plants.addSubType(trees);
        plants.addSubType(flowers);
        Set<NatureType> expectSet = new HashSet<NatureType>();
        expectSet.add(trees);
        expectSet.add(flowers);
        while (expectSet.iterator().hasNext()) {
            assertEquals(expectSet.iterator().next()
                    , plants.getSubTypesIterator().next());
        }
        //assertEquals(expectSet.iterator(), plants.getSubTypesIterator());
    }

    @Test
    public void testHasInstanceWithOutInstance() {
        NatureType plants = new NatureType("Plants");
        NatureType trees = new NatureType("Trees");
        assertFalse(plants.hasInstance(new Nature(trees)));
    }

    @Test
    public void testHasInstanceWithInstance() {
        NatureType plants = new NatureType("Plants");
        NatureType trees = new NatureType("Trees");
        plants.addSubType(trees);
        assertTrue(plants.hasInstance(new Nature(trees)));
    }

    @Test
    public void testIsSubType() {
        NatureType plants = new NatureType("Plants");
        NatureType trees = new NatureType("Trees");
        trees.setSuperType(plants);
        assertTrue(trees.isSubType(plants));
    }
}
