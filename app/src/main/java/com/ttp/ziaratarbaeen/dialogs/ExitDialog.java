package com.ttp.ziaratarbaeen.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.ttp.ziaratarbaeen.R;
import com.ttp.ziaratarbaeen.classes.MyIntent;

public class ExitDialog extends Dialog {

    TextView exitButton;
    TextView otherAppButton;
    TextView commentButton;

    Context context;

    public ExitDialog(Context context) {
        super(context);
        this.context=context;
        setContentView(R.layout.dialog_exit);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));


        findViews();
        configuration();

    }

    private void configuration() {
        exitButton.setOnClickListener(v -> {

            getOwnerActivity().finish();
        });

        otherAppButton.setOnClickListener(v -> MyIntent.otherAppIntent(context));

        commentButton.setOnClickListener(v -> MyIntent.commentIntent(context));
    }

    private void findViews() {
        exitButton = findViewById(R.id.btn_exit);
        otherAppButton = findViewById(R.id.btn_other_apps);
        commentButton = findViewById(R.id.btn_comment);
    }


}
