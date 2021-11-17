package com.ttp.ziaratarbaeen.classes;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import com.ttp.ziaratarbaeen.R;

public class MyIntent {


    public static void shareAppIntent(Context context) {
        try {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");

            String shareMessage = "لینک دانلود برنامه "
                    +context.getApplicationInfo().loadLabel(context.getPackageManager()).toString() + "\n\n"
                    + "http://cafebazaar.ir/app/?id=" + "com.ttp.salawatcount" + "\n\n";

            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
            context.startActivity(Intent.createChooser(shareIntent, "اشتراک گذاری برنامه"));
        } catch (Exception e) {
            Toast.makeText(context, R.string.error, Toast.LENGTH_SHORT).show();
        }
    }

    public static void otherAppIntent(Context context) {

        if (UseFullMethod.isNetworkAvailable(context)) {
            Intent otherAppIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://cafebazaar.ir/developer/mohammad9731"));
            context.startActivity(otherAppIntent);
        }
    }

    public static void commentIntent(Context context) {
        try {
            Intent commentIntent = new Intent(Intent.ACTION_EDIT, Uri.parse("bazaar://details?id="+ "com.ttp.ziaratarbaeen"));
            context.startActivity(commentIntent);
        } catch (Exception e) {
            Toast.makeText(context, R.string.need_install_cafebazaar, Toast.LENGTH_LONG).show();
        }
    }

}
