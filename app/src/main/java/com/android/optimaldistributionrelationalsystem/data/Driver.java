package com.android.optimaldistributionrelationalsystem.data;

import android.location.Location;

import java.util.ArrayList;

public class Driver {
    String name;
    private String email;
    private String password;
    private String address;
    private String phone_number;
    int driver_image;
    private Driver_track dr;
    private ArrayList<Driver_track> track_array;

    public Driver(String name, String email, String password, String address, String phone_number, int driver_image) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.phone_number = phone_number;
        this.driver_image = driver_image;
    }

    public Driver(String name, String email, String password, String address, String phone_number) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.phone_number = phone_number;
    }

    public Driver() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getDriver_image() {
        return driver_image;
    }

    public void setDriver_image(int driver_image) {
        this.driver_image = driver_image;
    }

    public Driver_track getDr() {
        return dr;
    }

    public void setDr(Driver_track dr) {
        this.dr = dr;
    }

    public ArrayList<Driver_track> getTrack_array() {
        return track_array;
    }

    public void setTrack_array(ArrayList<Driver_track> track_array) {
        this.track_array = track_array;
    }
}
