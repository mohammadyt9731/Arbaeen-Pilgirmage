package com.ttp.ziaratarbaeen.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.ttp.ziaratarbaeen.utils.MyConstants;

@Entity(tableName = MyConstants.TABLE_NAME)
public class MentionEntity {
    @PrimaryKey
    private int id;
    private String title;
    private String maximumNumber;

    public MentionEntity(int id, String title, String maximumNumber) {
        this.id = id;
        this.title = title;
        this.maximumNumber = maximumNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMaximumNumber() {
        return maximumNumber;
    }

    public void setMaximumNumber(String maximumNumber) {
        this.maximumNumber = maximumNumber;
    }
}
