package com.android.optimaldistributionrelationalsystem.data;

public class Driver_track {
    String name;
    boolean isstore;
    int rank;


    public Driver_track() {
    }

    public Driver_track(String name, boolean isstore,int rank) {
        this.name = name;
        this.isstore = isstore;
        this.rank=rank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isIsstore() {
        return isstore;
    }

    public void setIsstore(boolean isstore) {
        this.isstore = isstore;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}

