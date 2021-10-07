package com.ttp.ziaratarbaeen.classes;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;

import com.ttp.ziaratarbaeen.R;

public class ParagraphView {

    View rootView;

    TextView tvArabicText;
    TextView tvPersianText;
    ImageView imSeparator;

    ProgramSetting programSetting;

    Typeface tfArabicFont;
    Typeface tfPersianFont;

    Context context;

    public ParagraphView(Activity context) {

        this.context = context;

        findViews();


        applySetting(context);
    }

    private void findViews(){
        rootView= LayoutInflater.from(context).inflate(R.layout.view_paragraph,null,false);

        tvArabicText =rootView.findViewById(R.id.tv_arabic_text);
        tvPersianText = rootView.findViewById(R.id.tv_persian_text);
        imSeparator = rootView.findViewById(R.id.iv_separator);

    }


    private void applySetting(Context context) {

        programSetting = new ProgramSetting(context);

        tvArabicText.setTextSize(programSetting.getArabicTextSize());
        tvPersianText.setTextSize(programSetting.getPersianTextSize());

        tvArabicText.setLineSpacing(programSetting.getTextLineSpace(), programSetting.getTextLineSpace());
        tvPersianText.setLineSpacing(programSetting.getTextLineSpace(), programSetting.getTextLineSpace());


        tfArabicFont = ResourcesCompat.getFont(context, programSetting.getArabicFontId());
        tfPersianFont = ResourcesCompat.getFont(context, programSetting.getArabicFontId());

        tvArabicText.setTypeface(tfArabicFont);
        tvPersianText.setTypeface(tfPersianFont);


        if (!programSetting.isShowTranslation())
            tvPersianText.setVisibility(View.GONE);

        if (!programSetting.isShowSeparator())
            imSeparator.setVisibility(View.GONE);

        if (programSetting.isDarkTheme()) {
            tvArabicText.setTextColor(Color.YELLOW);
            tvPersianText.setTextColor(Color.WHITE);
        }

    }


    public void setText(String pilgrimageText, String translationText) {

        tvArabicText.setText(pilgrimageText);
        tvPersianText.setText(translationText);

    }

    public void setTextSize(int arabicTextSize, int persianTextSize) {

        tvArabicText.setTextSize(arabicTextSize);
        tvPersianText.setTextSize(persianTextSize);

        programSetting.setArabicTextSize(arabicTextSize);
        programSetting.setPersianTextSize(persianTextSize);

        programSetting.updateSetting(context);


    }


    public TextView getTvArabicText() {
        return tvArabicText;
    }

    public View getRootView() {
        return rootView;
    }
}
