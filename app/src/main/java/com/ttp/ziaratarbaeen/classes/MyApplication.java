package com.ttp.ziaratarbaeen.classes;

import android.app.Application;
import android.util.Log;

import ir.tapsell.plus.TapsellPlus;
import ir.tapsell.plus.TapsellPlusInitListener;
import ir.tapsell.plus.model.AdNetworkError;
import ir.tapsell.plus.model.AdNetworks;

public class MyApplication extends Application {

    @Override
    public void onCreate() {

        super.onCreate();
        initTapsell();

    }

    private void initTapsell(){

        TapsellPlus.initialize(this, MyConstants.TAPSELL_KEY,
                new TapsellPlusInitListener() {
                    @Override
                    public void onInitializeSuccess(AdNetworks adNetworks) {
                    }

                    @Override
                    public void onInitializeFailed(AdNetworks adNetworks,AdNetworkError adNetworkError) {

                    }
                });
        TapsellPlus.setGDPRConsent(this, true);


    }

}
