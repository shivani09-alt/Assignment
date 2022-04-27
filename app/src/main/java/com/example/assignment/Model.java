package com.example.assignment;

import android.graphics.Bitmap;

public class Model {
    String StationId,StationName;
    Bitmap Logo;

    public Model(String stationId, String stationName, Bitmap logo) {
        StationId = stationId;
        StationName = stationName;
        Logo = logo;
    }

    public String getStationId() {
        return StationId;
    }

    public String getStationName() {
        return StationName;
    }

    public Bitmap getLogo() {
        return Logo;
    }
}
