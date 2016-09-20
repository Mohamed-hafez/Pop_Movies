package com.example.mohamed.pop_movies.data;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Configuration;
import com.activeandroid.app.Application;

/**
 * Created by mohamed on 9/8/2016.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Configuration dbConfiguration = new Configuration.Builder(this).setDatabaseName("Pop_Movies_DB.db").create();
        ActiveAndroid.initialize(dbConfiguration);
    }
}
