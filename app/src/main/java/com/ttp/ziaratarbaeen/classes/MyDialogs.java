package com.ttp.ziaratarbaeen.classes;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ttp.ziaratarbaeen.R;
import static ir.tapsell.plus.TapsellPlusBannerType.BANNER_300x250;


public class MyDialogs {

    public static void showAboutUsDialog(Context context) {

        final int WIDTH = (int) (context.getResources().getDisplayMetrics().widthPixels * 0.90);

        LayoutInflater inflater = LayoutInflater.from(context);
        View dialogView = inflater.inflate(R.layout.dialog_about_us, null);

        Dialog aboutUsDialog = new Dialog(context);

        aboutUsDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        aboutUsDialog.setContentView(dialogView);
        aboutUsDialog.getWindow().setLayout(WIDTH, LinearLayout.LayoutParams.WRAP_CONTENT);
        aboutUsDialog.getWindow().setBackgroundDrawable(context.getResources().getDrawable(R.drawable.dialog_background));
        aboutUsDialog.show();
    }

    public static void showExitDialog(Context context) {

        final int WIDTH = (int) (context.getResources().getDisplayMetrics().widthPixels * 0.90);

        LayoutInflater inflater = LayoutInflater.from(context);
        View dialogView = inflater.inflate(R.layout.dialog_exit, null);

        Dialog exitDialog = new Dialog(context);

        exitDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        exitDialog.setContentView(dialogView);
        exitDialog.getWindow().setLayout(WIDTH, LinearLayout.LayoutParams.WRAP_CONTENT);
        exitDialog.getWindow().setBackgroundDrawable(context.getResources().getDrawable(R.drawable.dialog_background));
        exitDialog.show();

        ViewGroup rlTapsellAd = exitDialog.findViewById(R.id.rl_tapsell_ad);

        new TapsellAD(BANNER_300x250, rlTapsellAd, (Activity) context).showStandardBannerAD(MyConstants.EXIT_DIALOG_AD_ID);

        setOnClickDialogButtons(exitDialog, context);
    }

    private static void setOnClickDialogButtons(Dialog exitDialog, Context context) {

        TextView exitButton = exitDialog.findViewById(R.id.btn_exit);
        TextView otherAppButton = exitDialog.findViewById(R.id.btn_other_app);
        TextView commentButton = exitDialog.findViewById(R.id.btn_comment);

        exitButton.setOnClickListener(v -> {
            Activity activity = (Activity) context;
            activity.finish();
        });

        otherAppButton.setOnClickListener(v -> MyIntent.otherAppIntent(context));

        commentButton.setOnClickListener(v -> MyIntent.commentIntent(context));

    }

    public static void showAdDialog(Context context) {

        final int WIDTH = (int) (context.getResources().getDisplayMetrics().widthPixels * 0.90);

        LayoutInflater inflater = LayoutInflater.from(context);
        View dialogView = inflater.inflate(R.layout.dialog_ad, null);

        Dialog AdDialog = new Dialog(context);

        AdDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        AdDialog.setContentView(dialogView);
        AdDialog.getWindow().setLayout(WIDTH, LinearLayout.LayoutParams.WRAP_CONTENT);
        AdDialog.getWindow().setBackgroundDrawable(context.getResources().getDrawable(R.drawable.dialog_background));
        AdDialog.show();


        Button btnVideoAd = AdDialog.findViewById(R.id.btn_video_ad);
        Button btnBannerAd = AdDialog.findViewById(R.id.btn_banner_ad);
        ViewGroup rlBannerContainer = AdDialog.findViewById(R.id.rl_banner_container);


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
}
