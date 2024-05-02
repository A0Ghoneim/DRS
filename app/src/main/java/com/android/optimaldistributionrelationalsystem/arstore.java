package com.android.optimaldistributionrelationalsystem;

import com.android.optimaldistributionrelationalsystem.data.Store;

import java.io.Serializable;
import java.util.ArrayList;

public class arstore implements Serializable {
    ArrayList<Store> astore;

    public arstore() {
    }

    public arstore(ArrayList<Store> astore) {
        this.astore = astore;
    }


    public ArrayList<Store> getAstore() {
        return astore;
    }

    public void setAstore(ArrayList<Store> astore) {
        this.astore = astore;
    }
}

