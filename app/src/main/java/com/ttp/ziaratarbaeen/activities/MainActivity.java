package com.ttp.ziaratarbaeen.activities;

import android.os.Bundle;
import android.view.Gravity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.fragment.NavHostFragment;

import com.ttp.ziaratarbaeen.R;
import com.ttp.ziaratarbaeen.utils.MyIntent;
import com.ttp.ziaratarbaeen.utils.UseFullMethod;
import com.ttp.ziaratarbaeen.databinding.ActivityMainBinding;
import com.ttp.ziaratarbaeen.dialogs.AboutUsDialog;
import com.ttp.ziaratarbaeen.dialogs.CommentDialog;
import com.ttp.ziaratarbaeen.dialogs.ExitDialog;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private NavHostFragment navHostFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        init();
        setOnClick();
        setUpMenu();


    }
    ////////////////////////////////////////////////////////////////////////////////////////

    private void init() {
        navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragmentContainerView_activityMain);
    }

    private void setOnClick() {

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
                    new CommentDialog(this).show();
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

    @Override
    public void onBackPressed() {

        if (navHostFragment.getNavController().getCurrentDestination().getId() == R.id.mainFragment)
            new ExitDialog(this).show();
        else
            super.onBackPressed();
    }
}