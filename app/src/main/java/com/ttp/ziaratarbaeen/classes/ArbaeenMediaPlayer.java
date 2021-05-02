package com.ttp.ziaratarbaeen.classes;

import android.content.Context;
import android.media.MediaPlayer;

import com.ttp.ziaratarbaeen.R;

public class ArbaeenMediaPlayer {

    static MediaPlayer mediaPlayer;


    public static MediaPlayer getMediaPlayer(Context context) {

        mediaPlayer = MediaPlayer.create(context, R.raw.z_arbaein_sedghi);
        return mediaPlayer;
    }


    public static int getCurrentPosition(int index) {
        if (index == 0) return 0;
        else if (index == 1) return 36;
        else if (index == 2) return 51;
        else if (index == 3) return 68;
        else if (index == 4) return 78;
        else if (index == 5) return 98;
        else if (index == 6) return 112;
        else if (index == 7) return 133;
        else if (index == 8) return 147;
        else if (index == 9) return 161;
        else if (index == 10) return 178;
        else if (index == 11) return 188;
        else if (index == 12) return 200;
        else if (index == 13) return 219;
        else if (index == 14) return 229;
        else if (index == 15) return 242;
        else if (index == 16) return 263;
        else if (index == 17) return 278;
        else if (index == 18) return 294;
        else if (index == 19) return 309;
        else if (index == 20) return 320;
        else if (index == 21) return 337;
        else if (index == 22) return 350;
        else if (index == 23) return 365;
        else if (index == 24) return 377;
        else if (index == 25) return 398;
        else if (index == 26) return 415;
        else if (index == 27) return 434;
        else if (index == 28) return 442;
        else if (index == 29) return 454;

        return 1;

    }

    public static int getCurrentIndex(int position) {

        if (position == 0) return 0;
        else if (position == 36) return 1;
        else if (position == 51) return 2;
        else if (position == 68) return 3;
        else if (position == 78) return 4;
        else if (position == 98) return 5;
        else if (position == 112) return 6;
        else if (position == 133) return 7;
        else if (position == 147) return 8;
        else if (position == 161) return 9;
        else if (position == 178) return 10;
        else if (position == 188) return 11;
        else if (position == 200) return 12;
        else if (position == 219) return 13;
        else if (position == 229) return 14;
        else if (position == 242) return 15;
        else if (position == 263) return 16;
        else if (position == 278) return 17;
        else if (position == 294) return 18;
        else if (position == 309) return 19;
        else if (position == 320) return 20;
        else if (position == 337) return 21;
        else if (position == 350) return 22;
        else if (position == 365) return 23;
        else if (position == 377) return 24;
        else if (position == 398) return 25;
        else if (position == 415) return 26;
        else if (position == 434) return 27;
        else if (position == 442) return 28;
        else if (position == 454) return 29;

        return -1;
    }

    public static int getIndex(int position) {

        if (position < 36) return 0;
        else if (position < 51) return 1;
        else if (position < 68) return 2;
        else if (position < 78) return 3;
        else if (position < 98) return 4;
        else if (position < 112) return 5;
        else if (position < 133) return 6;
        else if (position < 147) return 7;
        else if (position < 161) return 8;
        else if (position < 178) return 9;
        else if (position < 188) return 10;
        else if (position < 200) return 11;
        else if (position < 219) return 12;
        else if (position < 229) return 13;
        else if (position < 242) return 14;
        else if (position < 263) return 15;
        else if (position < 278) return 16;
        else if (position < 294) return 17;
        else if (position < 309) return 18;
        else if (position < 320) return 19;
        else if (position < 337) return 20;
        else if (position < 350) return 21;
        else if (position < 365) return 22;
        else if (position < 377) return 23;
        else if (position < 398) return 24;
        else if (position < 415) return 25;
        else if (position < 434) return 26;
        else if (position < 442) return 27;
        else if (position < 454) return 28;
        else return 29;

    }

}
