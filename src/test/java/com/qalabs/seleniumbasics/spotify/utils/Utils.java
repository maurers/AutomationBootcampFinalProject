package com.qalabs.seleniumbasics.spotify.utils;

import org.apache.commons.lang3.RandomStringUtils;

public class Utils {

    public static String generateEmail(String domain, int length) {
        return RandomStringUtils.random(length, "abcdefghijklmnopqrstuvwxyz") + "@" + domain;
    }
}
