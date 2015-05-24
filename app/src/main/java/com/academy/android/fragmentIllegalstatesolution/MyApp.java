package com.academy.android.fragmentIllegalstatesolution;

import com.squareup.leakcanary.LeakCanary;

import android.app.Application;

public class MyApp extends Application {
    @Override public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
    }
}
