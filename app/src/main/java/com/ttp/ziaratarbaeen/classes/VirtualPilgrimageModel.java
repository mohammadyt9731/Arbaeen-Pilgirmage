package com.ttp.ziaratarbaeen.classes;

public class VirtualPilgrimageModel {

    String name;
    int photoId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    public VirtualPilgrimageModel(String name, int photoId) {
        this.name = name;
        this.photoId = photoId;
    }
}
