package com.ttp.ziaratarbaeen.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import com.ttp.ziaratarbaeen.R;
import com.ttp.ziaratarbaeen.databinding.DialogCommentBinding;
import com.ttp.ziaratarbaeen.utils.MyConstants;
import com.ttp.ziaratarbaeen.utils.MyIntent;
import com.ttp.ziaratarbaeen.utils.UseFullMethod;

public class CommentDialog extends Dialog {

    private final DialogCommentBinding binding;
    private final Activity activity;

    public CommentDialog(@NonNull Activity activity) {
        super(activity);
        binding = DialogCommentBinding.inflate(getLayoutInflater());
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

        binding.ratingBar.setOnRatingBarChangeListener((ratingBar, v, b) -> {
            if (v == 0f) {
                binding.tvCommentCaffeBazzar.setVisibility(View.GONE);
                binding.btnComment.setText(activity.getString(R.string.comment));
                binding.btnComment.setAlpha(MyConstants.DISABLE_BUTTON_ALPHA);
            } else {
                binding.tvCommentCaffeBazzar.setVisibility(View.VISIBLE);
                binding.btnComment.setAlpha(MyConstants.ENABLE_BUTTON_ALPHA);

                if (v < 4) {
                    binding.btnComment.setText(activity.getString(R.string.send_email));
                    binding.tvCommentCaffeBazzar.setText(activity.getString(R.string.please_send_email));
                } else {
                    binding.btnComment.setText(activity.getString(R.string.comment));
                    binding.tvCommentCaffeBazzar.setText(activity.getString(R.string.please_register_your_comment));
                }


            }
        });

        binding.ivClose.setOnClickListener(view -> cancel());

        binding.btnComment.setOnClickListener(view -> {

            switch ((int) binding.ratingBar.getRating()) {
                case 0: {
                    Toast.makeText(
                            activity,
                            activity.getString(R.string.submitt_comment),
                            Toast.LENGTH_SHORT
                    ).show();
                    return;
                }

                case 1:
                case 2:
                case 3:
                    MyIntent.emailIntent(activity);
                    break;

                default: {
                    MyIntent.commentIntent(activity);
                }
            }

            cancel();
        });

    }
}