package com.ttp.ziaratarbaeen.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.ttp.ziaratarbaeen.R;
import com.ttp.ziaratarbaeen.classes.MyConstants;
import com.ttp.ziaratarbaeen.classes.TapsellAD;

public class SalawatCountFragment extends Fragment {

    int counter;
    
    ConstraintLayout flZekrShomar;
    FrameLayout flAdContainer;
    TextView tvCounterText;
    Button btnCounterPlusPlus;
    ImageView ivReset;
    Button btnSound;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_salawat_count,container,false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);FrameLayout frameLayout=view.findViewById(R.id.frame_hadis);

        findVies(view);
        setSize();

        new TapsellAD(null,flAdContainer,getActivity()).showNativeAD(MyConstants.NATIVE_STANDARD_AD_ID);

        btnCounterPlusPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvCounterText.setText(String.valueOf(++counter));
            }
        });

    }

    private void findVies(View view) {

        flZekrShomar=view.findViewById(R.id.fl_zekr_shomar);
        flAdContainer=view.findViewById(R.id.fl_ad_container);
        tvCounterText=view.findViewById(R.id.tv_counter_text);
        btnCounterPlusPlus=view.findViewById(R.id.btn_counter_plus_plus);
        ivReset =view.findViewById(R.id.iv_reset);
        btnSound=view.findViewById(R.id.btn_sound);
    }

    private void setSize(){

        int screenWidth= MyConstants.getScreenWidth();
        int screenHeight= MyConstants.getScreenHeight();

        int zekrShomarLayoutWidth=flZekrShomar.getLayoutParams().width=screenWidth*62/100;
        int zekrShomarLayoutHeight=flZekrShomar.getLayoutParams().height=screenWidth*80/100;

        btnCounterPlusPlus.getLayoutParams().width=zekrShomarLayoutWidth*40/100;
        btnCounterPlusPlus.getLayoutParams().height=btnCounterPlusPlus.getLayoutParams().width;
        setMargins(btnCounterPlusPlus,0,0,10,zekrShomarLayoutHeight*10/100);

        ivReset.getLayoutParams().width=zekrShomarLayoutWidth*12/100;
        ivReset.getLayoutParams().height=ivReset.getLayoutParams().width;

        tvCounterText.getLayoutParams().width=zekrShomarLayoutWidth*71/100;
        tvCounterText.getLayoutParams().height=tvCounterText.getLayoutParams().width*40/100;

        setMargins(tvCounterText,0,zekrShomarLayoutHeight*17/100,0,0);

        btnSound.getLayoutParams().width=zekrShomarLayoutWidth*18/100;
        btnSound.getLayoutParams().height=btnSound.getLayoutParams().width;


    }

    private void setMargins (View view, int left, int top, int right, int bottom) {
        if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            p.setMargins(left, top, right, bottom);
            view.requestLayout();
        }
    }

}
