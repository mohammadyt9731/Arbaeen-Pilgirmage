package com.ttp.ziaratarbaeen.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ttp.ziaratarbaeen.R;
import com.ttp.ziaratarbaeen.classes.MyConstants;
import com.ttp.ziaratarbaeen.classes.MyIntent;
import com.ttp.ziaratarbaeen.classes.MyTapsell;

import ir.tapsell.plus.TapsellPlusBannerType;

public class ExitDialog extends Dialog {

    TextView exitButton;
    TextView otherAppButton;
    TextView commentButton;

    RelativeLayout rlAdvertising;

    Activity activity;

    public ExitDialog(Activity activity) {
        super(activity);
        this.activity=activity;
        setContentView(R.layout.dialog_exit);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));


        findViews();
        configuration();
        showAdvertising();

    }

    private void configuration() {
        exitButton.setOnClickListener(v -> {

            getOwnerActivity().finish();
        });

        otherAppButton.setOnClickListener(v -> MyIntent.otherAppIntent(activity));

        commentButton.setOnClickListener(v -> MyIntent.commentIntent(activity));
    }

    private void findViews() {
        exitButton = findViewById(R.id.btn_exit);
        otherAppButton = findViewById(R.id.btn_other_apps);
        commentButton = findViewById(R.id.btn_comment);

        rlAdvertising=findViewById(R.id.rl_tapsell_ad);
    }

    private void  showAdvertising(){

        MyTapsell.showStandardBanner(activity, MyConstants.STANDARD_1_AD_ID
        ,rlAdvertising, TapsellPlusBannerType.BANNER_250x250);

    }


}
