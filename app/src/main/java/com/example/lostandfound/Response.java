package com.example.lostandfound;

public class Response {
    public String text;
    public String key;

    public Response(){}

    public Response(String text, String key) {
        this.text = text;
        this.key = key;
    }

    public String toString() {
        return text + " " + key;
    }
}
