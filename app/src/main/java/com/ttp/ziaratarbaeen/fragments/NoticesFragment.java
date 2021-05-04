package com.ttp.ziaratarbaeen.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ttp.ziaratarbaeen.R;
import com.ttp.ziaratarbaeen.classes.MyConstants;
import com.ttp.ziaratarbaeen.classes.TapsellAD;

import static ir.tapsell.plus.TapsellPlusBannerType.BANNER_320x100;
import static ir.tapsell.plus.TapsellPlusBannerType.BANNER_320x50;

public class NoticesFragment extends Fragment {

    FrameLayout firstBannerView;
    RelativeLayout secondBannerView;
    RelativeLayout thirdBannerView;
    RelativeLayout fourthBannerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notices, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        findViews(view);
        configuration();


    }

    private void findViews(View view) {
        firstBannerView = view.findViewById(R.id.first_standard_banner);
        secondBannerView = view.findViewById(R.id.second_standard_banner);
        thirdBannerView = view.findViewById(R.id.third_standard_banner);
        fourthBannerView = view.findViewById(R.id.video_banner);
    }

    private void configuration() {

        new TapsellAD(null, firstBannerView, getActivity()).showNativeAD(MyConstants.NATIVE_STANDARD_AD_ID);
        // new TapsellAD(BANNER_320x50,firstBannerView,getActivity()).showStandardBannerAD(MyConstants.STANDARD_1_AD_ID);
        new TapsellAD(BANNER_320x100, secondBannerView, getActivity()).showStandardBannerAD(MyConstants.STANDARD_2_AD_ID);
        new TapsellAD(BANNER_320x50, thirdBannerView, getActivity()).showStandardBannerAD(MyConstants.STANDARD_3_AD_ID);
        // new TapsellAD(null,null,getActivity()).showInterstitialVideoAD(MyConstants.Interstitial_VIDEO_AD_ID);

    }

    @Override
    public void onDestroy() {

        //  new TapsellAD(null,null,getActivity()).showInterstitialAD(MyConstants.Interstitial_AD_ID);
        super.onDestroy();

    }
}
