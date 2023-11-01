package com.bus;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("INFO: Started.............");

        float riderMeanTime = 2f * 1000;
        float busMeanTime = 1f * 60 * 1000;

        Resource resource = new Resource();

        Thread riderScheduler = new Thread(new RiderScheduler(resource, riderMeanTime));
        Thread busScheduler = new Thread(new BusScheduler(resource, busMeanTime));

        riderScheduler.start();
        busScheduler.start();

        try {
            busScheduler.join();
            riderScheduler.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}