package com.android.optimaldistributionrelationalsystem;

import com.android.optimaldistributionrelationalsystem.data.Warehouse;

import java.io.Serializable;
import java.util.ArrayList;

public class arware implements Serializable {
    ArrayList<Warehouse> aware;

    public arware() {
    }

    public arware(ArrayList<Warehouse> aware) {
        this.aware = aware;
    }

    public ArrayList<Warehouse> getAware() {
        return aware;
    }

    public void setAware(ArrayList<Warehouse> aware) {
        this.aware = aware;
    }
}

