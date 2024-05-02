package com.android.optimaldistributionrelationalsystem.data;

import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Store implements Serializable {

    public String name;
    public Location slocation;
    private double lat;
    private double longt;
    private String email;
    private String password;
    private String address;
    private String phone_number;

    public Store() {}

    public Store(String n,double a, double o) {
        name=n;
        slocation=new Location(n);
        lat=a;
        longt=o;
        slocation.setLatitude(a);
        slocation.setLongitude(o);
    }

    public Store(String name, double lat, double longt, String email, String password, String address, String phone_number) {
        this.name = name;
        this.lat = lat;
        this.longt = longt;
        this.email = email;
        this.password = password;
        this.address = address;
        this.phone_number = phone_number;
    }

    protected Store(Parcel in) {
        name = in.readString();
        slocation = in.readParcelable(Location.class.getClassLoader());
        lat = in.readDouble();
        longt = in.readDouble();
        email = in.readString();
        password = in.readString();
        address = in.readString();
        phone_number = in.readString();
    }

   /* public static final Creator<Store> CREATOR = new Creator<Store>() {
        @Override
        public Store createFromParcel(Parcel in) {
            return new Store(in);
        }

        @Override
        public Store[] newArray(int size) {
            return new Store[size];
        }
    };*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getSlocation() {
        return slocation;
    }

    public void setSlocation(Location slocation) {
        this.slocation = slocation;
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

 /*   @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeDouble(lat);
        parcel.writeDouble(longt);
        parcel.writeString(name);
        parcel.writeString(email);
        parcel.writeString(password);
        parcel.writeString(address);
        parcel.writeString(phone_number);
    }

  */
}
