package com.shopify.noah.shopifychallenge;

import android.graphics.Bitmap;

/**
 * Created by cadav on 1/1/2018.
 */

public class Image {
    public String src;
    private Bitmap bmp;
    public Image(String src){
        this.src = src;
        this.bmp = null;
    }
    public void setBmp(Bitmap bmp){
        this.bmp = bmp;
    }

    public Bitmap getBmp() {
        return bmp;
    }
}
