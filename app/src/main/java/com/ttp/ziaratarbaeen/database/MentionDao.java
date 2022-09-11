package com.ttp.ziaratarbaeen.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.ttp.ziaratarbaeen.utils.MyConstants;

import java.util.List;

@Dao
public interface MentionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMention(MentionEntity mentionEntity);

    @Update
    void updateMention(MentionEntity mentionEntity);

    @Delete
    void deleteMention(MentionEntity mentionEntity);

    @Query("SELECT * FROM " + MyConstants.TABLE_NAME)
    List<MentionEntity> getAllMention();

    @Query("SELECT * FROM " + MyConstants.TABLE_NAME + " WHERE id=:id")
    MentionEntity getMention(int id);
}
