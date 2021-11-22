package com.ttp.ziaratarbaeen.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.ttp.ziaratarbaeen.R;
import com.ttp.ziaratarbaeen.classes.Mention;
import com.ttp.ziaratarbaeen.classes.MyConstants;
import com.ttp.ziaratarbaeen.classes.MySharedPreference;
import com.ttp.ziaratarbaeen.classes.MyTapsell;
import com.ttp.ziaratarbaeen.classes.UseFullMethod;
import com.ttp.ziaratarbaeen.dialogs.ResetCounterDialog;

import java.util.ArrayList;

public class SalawatCountFragment extends Fragment {

    int counter;
    
    ConstraintLayout flZekrShomar;
    RelativeLayout flAdContainer;
    TextView tvCounterText;
    Button btnCounterPlusPlus;
    ImageView ivReset;

    Button btnSound;
    TextView tvMentionType;
    TextView tvMention;

    PopupMenu popupMenu;
    ArrayList<Mention>mentionList;
    ////////////






    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_salawat_count,container,false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        findViews(view);
        configuration();



    }





    private void configuration() {
        btnCounterPlusPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvCounterText.setText(String.valueOf(++counter));
            }
        });

        ivReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ResetCounterDialog(getContext()).show();
            }
        });


    }

    private void findViews(View view) {

        flZekrShomar=view.findViewById(R.id.fl_zekr_shomar);
        flAdContainer=view.findViewById(R.id.fl_ad_container);
        tvCounterText=view.findViewById(R.id.tv_counter_text);
        btnCounterPlusPlus=view.findViewById(R.id.btn_counter_plus_plus);
        ivReset =view.findViewById(R.id.iv_reset);
        btnSound=view.findViewById(R.id.btn_sound);
        tvMention=view.findViewById(R.id.tv_mention);

    }



}
