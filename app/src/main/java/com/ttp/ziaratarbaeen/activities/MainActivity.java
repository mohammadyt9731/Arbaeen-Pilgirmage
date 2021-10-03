package com.ttp.ziaratarbaeen.activities;

import android.animation.Animator;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.navigation.NavigationView;
import com.ttp.ziaratarbaeen.R;
import com.ttp.ziaratarbaeen.classes.MyConstants;
import com.ttp.ziaratarbaeen.classes.MyIntent;
import com.ttp.ziaratarbaeen.classes.MyTapsell;
import com.ttp.ziaratarbaeen.dialogs.AboutUsDialog;
import com.ttp.ziaratarbaeen.dialogs.AdvertisingDialog;
import com.ttp.ziaratarbaeen.dialogs.ExitDialog;
import com.ttp.ziaratarbaeen.fragments.NarrativesFragment;
import com.ttp.ziaratarbaeen.fragments.NoticesFragment;
import com.ttp.ziaratarbaeen.fragments.PilgrimageFragment;
import com.ttp.ziaratarbaeen.fragments.SalawatCountFragment;
import com.ttp.ziaratarbaeen.fragments.SettingFragment;

import ir.tapsell.plus.TapsellPlusBannerType;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    RelativeLayout rlAdContainer;

    LottieAnimationView lottieAnimationView;

    PilgrimageFragment pilgrimageFragment;
    SettingFragment settingFragment;
    NarrativesFragment narrativesFragment;
    NoticesFragment noticesFragment;
    SalawatCountFragment salawatCountFragment;

    AboutUsDialog aboutUsDialog;
    ExitDialog exitDialog;
    AdvertisingDialog advertisingDialog;

    Animation scaleAnimation;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViews();
        init();
        configuration();



    }
    ////////////////////////////////////////////////////////////////////////////////////////

    private void findViews() {

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        rlAdContainer = findViewById(R.id.ad_container);

        lottieAnimationView=findViewById(R.id.lottie_animation_view);

    }

    private void init() {

        pilgrimageFragment = new PilgrimageFragment();
        settingFragment = new SettingFragment();
        narrativesFragment = new NarrativesFragment();
        noticesFragment = new NoticesFragment();
        salawatCountFragment = new SalawatCountFragment();

        aboutUsDialog = new AboutUsDialog(MainActivity.this);
        exitDialog = new ExitDialog(MainActivity.this);
        advertisingDialog = new AdvertisingDialog(MainActivity.this);

        scaleAnimation= AnimationUtils.loadAnimation(this,R.anim.scale_animation);



    }

    private void configuration(){

        setUpLottieAnimation();
        setUpMenu();

    }

    private void setUpLottieAnimation(){

        lottieAnimationView.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        lottieAnimationView.playAnimation();
                    }
                },MyConstants.LOTTIE_ANIMATION_DELAY);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    public void onClick(View view) {

        view.startAnimation(scaleAnimation);

        switch (view.getId()) {

            case R.id.btn_pilgrimage:
                loadFragment(pilgrimageFragment);
                break;


            case R.id.btn_narratives:
                loadFragment(narrativesFragment);
                break;

            case R.id.btn_salawat_count:
                loadFragment(salawatCountFragment);
                break;

            case R.id.btn_notices:

                if (MyConstants.isNetworkAvailable(this))
                    loadFragment(noticesFragment);
                break;

            case R.id.btn_setting:
                loadFragment(settingFragment);
                break;

            case R.id.btn_share_app:
                MyIntent.shareAppIntent(MainActivity.this);
                break;


            case R.id.btn_other_apps:
                MyIntent.otherAppIntent(MainActivity.this);
                break;

            case R.id.btn_comment:
                MyIntent.commentIntent(MainActivity.this);
                break;


            case R.id.btn_exit:
                exitDialog.show();
                break;

            case R.id.btn_toolbar_advertising:
            case R.id.lottie_animation_view:
                if (MyConstants.isNetworkAvailable(this))
                    advertisingDialog.show();
                break;





            case R.id.btn_open_navigation_view:
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;


        }

    }

    private void setUpMenu() {

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.nav_home_page:
                        closeFragment();
                        break;

                    case R.id.nav_pilgrimage:
                        loadFragment(pilgrimageFragment);
                        break;

                    case R.id.nav_salawat_count:
                        loadFragment(salawatCountFragment);
                        break;

                    case R.id.nav_narratives:
                        loadFragment(narrativesFragment);
                        break;

                    case R.id.nav_setting:
                        loadFragment(settingFragment);
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
                        aboutUsDialog.show();
                        break;

                    case R.id.nav_exit:
                        exitDialog.show();
                        break;


                }
                drawerLayout.closeDrawer(Gravity.RIGHT);
                return false;
            }
        });


    }

    private void loadFragment(Fragment fragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                .replace(R.id.fl_fragment_container, fragment)
                .addToBackStack(null).commit();

    }

    private void closeFragment() {
        while (getSupportFragmentManager().getBackStackEntryCount() > 0)
            super.onBackPressed();
    }

    ////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(Gravity.RIGHT))
            drawerLayout.closeDrawer(Gravity.RIGHT);

        else if (getSupportFragmentManager().getBackStackEntryCount() > 0)
            closeFragment();

        else
            exitDialog.show();
    }

    @Override
    protected void onResume() {
        super.onResume();

        MyTapsell.showStandardBanner(this,MyConstants.STANDARD_1_AD_ID, rlAdContainer, TapsellPlusBannerType.BANNER_320x50);
    }

}