package com.android.optimaldistributionrelationalsystem.sara;

import android.graphics.Bitmap;

public class productitem {
    int image;
    String name,title, quantity,id;
    Bitmap bitmap;


    public productitem(int image, String id, String name, String title, String price) {
        this.image = image;
        this.id = id;
        this.name = name;
        this.title = title;
        this.quantity = price;
    }

    public productitem( Bitmap product_image,String id, String name, String description , String quantity) {
        this.id = id;
        this.name = name;
        this.title = description;
        this.quantity=quantity;
        this.bitmap = product_image;
    }
    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public int getImage() {
        return image;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public String getQuantity() {
        return quantity;
    }
}

