package com.example.galaxyreader.parser;

public class Line {
    public String text;
    public float fontSize;
    public float x;
    public float y;

    public Line(String text, float fontSize, float x, float y) {
        this.text = text;
        this.fontSize = fontSize;
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "{ SIZE: " + fontSize + ", TEXT: " + text + " }";
    }
}