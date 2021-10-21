package com.ttp.ziaratarbaeen.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ttp.ziaratarbaeen.R;

import java.util.ArrayList;
import java.util.Arrays;

public class NarrativeAdapter extends RecyclerView.Adapter<NarrativeAdapter.ViewHolder> {

    ArrayList<String> narrativeTitleList;
    ArrayList<String> narrativeTextList;


    public NarrativeAdapter(Context context) {

        narrativeTitleList = new ArrayList<>();
        narrativeTextList = new ArrayList<>();

        narrativeTitleList.addAll(Arrays.asList(context.getResources().getStringArray(R.array.narratives_title)));
        narrativeTextList.addAll(Arrays.asList(context.getResources().getStringArray(R.array.narratives_text)));


    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_narrative, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NarrativeAdapter.ViewHolder holder, int position) {

        holder.tvTitle.setText(narrativeTitleList.get(position));
        holder.tvText.setText(narrativeTextList.get(position));

        if (holder.tvTitle.getText().length() == 0)
            holder.tvTitle.setVisibility(View.GONE);

    }

    @Override
    public int getItemCount() {
        return narrativeTitleList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {


        TextView tvTitle;
        TextView tvText;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tv_narrative_title);
            tvText = itemView.findViewById(R.id.tv_narrative_text);

        }
    }
}
