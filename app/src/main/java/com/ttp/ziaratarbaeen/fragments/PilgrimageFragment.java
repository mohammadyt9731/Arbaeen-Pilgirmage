package com.ttp.ziaratarbaeen.fragments;

import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ttp.ziaratarbaeen.R;
import com.ttp.ziaratarbaeen.adapter.PilgrimageAdapter;
import com.ttp.ziaratarbaeen.classes.ArbaeenMediaPlayer;
import com.ttp.ziaratarbaeen.classes.LinearLayoutManagerWithSmoothScroller;
import com.ttp.ziaratarbaeen.classes.MyConstants;
import com.ttp.ziaratarbaeen.classes.MyTapsell;
import com.ttp.ziaratarbaeen.classes.ProgramSetting;

import java.util.Timer;
import java.util.TimerTask;

public class PilgrimageFragment extends Fragment {

    public static MediaPlayer mpPilgrimage;
    RecyclerView rvPilgrimage;
    PilgrimageAdapter pilgrimageAdapter;
    private ProgramSetting programSetting;
    private int arabicTextSize;
    private int persianTextSize;
    private boolean autoScroll = false;
    private Button btnZoomIn;
    private Button btnZoomOut;
    public static Button btnPlayPause;
    private Button btnStop;
    private Button btnAdvertising;
    private SeekBar seekBar;
    private TextView tvCurrentTime;
    private Animation scaleAnimation;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pilgrimage, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findViews(view);
        configuration();
        setOnClick();
        startTimer();

    }

    private void findViews(View view) {

        btnZoomIn = view.findViewById(R.id.btn_zoom_in);
        btnZoomOut = view.findViewById(R.id.btn_zoom_out);
        btnPlayPause = view.findViewById(R.id.btn_play);
        btnStop = view.findViewById(R.id.btn_stop);
        btnAdvertising = view.findViewById(R.id.btn_advertising);

        seekBar = view.findViewById(R.id.seek_bar_pilgrimage_time);
        tvCurrentTime = view.findViewById(R.id.tv_current_time);

        rvPilgrimage = view.findViewById(R.id.rv_pilgrimage);

    }

    private void configuration() {

        init();
        applySetting();
        setUpList();
    }

    private void init() {

        programSetting = new ProgramSetting(getActivity());

        scaleAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.animation_scale);

        mpPilgrimage = ArbaeenMediaPlayer.getMediaPlayer(getActivity());
        seekBar.setMax(mpPilgrimage.getDuration());

    }

    private void applySetting() {

        arabicTextSize = programSetting.getArabicTextSize();
        persianTextSize = programSetting.getPersianTextSize();

        autoScroll = programSetting.isAutoScroll();
    }

    private void setUpList() {

        pilgrimageAdapter = new PilgrimageAdapter(getActivity());
        rvPilgrimage.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvPilgrimage.setAdapter(pilgrimageAdapter);
    }


    private String convertMilliSecondToMinute(int progress) {

        int second = (progress / 1000) % 60;
        int minute = (progress / 1000) / 60;

        return minute + ":" + (second < 10 ? "0" + second : second);
    }


    private void setOnClick() {

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean userChangeProgress) {

                if (seekBar.getProgress() == seekBar.getMax())
                    reset();

                else {
                    tvCurrentTime.setText(convertMilliSecondToMinute(progress));

                    int currentIndex;

                    if (userChangeProgress) {
                        mpPilgrimage.seekTo(seekBar.getProgress());
                        currentIndex = ArbaeenMediaPlayer.getIndex(seekBar.getProgress() / 1000);
                    } else
                        currentIndex = ArbaeenMediaPlayer.getCurrentIndex(seekBar.getProgress() / 1000);


                    if (currentIndex != -1) {

                        pilgrimageAdapter.update(currentIndex);

                        if (autoScroll) {
                            rvPilgrimage.setLayoutManager(new LinearLayoutManagerWithSmoothScroller(getContext()));
                            rvPilgrimage.scrollToPosition(currentIndex);
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

        btnZoomIn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                scaleAnimate(v);
                if (arabicTextSize >= MyConstants.MAXIMUM_TEXT_SIZE) {
                    Toast.makeText(getActivity(), getString(R.string.max_text_size_selected), Toast.LENGTH_SHORT).show();
                } else
                    setTextSize(++arabicTextSize, ++persianTextSize);
            }
        });

        btnZoomOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                scaleAnimate(v);
                if (arabicTextSize <= MyConstants.MINIMUM_TEXT_SIZE) {
                    Toast.makeText(getActivity(), getString(R.string.min_text_size_selected), Toast.LENGTH_SHORT).show();
                } else
                    setTextSize(--arabicTextSize, --persianTextSize);
            }
        });

        btnPlayPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                scaleAnimate(v);
                changeState();
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                scaleAnimate(v);
                reset();
            }
        });

        btnAdvertising.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scaleAnimate(view);
                MyTapsell.showInterstitialAd(getActivity(), MyConstants.Interstitial_AD_ID);
            }
        });
    }

    private void startTimer() {

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                if (mpPilgrimage.isPlaying()) {

                    seekBar.setProgress(mpPilgrimage.getCurrentPosition());
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

        btnPlayPause.setBackground(getResources().getDrawable(R.drawable.ic_play2));

        if (mpPilgrimage.isPlaying()) {
            mpPilgrimage.pause();
        }

        mpPilgrimage.seekTo(0);
        seekBar.setProgress(0);
    }

    private void changeState() {

        if (mpPilgrimage.isPlaying()) {

            btnPlayPause.setBackground(getResources().getDrawable(R.drawable.ic_play2));
            mpPilgrimage.pause();
        } else {

            btnPlayPause.setBackground(getResources().getDrawable(R.drawable.ic_pause2));
            mpPilgrimage.start();

        }
    }

    @Override
    public void onPause() {

        reset();
        super.onPause();
    }

}
