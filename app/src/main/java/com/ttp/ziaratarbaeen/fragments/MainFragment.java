package com.ttp.ziaratarbaeen.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ttp.ziaratarbaeen.R;
import com.ttp.ziaratarbaeen.utils.MyIntent;
import com.ttp.ziaratarbaeen.utils.UseFullMethod;
import com.ttp.ziaratarbaeen.databinding.FragmentMainBinding;
import com.ttp.ziaratarbaeen.dialogs.CommentDialog;


public class MainFragment extends Fragment {

    private FragmentMainBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMainBinding.inflate(getLayoutInflater(), container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setOnClick();
    }

    private void setOnClick() {

        binding.btnPilgrimageFragmentMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UseFullMethod.safeNavigate(v, R.id.pilgrimageFragment);
            }
        });
        binding.ivSettingFragmentMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UseFullMethod.safeNavigate(v, R.id.settingFragment);
            }
        });
        binding.ivNarrativesFragmentMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UseFullMethod.safeNavigate(v, R.id.narrativesFragment);
            }
        });
        binding.ivSalawatCountFragmentMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UseFullMethod.safeNavigate(v, R.id.selectMentionFragment);
            }
        });
        binding.ivCommentFragmentMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CommentDialog(requireActivity()).show();
            }
        });
        binding.ivOtherAppsFragmentMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyIntent.otherAppIntent(getContext());
            }
        });
        binding.ivShareAppFragmentMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyIntent.shareAppIntent(getContext());
            }
        });
    }
}