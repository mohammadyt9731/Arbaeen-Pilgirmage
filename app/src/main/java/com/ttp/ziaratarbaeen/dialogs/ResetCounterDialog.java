package com.ttp.ziaratarbaeen.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;

import androidx.annotation.NonNull;

import com.ttp.ziaratarbaeen.databinding.DialogResetBinding;

public class ResetCounterDialog extends Dialog {

    private DialogResetBinding binding;

    public ResetCounterDialog(@NonNull Context context) {
        super(context);
        binding = DialogResetBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        this.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));


        configuration();

    }

    private void configuration() {
        binding.yesReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // resetCounter();
                cancel();
            }
        });

        binding.noReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
            }
        });
    }


}
