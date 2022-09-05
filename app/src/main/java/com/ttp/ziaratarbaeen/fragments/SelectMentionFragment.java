package com.ttp.ziaratarbaeen.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ttp.ziaratarbaeen.adapter.MentionAdapter;
import com.ttp.ziaratarbaeen.classes.Mention;
import com.ttp.ziaratarbaeen.databinding.FragmentSelectMentionBinding;

import java.util.ArrayList;

public class SelectMentionFragment extends Fragment {

    private FragmentSelectMentionBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSelectMentionBinding.inflate(getLayoutInflater(), container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        setUpMentionList();
    }


    private void setUpMentionList() {

        ArrayList<Mention> mentionList = new ArrayList<>();

        for (int i = 0; i < 20; i++)
            mentionList.add(new Mention("الله اکیر", "خداوند بزرگ است", 100, 3));


        MentionAdapter mentionAdapter = new MentionAdapter(mentionList);
        binding.rvMention.setAdapter(mentionAdapter);
    }
}
