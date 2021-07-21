package com.company.numberguessinggame;

public class RandomNbrGenerator {
    public int generateRandomInt(int max) {
        int min = 1;
        int range = max - min + 1;
        return (int) (Math.random() * range) + 1;
    }
}
