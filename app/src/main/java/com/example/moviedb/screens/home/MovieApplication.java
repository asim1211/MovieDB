package com.example.moviedb.screens.home;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MovieApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        init();
    }

    private void init() {
        Realm.init(this);
        Realm.setDefaultConfiguration(new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build());
    }
}
