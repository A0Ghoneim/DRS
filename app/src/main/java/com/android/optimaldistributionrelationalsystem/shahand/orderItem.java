package com.android.optimaldistributionrelationalsystem.shahand;

import android.graphics.Bitmap;

public class orderItem {
    Bitmap bitmap;
    int orderImage;
    String name;
    int number;
    String product_id;

    public orderItem(int orderImage, String name, int number,String product_id) {
        this.orderImage = orderImage;
        this.name = name;
        this.number = number;
        this.product_id=product_id;
    }


    public orderItem(Bitmap bitmap, String name, int number,String product_id) {
        this.bitmap = bitmap;
        this.name = name;
        this.number = number;
        this.product_id=product_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }


    public void setNumber(int number) {
        this.number = number;
    }

    public int getOrderImage() {
        return orderImage;
    }

    public void setOrderImage(int orderImage) {
        this.orderImage = orderImage;
    }

}