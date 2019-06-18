package com.example.shortlink.util;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RandomCharacter {

    private final static char[] characters = {
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    private final static int charactersLength = characters.length;

    //    private static final ThreadLocalRandom random = ThreadLocalRandom.current();
    private static final Random random = new Random();

    public static String getRandomString(int len) {
        if (len < 1) return "";
//        ThreadLocalRandom random = ThreadLocalRandom.current();
        StringBuilder sb = new StringBuilder(len);
        for (int f = 0; f < len; f++) {
            sb.append(characters[random.nextInt(charactersLength)]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) System.out.println(getRandomString(10));
    }
}
