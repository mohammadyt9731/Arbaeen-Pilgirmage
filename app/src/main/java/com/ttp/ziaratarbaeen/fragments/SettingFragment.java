package com.ttp.ziaratarbaeen.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.tabs.TabLayout;
import com.ttp.ziaratarbaeen.R;
import com.ttp.ziaratarbaeen.classes.MySharedPreference;
import com.ttp.ziaratarbaeen.classes.ProgramSetting;
import com.ttp.ziaratarbaeen.classes.ViewPagerAdapter;

public class SettingFragment extends Fragment {


    BottomNavigationView bottomNav;
    FragmentManager fragmentManager;

  //  TabLayout tabLayoutSetting;
  //  ViewPager viewPagerSetting;


    MainSettingFragment mainSettingFragment;
    PersianFontFragment persianFontFragment;
    ArabicFontFragment arabicFontFragment;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_setting, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findViews(view);
        configuration();
        setOnClick();

    }

    private void findViews(View view) {

        bottomNav = view.findViewById(R.id.navigation_bottom);
      //  tabLayoutSetting=view.findViewById(R.id.tab_layout_setting);
      //  viewPagerSetting=view.findViewById(R.id.view_pager_setting);

    }

    private void configuration() {

        mainSettingFragment = new MainSettingFragment();
        arabicFontFragment = new ArabicFontFragment();
        persianFontFragment = new PersianFontFragment();

     //   tabLayoutSetting.setupWithViewPager(viewPagerSetting);


//        ViewPagerAdapter viewPagerAdapter=new ViewPagerAdapter(getChildFragmentManager());
//        viewPagerAdapter.addToList(mainSettingFragment,"تنظیمات");
//        viewPagerAdapter.addToList(arabicFontFragment,"متن عربی");
//        viewPagerAdapter.addToList(persianFontFragment,"متن فارسی");
//
//        viewPagerSetting.setAdapter(viewPagerAdapter);
//
//        tabLayoutSetting.setupWithViewPager(viewPagerSetting);

        fragmentManager = getActivity().getSupportFragmentManager();
        loadFragment(mainSettingFragment);


    }

    private void setOnClick() {

        bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected( MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.nav_bottom_setting:
                        loadFragment(mainSettingFragment);
                        break;

                    case R.id.nav_bottom_persian_font:
                        loadFragment(persianFontFragment);
                        break;

                    case R.id.nav_bottom_arabic_font:
                        loadFragment(arabicFontFragment);
                        break;


                }

                return false;
            }
        });


    }

    private void loadFragment(Fragment fragment) {

        Log.i("aaaaa","sss");
        fragmentManager.beginTransaction().replace(R.id.fl_fragment_container, fragment).addToBackStack(null).commit();
    }


}
