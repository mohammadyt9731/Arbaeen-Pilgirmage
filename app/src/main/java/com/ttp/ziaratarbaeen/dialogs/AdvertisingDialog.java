package com.ttp.ziaratarbaeen.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.ttp.ziaratarbaeen.R;
import com.ttp.ziaratarbaeen.classes.MyConstants;
import com.ttp.ziaratarbaeen.classes.MyIntent;
import com.ttp.ziaratarbaeen.classes.TapsellAD;

public class AdvertisingDialog extends Dialog {

    Button btnVideoAd;
    Button btnBannerAd;
    ViewGroup rlBannerContainer;

    Context context;

    public AdvertisingDialog(Context context) {
        super(context);
        this.context=context;
        setContentView(R.layout.dialog_ad);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));


        findViews();
        configuration();

    }

    private void configuration() {
        btnVideoAd.setOnClickListener(view -> {
            new TapsellAD(null, null, (Activity) context)
                    .showInterstitialVideoAD(MyConstants.Interstitial_VIDEO_AD_ID);
        });

        btnBannerAd.setOnClickListener(view -> {

            new TapsellAD(null, null, (Activity) context)
                    .showInterstitialAD(MyConstants.Interstitial_AD_ID);
        });

        new TapsellAD(null,rlBannerContainer,(Activity)context).showNativeAD(MyConstants.NATIVE_STANDARD_AD_ID);

    }

    private void findViews() {
       btnVideoAd = findViewById(R.id.btn_video_ad);
        btnBannerAd = findViewById(R.id.btn_banner_ad);
        rlBannerContainer =findViewById(R.id.rl_banner_container);
    }


}
