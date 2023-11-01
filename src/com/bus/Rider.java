package com.bus;

public class Rider implements Runnable {
    private Resource resource;

    public Rider(Resource resource) {
        this.resource = resource;
    }

    private void getBoarded() {
        System.out.println("RIDER gets boarded to the bus...");
    }

    @Override
    public void run() {
        try {
            resource.mutex.acquire();
            resource.waiting_count += 1;

            System.out.println("RIDER on the waiting area...");

            resource.bus_arrival.acquire();
            getBoarded();

            resource.current_bus.onboarded_count += 1;
            if (resource.current_bus.onboarded_count == 50 || resource.current_bus.onboarded_count == resource.waiting_count){
                resource.waiting_count = Math.max(0,resource.waiting_count-50);
                resource.boarding.release();
            }else{
                resource.bus_arrival.release();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
