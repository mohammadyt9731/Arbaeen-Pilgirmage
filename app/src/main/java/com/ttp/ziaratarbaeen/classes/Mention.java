package com.ttp.ziaratarbaeen.classes;

public class Mention {

    String mentionName;
    int maximum;
    int counter;

    public Mention(String mentionName, int maximum, int counter) {
        this.mentionName = mentionName;
        this.maximum = maximum;
        this.counter = counter;
    }

    public String getMentionName() {
        return mentionName;
    }

    public void setMentionName(String mentionName) {
        this.mentionName = mentionName;
    }

    public int getMaximum() {
        return maximum;
    }

    public void setMaximum(int maximum) {
        this.maximum = maximum;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}

