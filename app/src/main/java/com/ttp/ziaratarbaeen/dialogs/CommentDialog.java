package com.ttp.ziaratarbaeen.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;

import androidx.annotation.NonNull;

import com.ttp.ziaratarbaeen.R;

public class CommentDialog extends Dialog {

    public CommentDialog(@NonNull Context context) {
        super(context);
        setContentView(R.layout.dialog_comment);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
    }
}
