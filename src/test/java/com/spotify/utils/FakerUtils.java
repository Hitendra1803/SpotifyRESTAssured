package com.spotify.utils;

import com.github.javafaker.Faker;

public class FakerUtils {

    public static String generateName(){
        Faker faker = new Faker();
       return"Playlist "+ faker.regexify("[A-Za_z ,_-]{10}");
    }

    public static String generateDescription(){
        Faker faker = new Faker();
        return"Description "+ faker.regexify("[A-Za_z ,_-]{50}");
    }
}
