package com.ttp.ziaratarbaeen.activities;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.navigation.NavigationView;
import com.ttp.ziaratarbaeen.R;
import com.ttp.ziaratarbaeen.classes.MyIntent;
import com.ttp.ziaratarbaeen.classes.UseFullMethod;
import com.ttp.ziaratarbaeen.databinding.ActivityMainBinding;
import com.ttp.ziaratarbaeen.dialogs.AboutUsDialog;
import com.ttp.ziaratarbaeen.dialogs.ExitDialog;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    AboutUsDialog aboutUsDialog;
    ExitDialog exitDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        init();
        configuration();


    }
    ////////////////////////////////////////////////////////////////////////////////////////


    private void init() {


        aboutUsDialog = new AboutUsDialog(MainActivity.this);
        exitDialog = new ExitDialog(MainActivity.this);


    }

    private void configuration() {


        setUpMenu();

    }


    public void onClick(View view) {


        switch (view.getId()) {


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

                        UseFullMethod.safeNavigate(MainActivity.this
                                , R.id.fragmentContainerView, R.id.mainFragment);
                        break;

                    case R.id.nav_pilgrimage:
                        UseFullMethod.safeNavigate(MainActivity.this
                                , R.id.fragmentContainerView, R.id.pilgrimageFragment);
                        break;

                    case R.id.nav_salawat_count:
                        UseFullMethod.safeNavigate(MainActivity.this
                                , R.id.fragmentContainerView, R.id.selectMentionFragment);
                        break;

                    case R.id.nav_narratives:
                        UseFullMethod.safeNavigate(MainActivity.this
                                , R.id.fragmentContainerView, R.id.narrativesFragment);
                        break;

                    case R.id.nav_setting:
                        UseFullMethod.safeNavigate(MainActivity.this
                                , R.id.fragmentContainerView, R.id.settingFragment);
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


}