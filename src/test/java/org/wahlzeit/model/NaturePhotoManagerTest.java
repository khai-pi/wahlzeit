package org.wahlzeit.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class NaturePhotoManagerTest {

    @Test
    public void testAddPhotoInCache() {
        // arrange
        NaturePhotoFactory naturePhotoFactory = NaturePhotoFactory.getInstance();
        NaturePhoto naturePhoto = naturePhotoFactory.createPhoto();
        NaturePhotoManager naturePhotoManager = NaturePhotoManager.instance;

        // act
        naturePhotoManager.doAddPhoto(naturePhoto);

        // assert
        assertTrue(naturePhotoManager.doHasPhoto(naturePhoto.getId()));
    }

    @Test
    public void testIfPhotoSavedAsNaturePhotoInCache() {
        // arrange
        NaturePhotoFactory naturePhotoFactory = NaturePhotoFactory.getInstance();
        PhotoId photoId = new PhotoId(123);
        NaturePhoto naturePhoto = naturePhotoFactory.createPhoto(photoId);
        NaturePhotoManager naturePhotoManager = NaturePhotoManager.instance;

        // act
        naturePhotoManager.doAddPhoto(naturePhoto);

        // assert
        assertTrue(naturePhotoManager.doGetPhotoFromId(photoId) instanceof NaturePhoto);
    }

    // TODO test in database
}
