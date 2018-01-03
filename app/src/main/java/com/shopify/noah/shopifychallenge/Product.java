package com.shopify.noah.shopifychallenge;

import android.text.TextUtils;

import java.util.ArrayList;

/**
 * Created by cadav on 1/1/2018.
 */

public class Product {
    public String id;
    public String title;
    public String vendor;
    public String product_type;
    public String tags;
    public String body_html;
    public Image image;
    public ArrayList<ProductVariant> variants = new ArrayList<>();
    public Product(String id, String title, String vendor, String product_type, String tags, String body_html, Image image, ArrayList<ProductVariant> variants){
        this.id = id;
        this.title = title;
        this.vendor = vendor;
        this.product_type = product_type;
        this.tags = tags;
        this.body_html = body_html;
        this.image = image;
        this.variants = variants;
    }

    public String toString() {
        return "Item: " + this.title +
                "\nDescription: " + this.body_html;
    }

    public String detailsToString(){
        return "Item: " + this.title +
                "\nDescription: " + this.body_html +
                "\nVendor: " + this.vendor +
                "\nProduct Type: " + this.product_type +
                "\nTags: " + this.tags +
                "\nOptions: \n\t" + TextUtils.join("\n\t", this.variants);
    }
}
