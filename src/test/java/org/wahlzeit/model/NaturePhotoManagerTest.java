package org.wahlzeit.model;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

public class NaturePhotoManagerTest {

    @Test
    public void testAddPhotoInCache() {
        // arrange
        NaturePhotoFactory naturePhotoFactory = NaturePhotoFactory.getInstance();
        NaturePhoto naturePhoto = naturePhotoFactory.createPhoto();
        NaturePhotoManager naturePhotoManager = NaturePhotoManager.getInstance();

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
        NaturePhotoManager naturePhotoManager = NaturePhotoManager.getInstance();

        // act
        naturePhotoManager.doAddPhoto(naturePhoto);

        // assert
        assertTrue(naturePhotoManager.doGetPhotoFromId(photoId) instanceof NaturePhoto);
        // alternative assert, just to learn another way to check instanceof
        assertThat(naturePhoto, instanceOf(NaturePhoto.class));
    }

    // TODO test in database
    // no-database-connection even that i have run database with command line and connect postgresql server to intellij
//    @Test
//    public void testIfPhotoSavedAsNaturePhotoInDatabase() {
//        // arrange
//        NaturePhotoFactory naturePhotoFactory = NaturePhotoFactory.getInstance();
//        PhotoId photoId = new PhotoId(123);
//        NaturePhoto naturePhoto = naturePhotoFactory.createPhoto(photoId);
//        NaturePhotoManager naturePhotoManager = NaturePhotoManager.instance;
//
//        // act
//        naturePhotoManager.addPhoto(naturePhoto);
//
//        // assert
//        assertTrue(NaturePhotoManager.hasPhoto(photoId));
//    }
}
