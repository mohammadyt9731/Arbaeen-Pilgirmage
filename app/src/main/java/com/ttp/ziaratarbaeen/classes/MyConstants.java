package com.ttp.ziaratarbaeen.classes;

import android.content.Context;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.ttp.ziaratarbaeen.R;

public class MyConstants {

    //Main Activity
    public static final int LOTTIE_ANIMATION_DELAY = 10000;


    //MySharedPreferences
    public static final String SETTING_KEY="setting";
    public static final String PROGRAM_SETTING="program_setting";


    
    //Program Setting
    public static final boolean AUTO_SCROLL =true;
    public static final boolean SHOW_TRANSLATION =true;
    public static final boolean SHOW_SEPARATOR =true;
    public static final boolean DARK_THEME =false;

    public static final int DEFAULT_ARABIC_TEXT_SIZE = 22;
    public static final int DEFAULT_PERSIAN_TEXT_SIZE = 20;

    public static final float DEFAULT_TEXT_LINE_SPACE = 1.0f;

    public static final int DEFAULT_ARABIC_FONT_ID = R.font.nabi;
    public static final int DEFAULT_PERSIAN_FONT_ID = R.font.b_nazanin;




    //SettingFragment
    public static final int ARABIC_FONT_1=R.font.homa;
    public static final int ARABIC_FONT_2=R.font.morvarid;
    public static final int ARABIC_FONT_3=R.font.nabi;

    public static final int PERSIAN_FONT_1 =R.font.ferdosi;
    public static final int PERSIAN_FONT_2=R.font.b_nazanin;
    public static final int PERSIAN_FONT_3=R.font.b_hamid;




    //PilgrimageFragment
    public static final int MAXIMUM_TEXT_SIZE = 28;
    public static final int MINIMUM_TEXT_SIZE = 14;





    public static final String TAPSELL_KEY = "eidqinttshkgrmaferdbgcbomfmkoctfaeisbcacrjhdlteafdjiebrmbpltkmlfpehqnt";

    public static final String HOME_PAGE_AD_ID = "5f8e290f4203f300016aaefb";
    public static final String EXIT_DIALOG_AD_ID = "5f94ad25fe1e3b0001c688de";
    public static final String STANDARD_1_AD_ID = "5f961560c0df4d00014b5a82";
    public static final String STANDARD_2_AD_ID = "6067a697260b920001cc7ebe";
    public static final String STANDARD_3_AD_ID = "6067a6b4260b920001cc7ebf";

    public static final String Interstitial_VIDEO_AD_ID = "606cd37ce9487f000165d37a";
    public static final String Interstitial_AD_ID = "606cd165d06801000199485a";

    public static final String NATIVE_VIDEO_AD_ID = "6067a77d260b920001cc7ec0";
    public static final String NATIVE_STANDARD_AD_ID = "608412e3bf5fdd0001c09651";


    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        boolean connect=activeNetworkInfo != null && activeNetworkInfo.isConnected();

        if(!connect)
            Toast.makeText(context, R.string.need_internet, Toast.LENGTH_SHORT).show();

        return connect;
    }

}
