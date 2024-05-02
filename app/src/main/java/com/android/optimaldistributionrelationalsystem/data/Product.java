package com.android.optimaldistributionrelationalsystem.data;

import android.graphics.Bitmap;

public class Product {
    public String id;
    public String name;
    public String description;
    public String quantity;
    public int image_number;
    public Bitmap product_image;







    public Product(String id, String name, String description , String quantity, Bitmap product_image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.quantity=quantity;
        this.product_image = product_image;
    }
    public Product( int image_number,String id, String name, String description , String quantity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.quantity=quantity;
        this.image_number = image_number;
    }

    public Product(String id, String name, String description,String quantity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
    }

    public Product() {
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public int getImage_number() {
        return image_number;
    }

    public void setImage_number(int image_number) {
        this.image_number = image_number;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Bitmap getProduct_image() {
        return product_image;
    }

    public void setProduct_image(Bitmap product_image) {
        this.product_image = product_image;
    }
}
