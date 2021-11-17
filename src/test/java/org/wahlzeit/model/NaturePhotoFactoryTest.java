package org.wahlzeit.model;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

public class NaturePhotoFactoryTest {

//    @Test
//    public void testSetInstance() {
//        NaturePhotoFactory naturePhotoFactory = new NaturePhotoFactory();
//        NaturePhotoFactory.setInstance(naturePhotoFactory);
//        assertEquals(naturePhotoFactory, NaturePhotoFactory.getInstance());
//    }
    NaturePhotoFactory naturePhotoFactory = NaturePhotoFactory.getInstance();
    @Test
    public void testGetInstance() {
        //PhotoFactory naturePhotoFactory = NaturePhotoFactory.getInstance();
        assertNotNull(naturePhotoFactory);
    }

    @Test
    public void testCreateNaturePhoto() {
        //NaturePhotoFactory naturePhotoFactory = NaturePhotoFactory.getInstance();
        NaturePhoto np = naturePhotoFactory.createPhoto();
        assertNotNull(np);
    }

    @Test
    public void testCreateNaturePhotoWithId() {
        //NaturePhotoFactory naturePhotoFactory = NaturePhotoFactory.getInstance();
        NaturePhoto np = naturePhotoFactory.createPhoto(new PhotoId(123));
        assertNotNull(np);
        assertEquals(123,np.id.value);
    }

    // TODO testCreateNaturePhotoWithResultSet()
    // I need some help here i could not find a way to make this test,
    // can you please make an example of it
//    @Test
//    public void testCreateNaturePhotoWithResultSet() throws SQLException {
//        // arrange
//        NaturePhotoFactory naturePhotoFactory = NaturePhotoFactory.getInstance();
//        ResultSet rset = Mockito.mock(ResultSet.class);
//
//        // act
//        NaturePhoto np = naturePhotoFactory.createPhoto(rset);
//
//        // assert
//
//    }

}
