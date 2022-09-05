package com.ttp.ziaratarbaeen.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.ttp.ziaratarbaeen.R;
import com.ttp.ziaratarbaeen.classes.Mention;
import com.ttp.ziaratarbaeen.databinding.FragmentSalawatCountBinding;
import com.ttp.ziaratarbaeen.dialogs.ResetCounterDialog;

import java.util.ArrayList;

public class SalawatCountFragment extends Fragment {

    private FragmentSalawatCountBinding binding;

    int counter;

    PopupMenu popupMenu;
    ArrayList<Mention>mentionList;
    ////////////


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding=FragmentSalawatCountBinding.inflate(getLayoutInflater(),container,false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        configuration();

        SelectMentionFragment selectMentionFragment=new SelectMentionFragment();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_fragment_container,selectMentionFragment).addToBackStack(null).commit();

    }





    private void configuration() {
        binding.btnCounterPlusPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.tvCounterText.setText(String.valueOf(++counter));
            }
        });

        binding.ivReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ResetCounterDialog(getContext()).show();
            }
        });


    }



}
