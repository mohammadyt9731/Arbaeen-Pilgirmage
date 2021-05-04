package com.ttp.ziaratarbaeen.classes;

import android.app.Application;
import ir.tapsell.plus.TapsellPlus;

public class MyApplication extends Application {

    @Override
    public void onCreate() {

        TapsellPlus.initialize(this, MyConstants.TAPSELL_KEY);
        super.onCreate();
    }
}
