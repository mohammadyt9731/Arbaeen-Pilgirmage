package com.ttp.ziaratarbaeen.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ttp.ziaratarbaeen.R;

public class SettingFragment extends Fragment {


    BottomNavigationView bottomNav;
    FragmentManager fragmentManager;


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
    }

    private void configuration() {

        mainSettingFragment = new MainSettingFragment();
        arabicFontFragment = new ArabicFontFragment();
        persianFontFragment = new PersianFontFragment();

        fragmentManager = getActivity().getSupportFragmentManager();
        loadFragment(mainSettingFragment);


    }

    private void setOnClick() {

        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                item.setChecked(true);

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

        fragmentManager.beginTransaction().replace(R.id.fl_fragment_container, fragment).addToBackStack(null).commit();
    }


}
