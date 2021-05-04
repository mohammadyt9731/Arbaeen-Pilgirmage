package com.ttp.ziaratarbaeen.classes;

import android.app.Activity;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ttp.ziaratarbaeen.R;

import ir.tapsell.plus.AdRequestCallback;
import ir.tapsell.plus.TapsellPlus;
import ir.tapsell.plus.TapsellPlusBannerType;
import ir.tapsell.sdk.nativeads.TapsellNativeBannerManager;
import ir.tapsell.sdk.nativeads.TapsellNativeBannerViewManager;

public class TapsellAD {

    TapsellPlusBannerType bannerType;
    ViewGroup containerView;
    Activity activity;

    public TapsellAD(TapsellPlusBannerType bannerType, ViewGroup viewId, Activity activity) {

        this.bannerType = bannerType;
        this.containerView = viewId;
        this.activity = activity;
    }

    public void showStandardBannerAD(String zoneId) {

        TapsellPlus.showBannerAd(
                activity,
                containerView,
                zoneId,
                bannerType,
                new AdRequestCallback() {
                    @Override
                    public void response() {
                    }

                    @Override
                    public void error(String message) {
                    }
                });

    }

    public void showInterstitialAD(String zoneId) {

        TapsellPlus.requestInterstitial(
                activity,
                zoneId,
                new AdRequestCallback() {
                    @Override
                    public void response() {
                        Toast.makeText(activity, R.string.please_wait, Toast.LENGTH_SHORT).show();
                        showAd(zoneId);
                    }

                    @Override
                    public void error(String message) {

                        Toast.makeText(activity, R.string.need_internet, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void showInterstitialVideoAD(String zoneId) {

        TapsellPlus.requestRewardedVideo(
                activity,
                zoneId,
                new AdRequestCallback() {
                    @Override
                    public void response() {
                        Toast.makeText(activity, R.string.please_wait, Toast.LENGTH_LONG).show();
                        showAd(zoneId);
                    }

                    @Override
                    public void error(String message) {
                        Toast.makeText(activity, R.string.need_internet, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void showAd(String zoneId) {
        TapsellPlus.showAd(activity, zoneId);
    }

    public void showNativeAD(String zoneId) {

        TapsellNativeBannerViewManager nativeBannerViewManager = new TapsellNativeBannerManager
                .Builder()
                .setParentView(containerView)
                .setContentViewTemplate(R.layout.native_ad)
                .setAppInstallationViewTemplate(R.layout.native_ad)
                .inflateTemplate(activity);


        TapsellNativeBannerManager.getAd(activity, zoneId,
                new ir.tapsell.sdk.AdRequestCallback() {
                    @Override
                    public void onResponse(String[] adId) {

                        TapsellNativeBannerManager.bindAd(
                                activity,
                                nativeBannerViewManager,
                                zoneId,
                                adId[0]);
                    }

                    @Override
                    public void onFailed(String s) {

                    }
                });
    }
}
