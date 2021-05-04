package com.ttp.ziaratarbaeen.classes;

import android.content.Context;
import com.ttp.ziaratarbaeen.R;

public class ProgramSetting {

    boolean autoScroll;
    boolean showTranslation;
    boolean showSeparator;
    boolean darkTheme;

    int arabicTextSize;
    int persianTextSize;

    float arabicTextLineSpace;
    float persianTextLineSpace;

    String persianTextStyle;
    String arabicTextStyle;

    int persianFontId;
    int arabicFontId;


    public ProgramSetting(Context context) {

        ProgramSetting savedSetting = MySharedPreference.getInstance(context).getProgramSetting();

        if (savedSetting == null) {
            autoScroll = false;
            showTranslation = true;
            showSeparator = true;
            darkTheme = false;

            arabicTextSize = MyConstants.DEFAULT_ARABIC_TEXT_SIZE;
            persianTextSize = MyConstants.DEFAULT_PERSIAN_TEXT_SIZE;

            arabicTextLineSpace = MyConstants.DEFAULT_ARABIC_LINE_SPACE;
            persianTextLineSpace = MyConstants.DEFAULT_PERSIAN_LINE_SPACE;

            arabicTextStyle = context.getString(R.string.normal);
            persianTextStyle = context.getString(R.string.normal);

            arabicFontId = R.font.nabi;
            persianFontId = R.font.b_nazanin;

            updateSetting(context);

        } else {


            autoScroll = savedSetting.isAutoScroll();
            showTranslation = savedSetting.isShowTranslation();
            showSeparator = savedSetting.isShowSeparator();
            darkTheme = savedSetting.isDarkTheme();

            arabicTextSize = savedSetting.getArabicTextSize();
            persianTextSize = savedSetting.getPersianTextSize();

            arabicTextLineSpace = savedSetting.getArabicTextLineSpace();
            persianTextLineSpace = savedSetting.getPersianTextLineSpace();

            arabicTextStyle = savedSetting.getArabicTextStyle();
            persianTextStyle = savedSetting.getPersianTextStyle();

            arabicFontId = savedSetting.getArabicFontId();
            persianFontId = savedSetting.getPersianFontId();

        }


    }

    public void updateSetting(Context context) {
        MySharedPreference.getInstance(context).putProgramSetting(this);
    }
    /////////////////////////////////////////////////////////////////////////////////////

    public boolean isAutoScroll() {
        return autoScroll;
    }

    public boolean isShowTranslation() {
        return showTranslation;
    }

    public boolean isShowSeparator() {
        return showSeparator;
    }

    public boolean isDarkTheme() {
        return darkTheme;
    }

    public int getArabicTextSize() {
        return arabicTextSize;
    }

    public int getPersianTextSize() {
        return persianTextSize;
    }

    public float getPersianTextLineSpace() {
        return persianTextLineSpace;
    }

    public float getArabicTextLineSpace() {
        return arabicTextLineSpace;
    }

    public String getPersianTextStyle() {
        return persianTextStyle;
    }

    public String getArabicTextStyle() {
        return arabicTextStyle;
    }

    public int getPersianFontId() {
        return persianFontId;
    }

    public int getArabicFontId() {
        return arabicFontId;
    }

    public void setAutoScroll(boolean autoScroll) {
        this.autoScroll = autoScroll;
    }

    public void setShowTranslation(boolean showTranslation) {
        this.showTranslation = showTranslation;
    }

    public void setShowSeparator(boolean showSeparator) {
        this.showSeparator = showSeparator;
    }

    public void setDarkTheme(boolean darkTheme) {
        this.darkTheme = darkTheme;
    }

    public void setArabicTextSize(int arabicTextSize) {
        this.arabicTextSize = arabicTextSize;
    }

    public void setPersianTextSize(int persianTextSize) {
        this.persianTextSize = persianTextSize;
    }

    public void setPersianTextLineSpace(float persianTextLineSpace) {
        this.persianTextLineSpace = persianTextLineSpace;
    }

    public void setArabicTextLineSpace(float arabicTextLineSpace) {
        this.arabicTextLineSpace = arabicTextLineSpace;
    }

    public void setPersianTextStyle(String persianTextStyle) {

        this.persianTextStyle = persianTextStyle;
    }

    public void setArabicTextStyle(String arabicTextStyle) {
        this.arabicTextStyle = arabicTextStyle;
    }

    public void setPersianFontId(int persianFontId) {
        this.persianFontId = persianFontId;
    }

    public void setArabicFontId(int arabicFontId) {
        this.arabicFontId = arabicFontId;
    }
}
