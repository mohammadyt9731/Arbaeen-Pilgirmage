package com.ttp.ziaratarbaeen.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;


public class MySharedPreference {


    private static MySharedPreference mySharedPreference;
    private static Gson gson ;


    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;



    private MySharedPreference(Context context) {
        sharedPreferences = context.getSharedPreferences(MyConstants.SETTING_KEY, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public static MySharedPreference getInstance(Context context) {

        gson = new Gson();

        if (mySharedPreference == null)
            mySharedPreference = new MySharedPreference(context);

        return mySharedPreference;
    }



    public void putProgramSetting(ProgramSetting programSetting) {

        String settingJson = gson.toJson(programSetting, ProgramSetting.class);

        editor.putString(MyConstants.PROGRAM_SETTING, settingJson);
        editor.apply();
    }

    public ProgramSetting getProgramSetting() {

        String settingJson = sharedPreferences.getString(MyConstants.PROGRAM_SETTING, null);

        if (settingJson == null)
            return null;

        return gson.fromJson(settingJson, ProgramSetting.class);
    }


}
