package com.ttp.ziaratarbaeen.fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.slider.Slider;
import com.ttp.ziaratarbaeen.R;
import com.ttp.ziaratarbaeen.utils.MyConstants;
import com.ttp.ziaratarbaeen.utils.ProgramSetting;
import com.ttp.ziaratarbaeen.databinding.FragmentSettingBinding;

public class SettingFragment extends Fragment {

    FragmentSettingBinding binding;

    ProgramSetting programSetting;

    Typeface tfArabicFont;
    Typeface tfPersianFont;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding=FragmentSettingBinding.inflate(getLayoutInflater(),container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init();
        configuration();

    }



    private void init() {

        programSetting = new ProgramSetting(getContext());

        applySetting();
    }

    private void applySetting() {

       binding.switchAutoScroll.setChecked(programSetting.isAutoScroll());
       binding.switchShowTranslation.setChecked(programSetting.isShowTranslation());
       binding.switchShowSeparator.setChecked(programSetting.isShowSeparator());
       binding.switchDarkTheme.setChecked(programSetting.isDarkTheme());

       binding.sliderArabicTextSize.setValue(programSetting.getArabicTextSize());
       binding.sliderPersianTextSize.setValue(programSetting.getPersianTextSize());

       float lineSpace=programSetting.getTextLineSpace();
       binding.sliderLineSpacing.setValue(lineSpace);

       binding.tvSampleArabicText.setTextSize(programSetting.getArabicTextSize());
       binding.tvSampleArabicText.setLineSpacing(lineSpace, lineSpace);

       binding.tvSamplePersianText.setTextSize(programSetting.getPersianTextSize());
       binding.tvSamplePersianText.setLineSpacing(lineSpace, lineSpace);

        setArabicFont();
        setPersianFont();

        setVisibility(programSetting.isShowTranslation(), programSetting.isShowSeparator());


    }

    private void setVisibility(Boolean showTranslation, Boolean showSeparator) {

        if (showTranslation != null)
            if (showTranslation)
                binding.tvSamplePersianText.setVisibility(View.VISIBLE);
            else
                binding.tvSamplePersianText.setVisibility(View.GONE);

        if (showSeparator != null)
            if (showSeparator)
                binding.ivSeparator.setVisibility(View.VISIBLE);
            else
                binding.ivSeparator.setVisibility(View.GONE);

    }

    private void setArabicFont() {

        int arabicFontId = programSetting.getArabicFontId();
        tfArabicFont = ResourcesCompat.getFont(getActivity(), arabicFontId);
        binding.tvSampleArabicText.setTypeface(tfArabicFont);

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
        binding.tvSamplePersianText.setTypeface(tfPersianFont);
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

        binding.switchAutoScroll.setOnCheckedChangeListener(checkedChangeListener);
        binding.switchShowTranslation.setOnCheckedChangeListener(checkedChangeListener);
        binding.switchShowSeparator.setOnCheckedChangeListener(checkedChangeListener);
        binding.switchDarkTheme.setOnCheckedChangeListener(checkedChangeListener);

        binding.rgArabicFonts.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
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
        binding.rgPersianFonts.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
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

        binding.sliderArabicTextSize.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                programSetting.setArabicTextSize((int) value);
                binding.tvSampleArabicText.setTextSize(value);

            }
        });
        binding.sliderPersianTextSize.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                programSetting.setPersianTextSize((int) value);
                binding.tvSamplePersianText.setTextSize(value);

            }
        });
        binding.sliderLineSpacing.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {

                programSetting.setTextLineSpace(value);
                binding.tvSampleArabicText.setLineSpacing(value, value);
                binding.tvSamplePersianText.setLineSpacing(value, value);

            }
        });

    }


    @Override
    public void onPause() {

        programSetting.updateSetting(getActivity());
        super.onPause();
    }
}
