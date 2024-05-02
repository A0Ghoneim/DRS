package com.android.optimaldistributionrelationalsystem.sara;

public class driveritem {
    int driverimage;
    String drivername;

    public driveritem(int driverimage, String drivername) {
        this.driverimage = driverimage;
        this.drivername = drivername;
    }

    public int getDriverimage() {
        return driverimage;
    }

    public String getDrivername() {
        return drivername;
    }

    public void setDriverimage(int driverimage) {
        this.driverimage = driverimage;
    }

    public void setDrivername(String drivername) {
        this.drivername = drivername;
    }
}
