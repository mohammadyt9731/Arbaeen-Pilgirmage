package com.ttp.ziaratarbaeen.fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.slider.Slider;
import com.ttp.ziaratarbaeen.R;
import com.ttp.ziaratarbaeen.classes.MyConstants;
import com.ttp.ziaratarbaeen.classes.ProgramSetting;

import ir.tapsell.plus.TapsellPlusBannerType;

public class SettingFragment extends Fragment {

    ProgramSetting programSetting;


    Switch switchAutoScroll;
    Switch switchShowTranslation;
    Switch switchShowSeparator;
    Switch switchDarkTheme;

    RadioGroup rgArabicFonts;
    RadioGroup rgPersianFonts;

    Slider sliderArabicTextSize;
    Slider sliderPersianTextSize;
    Slider sliderLineSpacing;

    Typeface tfArabicFont;
    Typeface tfPersianFont;

    TextView tvSampleArabicText;
    TextView tvSamplePersianText;
    ImageView ivSeparator;

    RelativeLayout rlAdvertising;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_setting, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findViews(view);
        init();
        configuration();

    }



    private void findViews(View view) {

        switchAutoScroll = view.findViewById(R.id.switch_auto_scroll);
        switchShowTranslation = view.findViewById(R.id.switch_show_translation);
        switchShowSeparator = view.findViewById(R.id.switch_show_separator);
        switchDarkTheme = view.findViewById(R.id.switch_dark_theme);

        rgArabicFonts = view.findViewById(R.id.rg_arabic_fonts);
        rgPersianFonts = view.findViewById(R.id.rg_persian_fonts);

        sliderArabicTextSize = view.findViewById(R.id.slider_arabic_text_size);
        sliderPersianTextSize = view.findViewById(R.id.slider_persian_text_size);
        sliderLineSpacing = view.findViewById(R.id.slider_line_spacing);

        tvSampleArabicText = view.findViewById(R.id.tv_sample_arabic_text);
        tvSamplePersianText = view.findViewById(R.id.tv_sample_persian_text);
        ivSeparator = view.findViewById(R.id.iv_separator);

        rlAdvertising=view.findViewById(R.id.rl_ad_setting);


    }

    private void init() {

        programSetting = new ProgramSetting(getContext());

        applySetting();

    }

    private void applySetting() {

        switchAutoScroll.setChecked(programSetting.isAutoScroll());
        switchShowTranslation.setChecked(programSetting.isShowTranslation());
        switchShowSeparator.setChecked(programSetting.isShowSeparator());
        switchDarkTheme.setChecked(programSetting.isDarkTheme());

        sliderArabicTextSize.setValue(programSetting.getArabicTextSize());
        sliderPersianTextSize.setValue(programSetting.getPersianTextSize());

        float lineSpace=programSetting.getTextLineSpace();
        sliderLineSpacing.setValue(lineSpace);

        tvSampleArabicText.setTextSize(programSetting.getArabicTextSize());
        tvSampleArabicText.setLineSpacing(lineSpace, lineSpace);

        tvSamplePersianText.setTextSize(programSetting.getPersianTextSize());
        tvSamplePersianText.setLineSpacing(lineSpace, lineSpace);

        setArabicFont();
        setPersianFont();

        setVisibility(programSetting.isShowTranslation(), programSetting.isShowSeparator());


    }

    private void setVisibility(Boolean showTranslation, Boolean showSeparator) {

        if (showTranslation != null)
            if (showTranslation)
                tvSamplePersianText.setVisibility(View.VISIBLE);
            else
                tvSamplePersianText.setVisibility(View.GONE);

        if (showSeparator != null)
            if (showSeparator)
                ivSeparator.setVisibility(View.VISIBLE);
            else
                ivSeparator.setVisibility(View.GONE);

    }

    private void setArabicFont() {

        int arabicFontId = programSetting.getArabicFontId();
        tfArabicFont = ResourcesCompat.getFont(getActivity(), arabicFontId);
        tvSampleArabicText.setTypeface(tfArabicFont);

        RadioButton rbArabicFont;

        switch (arabicFontId) {

            case MyConstants.ARABIC_FONT_2:
                rbArabicFont = getView().findViewById(R.id.rb_arabic_font_2);
                break;

            case MyConstants.ARABIC_FONT_3:
                rbArabicFont = getView().findViewById(R.id.rb_arabic_font_3);
                break;

            default:
                rbArabicFont = getView().findViewById(R.id.rb_arabic_font_1);
                break;

        }

        rbArabicFont.setChecked(true);

    }

    private void setPersianFont() {

        int persianFontId = programSetting.getPersianFontId();
        tfPersianFont = ResourcesCompat.getFont(getActivity(),persianFontId);
        tvSamplePersianText.setTypeface(tfPersianFont);
        RadioButton rbPersianFont;

        switch (persianFontId) {

            case  MyConstants.PERSIAN_FONT_2:
                rbPersianFont = getView().findViewById(R.id.rb_persian_font_2);
                break;

            case  MyConstants.PERSIAN_FONT_3:
                rbPersianFont = getView().findViewById(R.id.rb_persian_font_3);
                break;

            default:
                rbPersianFont = getView().findViewById(R.id.rb_persian_font_1);
                break;
        }

        rbPersianFont.setChecked(true);
    }

    private void configuration() {

        CompoundButton.OnCheckedChangeListener checkedChangeListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                switch (compoundButton.getId()) {

                    case R.id.switch_auto_scroll:
                        programSetting.setAutoScroll(isChecked);
                        break;

                    case R.id.switch_show_translation:
                        programSetting.setShowTranslation(isChecked);
                        setVisibility(isChecked, null);
                        break;

                    case R.id.switch_show_separator:
                        programSetting.setShowSeparator(isChecked);
                        setVisibility(null, isChecked);
                        break;
                    case R.id.switch_dark_theme:
                        programSetting.setDarkTheme(isChecked);
                        break;


                }

                programSetting.updateSetting(getActivity());
            }
        };

        switchAutoScroll.setOnCheckedChangeListener(checkedChangeListener);
        switchShowTranslation.setOnCheckedChangeListener(checkedChangeListener);
        switchShowSeparator.setOnCheckedChangeListener(checkedChangeListener);
        switchDarkTheme.setOnCheckedChangeListener(checkedChangeListener);

        rgArabicFonts.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                switch (radioGroup.getCheckedRadioButtonId()) {

                    case R.id.rb_arabic_font_1:
                        programSetting.setArabicFontId(MyConstants.ARABIC_FONT_1);
                        break;

                    case R.id.rb_arabic_font_2:
                        programSetting.setArabicFontId(MyConstants.ARABIC_FONT_2);
                        break;

                    case R.id.rb_arabic_font_3:
                        programSetting.setArabicFontId(MyConstants.ARABIC_FONT_3);
                        break;

                }
                setArabicFont();
            }
        });
        rgPersianFonts.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                switch (radioGroup.getCheckedRadioButtonId()) {

                    case R.id.rb_persian_font_1:
                        programSetting.setPersianFontId(MyConstants.PERSIAN_FONT_1);
                        break;

                    case R.id.rb_persian_font_2:
                        programSetting.setPersianFontId(MyConstants.PERSIAN_FONT_2);
                        break;

                    case R.id.rb_persian_font_3:
                        programSetting.setPersianFontId(MyConstants.PERSIAN_FONT_3);
                        break;
                }
                setPersianFont();
            }
        });

        sliderArabicTextSize.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                programSetting.setArabicTextSize((int) value);
                tvSampleArabicText.setTextSize(value);

            }
        });
        sliderPersianTextSize.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                programSetting.setPersianTextSize((int) value);
                tvSamplePersianText.setTextSize(value);

            }
        });
        sliderLineSpacing.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {

                programSetting.setTextLineSpace(value);
                tvSampleArabicText.setLineSpacing(value, value);
                tvSamplePersianText.setLineSpacing(value, value);

            }
        });

    }


    @Override
    public void onPause() {

        programSetting.updateSetting(getActivity());
        super.onPause();
    }
}
