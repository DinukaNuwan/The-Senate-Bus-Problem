package com.bus;

public class BusScheduler extends Scheduler implements Runnable{

    private Resource resource;
    private float meanTime;

    public BusScheduler(Resource resource, Float meanTime) {
        this.resource = resource;
        this.meanTime = meanTime;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(calculateWaitingTime(meanTime));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            new Thread(new Bus(resource)).start();
        }
    }
}

