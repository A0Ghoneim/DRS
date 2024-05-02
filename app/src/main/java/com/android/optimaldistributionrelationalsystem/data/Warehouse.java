package com.android.optimaldistributionrelationalsystem.data;

import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.HashMap;

public class Warehouse implements Serializable, Parcelable {
    HashMap<String,Integer> products = new HashMap<String, Integer>();
    public String name;
    public Location whlocation;
    private double lat;
    private double longt;
    private String email;
    private String password;
    private String address;
    private String phone_number;

    public Warehouse() {
    }

    public Warehouse(String n, double a, double o, HashMap<String,Integer> h) {
        name=n;
        whlocation=new Location(n);
        lat=a;
        longt=o;
        whlocation.setLatitude(a);
        whlocation.setLongitude(o);
        products=h;
    }

    public Warehouse(String n,double a, double o) {
        name=n;
        whlocation=new Location(n);
        lat=a;
        longt=o;
        whlocation.setLatitude(a);
        whlocation.setLongitude(o);
    }

    public Warehouse(String name, double lat, double longt, String email, String password, String address, String phone_number) {
        this.name = name;
        this.lat = lat;
        this.longt = longt;
        this.email = email;
        this.password = password;
        this.address = address;
        this.phone_number = phone_number;
    }

    protected Warehouse(Parcel in) {
        name = in.readString();
        whlocation = in.readParcelable(Location.class.getClassLoader());
        lat = in.readDouble();
        longt = in.readDouble();
        email = in.readString();
        password = in.readString();
        address = in.readString();
        phone_number = in.readString();
    }

    public static final Creator<Warehouse> CREATOR = new Creator<Warehouse>() {
        @Override
        public Warehouse createFromParcel(Parcel in) {
            return new Warehouse(in);
        }

        @Override
        public Warehouse[] newArray(int size) {
            return new Warehouse[size];
        }
    };

    public HashMap<String, Integer> getProducts() {
        return products;
    }

    public void setProducts(HashMap<String, Integer> products) {
        this.products = products;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getWhlocation() {
        return whlocation;
    }

    public void setWhlocation(Location whlocation) {
        this.whlocation = whlocation;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLongt() {
        return longt;
    }

    public void setLongt(double longt) {
        this.longt = longt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(name);
   //     parcel.writeParcelable(whlocation, i);
        parcel.writeDouble(lat);
        parcel.writeDouble(longt);
        parcel.writeString(email);
        parcel.writeString(password);
        parcel.writeString(address);
        parcel.writeString(phone_number);
    }
}
