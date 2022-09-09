package com.ttp.ziaratarbaeen.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.navigation.Navigation;

import com.ttp.ziaratarbaeen.R;

public class UseFullMethod {


    public static int getScreenWidth( int dialogWidthPercentage) {
        return Resources.getSystem().getDisplayMetrics().widthPixels * dialogWidthPercentage /100;
    }


    public static void safeNavigate(View view, int id) {
        try {
            Navigation.findNavController(view).navigate(id);
        } catch (Exception e) {
            Log.e(MyConstants.ERROR_TAG, e.getMessage());
        }
    }

    public static void safeNavigate(Activity activity, int navHostId, int actionId) {
        try {

            Navigation.findNavController(activity, navHostId).navigate(actionId);
        } catch (Exception e) {
            Log.e(MyConstants.ERROR_TAG, e.getMessage());
        }
    }

    public static void showError(Context context) {
        Toast.makeText(context, context.getString(R.string.unknown_error), Toast.LENGTH_SHORT).show();
    }

}
