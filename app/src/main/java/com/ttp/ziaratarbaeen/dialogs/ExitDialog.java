package com.ttp.ziaratarbaeen.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.widget.AbsListView;
import androidx.annotation.NonNull;
import com.ttp.ziaratarbaeen.R;
import com.ttp.ziaratarbaeen.utils.MyConstants;
import com.ttp.ziaratarbaeen.utils.MyIntent;
import com.ttp.ziaratarbaeen.utils.UseFullMethod;
import com.ttp.ziaratarbaeen.databinding.DialogExitBinding;

public class ExitDialog extends Dialog {

    private final DialogExitBinding binding;
    private final Activity activity;

    public ExitDialog(@NonNull Activity activity) {
        super(activity);
        binding = DialogExitBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        this.activity = activity;

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


        binding.btnClose.setOnClickListener(view -> cancel());

        binding.btnExit.setOnClickListener(view -> activity.finish());

        binding.btnOtherApp.setOnClickListener(view -> MyIntent.otherAppIntent(activity));
    }
}




