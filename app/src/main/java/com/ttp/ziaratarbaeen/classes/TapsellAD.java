package com.ttp.ziaratarbaeen.classes;

import android.app.Activity;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.ttp.ziaratarbaeen.R;

import ir.tapsell.plus.AdRequestCallback;
import ir.tapsell.plus.AdShowListener;
import ir.tapsell.plus.TapsellPlus;
import ir.tapsell.plus.TapsellPlusBannerType;
import ir.tapsell.plus.model.TapsellPlusAdModel;
import ir.tapsell.plus.model.TapsellPlusErrorModel;
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

        TapsellPlus.requestStandardBannerAd(
                activity,
                zoneId,
                bannerType,
                new AdRequestCallback() {
                    @Override
                    public void response(TapsellPlusAdModel tapsellPlusAdModel) {
                        super.response(tapsellPlusAdModel);

                        //Ad is ready to show
                        //Put the ad's responseId to your responseId variable
                       // standardBannerResponseId = tapsellPlusAdModel.getResponseId();
                    }

                    @Override
                    public void error(@NonNull String message) {
                    }
                });

    }

    public void showInterstitialAD(String zoneId) {

        TapsellPlus.requestRewardedVideoAd(
                activity,
                zoneId,
                new AdRequestCallback() {
                    @Override
                    public void response(TapsellPlusAdModel tapsellPlusAdModel) {
                        super.response(tapsellPlusAdModel);

                        //Ad is ready to show
                        //Put the ad's responseId to your responseId variable
                     //   rewardedResponseId = tapsellPlusAdModel.getResponseId();
                    }

                    @Override
                    public void error(String message) {
                    }

                });
    }

    public void showInterstitialVideoAD(String zoneId) {

        TapsellPlus.showRewardedVideoAd(
                activity,
                zoneId,
                new AdShowListener() {
                    @Override
                    public void onOpened(TapsellPlusAdModel tapsellPlusAdModel) {
                        super.onOpened(tapsellPlusAdModel);
                    }

                    @Override
                    public void onClosed(TapsellPlusAdModel tapsellPlusAdModel) {
                        super.onClosed(tapsellPlusAdModel);
                    }

                    @Override
                    public void onRewarded(TapsellPlusAdModel tapsellPlusAdModel) {
                        super.onRewarded(tapsellPlusAdModel);
                    }

                    @Override
                    public void onError(TapsellPlusErrorModel tapsellPlusErrorModel) {
                        super.onError(tapsellPlusErrorModel);
                    }
                });
    }

    private void showAd(String zoneId) {
       // TapsellPlus.showAd(activity, zoneId);
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
