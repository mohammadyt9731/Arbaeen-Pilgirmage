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

        TapsellPlus.initialize(this, MyConstants.TAPSELL_KEY,
                new TapsellPlusInitListener() {
                    @Override
                    public void onInitializeSuccess(AdNetworks adNetworks) {
                        Log.d("onInitializeSuccess", adNetworks.name());
                    }

                    @Override
                    public void onInitializeFailed(AdNetworks adNetworks,
                                                   AdNetworkError adNetworkError) {
                        Log.e("onInitializeFailed", "ad network: " + adNetworks.name() + ", error: " +	adNetworkError.getErrorMessage());
                    }
                });
        TapsellPlus.setGDPRConsent(this, true);
        super.onCreate();
    }
}
