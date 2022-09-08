package com.ttp.ziaratarbaeen.activities;

import android.os.Bundle;
import android.view.Gravity;

import androidx.appcompat.app.AppCompatActivity;

import com.ttp.ziaratarbaeen.R;
import com.ttp.ziaratarbaeen.classes.MyIntent;
import com.ttp.ziaratarbaeen.classes.UseFullMethod;
import com.ttp.ziaratarbaeen.databinding.ActivityMainBinding;
import com.ttp.ziaratarbaeen.dialogs.AboutUsDialog;
import com.ttp.ziaratarbaeen.dialogs.ExitDialog;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setOnClick();
        setUpMenu();


    }
    ////////////////////////////////////////////////////////////////////////////////////////


    public void setOnClick() {

        binding.ivMenuActivityMain.setOnClickListener(view ->
                binding.drawerLayout.openDrawer(Gravity.RIGHT)
        );

    }

    private void setUpMenu() {

        binding.navigationViewActivityMain.setNavigationItemSelectedListener(item -> {

            switch (item.getItemId()) {

                case R.id.nav_home_page:

                    UseFullMethod.safeNavigate(MainActivity.this
                            , R.id.fragmentContainerView_activityMain, R.id.mainFragment);
                    break;

                case R.id.nav_pilgrimage:
                    UseFullMethod.safeNavigate(MainActivity.this
                            , R.id.fragmentContainerView_activityMain, R.id.pilgrimageFragment);
                    break;

                case R.id.nav_salawat_count:
                    UseFullMethod.safeNavigate(MainActivity.this
                            , R.id.fragmentContainerView_activityMain, R.id.selectMentionFragment);
                    break;

                case R.id.nav_narratives:
                    UseFullMethod.safeNavigate(MainActivity.this
                            , R.id.fragmentContainerView_activityMain, R.id.narrativesFragment);
                    break;

                case R.id.nav_setting:
                    UseFullMethod.safeNavigate(MainActivity.this
                            , R.id.fragmentContainerView_activityMain, R.id.settingFragment);
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
                    new AboutUsDialog(this).show();
                    break;

                case R.id.nav_exit:
                    new ExitDialog(this).show();
                    break;


            }
            binding.drawerLayout.closeDrawer(Gravity.RIGHT);
            return false;
        });


    }


}