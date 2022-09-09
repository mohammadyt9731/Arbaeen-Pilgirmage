package com.ttp.ziaratarbaeen.utils;

public class Mention {

    String mentionTitle;
    String mentionTranslation;

    int maximum;
    int counter;

    public Mention(String mentionName,String mentionTranslation, int maximum, int counter) {
        this.mentionTitle = mentionName;
        this.mentionTranslation = mentionTranslation;
        this.maximum = maximum;
        this.counter = counter;
    }

    public String getMentionTitle() {
        return mentionTitle;
    }

    public void setMentionTitle(String mentionTitle) {
        this.mentionTitle = mentionTitle;
    }

    public String getMentionTranslation() {
        return mentionTranslation;
    }

    public void setMentionTranslation(String mentionTranslation) {
        this.mentionTranslation = mentionTranslation;
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

