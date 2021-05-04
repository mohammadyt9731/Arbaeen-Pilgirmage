package com.ttp.ziaratarbaeen.fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import com.ttp.ziaratarbaeen.R;
import com.ttp.ziaratarbaeen.classes.ProgramSetting;

public class PersianFontFragment extends Fragment {

    RadioGroup rgFonts;
    RadioGroup rgStyle;

    RadioButton rbBold;
    RadioButton rbItalic;
    RadioButton rbNormal;
    RadioButton rbBoldItalic;

    SeekBar sbTextSize;
    SeekBar sbLineSpacing;

    TextView sampleText;
    ProgramSetting programSetting;

    Typeface persianFont;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_persian_font, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findViews(view);
        applySetting();
        configuration();
    }


    private void findViews(View view) {

        rgFonts = view.findViewById(R.id.rg_persian_fonts);
        rgStyle = view.findViewById(R.id.rg_persian_style);

        rbBold = view.findViewById(R.id.rb_bold_persian);
        rbItalic = view.findViewById(R.id.rb_italic_persian);
        rbNormal = view.findViewById(R.id.rb_normal_persian);
        rbBoldItalic = view.findViewById(R.id.rb_bold_italic_persian);

        sbTextSize = view.findViewById(R.id.sb_persian_text_size);
        sbLineSpacing = view.findViewById(R.id.sb_persian_line_spacing);

        sampleText = view.findViewById(R.id.tv_sample_translation_text);
    }

    private void applySetting() {

        programSetting = new ProgramSetting(getContext());

        setTypeFace();
        setStyle();

        sbTextSize.setProgress(programSetting.getPersianTextSize());
        sampleText.setTextSize(programSetting.getPersianTextSize());

        sbLineSpacing.setProgress((int) (programSetting.getPersianTextLineSpace() * 10));
        sampleText.setLineSpacing(programSetting.getPersianTextLineSpace(), programSetting.getPersianTextLineSpace());

    }

    private void setTypeFace() {

        int fontId = programSetting.getPersianFontId();
        RadioButton radioButton;

        switch (fontId) {

            case R.font.b_hamid:
                radioButton = getView().findViewById(R.id.rb_persian_font1);
                persianFont = ResourcesCompat.getFont(getActivity(), R.font.b_hamid);
                break;

            case R.font.b_nazanin:
                radioButton = getView().findViewById(R.id.rb_persian_font2);
                persianFont = ResourcesCompat.getFont(getActivity(), R.font.b_nazanin);
                break;

            case R.font.ferdosi:
                radioButton = getView().findViewById(R.id.rb_persian_font3);
                persianFont = ResourcesCompat.getFont(getActivity(), R.font.ferdosi);
                break;

            default:
                radioButton = getView().findViewById(R.id.rb_persian_font1);
                persianFont = ResourcesCompat.getFont(getActivity(), R.font.b_hamid);
                break;
        }

        radioButton.setChecked(true);
    }

    private void setStyle() {

        String textStyle = programSetting.getPersianTextStyle();

        switch (textStyle) {

            case "Normal":
                rbNormal.setChecked(true);
                sampleText.setTypeface(persianFont, Typeface.NORMAL);
                break;

            case "Italic":
                rbItalic.setChecked(true);
                sampleText.setTypeface(persianFont, Typeface.ITALIC);
                break;

            case "Bold":
                rbBold.setChecked(true);
                sampleText.setTypeface(persianFont, Typeface.BOLD);
                break;

            case "Bold_Italic":
                rbBoldItalic.setChecked(true);
                sampleText.setTypeface(persianFont, Typeface.BOLD_ITALIC);
                break;
        }
    }

    private void configuration() {

        rgFonts.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                switch (radioGroup.getCheckedRadioButtonId()) {

                    case R.id.rb_persian_font1:
                        persianFont = ResourcesCompat.getFont(getActivity(), R.font.b_hamid);
                        programSetting.setPersianFontId(R.font.b_hamid);
                        setStyle();
                        break;

                    case R.id.rb_persian_font2:
                        persianFont = ResourcesCompat.getFont(getActivity(), R.font.b_nazanin);
                        programSetting.setPersianFontId(R.font.b_nazanin);
                        setStyle();
                        break;

                    case R.id.rb_persian_font3:
                        persianFont = ResourcesCompat.getFont(getActivity(), R.font.ferdosi);
                        programSetting.setPersianFontId(R.font.ferdosi);
                        setStyle();
                        break;

                }

            }
        });

        rgStyle.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                changeTextStyle(radioGroup.getCheckedRadioButtonId());
            }
        });


        sbTextSize.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                programSetting.setPersianTextSize(i);
                sampleText.setTextSize(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        sbLineSpacing.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                float lineSpace = (float) i / 10;
                programSetting.setPersianTextLineSpace(lineSpace);
                sampleText.setLineSpacing(lineSpace, lineSpace);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    private void changeTextStyle(int radioButtonId) {

        switch (radioButtonId) {

            case R.id.rb_normal_persian:
                programSetting.setPersianTextStyle(getString(R.string.normal));
                sampleText.setTypeface(persianFont, Typeface.NORMAL);
                break;

            case R.id.rb_bold_persian:
                programSetting.setPersianTextStyle(getString(R.string.bold));
                sampleText.setTypeface(persianFont, Typeface.BOLD);
                break;

            case R.id.rb_italic_persian:
                programSetting.setPersianTextStyle(getString(R.string.italic));
                sampleText.setTypeface(persianFont, Typeface.ITALIC);
                break;

            case R.id.rb_bold_italic_persian:
                programSetting.setPersianTextStyle(getString(R.string.bold_italic));
                sampleText.setTypeface(persianFont, Typeface.BOLD_ITALIC);
                break;
        }
    }

    @Override
    public void onPause() {

        programSetting.updateSetting(getActivity());
        super.onPause();
    }


}