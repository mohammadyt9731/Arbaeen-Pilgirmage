package com.ttp.ziaratarbaeen.fragments;

import android.media.MediaPlayer;
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
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ttp.ziaratarbaeen.R;
import com.ttp.ziaratarbaeen.adapter.PilgrimageAdapter;
import com.ttp.ziaratarbaeen.classes.ArbaeenMediaPlayer;
import com.ttp.ziaratarbaeen.classes.LinearLayoutManagerWithSmoothScroller;
import com.ttp.ziaratarbaeen.classes.MyConstants;
import com.ttp.ziaratarbaeen.classes.ProgramSetting;
import com.ttp.ziaratarbaeen.interfaces.CallBackStartMedia;

import java.util.Timer;
import java.util.TimerTask;

public class PilgrimageFragment extends Fragment {

    private RecyclerView rvPilgrimage;


    private Button btnZoomIn;
    private Button btnZoomOut;
    private Button btnPlayPause;
    private Button btnStop;
    private Button btnSetting;

    private SeekBar sbPilgrimageTime;
    private TextView tvCurrentTime;

    private MediaPlayer mpPilgrimage;
    private PilgrimageAdapter pilgrimageAdapter;
    private ProgramSetting programSetting;
    private Animation scaleAnimation;
    private SettingFragment settingFragment;

    private final int TIMER_PERIOD=1000;
    private int arabicTextSize;
    private int persianTextSize;
    private boolean autoScroll = true;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pilgrimage, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findViews(view);
        configuration();
        setOnClick();
        startTimer();

    }

    @Override
    public void onStart() {
        super.onStart();

        if(mpPilgrimage.isPlaying()){

            changePlayPauseBtnBackground();
            pilgrimageAdapter.update(ArbaeenMediaPlayer
                    .getIndex(mpPilgrimage.getCurrentPosition()/1000));

        }
    }



    private void findViews(View view) {

        btnZoomIn = view.findViewById(R.id.btn_zoom_in);
        btnZoomOut = view.findViewById(R.id.btn_zoom_out);
        btnPlayPause = view.findViewById(R.id.btn_play_pause);
        btnStop = view.findViewById(R.id.btn_stop);
        btnSetting = view.findViewById(R.id.btn_setting_pilgrimage);

        rvPilgrimage = view.findViewById(R.id.rv_pilgrimage);

        sbPilgrimageTime = view.findViewById(R.id.seek_bar_pilgrimage_time);
        tvCurrentTime = view.findViewById(R.id.tv_current_time);
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

        sbPilgrimageTime.setMax(mpPilgrimage.getDuration());

        settingFragment=new SettingFragment();
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

        rvPilgrimage.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvPilgrimage.setAdapter(pilgrimageAdapter);
    }

    private void changePlayPauseBtnBackground(){
        btnPlayPause.setBackground(getResources().getDrawable(R.drawable.ic_pause));
    }



    private String convertMilliSecondToMinute(int progress) {

        int second = (progress / 1000) % 60;
        int minute = (progress / 1000) / 60;

        return minute + ":" + (second < 10 ? "0" + second : second);
    }


    private void setOnClick() {

        sbPilgrimageTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
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

                if (arabicTextSize >= MyConstants.MAXIMUM_ARABIC_TEXT_SIZE && persianTextSize>=MyConstants.MAXIMUM_PERSIAN_TEXT_SIZE) {
                    Toast.makeText(getActivity(), getString(R.string.max_text_size_selected), Toast.LENGTH_SHORT).show();
                }
                else{


                    if(arabicTextSize < MyConstants.MAXIMUM_ARABIC_TEXT_SIZE)
                        arabicTextSize++;

                    if(persianTextSize < MyConstants.MAXIMUM_PERSIAN_TEXT_SIZE)
                        persianTextSize++;

                    setTextSize(arabicTextSize, persianTextSize);
                }

            }
        });

        btnZoomOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                scaleAnimate(v);


                if (arabicTextSize <= MyConstants.MINIMUM_ARABIC_TEXT_SIZE && persianTextSize <= MyConstants.MINIMUM_PERSIAN_TEXT_SIZE) {
                    Toast.makeText(getActivity(), getString(R.string.min_text_size_selected), Toast.LENGTH_SHORT).show();
                } else{

                    if(arabicTextSize > MyConstants.MINIMUM_ARABIC_TEXT_SIZE)
                        arabicTextSize--;

                    if(persianTextSize > MyConstants.MINIMUM_PERSIAN_TEXT_SIZE)
                        persianTextSize--;

                    setTextSize(arabicTextSize,persianTextSize);

                }

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

        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scaleAnimate(view);

                loadFragment(settingFragment);
            }
        });
    }

    private void startTimer() {

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                if (mpPilgrimage.isPlaying()) {

                   sbPilgrimageTime.setProgress(mpPilgrimage.getCurrentPosition());
                }
            }
        }, 0, TIMER_PERIOD);

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
        sbPilgrimageTime.setProgress(0);
    }

    private void changeState() {

        if (mpPilgrimage.isPlaying()) {

            btnPlayPause.setBackground(getResources().getDrawable(R.drawable.ic_play2));
            mpPilgrimage.pause();
        } else {



            btnPlayPause.setBackground(getResources().getDrawable(R.drawable.ic_pause));
            mpPilgrimage.start();

        }
    }

    private void loadFragment(Fragment fragment) {

        getActivity().getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                .replace(R.id.fl_fragment_container, fragment)
                .addToBackStack(null).commit();

    }

    @Override
    public void onPause() {


        super.onPause();
    }



}
