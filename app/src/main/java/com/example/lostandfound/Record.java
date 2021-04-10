package com.example.lostandfound;

import android.graphics.Bitmap;

public class Record {
    public String text;
    public Double log, lat;
    public String type;

    public Record() {}

    public Record(String text, Double log, Double lat, String type) {
        this.text = text;
        this.log = log;
        this.lat = lat;
        //this.image = image;
        this.type = type;
    }

    public String toString() {
        return "text:" + text +
                ", log: " + log +
                ", lat: " + lat +
                ", type: " + type;
    }
}
