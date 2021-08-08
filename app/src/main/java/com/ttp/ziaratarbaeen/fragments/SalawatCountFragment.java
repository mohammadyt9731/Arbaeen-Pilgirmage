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
import com.ttp.ziaratarbaeen.dialogs.ResetCounterDialog;

import java.util.ArrayList;

public class SalawatCountFragment extends Fragment {

    int counter;
    
    ConstraintLayout flZekrShomar;
    FrameLayout flAdContainer;
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
        setSize();
        setUpPopupMenu();
        configuration();



    }

    private void setUpPopupMenu() {

        Context wrapper = new ContextThemeWrapper(getActivity(), R.style.PopupMenu);


        popupMenu=new PopupMenu(wrapper,tvMentionType, Gravity.LEFT);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu_mention_type,popupMenu.getMenu());



        initMentionList();

        for (Mention mention : mentionList){
            popupMenu.getMenu().add(mention.getMentionName());
        }



    }

    private void initMentionList(){
        mentionList= MySharedPreference.getInstance(getContext()).getMentionList();
        if(mentionList.isEmpty()){

            mentionList.add(new Mention("صلوات",100,20));
            mentionList.add(new Mention("ذکر روز هفته",100,20));
            mentionList.add(new Mention("تسبیحات حضرت زهرا (سلام الله علیها)",100,20));
            mentionList.add(new Mention("لا اله الا الله",100,20));
            mentionList.add(new Mention("تسبیحات اربئه",100,20));
            mentionList.add(new Mention("الله اکیر",100,20));
            mentionList.add(new Mention("سبحان الله",100,20));
            mentionList.add(new Mention("الحمدالله",100,20));


        }



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
               // MyDialogs.showResetDialog(getContext());
                new ResetCounterDialog(getContext()).show();
            }
        });

        tvMentionType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupMenu.show();
            }
        });

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {


                tvMentionType.setText(menuItem.getTitle());
                tvMention.setText(menuItem.getTitle());
                return false;
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
        tvMentionType=view.findViewById(R.id.tv_mention_type);
        tvMention=view.findViewById(R.id.tv_mention);

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

        tvMentionType.getLayoutParams().height=screenHeight*8/100;




    }

    private void setMargins (View view, int left, int top, int right, int bottom) {
        if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            p.setMargins(left, top, right, bottom);
            view.requestLayout();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        MyTapsell.showNativeAD(getActivity(),MyConstants.NATIVE_STANDARD_AD_ID,flAdContainer);

    }
}
