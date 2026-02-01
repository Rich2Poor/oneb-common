package com.oneb.common.util;

import java.util.Random;

public class NumberUtil {

    public static final Random RANDOM = new Random();

    public static int randomInt(int min, int max) {
        return RANDOM.nextInt((max - min) + 1) + min;
    }

    public static long randomLong(long min, long max) {
        return RANDOM.nextLong((max - min) + 1) + min;
    }
}
