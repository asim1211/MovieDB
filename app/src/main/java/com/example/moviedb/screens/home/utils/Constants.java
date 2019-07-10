package com.example.moviedb.screens.home.utils;

import androidx.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class Constants {

    public final static String IMAGE_URL_DOMAIN_MOVIE_DB = "https://image.tmdb.org/t/p";
    public final static String SMALL_IMAGE_URL_DOMAIN_MOVIE_DB = IMAGE_URL_DOMAIN_MOVIE_DB + "/w92/";
    public final static String ORIGINAL_IMAGE_URL_DOMAIN_MOVIE_DB = IMAGE_URL_DOMAIN_MOVIE_DB + "/original/";


    @Retention(RetentionPolicy.SOURCE)
    @StringDef({
            MOVIE_ID_EXTRA_KEY
    })
    public @interface EXTRA_KEYS {}
    public static final String MOVIE_ID_EXTRA_KEY = "MOVIE_ID";
}
