package com.ttp.ziaratarbaeen;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.navigation.NavigationView;
import com.ttp.ziaratarbaeen.classes.MyConstants;
import com.ttp.ziaratarbaeen.classes.MyDialogs;
import com.ttp.ziaratarbaeen.classes.MyIntent;
import com.ttp.ziaratarbaeen.classes.TapsellAD;
import com.ttp.ziaratarbaeen.fragments.NarrativesFragment;
import com.ttp.ziaratarbaeen.fragments.NoticesFragment;
import com.ttp.ziaratarbaeen.fragments.PilgrimageFragment;
import com.ttp.ziaratarbaeen.fragments.SettingFragment;

public class MainActivity extends AppCompatActivity {

    RelativeLayout rlAdvertising;

    Button btnPilgrimage;
    Button btnNarratives;
    Button btnShareApp;
    Button btnAboutUs;
    Button btnOtherApp;
    Button btnComment;
    Button btnNotices;
    Button btnExit;
    Button btnSetting;

    Button btnNavigationView;
    Button btnAd;

    DrawerLayout drawerLayout;
    NavigationView navigationView;

    PilgrimageFragment pilgrimageFragment;
    SettingFragment settingFragment;
    NarrativesFragment narrativesFragment;
    NoticesFragment noticesFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViews();
        init();
        configuration();
        CreateMenu();


    }
    ////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////

    private void findViews() {

        rlAdvertising = findViewById(R.id.rl_advertising);

        btnPilgrimage = findViewById(R.id.btn_Text_ziarat);
        btnNarratives = findViewById(R.id.btn_narratives);
        btnShareApp = findViewById(R.id.btn_share);
        btnAboutUs = findViewById(R.id.btn_about_us);
        btnOtherApp = findViewById(R.id.btn_other_app);
        btnComment = findViewById(R.id.btn_comment);
        btnNotices = findViewById(R.id.btn_notices);
        btnExit = findViewById(R.id.btn_exit);
        btnSetting = findViewById(R.id.btn_stop);

        btnNavigationView = findViewById(R.id.btn_menu_toolbar);
        btnAd = findViewById(R.id.btn_toolbar_advertising);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);


    }

    private void init() {
        pilgrimageFragment = new PilgrimageFragment();
        settingFragment = new SettingFragment();
        narrativesFragment = new NarrativesFragment();
        noticesFragment = new NoticesFragment();

    }

    private void configuration() {


        btnPilgrimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                loadFragment(pilgrimageFragment);


            }
        });
        ///////////////////////////////////////////////////////////////////////////////////////////////////
        btnOtherApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MyIntent.otherAppIntent(MainActivity.this);
            }
        });
        ///////////////////////////////////////////////////////////////////////////////////////////////////
        btnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MyIntent.commentIntent(MainActivity.this);
            }
        });
        ///////////////////////////////////////////////////////////////////////////////////////////////////
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MyDialogs.showExitDialog(MainActivity.this);
            }
        });
        ///////////////////////////////////////////////////////////////////////////////////////////////////
        btnShareApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MyIntent.shareAppIntent(MainActivity.this);
            }
        });
        ///////////////////////////////////////////////////////////////////////////////////////////////////
        btnAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MyDialogs.showAboutUsDialog(MainActivity.this);

            }
        });
        ///////////////////////////////////////////////////////////////////////////////////////////////////
        btnNotices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loadFragment(noticesFragment);

            }
        });
        ///////////////////////////////////////////////////////////////////////////////////////////////////
        btnNarratives.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loadFragment(narrativesFragment);
            }
        });
        ///////////////////////////////////////////////////////////////////////////////////////////////////
        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment(settingFragment);
                //startActivity(new Intent(MainActivity.this,SettingActivity.class));
            }
        });
        ///////////////////////////////////////////////////////////////////////////////////////////////////
        btnNavigationView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });
        ///////////////////////////////////////////////////////////////////////////////////////////////////
        btnAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDialogs.showAdDialog(MainActivity.this);
            }
        });


    }

    private void loadFragment(Fragment fragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fl_container, fragment)
                .addToBackStack(null).commit();

    }

    private void closeFragment() {
        while (getSupportFragmentManager().getBackStackEntryCount() > 0)
            super.onBackPressed();
    }

    private void CreateMenu() {


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.nav_home_page:
                        closeFragment();
                        break;

                    case R.id.nav_pilgrimage:
                        loadFragment(pilgrimageFragment);
                        //startActivity(new Intent(MainActivity.this,PilgrimageActivity.class));
                        break;

                    case R.id.nav_narratives:
                        loadFragment(narrativesFragment);
                        break;

                    case R.id.nav_setting:
                        loadFragment(settingFragment);
                        //  startActivity(new Intent(MainActivity.this,SettingActivity.class));
                        break;

                    case R.id.nav_noticec:
                        loadFragment(noticesFragment);
                        break;

                    case R.id.nav_share_app:
                        MyIntent.shareAppIntent(MainActivity.this);
                        break;

                    case R.id.nav_other_app:
                        MyIntent.otherAppIntent(MainActivity.this);
                        break;

                    case R.id.nav_comment:
                        MyIntent.commentIntent(MainActivity.this);
                        break;

                    case R.id.nav_about_us:
                        MyDialogs.showAboutUsDialog(MainActivity.this);
                        break;

                    case R.id.nav_exit:
                        MyDialogs.showExitDialog(MainActivity.this);
                        break;


                }
                drawerLayout.closeDrawer(Gravity.LEFT);
                return false;
            }
        });


    }


    ////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(Gravity.LEFT))
            drawerLayout.closeDrawer(Gravity.LEFT);

        else if (getSupportFragmentManager().getBackStackEntryCount() > 0)
            closeFragment();

        else
            MyDialogs.showExitDialog(MainActivity.this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // new TapsellAD(BANNER_320x50,rlAdvertising,this).showStandardBannerAD(MyConstants.HOME_PAGE_AD_ID);

        ViewGroup viewGroup = findViewById(R.id.adContainer);
        new TapsellAD(null, viewGroup, this).showNativeAD(MyConstants.NATIVE_AD_ID);
    }

}