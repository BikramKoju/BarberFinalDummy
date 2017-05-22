package com.example.bikramkoju.barberfinalapp;

import android.graphics.Bitmap;

/**
 * Created by Bikramkoju on 5/22/2017.
 */

public class ImageItem {
    private Bitmap image;
    private String utitle;
    private String title;


    public ImageItem(Bitmap image, String utitle, String title) {
        super();
        this.image = image;
        this.utitle=utitle;
        this.title = title;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getUtitle() {
        return utitle;
    }

    public void setUtitle(String utitle) {
        this.utitle = utitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
