package com.example.moviedb.screens.home.networking;

import android.graphics.Color;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviedb.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

public class FirebaseInstance {

    private FirebaseRemoteConfig firebaseInstance;
    private static FirebaseInstance instance;


    private FirebaseInstance(){
        firebaseInstance = FirebaseRemoteConfig.getInstance();

    }

    public void getFirebaseRemoteConfigData() {
        firebaseInstance.setConfigSettingsAsync(
                new FirebaseRemoteConfigSettings.Builder().setMinimumFetchIntervalInSeconds(3600L)
                        .build());

        firebaseInstance.setDefaults(R.xml.no_experiment);
    }

    public void fetchVariant(RecyclerView moviesRecyclerView, RecyclerView genresRecyclerView){

        firebaseInstance.fetch(0).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Log.d("123", "Fetch Succeeded");
                    firebaseInstance.activate();
                } else {
                    Log.d("456", "Fetch Failed");
                }
                runExperiment(moviesRecyclerView, genresRecyclerView);
            }
        });
    }

    private void runExperiment(RecyclerView moviesRecyclerView, RecyclerView genresRecyclerView){
        String firstParam = firebaseInstance.getString("firstParam");
        String secondParam = firebaseInstance.getString("secondParam");

        moviesRecyclerView.setBackgroundColor(Color.parseColor(firstParam));
        genresRecyclerView.setBackgroundColor(Color.parseColor(secondParam));
        }

    public static FirebaseInstance getInstance() {
        if (instance == null)
            instance = new FirebaseInstance();
        return instance;
    }



}
