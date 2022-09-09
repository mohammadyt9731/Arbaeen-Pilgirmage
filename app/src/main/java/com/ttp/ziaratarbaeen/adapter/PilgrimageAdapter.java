package com.ttp.ziaratarbaeen.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.ttp.ziaratarbaeen.R;
import com.ttp.ziaratarbaeen.utils.ArbaeenMediaPlayer;
import com.ttp.ziaratarbaeen.utils.ProgramSetting;
import com.ttp.ziaratarbaeen.interfaces.CallBackStartMedia;

import java.util.ArrayList;
import java.util.Arrays;

public class PilgrimageAdapter extends RecyclerView.Adapter<PilgrimageAdapter.ViewHolder> {

    private ArrayList<String> arabicTextList;
    private ArrayList<String> persianTextList;

    private  MediaPlayer mpPilgrimage;
    private final CallBackStartMedia callBackStartMedia;

    private ProgramSetting programSetting;
    private Context context;


    private int currentIndex = -1;


    public PilgrimageAdapter(Context context, CallBackStartMedia callBackStartMedia) {

        this.context = context;
        this.callBackStartMedia=callBackStartMedia;

        init();
    }

    private void init() {

        mpPilgrimage=ArbaeenMediaPlayer.getMediaPlayer(context);

        arabicTextList = new ArrayList<>();
        persianTextList = new ArrayList<>();

        arabicTextList.addAll(Arrays.asList(context.getResources().getStringArray(R.array.arabic_text_list)));
        persianTextList.addAll(Arrays.asList(context.getResources().getStringArray(R.array.persian_text_list)));

        programSetting = new ProgramSetting(context);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.item_pilgrimage_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PilgrimageAdapter.ViewHolder holder,
                                 @SuppressLint("RecyclerView") int position) {

        applySetting(holder);

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
                mpPilgrimage.seekTo(ArbaeenMediaPlayer.getCurrentPosition(currentIndex) * 1000);

                if(!mpPilgrimage.isPlaying()){
                    mpPilgrimage.start();
                    callBackStartMedia.startMedia();
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

            tvArabicText = itemView.findViewById(R.id.tv_arabicText_itemPilgrimageList);
            tvPersianText = itemView.findViewById(R.id.tv_persianText_itemPilgrimageList);
            ivSeparator = itemView.findViewById(R.id.iv_separator_fragmentSetting);


        }
    }

    private void applySetting(ViewHolder holder) {


        holder.tvArabicText.setTextSize(programSetting.getArabicTextSize());
        holder.tvPersianText.setTextSize(programSetting.getPersianTextSize());

        holder.tvArabicText.setLineSpacing(programSetting.getTextLineSpace(), programSetting.getTextLineSpace());
        holder.tvPersianText.setLineSpacing(programSetting.getTextLineSpace(), programSetting.getTextLineSpace());

        Typeface tfArabicFont= ResourcesCompat.getFont(context, programSetting.getArabicFontId());
        Typeface tfPersianFont= ResourcesCompat.getFont(context, programSetting.getPersianFontId());

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
