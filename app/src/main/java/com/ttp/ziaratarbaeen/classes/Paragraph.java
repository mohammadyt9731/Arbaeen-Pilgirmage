package com.ttp.ziaratarbaeen.classes;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;

import com.ttp.ziaratarbaeen.R;

public class Paragraph {

    TextView tvArabicText;
    TextView tvPersianText;
    ImageView imSeparator;

    ContextThemeWrapper arabicTextStyle;
    ContextThemeWrapper persianTextStyle;
    ContextThemeWrapper separatorStyle;

    ProgramSetting programSetting;

    Typeface arabicFont;
    Typeface persianFont;

    Context context;

    public Paragraph(Context context) {

        this.context = context;

        arabicTextStyle = new ContextThemeWrapper(context, R.style.arabic_text_style);
        persianTextStyle = new ContextThemeWrapper(context, R.style.persian_text_style);
        separatorStyle = new ContextThemeWrapper(context, R.style.separator_style);

        tvArabicText = new TextView(arabicTextStyle, null, 0);
        tvPersianText = new TextView(persianTextStyle, null, 0);
        imSeparator = new ImageView(separatorStyle, null, 0);
        imSeparator.setLayoutParams(new LinearLayout.LayoutParams(MyConstants.SEPARATOR_WIDTH, MyConstants.SEPARATOR_HEIGHT));

        applySetting(context);
    }


    private void applySetting(Context context) {

        programSetting = new ProgramSetting(context);

        tvArabicText.setTextSize(programSetting.getArabicTextSize());
        tvPersianText.setTextSize(programSetting.getPersianTextSize());

        tvArabicText.setLineSpacing(programSetting.getArabicTextLineSpace(), programSetting.getArabicTextLineSpace());
        tvPersianText.setLineSpacing(programSetting.getPersianTextLineSpace(), programSetting.getPersianTextLineSpace());

        initFonts();
        setTextStyle(tvArabicText, programSetting.getArabicTextStyle(), arabicFont);
        setTextStyle(tvPersianText, programSetting.getPersianTextStyle(), persianFont);


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

    public void setTextSize(int textSize, int translationTextSize) {

        tvArabicText.setTextSize(textSize);
        tvPersianText.setTextSize(translationTextSize);

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

    public TextView getTvPersianText() {
        return tvPersianText;
    }

    public ImageView getImSeparator() {
        return imSeparator;
    }

}
