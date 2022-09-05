package com.ttp.ziaratarbaeen.classes;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.ttp.ziaratarbaeen.R;

public class UseFullMethod {


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

        boolean connect = activeNetworkInfo != null && activeNetworkInfo.isConnected();

        if (!connect)
            Toast.makeText(context, R.string.need_internet, Toast.LENGTH_SHORT).show();

        return connect;
    }

    public static void safeNavigate(View view, int id) {
        try {
            Navigation.findNavController(view).navigate(id);
        } catch (Exception e) {
            Log.e(MyConstants.ERROR_TAG, e.getMessage());
        }
    }

    public static void safeNavigate(Activity activity,int navHostId, int actionId) {
        try {

            Navigation.findNavController(activity,navHostId).navigate(actionId);
        } catch (Exception e) {
            Log.e(MyConstants.ERROR_TAG, e.getMessage());
        }
    }

}
