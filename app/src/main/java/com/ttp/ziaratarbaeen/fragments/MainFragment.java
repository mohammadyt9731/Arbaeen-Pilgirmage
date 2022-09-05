package com.ttp.ziaratarbaeen.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.ttp.ziaratarbaeen.R;
import com.ttp.ziaratarbaeen.classes.MyIntent;
import com.ttp.ziaratarbaeen.classes.UseFullMethod;
import com.ttp.ziaratarbaeen.databinding.FragmentMainBinding;

public class MainFragment extends Fragment {

    private FragmentMainBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding=FragmentMainBinding.inflate(getLayoutInflater(),container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        setOnClick();
    }

    private void setOnClick(){

        binding.btnPilgrimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UseFullMethod.safeNavigate(v, R.id.pilgrimageFragment);
            }
        });
        binding.btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UseFullMethod.safeNavigate(v, R.id.settingFragment);
            }
        });
        binding.btnNarratives.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UseFullMethod.safeNavigate(v, R.id.narrativesFragment);
            }
        });
        binding.btnSalawatCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UseFullMethod.safeNavigate(v, R.id.selectMentionFragment);
            }
        });

        binding.btnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyIntent.shareAppIntent(getContext());
            }
        });
        binding.btnOtherApps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyIntent.otherAppIntent(getContext());
            }
        });
        binding.btnShareApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyIntent.shareAppIntent(getContext());
            }
        });



    }
}