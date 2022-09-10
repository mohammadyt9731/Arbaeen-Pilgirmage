package com.ttp.ziaratarbaeen.fragments;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ttp.ziaratarbaeen.R;
import com.ttp.ziaratarbaeen.adapter.PilgrimageAdapter;
import com.ttp.ziaratarbaeen.utils.ArbaeenMediaPlayer;
import com.ttp.ziaratarbaeen.utils.LinearLayoutManagerWithSmoothScroller;
import com.ttp.ziaratarbaeen.utils.MyConstants;
import com.ttp.ziaratarbaeen.utils.ProgramSetting;
import com.ttp.ziaratarbaeen.utils.UseFullMethod;
import com.ttp.ziaratarbaeen.databinding.FragmentPilgrimageBinding;
import com.ttp.ziaratarbaeen.interfaces.CallBackStartMedia;

import java.util.Timer;
import java.util.TimerTask;

public class PilgrimageFragment extends Fragment {



    private FragmentPilgrimageBinding binding;


    private MediaPlayer mpPilgrimage;
    private PilgrimageAdapter pilgrimageAdapter;
    private ProgramSetting programSetting;
    private Animation scaleAnimation;


    private int arabicTextSize;
    private int persianTextSize;
    private boolean autoScroll = true;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentPilgrimageBinding.inflate(getLayoutInflater(), container, false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        init();
        applySetting();
        setUpList();
        setOnClick();
        startTimer();
    }

    @Override
    public void onStart() {
        super.onStart();

        if (mpPilgrimage.isPlaying()) {

            changePlayPauseBtnBackground();
            pilgrimageAdapter.update(ArbaeenMediaPlayer
                    .getIndex(mpPilgrimage.getCurrentPosition() / 1000));

        }
    }



    private void init() {

        programSetting = new ProgramSetting(getActivity());

        scaleAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.scale_animation);

        mpPilgrimage = ArbaeenMediaPlayer.getMediaPlayer(getActivity());

        binding.seekBarFragmentPilgrimage.setMax(mpPilgrimage.getDuration());


    }

    private void applySetting() {

        arabicTextSize = programSetting.getArabicTextSize();
        persianTextSize = programSetting.getPersianTextSize();

        autoScroll = programSetting.isAutoScroll();

    }

    private void setUpList() {

        pilgrimageAdapter = new PilgrimageAdapter(getActivity(), new CallBackStartMedia() {
            @Override
            public void startMedia() {
                changePlayPauseBtnBackground();
            }
        });

        binding.rvPilgrimageFragmentPilgrimage.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.rvPilgrimageFragmentPilgrimage.setAdapter(pilgrimageAdapter);
    }

    private void changePlayPauseBtnBackground() {
        binding.btnPlayPauseFragmentPilgrimage.setBackground(getResources().getDrawable(R.drawable.ic_pause));
    }


    private String convertMilliSecondToMinute(int progress) {

        int second = (progress / 1000) % 60;
        int minute = (progress / 1000) / 60;

        return minute + ":" + (second < 10 ? "0" + second : second);
    }


    private void setOnClick() {

        binding.seekBarFragmentPilgrimage.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean userChangeProgress) {

                if (seekBar.getProgress() == seekBar.getMax())
                    reset();

                else {
                    binding.tvCurrentTimeFragmentPilgrimage.setText(convertMilliSecondToMinute(progress));

                    int currentIndex;

                    if (userChangeProgress) {
                        mpPilgrimage.seekTo(seekBar.getProgress());
                        currentIndex = ArbaeenMediaPlayer.getIndex(seekBar.getProgress() / 1000);
                    } else
                        currentIndex = ArbaeenMediaPlayer.getCurrentIndex(seekBar.getProgress() / 1000);


                    if (currentIndex != -1) {

                        pilgrimageAdapter.update(currentIndex);

                        if (autoScroll) {
                            binding.rvPilgrimageFragmentPilgrimage.setLayoutManager(new LinearLayoutManagerWithSmoothScroller(getContext()));
                            binding.rvPilgrimageFragmentPilgrimage.scrollToPosition(currentIndex);
                        }

                    }


                }
            }


            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        binding.btnZoomInFragmentPilgrimage.setOnClickListener(view -> {

            scaleAnimate(view);

            if (arabicTextSize >= MyConstants.MAXIMUM_ARABIC_TEXT_SIZE && persianTextSize >= MyConstants.MAXIMUM_PERSIAN_TEXT_SIZE) {
                Toast.makeText(getActivity(), getString(R.string.max_text_size_selected), Toast.LENGTH_SHORT).show();
            } else {


                if (arabicTextSize < MyConstants.MAXIMUM_ARABIC_TEXT_SIZE)
                    arabicTextSize++;

                if (persianTextSize < MyConstants.MAXIMUM_PERSIAN_TEXT_SIZE)
                    persianTextSize++;

                setTextSize(arabicTextSize, persianTextSize);
            }

        });

        binding.btnZoomOutFragmentPilgrimage.setOnClickListener(view -> {

            scaleAnimate(view);


            if (arabicTextSize <= MyConstants.MINIMUM_ARABIC_TEXT_SIZE && persianTextSize <= MyConstants.MINIMUM_PERSIAN_TEXT_SIZE) {
                Toast.makeText(getActivity(), getString(R.string.min_text_size_selected), Toast.LENGTH_SHORT).show();
            } else {

                if (arabicTextSize > MyConstants.MINIMUM_ARABIC_TEXT_SIZE)
                    arabicTextSize--;

                if (persianTextSize > MyConstants.MINIMUM_PERSIAN_TEXT_SIZE)
                    persianTextSize--;

                setTextSize(arabicTextSize, persianTextSize);

            }

        });

        binding.btnPlayPauseFragmentPilgrimage.setOnClickListener(view -> {

            scaleAnimate(view);
            changeState();
        });

        binding.btnStopFragmentPilgrimage.setOnClickListener(view -> {

            scaleAnimate(view);
            reset();
        });

        binding.btnSettingFragmentPilgrimage.setOnClickListener(view -> {
            scaleAnimate(view);
            UseFullMethod.safeNavigate(view,R.id.settingFragment);
        });
    }

    private void startTimer() {

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                if (mpPilgrimage.isPlaying()) {

                    binding.seekBarFragmentPilgrimage.setProgress(mpPilgrimage.getCurrentPosition());
                }
            }
        }, 0, 1000);

    }

    private void setTextSize(int arabicTextSize, int persianTextSize) {


        programSetting.setArabicTextSize(arabicTextSize);
        programSetting.setPersianTextSize(persianTextSize);
        programSetting.updateSetting(getContext());

        pilgrimageAdapter.updateView();

    }

    private void scaleAnimate(View view) {

        view.startAnimation(scaleAnimation);
    }

    private void reset() {

        binding.btnPlayPauseFragmentPilgrimage.setBackground(getResources().getDrawable(R.drawable.ic_play2));

        if (mpPilgrimage.isPlaying()) {
            mpPilgrimage.pause();
        }

        mpPilgrimage.seekTo(0);
        binding.seekBarFragmentPilgrimage.setProgress(0);
    }

    private void changeState() {

        if (mpPilgrimage.isPlaying()) {

            binding.btnPlayPauseFragmentPilgrimage.setBackground(getResources().getDrawable(R.drawable.ic_play2));
            mpPilgrimage.pause();
        } else {


            binding.btnPlayPauseFragmentPilgrimage.setBackground(getResources().getDrawable(R.drawable.ic_pause));
            mpPilgrimage.start();

        }
    }


}
