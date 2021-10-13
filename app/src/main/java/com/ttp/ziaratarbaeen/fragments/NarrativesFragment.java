package com.ttp.ziaratarbaeen.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ttp.ziaratarbaeen.R;
import com.ttp.ziaratarbaeen.classes.MyConstants;
import com.ttp.ziaratarbaeen.classes.MyTapsell;
import com.ttp.ziaratarbaeen.classes.NarrativeAdapter;

import ir.tapsell.plus.TapsellPlusBannerType;

public class NarrativesFragment extends Fragment {

    RelativeLayout rlAdvertising;
    RecyclerView rvNarratives;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_narratives, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findViews(view);
        setUpList();


    }

    @Override
    public void onResume() {
        super.onResume();

        MyTapsell.showStandardBanner(getActivity(), MyConstants.STANDARD_1_AD_ID, rlAdvertising, TapsellPlusBannerType.BANNER_320x50);
    }

    private void findViews(View view) {

        rlAdvertising = view.findViewById(R.id.rl_ad_narrative);
        rvNarratives = view.findViewById(R.id.rv_narrative);

    }

    private void setUpList() {

        NarrativeAdapter narrativeAdapter = new NarrativeAdapter(getActivity());

        rvNarratives.setLayoutManager(new LinearLayoutManager(getContext()));
        rvNarratives.setAdapter(narrativeAdapter);


    }
}
