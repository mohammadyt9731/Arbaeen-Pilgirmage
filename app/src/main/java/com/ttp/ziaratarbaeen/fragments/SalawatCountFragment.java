package com.ttp.ziaratarbaeen.fragments;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ttp.ziaratarbaeen.R;
import com.ttp.ziaratarbaeen.database.MentionDataBase;
import com.ttp.ziaratarbaeen.database.MentionEntity;
import com.ttp.ziaratarbaeen.databinding.FragmentSalawatCountBinding;
import com.ttp.ziaratarbaeen.dialogs.ResetCounterDialog;
import com.ttp.ziaratarbaeen.utils.MyConstants;

public class SalawatCountFragment extends Fragment {

    private FragmentSalawatCountBinding binding;
    private MentionEntity mentionEntity;

    private int counter = 0;
    private int maximumNumber = 0;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSalawatCountBinding.inflate(getLayoutInflater(), container, false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init(getArguments().getInt(MyConstants.ARG_ID));
        setOnClick();

    }

    private void init(int id) {

        mentionEntity = MentionDataBase.getInstance(requireContext())
                .mentionDao().getMention(id);

        binding.tvMentionTitleFragmentSalawatCount.setText(mentionEntity.getTitle());
        binding.tvMaxNumberItemMentionLIst.setText("تعداد کل : " + mentionEntity.getMaximumNumber());
        binding.tvCounterFragmentSalawatCount.setText(String.valueOf(mentionEntity.getCounter()));

        counter = mentionEntity.getCounter();
        maximumNumber = mentionEntity.getMaximumNumber();

    }


    private void setOnClick() {
        binding.btnCounterIncrementFragmentSalawatCount
                .setOnClickListener(view -> {

                    ++counter;
                    binding.tvCounterFragmentSalawatCount.setText(String.valueOf(counter));

                    if (counter == maximumNumber) {
                        Vibrator v = (Vibrator) requireContext().
                                getSystemService(Context.VIBRATOR_SERVICE);

                        Toast.makeText(requireContext(), getString(R.string.completeMenion), Toast.LENGTH_SHORT)
                                .show();

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            v.vibrate(VibrationEffect.createOneShot(1000, VibrationEffect.DEFAULT_AMPLITUDE));
                        } else {

                            v.vibrate(1000);
                        }
                    }

                });

        binding.ivResetFragmentSalawatCount.setOnClickListener(view ->
                new ResetCounterDialog(requireContext(), () -> {
                    counter = 0;
                    binding.tvCounterFragmentSalawatCount
                            .setText(String.valueOf(counter));
                }).show());

    }

    @Override
    public void onPause() {
        super.onPause();

        saveData();
    }

    private void saveData() {

        mentionEntity.setCounter(counter);
        MentionDataBase.getInstance(requireContext()).mentionDao()
                .updateMention(mentionEntity);
    }
}
