package com.bus;

import java.util.concurrent.Semaphore;

public class Resource {

    public int waiting_count;
    public Semaphore mutex;
    public Semaphore bus_arrival;
    public Semaphore boarding;
    public Bus current_bus;

    public Resource() {
        this.waiting_count = 0;
        this.mutex = new Semaphore(1);          // to protect rider count in the waiting queue
        this.bus_arrival = new Semaphore(0);    // signal when a bus arrive and wait on bus
        this.boarding = new Semaphore(0);       // wait bus until all boarded
    }
}
