package com.ttp.ziaratarbaeen.activities;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.navigation.NavigationView;
import com.ttp.ziaratarbaeen.R;
import com.ttp.ziaratarbaeen.classes.MyIntent;
import com.ttp.ziaratarbaeen.databinding.ActivityMainBinding;
import com.ttp.ziaratarbaeen.dialogs.AboutUsDialog;
import com.ttp.ziaratarbaeen.dialogs.ExitDialog;
import com.ttp.ziaratarbaeen.fragments.NarrativesFragment;
import com.ttp.ziaratarbaeen.fragments.PilgrimageFragment;
import com.ttp.ziaratarbaeen.fragments.SalawatCountFragment;
import com.ttp.ziaratarbaeen.fragments.SettingFragment;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    PilgrimageFragment pilgrimageFragment;
    SettingFragment settingFragment;
    NarrativesFragment narrativesFragment;
    SalawatCountFragment salawatCountFragment;

    AboutUsDialog aboutUsDialog;
    ExitDialog exitDialog;


    Animation scaleAnimation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_main);

        init();
        configuration();


    }
    ////////////////////////////////////////////////////////////////////////////////////////


    private void init() {

        pilgrimageFragment = new PilgrimageFragment();
        settingFragment = new SettingFragment();
        narrativesFragment = new NarrativesFragment();
        salawatCountFragment = new SalawatCountFragment();

        aboutUsDialog = new AboutUsDialog(MainActivity.this);
        exitDialog = new ExitDialog(MainActivity.this);

        scaleAnimation = AnimationUtils.loadAnimation(this, R.anim.animation_scale);

    }

    private void configuration() {


        setUpMenu();

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


            case R.id.btn_open_navigation_view:
                binding.drawerLayout.openDrawer(Gravity.RIGHT);
                break;


        }

    }

    private void setUpMenu() {

        binding.navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
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
                binding.drawerLayout.closeDrawer(Gravity.RIGHT);
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
        //   while (getSupportFragmentManager().getBackStackEntryCount() > 0)
        super.onBackPressed();
    }

    ////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void onBackPressed() {

        if (binding.drawerLayout.isDrawerOpen(Gravity.RIGHT))
            binding.drawerLayout.closeDrawer(Gravity.RIGHT);

        else if (getSupportFragmentManager().getBackStackEntryCount() > 0)
            closeFragment();

        else
            exitDialog.show();
    }

}