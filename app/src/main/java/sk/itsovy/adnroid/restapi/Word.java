package sk.itsovy.adnroid.restapi;

import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Array;
import java.util.Objects;

public class Word {
    private String word;
    private Array phonetics;
    private Array meanings;
    @SerializedName("body")
    private String text;
    public String getWord() {
        return word;
    }

    public Array getPhonetics() {
        return phonetics;
    }

    public Array getMeanings() {
        return meanings;
    }

    public String getText() {
        return text;
    }
}
