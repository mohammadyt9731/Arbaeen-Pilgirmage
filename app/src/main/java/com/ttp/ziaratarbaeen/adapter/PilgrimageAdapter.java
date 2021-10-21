package com.ttp.ziaratarbaeen.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.ttp.ziaratarbaeen.R;
import com.ttp.ziaratarbaeen.classes.ArbaeenMediaPlayer;
import com.ttp.ziaratarbaeen.classes.ProgramSetting;
import com.ttp.ziaratarbaeen.fragments.PilgrimageFragment;

import java.util.ArrayList;
import java.util.Arrays;

public class PilgrimageAdapter extends RecyclerView.Adapter<PilgrimageAdapter.ViewHolder> {

    ArrayList<String> arabicTextList;
    ArrayList<String> persianTextList;

    ProgramSetting programSetting;
    Context context;
    int currentIndex = -1;


    public PilgrimageAdapter(Context context) {
        init(context);
    }

    private void init(Context context) {

        this.context = context;
        arabicTextList = new ArrayList<>();
        persianTextList = new ArrayList<>();

        arabicTextList.addAll(Arrays.asList(context.getResources().getStringArray(R.array.arabic_text_list)));
        persianTextList.addAll(Arrays.asList(context.getResources().getStringArray(R.array.persian_text_list)));

        programSetting = new ProgramSetting(context);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_paragraph, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PilgrimageAdapter.ViewHolder holder,
                                 @SuppressLint("RecyclerView") int position) {

        applySetting(context, holder);

        holder.tvArabicText.setText(arabicTextList.get(position));
        holder.tvPersianText.setText(persianTextList.get(position));

        if (currentIndex == position)
            holder.tvArabicText.setTextColor(Color.RED);
        else
            holder.tvArabicText.setTextColor(Color.BLACK);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex = position;
                PilgrimageFragment.mpPilgrimage
                        .seekTo(ArbaeenMediaPlayer.getCurrentPosition(currentIndex) * 1000);

                if(!PilgrimageFragment.mpPilgrimage.isPlaying()){
                    PilgrimageFragment.mpPilgrimage.start();
                    PilgrimageFragment.btnPlayPause
                            .setBackground(context.getResources().getDrawable(R.drawable.ic_pause2));
                }


                notifyDataSetChanged();

            }
        });

    }

    public void update(int currentIndex) {

        this.currentIndex = currentIndex;
        notifyDataSetChanged();

    }

    public void updateView(){

        Toast.makeText(context, String.valueOf(programSetting.getArabicTextSize()), Toast.LENGTH_SHORT).show();
        programSetting=new ProgramSetting(context);
       notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return arabicTextList.size();

    }



    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvArabicText;
        TextView tvPersianText;
        ImageView ivSeparator;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvArabicText = itemView.findViewById(R.id.tv_arabic_text);
            tvPersianText = itemView.findViewById(R.id.tv_persian_text);
            ivSeparator = itemView.findViewById(R.id.iv_separator);


        }
    }

    private void applySetting(Context context, ViewHolder holder) {


        holder.tvArabicText.setTextSize(programSetting.getArabicTextSize());
        holder.tvPersianText.setTextSize(programSetting.getPersianTextSize());

        holder.tvArabicText.setLineSpacing(programSetting.getTextLineSpace(), programSetting.getTextLineSpace());
        holder.tvPersianText.setLineSpacing(programSetting.getTextLineSpace(), programSetting.getTextLineSpace());

        Typeface tfArabicFont= ResourcesCompat.getFont(context, programSetting.getArabicFontId());
        Typeface tfPersianFont= ResourcesCompat.getFont(context, programSetting.getArabicFontId());

        holder.tvArabicText.setTypeface(tfArabicFont);
        holder.tvPersianText.setTypeface(tfPersianFont);


        if (!programSetting.isShowTranslation())
            holder.tvPersianText.setVisibility(View.GONE);

        if (!programSetting.isShowSeparator())
            holder.ivSeparator.setVisibility(View.GONE);

        if (programSetting.isDarkTheme()) {
            holder.itemView.setBackgroundColor(Color.BLACK);
            holder.tvArabicText.setTextColor(Color.YELLOW);
            holder.tvPersianText.setTextColor(Color.WHITE);
        }

    }
}
