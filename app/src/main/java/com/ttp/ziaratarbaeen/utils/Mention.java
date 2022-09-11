package com.ttp.ziaratarbaeen.utils;

public class Mention {

    String mentionTitle;

    int maximumNumber;
    int counter;

    public Mention(String mentionName, int maximum, int counter) {
        this.mentionTitle = mentionName;
        this.maximumNumber = maximum;
        this.counter = counter;
    }

    public String getMentionTitle() {
        return mentionTitle;
    }

    public void setMentionTitle(String mentionTitle) {
        this.mentionTitle = mentionTitle;
    }



    public int getMaximumNumber() {
        return maximumNumber;
    }

    public void setMaximumNumber(int maximumNumber) {
        this.maximumNumber = maximumNumber;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}

