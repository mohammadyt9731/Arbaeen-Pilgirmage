package com.ttp.ziaratarbaeen.classes;

import android.app.Activity;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.ttp.ziaratarbaeen.R;

import ir.tapsell.plus.AdHolder;
import ir.tapsell.plus.AdRequestCallback;
import ir.tapsell.plus.AdShowListener;
import ir.tapsell.plus.TapsellPlus;
import ir.tapsell.plus.TapsellPlusBannerType;
import ir.tapsell.plus.model.TapsellPlusAdModel;
import ir.tapsell.plus.model.TapsellPlusErrorModel;
import ir.tapsell.sdk.nativeads.TapsellNativeBannerManager;
import ir.tapsell.sdk.nativeads.TapsellNativeBannerViewManager;

public class MyTapsell {

    public static void showNativeAD(Activity activity,String zoneId,ViewGroup containerView) {



        AdHolder adHolder = TapsellPlus.createAdHolder(
                activity, containerView, R.layout.native_ad);

            TapsellPlus.requestNativeAd(
                    activity,
                    zoneId,
                    new AdRequestCallback() {
                        @Override
                        public void response(TapsellPlusAdModel tapsellPlusAdModel) {
                            super.response(tapsellPlusAdModel);

                           String nativeAdResponseId = tapsellPlusAdModel.getResponseId();
                            TapsellPlus.showNativeAd(activity, nativeAdResponseId, adHolder,
                                    new AdShowListener() {
                                        @Override
                                        public void onOpened(TapsellPlusAdModel tapsellPlusAdModel) {
                                            super.onOpened(tapsellPlusAdModel);
                                        }

                                        @Override
                                        public void onError(TapsellPlusErrorModel tapsellPlusErrorModel) {
                                            super.onError(tapsellPlusErrorModel);
                                        }
                                    });
                        }

                        @Override
                        public void error(@NonNull String message) {
                        }
                    });

    }

    public static void showStandardBanner(Activity activity, String zoneId, RelativeLayout relativeLayout,TapsellPlusBannerType bannerType) {


        TapsellPlus.requestStandardBannerAd(
                activity, zoneId,
                bannerType,
                new AdRequestCallback() {
                    @Override
                    public void response(TapsellPlusAdModel tapsellPlusAdModel) {
                        super.response(tapsellPlusAdModel);

                        String standardBannerResponseId = tapsellPlusAdModel.getResponseId();

                        TapsellPlus.showStandardBannerAd(activity, standardBannerResponseId,
                                relativeLayout,
                                new AdShowListener() {
                                    @Override
                                    public void onOpened(TapsellPlusAdModel tapsellPlusAdModel) {
                                        super.onOpened(tapsellPlusAdModel);
                                    }

                                    @Override
                                    public void onError(TapsellPlusErrorModel tapsellPlusErrorModel) {
                                        super.onError(tapsellPlusErrorModel);

                                    }
                                });
                    }

                    @Override
                    public void error(String message) {

                    }
                });
    }

    public static void showInterstitialAd(Activity activity, String zoneId) {
        TapsellPlus.requestRewardedVideoAd(
                activity,
                zoneId,
                new AdRequestCallback() {
                    @Override
                    public void response(TapsellPlusAdModel tapsellPlusAdModel) {
                        super.response(tapsellPlusAdModel);

                        String rewardedResponseId = tapsellPlusAdModel.getResponseId();


                        TapsellPlus.showRewardedVideoAd(activity, rewardedResponseId,
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

                    @Override
                    public void error(String message) {

                    }

                });
    }

}
