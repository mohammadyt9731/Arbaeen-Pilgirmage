package com.ttp.ziaratarbaeen.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ttp.ziaratarbaeen.R;
import com.ttp.ziaratarbaeen.databinding.ItemNarrativeListBinding;

import java.util.ArrayList;
import java.util.Arrays;

public class NarrativeAdapter extends RecyclerView.Adapter<NarrativeAdapter.ViewHolder> {


    ArrayList<String> narrativeTitleList;
    ArrayList<String> narrativeTextList;
    private ItemNarrativeListBinding binding;


    public NarrativeAdapter(Context context) {

        narrativeTitleList = new ArrayList<>();
        narrativeTextList = new ArrayList<>();

        narrativeTitleList.addAll(Arrays.asList(context.getResources().getStringArray(R.array.narratives_title)));
        narrativeTextList.addAll(Arrays.asList(context.getResources().getStringArray(R.array.narratives_text)));

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        binding = ItemNarrativeListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
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


        public ViewHolder(ItemNarrativeListBinding binding) {
            super(binding.getRoot());

            tvTitle = binding.tvNarrativeTitleItemNarrativeLIst;
            tvText = binding.tvNarrativeTextItemNarrativeLIst;

        }
    }
}
