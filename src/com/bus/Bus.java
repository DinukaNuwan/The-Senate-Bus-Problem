package com.bus;

public class Bus implements Runnable {
    private final int busId;
    private Resource resource;
    public int onboarded_count = 0;

    public Bus(int busId, Resource resource) {
        this.busId = busId;
        this.resource = resource;
    }

    private void depart() {
        System.out.println("BUS INFO: " + onboarded_count + " riders boarded to the bus.");
        System.out.println("BUS INFO: " + resource.waiting_count + " riders are left at the bus stop.");
        System.out.println("BUS INFO: Bus " + busId + " Departed!\n");
    }

    @Override
    public void run() {
        try {
            resource.mutex.acquire();   // Ensuring new passengers will not boarded to the current bus
            System.out.println("\nBUS INFO: Bus " + busId + " Arrived!");
            System.out.println("BUS INFO: " + resource.waiting_count + " riders are waiting for the bus.");
            if (resource.waiting_count > 0) {
                resource.current_bus = this;
                resource.bus_arrival.release(); // Signal riders about the new bus arrived
                resource.boarding.acquire();    // Wait until all the eligible passengers are onboarded
            }
            depart();
            resource.mutex.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
