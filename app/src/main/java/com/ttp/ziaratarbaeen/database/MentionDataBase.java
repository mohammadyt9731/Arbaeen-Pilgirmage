package com.ttp.ziaratarbaeen.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.ttp.ziaratarbaeen.utils.MyConstants;

@Database(entities = {MentionEntity.class},version = 1)
public abstract class MentionDataBase extends RoomDatabase {

    private static MentionDataBase mentionDataBase;

    public static MentionDataBase getInstance(Context context) {
        if (null == mentionDataBase) {
            mentionDataBase = buildDatabaseInstance(context);
        }
        return mentionDataBase;
    }

    private static MentionDataBase buildDatabaseInstance(Context context) {
        return Room.databaseBuilder(context,
                        MentionDataBase.class,
                        MyConstants.DATABASE_NAME)
                .allowMainThreadQueries().build();
    }
}
