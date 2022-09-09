package com.ttp.ziaratarbaeen.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.widget.AbsListView;

import androidx.annotation.NonNull;

import com.ttp.ziaratarbaeen.R;
import com.ttp.ziaratarbaeen.databinding.DialogResetBinding;
import com.ttp.ziaratarbaeen.utils.MyConstants;
import com.ttp.ziaratarbaeen.utils.UseFullMethod;

public class ResetCounterDialog extends Dialog {

    private DialogResetBinding binding;

    public ResetCounterDialog(@NonNull Context context) {
        super(context);
        binding = DialogResetBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        this.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));


        config();
        setOnclick();

    }

    private void config() {

        //background transparent
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        //dialog animation
        getWindow().getAttributes().windowAnimations = R.style.scale_anim_style;

        //set width and height
        getWindow().setLayout(
                UseFullMethod.getScreenWidth(MyConstants.DIALOG_WIDTH_PERCENTAGE),
                AbsListView.LayoutParams.WRAP_CONTENT);
    }

    private void setOnclick() {

        binding.yesReset.setOnClickListener(v -> {
            // resetCounter();
            cancel();
        });

        binding.noReset.setOnClickListener(v -> cancel());

    }

}
