package org.wahlzeit.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NaturePhotoFactoryTest {

    @Test
    public void testGetInstance() {
        NaturePhotoFactory naturePhotoFactory = NaturePhotoFactory.getInstance();
        assertNotNull(naturePhotoFactory);
    }

    @Test
    public void testCreateNaturePhoto() {
        NaturePhotoFactory naturePhotoFactory = NaturePhotoFactory.getInstance();
        NaturePhoto np = naturePhotoFactory.createPhoto();
        assertNotNull(np);
    }

    @Test
    public void testCreateNaturePhotoWithId() {
        NaturePhotoFactory naturePhotoFactory = NaturePhotoFactory.getInstance();
        NaturePhoto np = naturePhotoFactory.createPhoto(new PhotoId(123));
        assertNotNull(np);
        assertEquals(123,np.id.value);
    }

    // TODO testCreateNaturePhotoWithResultSet()


}
