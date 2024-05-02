package com.android.optimaldistributionrelationalsystem.data;

import android.location.Location;

public class Order {
    Location semi_location;
    String order_id;
    Store sname;
    Warehouse whname;
    public String product;
    int quantity;

    public Order(String id,Store n,String p,int q){
        order_id=id;
        sname=n;
        product=p;
        quantity=q;
    }

    public Order(Store n,String p,int q){
        sname=n;
        product=p;
        quantity=q;
    }

    public Order() {

    }

    public Location getSemi_location() {
        return semi_location;
    }

    public void setSemi_location(Location semi_location) {
        this.semi_location = semi_location;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public Store getSname() {
        return sname;
    }

    public void setSname(Store sname) {
        this.sname = sname;
    }

    public Warehouse getWhname() {
        return whname;
    }

    public void setWhname(Warehouse whname) {
        this.whname = whname;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
