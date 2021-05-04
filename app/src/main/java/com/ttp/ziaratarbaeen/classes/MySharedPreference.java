package com.ttp.ziaratarbaeen.classes;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class MySharedPreference {

    private static MySharedPreference mySharedPreference;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    private MySharedPreference(Context context) {
        sharedPreferences = context.getSharedPreferences("setting", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public static MySharedPreference getInstance(Context context) {

        if (mySharedPreference == null)
            mySharedPreference = new MySharedPreference(context);

        return mySharedPreference;
    }

    public void putProgramSetting(ProgramSetting programSetting) {

        Gson gson = new Gson();
        String setting = gson.toJson(programSetting, ProgramSetting.class);

        editor.putString("program_setting", setting);
        editor.apply();
    }

    public ProgramSetting getProgramSetting() {

        String programSetting = sharedPreferences.getString("program_setting", null);

        if (programSetting == null)
            return null;

        Gson gson = new Gson();
        return gson.fromJson(programSetting, ProgramSetting.class);
    }
}
