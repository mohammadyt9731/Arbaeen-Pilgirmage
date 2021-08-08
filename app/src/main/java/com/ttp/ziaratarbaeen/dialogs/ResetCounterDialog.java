package com.ttp.ziaratarbaeen.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.ttp.ziaratarbaeen.R;

public class ResetCounterDialog extends Dialog {

    TextView yesButton;
    TextView noButton;

    public ResetCounterDialog(@NonNull Context context) {
        super(context);
        setContentView(R.layout.dialog_reset);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        findViews();
        configuration();

    }

    private void configuration() {
        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // resetCounter();
               cancel();
            }
        });

        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               cancel();
            }
        });
    }

    private void findViews() {
        yesButton =findViewById(R.id.yes_reset);
        noButton =findViewById(R.id.no_reset);
    }
}
