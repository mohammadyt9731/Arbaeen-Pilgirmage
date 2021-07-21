package com.ttp.ziaratarbaeen.classes;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONObject;

public class MySharedPreference {

    private static MySharedPreference mySharedPreference;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    final String SETTING="setting";
    final String PROGRAM_SETTING="program_setting";

    private MySharedPreference(Context context) {
        sharedPreferences = context.getSharedPreferences(SETTING, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public static MySharedPreference getInstance(Context context) {

        if (mySharedPreference == null)
            mySharedPreference = new MySharedPreference(context);

        return mySharedPreference;
    }

    public void putProgramSetting(ProgramSetting programSetting) {

        Gson gson = new Gson();
        String settingJson = gson.toJson(programSetting, ProgramSetting.class);

        editor.putString(PROGRAM_SETTING, settingJson);
        editor.apply();
    }

    public ProgramSetting getProgramSetting() {

        String settingJson = sharedPreferences.getString(PROGRAM_SETTING, null);

        if (settingJson == null)
            return null;

        Gson gson = new Gson();
        return gson.fromJson(settingJson, ProgramSetting.class);
    }
}
