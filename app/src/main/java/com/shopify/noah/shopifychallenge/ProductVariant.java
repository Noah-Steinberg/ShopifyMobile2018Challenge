package com.shopify.noah.shopifychallenge;

import android.text.TextUtils;

import java.util.ArrayList;

/**
 * Created by cadav on 1/1/2018.
 */

public class ProductVariant {
    public String title;
    public String price;
    public String weight;
    public String weight_unit;
    public String inventory_quantity;
    public String requires_shipping;
    public ProductVariant(String title, String price, String weight, String weight_unit, String inventory_quantity, String requires_shipping){
        this.title = title;
        this.price = price;
        this.weight = weight;
        this.weight_unit = weight_unit;
        this.inventory_quantity = inventory_quantity;
        this.requires_shipping = requires_shipping;
    }

    public String toString(){
        return "Color: " + this.title +
                "\n\tPrice: $" + this.price +
                "\n\tWeight: " + this.weight + this.weight_unit +
                "\n\tQuantity: " + this.inventory_quantity +
                "\n\tEligible for Shipping: " + this.requires_shipping + "\n";
    }
    public String simpleString(){
        return "Color: " + this.title +
                "\nPrice: $" + this.price;
    }
}
