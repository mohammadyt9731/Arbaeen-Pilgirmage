package com.ttp.ziaratarbaeen.fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.slider.Slider;
import com.ttp.ziaratarbaeen.R;
import com.ttp.ziaratarbaeen.classes.ProgramSetting;

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

    Typeface arabicFont;
    Typeface persianFont;

    TextView tvSampleArabicText;
    TextView tvSamplePersianText;
    ImageView ivSeparator;


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
        sliderLineSpacing.setValue(programSetting.getTextLineSpace());

        float lineSpace=programSetting.getTextLineSpace()/10;

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
        RadioButton rbArabicFont;

        switch (arabicFontId) {

            case R.font.homa:
                rbArabicFont = getView().findViewById(R.id.rb_arabic_font1);
                arabicFont = ResourcesCompat.getFont(getActivity(), R.font.homa);

                break;
            case R.font.morvarid:
                rbArabicFont = getView().findViewById(R.id.rb_arabic_font2);
                arabicFont = ResourcesCompat.getFont(getActivity(), R.font.morvarid);
                break;
            case R.font.nabi:
                rbArabicFont = getView().findViewById(R.id.rb_arabic_font3);
                arabicFont = ResourcesCompat.getFont(getActivity(), R.font.nabi);

                break;
            default:
                rbArabicFont = getView().findViewById(R.id.rb_arabic_font1);
                arabicFont = ResourcesCompat.getFont(getActivity(), R.font.nabi);
                break;


        }
        tvSampleArabicText.setTypeface(arabicFont);
        rbArabicFont.setChecked(true);

    }

    private void setPersianFont() {

        int PersianFontId = programSetting.getPersianFontId();
        RadioButton rbPersianFont;

        switch (PersianFontId) {

            case R.font.b_hamid:
                rbPersianFont = getView().findViewById(R.id.rb_persian_font1);
                persianFont = ResourcesCompat.getFont(getActivity(), R.font.b_hamid);
                break;

            case R.font.b_nazanin:
                rbPersianFont = getView().findViewById(R.id.rb_persian_font2);
                persianFont = ResourcesCompat.getFont(getActivity(), R.font.b_nazanin);
                break;

            case R.font.ferdosi:
                rbPersianFont = getView().findViewById(R.id.rb_persian_font3);
                persianFont = ResourcesCompat.getFont(getActivity(), R.font.ferdosi);
                break;

            default:
                rbPersianFont = getView().findViewById(R.id.rb_persian_font1);
                persianFont = ResourcesCompat.getFont(getActivity(), R.font.b_hamid);
                break;
        }

        tvSamplePersianText.setTypeface(persianFont);
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


        rgPersianFonts.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                switch (radioGroup.getCheckedRadioButtonId()) {

                    case R.id.rb_persian_font1:
                        persianFont = ResourcesCompat.getFont(getActivity(), R.font.b_hamid);
                        programSetting.setPersianFontId(R.font.b_hamid);
                        setPersianFont();
                        break;

                    case R.id.rb_persian_font2:
                        persianFont = ResourcesCompat.getFont(getActivity(), R.font.b_nazanin);
                        programSetting.setPersianFontId(R.font.b_nazanin);
                        setPersianFont();
                        break;

                    case R.id.rb_persian_font3:
                        persianFont = ResourcesCompat.getFont(getActivity(), R.font.ferdosi);
                        programSetting.setPersianFontId(R.font.ferdosi);
                        setPersianFont();
                        break;

                }

            }
        });
        rgArabicFonts.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                switch (radioGroup.getCheckedRadioButtonId()) {

                    case R.id.rb_arabic_font1:
                        arabicFont = ResourcesCompat.getFont(getActivity(), R.font.homa);
                        programSetting.setArabicFontId(R.font.homa);
                        setArabicFont();
                        break;

                    case R.id.rb_arabic_font2:
                        arabicFont = ResourcesCompat.getFont(getActivity(), R.font.morvarid);
                        programSetting.setArabicFontId(R.font.morvarid);
                        setArabicFont();
                        break;

                    case R.id.rb_arabic_font3:
                        arabicFont = ResourcesCompat.getFont(getActivity(), R.font.nabi);
                        programSetting.setArabicFontId(R.font.nabi);
                        setArabicFont();
                        break;

                }

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
                float lineSpace = value / 10;
                programSetting.setTextLineSpace(lineSpace);
                tvSampleArabicText.setLineSpacing(lineSpace, lineSpace);
                tvSamplePersianText.setLineSpacing(lineSpace, lineSpace);

            }
        });

    }


    @Override
    public void onPause() {

        programSetting.updateSetting(getActivity());
        super.onPause();
    }
}
