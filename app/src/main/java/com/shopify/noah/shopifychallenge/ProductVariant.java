package com.shopify.noah.shopifychallenge;

import android.text.TextUtils;

import java.util.ArrayList;

/**
 * Created by cadav on 1/1/2018.
 */

public class ProductVariant {
    public String title;
    public String id;
    public String price;
    public String weight;
    public String weight_unit;
    public String inventory_quantity;
    public String requires_shipping;
    public ProductVariant(String title, String id, String price, String weight, String weight_unit, String inventory_quantity, String requires_shipping){
        this.title = title;
        this.id = id;
        this.price = price;
        this.weight = weight;
        this.weight_unit = weight_unit;
        this.inventory_quantity = inventory_quantity;
        this.requires_shipping = requires_shipping;
    }

    public String toString(){
        return "ID: " + this.id +
                "\n\tColor: " + this.title +
                "\n\tPrice: $" + this.price +
                "\n\tWeight: " + this.weight + this.weight_unit +
                "\n\tInventory: " + this.inventory_quantity +
                "\n\tShipping Available: " + (this.requires_shipping.equals("true") ? "Yes": "No") + "\n";
    }
    public String simpleString(){
        return "Color: " + this.title +
                "\nPrice: $" + this.price;
    }
}
