package com.ttp.ziaratarbaeen.classes;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MySharedPreference {


    private static MySharedPreference mySharedPreference;
    private static Gson gson ;


    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    private final String SETTING="setting";
    private final String PROGRAM_SETTING="program_setting";
    private static final String MENTION_LIST = "mention_list";


    private MySharedPreference(Context context) {
        sharedPreferences = context.getSharedPreferences(SETTING, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public static MySharedPreference getInstance(Context context) {

        gson = new Gson();
        if (mySharedPreference == null)
            mySharedPreference = new MySharedPreference(context);

        return mySharedPreference;
    }



    public void putProgramSetting(ProgramSetting programSetting) {

        Log.i("aaaaa","aa");
        String settingJson = gson.toJson(programSetting, ProgramSetting.class);

        editor.putString(PROGRAM_SETTING, settingJson);
        editor.apply();
    }

    public ProgramSetting getProgramSetting() {
        Log.i("aaaaa","ggg");
        String settingJson = sharedPreferences.getString(PROGRAM_SETTING, null);

        if (settingJson == null)
            return null;

        return gson.fromJson(settingJson, ProgramSetting.class);
    }

    public void putMentionList(ArrayList<Mention > mentionList){

        Type listType = new TypeToken<ArrayList<Mention>>() {
        }.getType();
        String strMentionList= gson.toJson(mentionList,listType);

        editor.putString(MENTION_LIST,strMentionList).apply();


    }

    public ArrayList<Mention > getMentionList(){

        String strMentionList= sharedPreferences.getString(MENTION_LIST,null);
        if (strMentionList==null)
            return new ArrayList<>();

        Type listType = new TypeToken<ArrayList<Mention>>() {
        }.getType();

        ArrayList<Mention >mentionList =gson.fromJson(strMentionList,listType);

        return mentionList;
    }
}
