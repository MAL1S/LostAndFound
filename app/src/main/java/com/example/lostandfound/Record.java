package com.example.lostandfound;

public class Record {
    public String text;
    public Double log, lat;
    public String type;
    public String mail;

    public Record() {}

    public Record(String text, Double log, Double lat, String type,String mail) {
        this.text = text;
        this.log = log;
        this.lat = lat;
        //this.image = image;
        this.type = type;
        this.mail = mail;
    }

    public String toString() {
        return "text:" + text +
                ", log: " + log +
                ", lat: " + lat +
                ", type: " + type +
                ", mail: " + mail;
    }
}
