package org.wahlzeit.model;

public class Nature {

    private static int idCounter = 0;

    protected NatureType natureType;
    int id;

    public Nature(NatureType natureType) {
        this.natureType = natureType;
        this.id = idCounter++;
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
}
