package com.example.hazal.myagenda.DatabaseAndClasses;

/**
 * Created by ha3al on 7/19/16.
 */
public class Settings {
    private String colorHex;
    private int colorFont;

    public Settings() {
    }

    public Settings(String colorHex, int colorFont) {
        this.colorHex = colorHex;
        this.colorFont = colorFont;
    }

    public String getColorHex() {
        return colorHex;
    }

    public void setColorHex(String colorHex) {
        this.colorHex = colorHex;
    }

    public int getColorFont() {
        return colorFont;
    }

    public void setColorFont(int colorFont) {
        this.colorFont = colorFont;
    }
}
