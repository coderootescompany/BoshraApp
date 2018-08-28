package com.example.hossam.boshraapp.Modules;

import android.net.Uri;

public class ImagesModel {
    private Uri uri;
    private String path;
    private int id;

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
