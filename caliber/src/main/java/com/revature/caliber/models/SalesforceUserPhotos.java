package com.revature.caliber.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Martino on 1/22/2017.
 */
public class SalesforceUserPhotos {
    @JsonProperty
    private String picture;
    @JsonProperty
    private String thumbnail;

    public SalesforceUserPhotos() {
    }

    public SalesforceUserPhotos(String picture, String thumbnail) {
        this.picture = picture;
        this.thumbnail = thumbnail;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    @Override
    public String toString() {
        return "SalesforceUserPhotos{" +
                "picture='" + picture + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                '}';
    }
}
