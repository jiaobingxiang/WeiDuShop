package com.bawie.weidushop.app;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

public class Applic extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
