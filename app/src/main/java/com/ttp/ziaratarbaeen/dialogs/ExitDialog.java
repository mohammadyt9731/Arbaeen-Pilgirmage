package com.ttp.ziaratarbaeen.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;

import com.ttp.ziaratarbaeen.R;
import com.ttp.ziaratarbaeen.classes.MyIntent;
import com.ttp.ziaratarbaeen.databinding.DialogExitBinding;

public class ExitDialog extends Dialog {

    Activity activity;
    private DialogExitBinding binding;

    public ExitDialog(Activity activity) {
        super(activity);

        binding = DialogExitBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        this.activity = activity;
        this.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));


        configuration();


    }

    private void configuration() {
        binding.btnExit.setOnClickListener(v -> {

            getOwnerActivity().finish();
        });

        binding.btnOtherApp.setOnClickListener(v -> MyIntent.otherAppIntent(activity));

        binding.btnExit.setOnClickListener(v -> MyIntent.commentIntent(activity));
    }


}
