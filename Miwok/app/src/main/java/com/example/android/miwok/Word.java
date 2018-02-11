package com.example.android.miwok;

public class Word {
    String englishTranslation;
    String miworkTranslation;

    public String getEnglishTranslation() {
        return englishTranslation;
    }

    public String getMiworkTranslation() {
        return miworkTranslation;
    }

    public Word(String english, String miwork) {
        this.englishTranslation = english;
        this.miworkTranslation = miwork;
    }
}