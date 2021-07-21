package com.ttp.ziaratarbaeen.fragments;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.ttp.ziaratarbaeen.R;
import com.ttp.ziaratarbaeen.classes.ArbaeenMediaPlayer;
import com.ttp.ziaratarbaeen.classes.MyConstants;
import com.ttp.ziaratarbaeen.classes.Paragraph;
import com.ttp.ziaratarbaeen.classes.ProgramSetting;
import com.ttp.ziaratarbaeen.classes.TapsellAD;

import java.util.Timer;
import java.util.TimerTask;

public class PilgrimageFragment extends Fragment {


    final int VERSE_NUMBER = MyConstants.VERSE_NUMBER;
    final int ANIMATION_DURATION = MyConstants.ANIMATION_DURATION;

    int arabicTextSize;
    int persianTextSize;

    boolean autoScroll = false;
    boolean darkTheme = false;

    Paragraph[] paragraphs;
    ProgramSetting programSetting;

    String[] arabicTexts;
    String[] persianTexts;

    LinearLayout linearLayout;
    ScrollView scrollView;

    Button btnZoomIn;
    Button btnZoomOut;
    Button btnPlayPause;
    Button btnStop;
    Button btnAdvertising;

    SeekBar seekBar;
    TextView tvCurrentTime;

    Animation zoomInOutAnimation;
    MediaPlayer mpPilgrimage;
    Timer timer;
    View.OnClickListener tvListener;

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
        createDynamicViews();
        setOnClick();
        startTimer();

    }

    private void findViews(View view) {

        scrollView = view.findViewById(R.id.scroll_view);
        linearLayout = view.findViewById(R.id.ll_pilgrimage);

        btnZoomIn = view.findViewById(R.id.btn_zoom_in);
        btnZoomOut = view.findViewById(R.id.btn_zoom_out);
        btnPlayPause = view.findViewById(R.id.btn_play);
        btnStop = view.findViewById(R.id.btn_stop);
        btnAdvertising = view.findViewById(R.id.btn_advertising);
        seekBar = view.findViewById(R.id.seek_bar);
        tvCurrentTime = view.findViewById(R.id.tv_current_time);

    }

    private void configuration() {

        init();
        applySetting();

    }

    private void applySetting() {



        arabicTextSize = programSetting.getArabicTextSize();
        persianTextSize = programSetting.getPersianTextSize();

        autoScroll = programSetting.isAutoScroll();
        darkTheme = programSetting.isDarkTheme();

        if (darkTheme) {
            scrollView.setBackground(getActivity().getDrawable(R.drawable.dark_theme_background));
        }
    }

    private void init() {

        programSetting = new ProgramSetting(getActivity());

        arabicTexts = getResources().getStringArray(R.array.pilgrimage_text);
        persianTexts = getResources().getStringArray(R.array.translation_text);

        paragraphs = new Paragraph[VERSE_NUMBER];

        zoomInOutAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.zoom_in_out_animation);
        zoomInOutAnimation.setDuration(ANIMATION_DURATION);

        mpPilgrimage = ArbaeenMediaPlayer.getMediaPlayer(getActivity());
        seekBar.setMax(mpPilgrimage.getDuration());

        tvListener = new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {


                int position = 1;
                for (int i = 0; i < paragraphs.length; i++) {
                    TextView tvPilgrimage = paragraphs[i].getTvArabicText();
                    if (tvPilgrimage == v) {
                        tvPilgrimage.setTextColor(Color.RED);
                        position = ArbaeenMediaPlayer.getCurrentPosition(i);
                    } else
                        tvPilgrimage.setTextColor(getResources().getColor(R.color.black));
                }


                mpPilgrimage.seekTo(position * 1000);

                if (!mpPilgrimage.isPlaying())
                    changeState();
            }
        };

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean userChangeProgress) {

                if (seekBar.getProgress() == seekBar.getMax())
                    reset();

                else {
                    tvCurrentTime.setText(convertSecondToMinute(progress));
                    if (userChangeProgress) {
                        mpPilgrimage.seekTo(seekBar.getProgress());
                        setCurrentParagraph(ArbaeenMediaPlayer.getIndex(seekBar.getProgress() / 1000));
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
    }

    private String convertSecondToMinute(int progress) {

        int second = (progress / 1000) % 60;
        int minute = (progress / 1000) / 60;

        return minute + ":" + (second < 10 ? "0" + String.valueOf(second) : String.valueOf(second));
    }

    private void createDynamicViews() {

        for (int i = 0; i < paragraphs.length; i++) {

            paragraphs[i] = new Paragraph(getActivity());
            paragraphs[i].setText(arabicTexts[i], persianTexts[i]);


            linearLayout.addView(paragraphs[i].getTvArabicText());
            linearLayout.addView(paragraphs[i].getTvPersianText());
            linearLayout.addView(paragraphs[i].getImSeparator());

        }
    }

    private void setOnClick() {

        for (Paragraph paragraph : paragraphs)
            paragraph.getTvArabicText().setOnClickListener(tvListener);


        btnZoomIn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                zoomAnimate(v);
                if (arabicTextSize == MyConstants.MAXIMUM_TEXT_SIZE) {
                    Toast.makeText(getActivity(), "حداکثر اندازه متن انتخاب شده است", Toast.LENGTH_SHORT).show();
                } else
                    setTextSize(++arabicTextSize, ++persianTextSize);
            }
        });

        btnZoomOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                zoomAnimate(v);
                if (arabicTextSize == MyConstants.MINIMUM_TEXT_SIZE) {
                    Toast.makeText(getActivity(), "حداقل اندازه متن انتخاب شده است", Toast.LENGTH_SHORT).show();
                } else
                    setTextSize(--arabicTextSize, --persianTextSize);
            }
        });

        btnPlayPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                zoomAnimate(v);
                changeState();

            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                zoomAnimate(v);
                reset();
            }
        });

        btnAdvertising.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zoomAnimate(view);
                new TapsellAD(null, null, getActivity()).showInterstitialAD(MyConstants.Interstitial_AD_ID);
            }
        });


    }

    private void startTimer() {

        timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                if (mpPilgrimage.isPlaying()) {


                    int currentPosition = mpPilgrimage.getCurrentPosition() / 1000;

                    seekBar.setProgress(mpPilgrimage.getCurrentPosition());

                    if (currentPosition == mpPilgrimage.getDuration() / 1000) {
                        reset();
                        return;
                    }

                    int currentIndex = ArbaeenMediaPlayer.getCurrentIndex(currentPosition);
                    if (currentIndex != -1)
                        setCurrentParagraph(currentIndex);
                }
            }
        }, 0, 1000);

    }

    private void setTextSize(int textSize, int transTextSize) {

        for (Paragraph paragraph : paragraphs) {
            paragraph.setTextSize(textSize, transTextSize);
        }
    }

    private void setCurrentParagraph(int index) {

        for (int i = 0; i < paragraphs.length; i++) {

            TextView tvPilgrimage = paragraphs[i].getTvArabicText();
            if (i == index) {
                tvPilgrimage.setTextColor(Color.RED);
                if (autoScroll)
                    scrollView.smoothScrollTo(0, (tvPilgrimage.getTop() - 100));
            } else
                tvPilgrimage.setTextColor(getResources().getColor(R.color.black));
        }
    }

    private void zoomAnimate(View view) {

        view.startAnimation(zoomInOutAnimation);
    }

    private void reset() {

        setCurrentParagraph(VERSE_NUMBER + 1);
        btnPlayPause.setBackground(getResources().getDrawable(R.drawable.ic_play));

        if (mpPilgrimage.isPlaying()) {
            mpPilgrimage.pause();
        }

        mpPilgrimage.seekTo(0);
        seekBar.setProgress(0);
    }

    private void changeState() {

        if (mpPilgrimage.isPlaying()) {

            btnPlayPause.setBackground(getResources().getDrawable(R.drawable.ic_play));
            mpPilgrimage.pause();
        } else {

            btnPlayPause.setBackground(getResources().getDrawable(R.drawable.ic_pause));
            mpPilgrimage.start();

        }
    }

    @Override
    public void onPause() {

        reset();
        super.onPause();
    }

}
