package com.ttp.ziaratarbaeen.adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ttp.ziaratarbaeen.R;
import com.ttp.ziaratarbaeen.dialogs.AddMentionDialog;
import com.ttp.ziaratarbaeen.utils.Mention;
import com.ttp.ziaratarbaeen.utils.UseFullMethod;

import java.util.ArrayList;

public class MentionAdapter extends RecyclerView.Adapter<MentionAdapter.ViewHolder> {


    ArrayList<Mention> mentionList;

    public MentionAdapter(ArrayList<Mention> mentionList) {

        this.mentionList = mentionList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater
                .from(parent.getContext()).inflate(R.layout.item_mention_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.tvMentionTitle.setText(mentionList.get(position).getMentionTitle());
        holder.tvMaximumNumber.setText("تعداد کل : " + mentionList.get(position).getMaximumNumber());

        holder.itemView.setOnClickListener(view -> {
            UseFullMethod.safeNavigate(view, R.id.salawatCountFragment);
        });

        holder.ivEdit.setOnClickListener(view -> {
            new AddMentionDialog(view.getContext()).show();
        });

        holder.ivDelete.setOnClickListener(view -> {
            new AlertDialog.Builder(view.getContext())
                    .setTitle(view.getContext().getString(R.string.delete_Mention))
                    .setMessage(view.getContext().getString(R.string.doYouWantToDeleteThisMention))
                    .setCancelable(true)
                    .setPositiveButton(view.getContext().getString(R.string.yes), (dialogInterface, i) -> {

                    })
                    .setNegativeButton(view.getContext().getString(R.string.no), (dialogInterface, i) -> {
                            dialogInterface.cancel();
                    })
                    .create()
                    .show();
        });


    }

    @Override
    public int getItemCount() {
        return mentionList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvMentionTitle;
        TextView tvMaximumNumber;

        ImageView ivDelete;
        ImageView ivEdit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvMentionTitle = itemView.findViewById(R.id.tv_title_itemMentionLIst);
            tvMaximumNumber = itemView.findViewById(R.id.tv_maxNumber_itemMentionLIst);
            ivDelete = itemView.findViewById(R.id.iv_delete_itemMentionLIst);
            ivEdit = itemView.findViewById(R.id.iv_edit_itemMentionLIst);


        }
    }
}
