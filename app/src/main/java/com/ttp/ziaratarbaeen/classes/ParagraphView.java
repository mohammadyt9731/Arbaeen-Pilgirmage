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

    Typeface arabicFont;
    Typeface persianFont;

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

        initFonts();

        tvArabicText.setTypeface(arabicFont);
        tvPersianText.setTypeface(arabicFont);
//        setTextStyle(tvArabicText, programSetting.getArabicTextStyle(), arabicFont);
//        setTextStyle(tvPersianText, programSetting.getPersianTextStyle(), persianFont);


        if (!programSetting.isShowTranslation())
            tvPersianText.setVisibility(View.GONE);

        if (!programSetting.isShowSeparator())
            imSeparator.setVisibility(View.GONE);

        if (programSetting.isDarkTheme()) {
            tvArabicText.setTextColor(Color.YELLOW);
            tvPersianText.setTextColor(Color.WHITE);
        }

    }

    private void initFonts() {

        int arabicFontId = programSetting.getArabicFontId();
        int persianFontId = programSetting.getPersianFontId();

        switch (arabicFontId) {

            case R.font.homa:
                arabicFont = ResourcesCompat.getFont(context, R.font.homa);
                break;
            case R.font.morvarid:
                arabicFont = ResourcesCompat.getFont(context, R.font.morvarid);
                break;
            case R.font.nabi:
                arabicFont = ResourcesCompat.getFont(context, R.font.nabi);
                break;
            default:
                arabicFont = ResourcesCompat.getFont(context, R.font.nabi);
                break;
        }
        switch (persianFontId) {

            case R.font.b_hamid:
                persianFont = ResourcesCompat.getFont(context, R.font.b_hamid);
                break;
            case R.font.b_nazanin:
                persianFont = ResourcesCompat.getFont(context, R.font.b_nazanin);
                break;
            case R.font.ferdosi:
                persianFont = ResourcesCompat.getFont(context, R.font.ferdosi);
                break;
            default:
                persianFont = ResourcesCompat.getFont(context, R.font.b_hamid);
                break;
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

    private void setTextStyle(TextView textView, String textStyle, Typeface font) {

        switch (textStyle) {

            case "Normal":
                textView.setTypeface(font, Typeface.NORMAL);
                break;

            case "Italic":
                textView.setTypeface(font, Typeface.ITALIC);
                break;

            case "Bold":
                textView.setTypeface(font, Typeface.BOLD);
                break;

            case "Bold_Italic":
                textView.setTypeface(font, Typeface.BOLD_ITALIC);
                break;
        }


    }


    public TextView getTvArabicText() {
        return tvArabicText;
    }

    public View getRootView() {
        return rootView;
    }
}
