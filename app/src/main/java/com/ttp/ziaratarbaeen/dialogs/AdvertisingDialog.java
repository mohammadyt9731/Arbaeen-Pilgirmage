package com.ttp.ziaratarbaeen.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.view.ViewGroup;
import android.widget.Button;

import com.ttp.ziaratarbaeen.R;
import com.ttp.ziaratarbaeen.classes.MyConstants;
import com.ttp.ziaratarbaeen.classes.MyTapsell;

public class AdvertisingDialog extends Dialog {

    Button btnVideoAd;
    Button btnBannerAd;
    ViewGroup rlBannerContainer;

    Activity activity;

    public AdvertisingDialog(Activity activity) {
        super(activity);
        this.activity=activity;
        setContentView(R.layout.dialog_ad);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));


        findViews();
        configuration();

    }

    private void configuration() {
        btnVideoAd.setOnClickListener(view -> {
            MyTapsell.showInterstitialAd(activity,MyConstants.Interstitial_VIDEO_AD_ID);
        });

        btnBannerAd.setOnClickListener(view -> {

            MyTapsell.showInterstitialAd(activity,MyConstants.Interstitial_AD_ID);

        });


        MyTapsell.showNativeAD(activity,MyConstants.NATIVE_STANDARD_AD_ID,rlBannerContainer);

    }

    private void findViews() {
       btnVideoAd = findViewById(R.id.btn_video_ad);
        btnBannerAd = findViewById(R.id.btn_banner_ad);
        rlBannerContainer =findViewById(R.id.rl_banner_container);
    }


}
