package com.ttp.ziaratarbaeen.classes;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import com.google.gson.Gson;


public class MySharedPreference {


    private static MySharedPreference mySharedPreference;
    private static Gson gson ;


    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;



   // private static final String MENTION_LIST = "mention_list";


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

//    public void putMentionList(ArrayList<Mention > mentionList){
//
//        Type listType = new TypeToken<ArrayList<Mention>>() {
//        }.getType();
//        String strMentionList= gson.toJson(mentionList,listType);
//
//        editor.putString(MENTION_LIST,strMentionList).apply();
//
//
//    }
//
//    public ArrayList<Mention > getMentionList(){
//
//        String strMentionList= sharedPreferences.getString(MENTION_LIST,null);
//        if (strMentionList==null)
//            return new ArrayList<>();
//
//        Type listType = new TypeToken<ArrayList<Mention>>() {
//        }.getType();
//
//        ArrayList<Mention >mentionList =gson.fromJson(strMentionList,listType);
//
//        return mentionList;
//    }
}
