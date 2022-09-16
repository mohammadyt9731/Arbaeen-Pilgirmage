package com.ttp.ziaratarbaeen.utils;

import android.content.Context;

public class ProgramSetting {

    boolean autoScroll;
    boolean showTranslation;
    boolean showSeparator;
    boolean screenOn;

    int arabicTextSize;
    int persianTextSize;

    float textLineSpace;

    int persianFontId;
    int arabicFontId;


    public ProgramSetting(Context context) {

        ProgramSetting savedSetting = MySharedPreference.getInstance(context).getProgramSetting();

        if (savedSetting == null) {

            autoScroll = MyConstants.AUTO_SCROLL;
            showTranslation = MyConstants.SHOW_TRANSLATION;
            showSeparator = MyConstants.SHOW_SEPARATOR;
            screenOn = MyConstants.DARK_THEME;

            arabicTextSize = MyConstants.DEFAULT_ARABIC_TEXT_SIZE;
            persianTextSize = MyConstants.DEFAULT_PERSIAN_TEXT_SIZE;

            textLineSpace = MyConstants.DEFAULT_TEXT_LINE_SPACE;

            arabicFontId = MyConstants.DEFAULT_ARABIC_FONT_ID;
            persianFontId = MyConstants.DEFAULT_PERSIAN_FONT_ID;

            updateSetting(context);

        } else {


            autoScroll = savedSetting.isAutoScroll();
            showTranslation = savedSetting.isShowTranslation();
            showSeparator = savedSetting.isShowSeparator();
            screenOn = savedSetting.isScreenOn();

            arabicTextSize = savedSetting.getArabicTextSize();
            persianTextSize = savedSetting.getPersianTextSize();

            textLineSpace = savedSetting.getTextLineSpace();

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

    public boolean isScreenOn() {
        return screenOn;
    }


    public int getArabicTextSize() {
        return arabicTextSize;
    }

    public int getPersianTextSize() {
        return persianTextSize;
    }


    public float getTextLineSpace() {
        return textLineSpace;
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

    public void setScreenOn(boolean screenOn) {
        this.screenOn = screenOn;
    }

    public void setArabicTextSize(int arabicTextSize) {
        this.arabicTextSize = arabicTextSize;
    }

    public void setPersianTextSize(int persianTextSize) {
        this.persianTextSize = persianTextSize;
    }

    public void setTextLineSpace(float textLineSpace) {
        this.textLineSpace = textLineSpace;
    }

    public void setPersianFontId(int persianFontId) {
        this.persianFontId = persianFontId;
    }

    public void setArabicFontId(int arabicFontId) {
        this.arabicFontId = arabicFontId;
    }
}
