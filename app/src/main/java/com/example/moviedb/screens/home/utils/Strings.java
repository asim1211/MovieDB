package com.example.moviedb.screens.home.utils;

public class Strings {

    private static Strings instance;

    private Strings(){}

    public boolean isStringNotEmpty(String string) {
        return string != null && !string.isEmpty();
    }

    public static Strings getInstance() {
        if (instance == null)
            instance = new Strings();
        return instance;
    }
}
