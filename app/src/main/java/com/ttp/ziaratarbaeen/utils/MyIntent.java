package com.ttp.ziaratarbaeen.utils;

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

            String shareMessage =
                    context.getString(R.string.app_link_download) + context.getString(R.string.app_name) +
                            context.getString(R.string.market_app_id) + context.getPackageName();

            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);


            context.startActivity(
                    Intent.createChooser(
                            shareIntent,
                            context.getString(R.string.share_app_with)
                    )
            );

        } catch (Exception e) {
            UseFullMethod.showError(context);
        }
    }

    public static void otherAppIntent(Context context) {
        try {
            Intent otherAppIntent = new Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(context.getString(R.string.developer_applications_link))
            );


            context.startActivity(otherAppIntent);
        } catch (Exception e) {
            UseFullMethod.showError(context);
        }
    }

    public static void commentIntent(Context context) {
        try {
            Uri commentUri =
                    Uri.parse(context.getString(R.string.comment_market_link) + context.getPackageName());

            Intent commentIntent = new Intent(Intent.ACTION_EDIT, commentUri);
            context.startActivity(commentIntent);
        } catch (Exception e) {
            Toast.makeText(
                    context,
                    context.getString(R.string.need_install_market),
                    Toast.LENGTH_LONG
            ).show();
        }
    }

    public static void emailIntent(Context context) {

        try {

            Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
            emailIntent.setType("mailto:");

            emailIntent.putExtra(Intent.EXTRA_EMAIL, context.getString(R.string.email_address));
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.app_name));


            context.startActivity(
                    Intent.createChooser(
                            emailIntent,
                            context.getString(R.string.send_email)
                    )
            );

        } catch (Exception e) {
            UseFullMethod.showError(context);
        }
    }
}
