package com.bus;

import java.util.Random;

/**
 * Super class of thread generators
 *
 */

public class Scheduler {

    private static final Random RANDOM =  new Random(); // random float between 0 and 1

    public long calculateWaitingTime(float meanTime){
        float lambda = 1 / meanTime;
        return Math.round(-Math.log(1-RANDOM.nextFloat()) / lambda);
    }
}
