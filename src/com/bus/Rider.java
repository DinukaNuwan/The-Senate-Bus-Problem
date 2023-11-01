package com.bus;

public class Rider implements Runnable {
    private final int riderId;
    private Resource resource;

    public Rider(int riderId, Resource resource) {
        this.riderId = riderId;
        this.resource = resource;
    }

    private void getBoarded() {
        System.out.println("Rider " + riderId + " gets boarded to the bus.");
    }

    @Override
    public void run() {
        try {
            resource.mutex.acquire();   // Ensuring only one passenger enter the bus stop at a time
            resource.waiting_count += 1;
            System.out.println("New rider " + riderId + " arrived. " + resource.waiting_count + " riders are waiting.");
            resource.mutex.release();

            resource.bus_arrival.acquire();
            getBoarded();

            resource.current_bus.onboarded_count += 1;
            if (resource.current_bus.onboarded_count == 50 || resource.current_bus.onboarded_count == resource.waiting_count){
                resource.waiting_count = Math.max(0,resource.waiting_count-50);
                resource.boarding.release();
            } else {
                resource.bus_arrival.release();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
