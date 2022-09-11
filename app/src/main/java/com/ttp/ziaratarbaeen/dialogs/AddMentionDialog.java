package com.ttp.ziaratarbaeen.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.widget.AbsListView;

import androidx.annotation.NonNull;

import com.ttp.ziaratarbaeen.R;
import com.ttp.ziaratarbaeen.databinding.DialogAddMentionBinding;
import com.ttp.ziaratarbaeen.databinding.DialogExitBinding;
import com.ttp.ziaratarbaeen.utils.MyConstants;
import com.ttp.ziaratarbaeen.utils.MyIntent;
import com.ttp.ziaratarbaeen.utils.UseFullMethod;

public class AddMentionDialog extends Dialog {

    private final DialogAddMentionBinding binding;


    public AddMentionDialog(@NonNull Context context) {
        super(context);
        binding = DialogAddMentionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        config();
        setOnClick();
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

    private void setOnClick() {

        binding.ivCloseDialogAddMention.setOnClickListener(view -> cancel());

        binding.btnOtherApp.setOnClickListener(view -> {
            cancel();
        });

    }
}




