package com.ttp.ziaratarbaeen.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.widget.AbsListView;
import androidx.annotation.NonNull;
import com.ttp.ziaratarbaeen.R;
import com.ttp.ziaratarbaeen.utils.MyConstants;
import com.ttp.ziaratarbaeen.utils.UseFullMethod;
import com.ttp.ziaratarbaeen.databinding.DialogAboutUsBinding;

public class AboutUsDialog extends Dialog {

    private final DialogAboutUsBinding binding;

    public AboutUsDialog(@NonNull Activity activity) {
        super(activity);
        binding = DialogAboutUsBinding.inflate(getLayoutInflater());
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

        binding.ivClose.setOnClickListener(view -> cancel());
    }

}
