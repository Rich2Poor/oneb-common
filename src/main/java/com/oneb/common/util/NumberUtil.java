package com.oneb.common.util;

import java.util.Random;

public class NumberUtil {

    public static final Random RANDOM = new Random();

    public static int randomInt(int min, int max) {
        return RANDOM.nextInt((max - min) + 1) + min;
    }
}
