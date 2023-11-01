package com.bus;

public class BusScheduler extends Scheduler implements Runnable{

    private Resource resource;
    private float meanTime;

    public BusScheduler(Resource resource, float meanTime) {
        this.resource = resource;
        this.meanTime = meanTime;
    }

    @Override
    public void run() {
        int busId = 1;
        while (true) {
            try {
                Thread.sleep(calculateWaitingTime(meanTime));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            new Thread(new Bus(busId, resource)).start();
            busId++;
        }
    }
}

