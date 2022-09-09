package com.ttp.ziaratarbaeen.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ttp.ziaratarbaeen.R;
import com.ttp.ziaratarbaeen.utils.Mention;

import java.util.ArrayList;

public class MentionAdapter extends RecyclerView.Adapter<MentionAdapter.ViewHolder> {


    ArrayList<Mention> mentionList;

    public MentionAdapter(ArrayList<Mention> mentionList){

        this.mentionList=mentionList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater
                .from(parent.getContext()).inflate(R.layout.view_mention,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.tvMentionTitle.setText(mentionList.get(position).getMentionTitle());
        holder.tvMentionTranslation.setText(mentionList.get(position).getMentionTranslation());

    }

    @Override
    public int getItemCount() {
        return mentionList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvMentionTitle;
        TextView tvMentionTranslation;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvMentionTitle =itemView.findViewById(R.id.tv_mention_title);
            tvMentionTranslation=itemView.findViewById(R.id.tv_number);


        }
    }
}
