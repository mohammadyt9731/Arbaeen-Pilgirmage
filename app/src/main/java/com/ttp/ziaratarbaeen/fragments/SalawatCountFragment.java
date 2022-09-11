package com.ttp.ziaratarbaeen.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ttp.ziaratarbaeen.databinding.FragmentSalawatCountBinding;
import com.ttp.ziaratarbaeen.dialogs.ResetCounterDialog;

public class SalawatCountFragment extends Fragment {

    private FragmentSalawatCountBinding binding;

    int counter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding=FragmentSalawatCountBinding.inflate(getLayoutInflater(),container,false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setOnClick();

    }



    private void setOnClick() {
        binding.btnCounterIncrementFragmentSalawatCount
                .setOnClickListener(view -> binding.tvCounterFragmentSalawatCount.setText(String.valueOf(++counter)));

        binding.ivResetFragmentSalawatCount.setOnClickListener(view ->
                new ResetCounterDialog(requireContext()).show());

    }

}
