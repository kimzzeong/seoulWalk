package com.example.seoulwalk.data;

import android.graphics.drawable.Drawable;

public class Dulle_theme_Data {

    String dulle_theme_name;
    Drawable dulle_theme_background;

    public Dulle_theme_Data(String dulle_theme_name, Drawable dulle_theme_background) {
        this.dulle_theme_name = dulle_theme_name;
        this.dulle_theme_background = dulle_theme_background;
    }

    public Drawable getDulle_theme_background() {
        return dulle_theme_background;
    }

    public void setDulle_theme_background(Drawable dulle_theme_background) {
        this.dulle_theme_background = dulle_theme_background;
    }

    public String getDulle_theme_name() {
        return dulle_theme_name;
    }

    public void setDulle_theme_name(String dulle_theme_name) {
        this.dulle_theme_name = dulle_theme_name;
    }
}
