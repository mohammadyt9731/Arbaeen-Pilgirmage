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

public class ArabicFontFragment extends Fragment {


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

    Typeface arabicFont;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_arabic_font, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findViews(view);
        applySetting();
        configuration();
    }

    private void findViews(View view) {

        rgFonts = view.findViewById(R.id.rg_arabic_fonts);

        rgStyle = view.findViewById(R.id.rg_arabic_style);
        rbBold = view.findViewById(R.id.rb_bold_arabic);
        rbItalic = view.findViewById(R.id.rb_italic_arabic);
        rbNormal = view.findViewById(R.id.rb_normal_arabic);
        rbBoldItalic = view.findViewById(R.id.rb_bold_italic_arabic);

        sbTextSize = view.findViewById(R.id.sb_arabic_text_size);
        sbLineSpacing = view.findViewById(R.id.tb_arabic_line_spacing);

        sampleText = view.findViewById(R.id.sample_pilgrimage_text);

    }

    private void applySetting() {

        programSetting = new ProgramSetting(getContext());

        setTypeFace();

        sbTextSize.setProgress(programSetting.getArabicTextSize());
        sampleText.setTextSize(programSetting.getArabicTextSize());

        sbLineSpacing.setProgress((int) (programSetting.getArabicTextLineSpace() * 10));
        sampleText.setLineSpacing(programSetting.getArabicTextLineSpace(), programSetting.getArabicTextLineSpace());


    }

    private void setTypeFace() {

        int fontId = programSetting.getArabicFontId();
        RadioButton radioButton;

        switch (fontId) {

            case R.font.homa:
                radioButton = getView().findViewById(R.id.rb_arabic_font1);
                arabicFont = ResourcesCompat.getFont(getActivity(), R.font.homa);
                break;
            case R.font.morvarid:
                radioButton = getView().findViewById(R.id.rb_arabic_font2);
                arabicFont = ResourcesCompat.getFont(getActivity(), R.font.morvarid);
                break;
            case R.font.nabi:
                radioButton = getView().findViewById(R.id.rb_arabic_font3);
                arabicFont = ResourcesCompat.getFont(getActivity(), R.font.nabi);

                break;
            default:
                radioButton = getView().findViewById(R.id.rb_arabic_font1);
                arabicFont = ResourcesCompat.getFont(getActivity(), R.font.nabi);
                break;


        }

        radioButton.setChecked(true);
        setStyle();


    }

    private void setStyle() {

        String textStyle = programSetting.getArabicTextStyle();

        switch (textStyle) {

            case "Normal":

                rbNormal.setChecked(true);
                sampleText.setTypeface(arabicFont, Typeface.NORMAL);
                break;

            case "Italic":

                rbItalic.setChecked(true);
                sampleText.setTypeface(arabicFont, Typeface.ITALIC);
                break;

            case "Bold":

                rbBold.setChecked(true);
                sampleText.setTypeface(arabicFont, Typeface.BOLD);
                break;

            case "Bold_Italic":

                rbBoldItalic.setChecked(true);
                sampleText.setTypeface(arabicFont, Typeface.BOLD_ITALIC);
                break;

        }


    }

    private void configuration() {

        rgFonts.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                switch (radioGroup.getCheckedRadioButtonId()) {

                    case R.id.rb_arabic_font1:
                        arabicFont = ResourcesCompat.getFont(getActivity(), R.font.homa);
                        programSetting.setArabicFontId(R.font.homa);
                        setStyle();
                        break;

                    case R.id.rb_arabic_font2:
                        arabicFont = ResourcesCompat.getFont(getActivity(), R.font.morvarid);
                        programSetting.setArabicFontId(R.font.morvarid);
                        setStyle();
                        break;

                    case R.id.rb_arabic_font3:
                        arabicFont = ResourcesCompat.getFont(getActivity(), R.font.nabi);
                        programSetting.setArabicFontId(R.font.nabi);
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
                programSetting.setArabicTextSize(i);
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
                programSetting.setArabicTextLineSpace(lineSpace);
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

            case R.id.rb_normal_arabic:

                programSetting.setArabicTextStyle(getString(R.string.normal));
                sampleText.setTypeface(arabicFont, Typeface.NORMAL);

                break;

            case R.id.rb_bold_arabic:

                programSetting.setArabicTextStyle(getString(R.string.bold));
                sampleText.setTypeface(arabicFont, Typeface.BOLD);


                break;

            case R.id.rb_italic_arabic:

                programSetting.setArabicTextStyle(getString(R.string.italic));
                sampleText.setTypeface(arabicFont, Typeface.ITALIC);


                break;

            case R.id.rb_bold_italic_arabic:

                programSetting.setArabicTextStyle(getString(R.string.bold_italic));
                sampleText.setTypeface(arabicFont, Typeface.BOLD_ITALIC);


                break;
        }

    }


    @Override
    public void onPause() {

        programSetting.updateSetting(getActivity());

        super.onPause();
    }
}
