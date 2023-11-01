package com.bus;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("INFO: Started.............");

        float busMeanTime = 0.1f * 60 * 1000;
        float riderMeanTime = 3f*1000;

        Resource resource = new Resource();

        Thread busScheduler = new Thread(new BusScheduler(resource, busMeanTime));
        Thread riderScheduler = new Thread(new RiderScheduler(resource, riderMeanTime));

        busScheduler.start();

        try {
            busScheduler.join();
            riderScheduler.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("INFO: Terminated..........");
    }

}