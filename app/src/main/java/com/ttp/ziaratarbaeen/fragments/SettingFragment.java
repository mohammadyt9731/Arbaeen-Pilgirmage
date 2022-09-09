package com.ttp.ziaratarbaeen.fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import com.ttp.ziaratarbaeen.R;
import com.ttp.ziaratarbaeen.databinding.FragmentSettingBinding;
import com.ttp.ziaratarbaeen.utils.MyConstants;
import com.ttp.ziaratarbaeen.utils.ProgramSetting;

public class SettingFragment extends Fragment {

    FragmentSettingBinding binding;

    ProgramSetting programSetting;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSettingBinding.inflate(getLayoutInflater(), container, false);

        init();
        applySetting();
        setOnclick();

        return binding.getRoot();
    }


    private void init() {

        programSetting = new ProgramSetting(getContext());
    }

    private void applySetting() {

        binding.switchAutoScrollFragmentSetting.setChecked(programSetting.isAutoScroll());
        binding.switchShowTranslationFragmentSetting.setChecked(programSetting.isShowTranslation());
        binding.switchShowSeparatorFragmentSetting.setChecked(programSetting.isShowSeparator());
        binding.switchDarkThemeFragmentSetting.setChecked(programSetting.isDarkTheme());

        binding.sliderArabicTextSizeFragmentSetting.setValue(programSetting.getArabicTextSize());
        binding.sliderPersianTextSizeFragmentSetting.setValue(programSetting.getPersianTextSize());
        binding.sliderLineSpacingFragmentSetting.setValue(programSetting.getTextLineSpace());

        binding.tvSampleArabicTextFragmentSetting.setTextSize(programSetting.getArabicTextSize());
        binding.tvSampleArabicTextFragmentSetting.setLineSpacing(programSetting.getTextLineSpace(), programSetting.getTextLineSpace());

        binding.tvSamplePersianTextFragmentSetting.setTextSize(programSetting.getPersianTextSize());
        binding.tvSamplePersianTextFragmentSetting.setLineSpacing(programSetting.getTextLineSpace(), programSetting.getTextLineSpace());

        setArabicFont();
        setPersianFont();

        setVisibility(programSetting.isShowTranslation(), programSetting.isShowSeparator());


    }

    private void setVisibility(Boolean showTranslation, Boolean showSeparator) {

        if (showTranslation != null)
            if (showTranslation)
                binding.tvSamplePersianTextFragmentSetting.setVisibility(View.VISIBLE);
            else
                binding.tvSamplePersianTextFragmentSetting.setVisibility(View.GONE);

        if (showSeparator != null)
            if (showSeparator)
                binding.ivSeparatorFragmentSetting.setVisibility(View.VISIBLE);
            else
                binding.ivSeparatorFragmentSetting.setVisibility(View.GONE);

    }

    private void setArabicFont() {

        int arabicFontId = programSetting.getArabicFontId();
        Typeface tfArabicFont = ResourcesCompat.getFont(requireActivity(), arabicFontId);
        binding.tvSampleArabicTextFragmentSetting.setTypeface(tfArabicFont);


        switch (arabicFontId) {

            case MyConstants.ARABIC_FONT_2:
                binding.rbArabicFont2FragmentSetting.setChecked(true);
                break;

            case MyConstants.ARABIC_FONT_3:
                binding.rbArabicFont3FragmentSetting.setChecked(true);
                break;

            default:
                binding.rbArabicFont1FragmentSetting.setChecked(true);
                break;

        }


    }

    private void setPersianFont() {

        int persianFontId = programSetting.getPersianFontId();
        Typeface tfPersianFont = ResourcesCompat.getFont(requireActivity(), persianFontId);
        binding.tvSamplePersianTextFragmentSetting.setTypeface(tfPersianFont);


        switch (persianFontId) {

            case MyConstants.PERSIAN_FONT_2:
                binding.rbPersianFont2FragmentSetting.setChecked(true);
                break;

            case MyConstants.PERSIAN_FONT_3:
                binding.rbPersianFont3FragmentSetting.setChecked(true);
                break;

            default:
                binding.rbPersianFont1FragmentSetting.setChecked(true);
                break;
        }


    }

    private void setOnclick() {

        CompoundButton.OnCheckedChangeListener checkedChangeListener = (compoundButton, isChecked) -> {

            switch (compoundButton.getId()) {

                case R.id.switch_autoScroll_fragmentSetting:
                    programSetting.setAutoScroll(isChecked);
                    break;

                case R.id.switch_showTranslation_fragmentSetting:
                    programSetting.setShowTranslation(isChecked);
                    setVisibility(isChecked, null);
                    break;

                case R.id.switch_showSeparator_fragmentSetting:
                    programSetting.setShowSeparator(isChecked);
                    setVisibility(null, isChecked);
                    break;
                case R.id.switch_darkTheme_fragmentSetting:
                    programSetting.setDarkTheme(isChecked);
                    break;


            }

            programSetting.updateSetting(getActivity());
        };

        binding.switchAutoScrollFragmentSetting.setOnCheckedChangeListener(checkedChangeListener);
        binding.switchShowTranslationFragmentSetting.setOnCheckedChangeListener(checkedChangeListener);
        binding.switchShowSeparatorFragmentSetting.setOnCheckedChangeListener(checkedChangeListener);
        binding.switchDarkThemeFragmentSetting.setOnCheckedChangeListener(checkedChangeListener);

        binding.rgArabicFontFragmentSetting.setOnCheckedChangeListener((radioGroup, i) -> {

            switch (radioGroup.getCheckedRadioButtonId()) {

                case R.id.rb_arabicFont1_fragmentSetting:
                    programSetting.setArabicFontId(MyConstants.ARABIC_FONT_1);
                    break;

                case R.id.rb_arabicFont2_fragmentSetting:
                    programSetting.setArabicFontId(MyConstants.ARABIC_FONT_2);
                    break;

                case R.id.rb_arabicFont3_fragmentSetting:
                    programSetting.setArabicFontId(MyConstants.ARABIC_FONT_3);
                    break;

            }
            setArabicFont();
        });
        binding.rgPersianFontFragmentSetting.setOnCheckedChangeListener((radioGroup, i) -> {

            switch (radioGroup.getCheckedRadioButtonId()) {

                case R.id.rb_persianFont1_fragmentSetting:
                    programSetting.setPersianFontId(MyConstants.PERSIAN_FONT_1);
                    break;

                case R.id.rb_persianFont2_fragmentSetting:
                    programSetting.setPersianFontId(MyConstants.PERSIAN_FONT_2);
                    break;

                case R.id.rb_persianFont3_fragmentSetting:
                    programSetting.setPersianFontId(MyConstants.PERSIAN_FONT_3);
                    break;
            }
            setPersianFont();
        });

        binding.sliderArabicTextSizeFragmentSetting.addOnChangeListener((slider, value, fromUser) -> {
            programSetting.setArabicTextSize((int) value);
            binding.tvSampleArabicTextFragmentSetting.setTextSize(value);

        });
        binding.sliderPersianTextSizeFragmentSetting.addOnChangeListener((slider, value, fromUser) -> {
            programSetting.setPersianTextSize((int) value);
            binding.tvSamplePersianTextFragmentSetting.setTextSize(value);

        });
        binding.sliderLineSpacingFragmentSetting.addOnChangeListener((slider, value, fromUser) -> {

            programSetting.setTextLineSpace(value);
            binding.tvSampleArabicTextFragmentSetting.setLineSpacing(value, value);
            binding.tvSamplePersianTextFragmentSetting.setLineSpacing(value, value);

        });

    }


    @Override
    public void onPause() {

        programSetting.updateSetting(getActivity());
        super.onPause();
    }
}
