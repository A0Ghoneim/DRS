package com.android.optimaldistributionrelationalsystem.sara;

import android.graphics.Bitmap;
import android.widget.Button;

public class orderitem {
    Bitmap bitmap;
    int ordimg;
    String name,quantity,price,store,add;
    Button b;




    public orderitem(int ordimg, String name, String quantity, String price, String store, String add) {
        this.ordimg = ordimg;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.store = store;
        this.add = add;
    }

    public orderitem(Bitmap bitmap, String name, String quantity) {
        this.bitmap = bitmap;
        this.name = name;
        this.quantity = quantity;
    }

    public orderitem(String name, String quantity, String store) {
        this.name = name;
        this.quantity = quantity;
        this.store = store;
    }

    public void setOrdimg(int ordimg) {
        this.ordimg = ordimg;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public void setAdd(String add) {
        this.add = add;
    }


    public int getOrdimg() {
        return ordimg;
    }

    public String getName() {
        return name;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getPrice() {
        return price;
    }

    public String getStore() {
        return store;
    }

    public String getAdd() {
        return add;
    }





}


