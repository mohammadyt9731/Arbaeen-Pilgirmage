package com.ttp.ziaratarbaeen.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.widget.AbsListView;
import android.widget.Toast;

import com.ttp.ziaratarbaeen.R;
import com.ttp.ziaratarbaeen.database.MentionDataBase;
import com.ttp.ziaratarbaeen.database.MentionEntity;
import com.ttp.ziaratarbaeen.databinding.DialogAddMentionBinding;
import com.ttp.ziaratarbaeen.interfaces.MyCallBack;
import com.ttp.ziaratarbaeen.utils.MyConstants;
import com.ttp.ziaratarbaeen.utils.UseFullMethod;

public class AddMentionDialog extends Dialog {

    private final DialogAddMentionBinding binding;
    private MyCallBack myCallBack;
    private int id;


    public AddMentionDialog(Context context, int id, MyCallBack myCallBack) {
        super(context);
        binding = DialogAddMentionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        init(myCallBack, id);
        config();
        setOnClick();
    }

    private void init(MyCallBack myCallBack, int id) {
        this.myCallBack = myCallBack;
        this.id = id;

        if (id != -1) {
            MentionEntity mentionEntity = MentionDataBase.getInstance(getContext())
                    .mentionDao().getMention(id);

            binding.etMentionTitleDialogAddMention.setText(mentionEntity.getTitle());
            binding.etMentionMaxNumberDialogAddMention.setText(String.valueOf(mentionEntity.getMaximumNumber()));


        }
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

        binding.btnSubmitDialogAddMention.setOnClickListener(view -> {

            if (isValidInput()) {
                if (id != -1) {
                    MentionEntity mentionEntity = MentionDataBase.getInstance(getContext())
                            .mentionDao().getMention(id);
                    mentionEntity.setTitle(binding.etMentionTitleDialogAddMention
                            .getText().toString().trim());
                    mentionEntity.setMaximumNumber(Integer.parseInt(binding.etMentionMaxNumberDialogAddMention
                            .getText().toString().trim()));

                    MentionDataBase.getInstance(getContext()).mentionDao()
                            .updateMention(mentionEntity);

                } else {
                    MentionEntity mentionEntity = new MentionEntity(0,
                            binding.etMentionTitleDialogAddMention
                                    .getText().toString().trim(),
                            Integer.parseInt(binding.etMentionMaxNumberDialogAddMention
                                    .getText().toString().trim()));

                    MentionDataBase.getInstance(getContext()).mentionDao().insertMention(mentionEntity);
                }
                myCallBack.callBack();
                cancel();
            }


        });

    }


    private boolean isValidInput() {
        if (binding.etMentionTitleDialogAddMention.getText().toString().trim().isEmpty()) {
            Toast.makeText(getContext(), "نام ذکر را وارد کنید", Toast.LENGTH_SHORT).show();
            return false;
        } else if (binding.etMentionMaxNumberDialogAddMention.getText().toString().trim().isEmpty()) {
            Toast.makeText(getContext(), "تعداد را وارد کنید", Toast.LENGTH_SHORT).show();
            return false;
        } else if (binding.etMentionMaxNumberDialogAddMention.getText().toString().trim().startsWith("0")) {
            Toast.makeText(getContext(), "تعداد باید بیشتر از صفر باشد", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}




