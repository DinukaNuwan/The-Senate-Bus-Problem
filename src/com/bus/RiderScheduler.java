package com.bus;

public class RiderScheduler extends Scheduler implements Runnable {
    private Resource resource;
    private float meanTime;

    public RiderScheduler(Resource resource, float meanTime) {
        this.resource = resource;
        this.meanTime = meanTime;
    }

    @Override
    public void run() {
        int riderId = 1;
        while (true) {
            try {
                Thread.sleep(calculateWaitingTime(meanTime));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            new Thread(new Rider(riderId, resource)).start();
            riderId++;
        }
    }
}


