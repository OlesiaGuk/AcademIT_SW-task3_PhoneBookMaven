package ru.academits.converter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class IdArraysConverter {
    private Gson gson = new GsonBuilder().create();

    public int[] convertFromJson(String idArrayJson) {
        return gson.fromJson(idArrayJson, int[].class);
    }
}
