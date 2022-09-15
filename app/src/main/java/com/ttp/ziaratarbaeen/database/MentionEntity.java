package com.ttp.ziaratarbaeen.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.ttp.ziaratarbaeen.utils.MyConstants;

@Entity(tableName = MyConstants.TABLE_NAME)
public class MentionEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private int maximumNumber;
    private int counter;

    public MentionEntity(int id, String title, int maximumNumber) {
        this.id = id;
        this.title = title;
        this.maximumNumber = maximumNumber;
        this.counter = 0;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
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

    public int getMaximumNumber() {
        return maximumNumber;
    }

    public void setMaximumNumber(int maximumNumber) {
        this.maximumNumber = maximumNumber;
    }
}
