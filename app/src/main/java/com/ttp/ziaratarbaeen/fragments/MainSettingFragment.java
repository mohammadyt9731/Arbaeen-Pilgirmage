package com.ttp.ziaratarbaeen.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ttp.ziaratarbaeen.R;
import com.ttp.ziaratarbaeen.classes.ProgramSetting;

public class MainSettingFragment extends Fragment {

    ProgramSetting programSetting;

    Switch autoScrollSwitch;
    Switch showTranslationSwitch;
    Switch showSeparatorSwitch;
    Switch darkModeSwitch;

    TextView tvPersianText;
    ImageButton ivSeparator;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main_setting, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findView(view);
        init();
        setOnClick();
    }

    private void findView(View view) {

        autoScrollSwitch = view.findViewById(R.id.switch_auto_scroll);
        showTranslationSwitch = view.findViewById(R.id.switch_show_translation);
        showSeparatorSwitch = view.findViewById(R.id.switch_show_separator);
        darkModeSwitch = view.findViewById(R.id.switch_dark_theme);

        tvPersianText = view.findViewById(R.id.tv_sample_persian_text);
        ivSeparator = view.findViewById(R.id.ib_separator);
    }

    private void init() {

        programSetting = new ProgramSetting(getContext());

        autoScrollSwitch.setChecked(programSetting.isAutoScroll());
        showTranslationSwitch.setChecked(programSetting.isShowTranslation());
        showSeparatorSwitch.setChecked(programSetting.isShowSeparator());
        darkModeSwitch.setChecked(programSetting.isDarkTheme());

        setVisibility(programSetting.isShowTranslation(), programSetting.isShowSeparator());
    }

    private void setVisibility(Boolean showTranslation, Boolean showSeparator) {

        if (showTranslation != null)
            if (showTranslation)
                tvPersianText.setVisibility(View.VISIBLE);
            else
                tvPersianText.setVisibility(View.GONE);

        if (showSeparator != null)
            if (showSeparator)
                ivSeparator.setVisibility(View.VISIBLE);
            else
                ivSeparator.setVisibility(View.GONE);

    }

    private void setOnClick() {

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

        autoScrollSwitch.setOnCheckedChangeListener(checkedChangeListener);
        showTranslationSwitch.setOnCheckedChangeListener(checkedChangeListener);
        showSeparatorSwitch.setOnCheckedChangeListener(checkedChangeListener);
        darkModeSwitch.setOnCheckedChangeListener(checkedChangeListener);


    }

    @Override
    public void onPause() {

        programSetting.updateSetting(getActivity());
        Log.i("aaaaa","mmm");
        super.onPause();
    }
}
