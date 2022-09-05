package com.ttp.ziaratarbaeen.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ttp.ziaratarbaeen.adapter.NarrativeAdapter;
import com.ttp.ziaratarbaeen.databinding.FragmentNarrativesBinding;

public class NarrativesFragment extends Fragment {

    FragmentNarrativesBinding binding;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding=FragmentNarrativesBinding.inflate(getLayoutInflater(),container,false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        setUpList();


    }



    private void setUpList() {

        NarrativeAdapter narrativeAdapter = new NarrativeAdapter(getActivity());

        binding.rvNarrative.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvNarrative.setAdapter(narrativeAdapter);

    }
}
