package com.ttp.ziaratarbaeen.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;

import androidx.annotation.NonNull;

import com.ttp.ziaratarbaeen.R;

public class AboutUsDialog extends Dialog {

    public AboutUsDialog(@NonNull Context context) {
        super(context);
        setContentView(R.layout.dialog_about_us);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
    }
}
