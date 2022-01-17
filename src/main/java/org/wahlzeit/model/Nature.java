package org.wahlzeit.model;

import java.util.HashMap;

public class Nature {

    // Used as key to HashMap in NatureManager to manage Nature
    private static int idCounter = 0;

    protected NatureType natureType;
    protected HashMap<PhotoId, NaturePhoto> naturePhotos = new HashMap<PhotoId, NaturePhoto>();
    int id;

    public Nature(NatureType natureType) {
        this.natureType = natureType;
        this.id = idCounter++; // increase each time object created
    }

    public void setNatureType(NatureType natureType) {
        this.natureType = natureType;
    }

    public NatureType getNatureType() {
        return this.natureType;
    }

    public int getId() {
        return id;
    }

    public void addNaturePhoto(NaturePhoto naturePhoto) {
        naturePhotos.put(naturePhoto.getId(), naturePhoto);
    }

    public NaturePhoto getNaturePhoto(PhotoId photoId) {
        return naturePhotos.get(photoId);
    }
}
