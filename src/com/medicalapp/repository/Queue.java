package com.medicalapp.repository;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Queue implements Serializable {
    private static final long serialVersionUID = 1L;

    private final List<String> list = new LinkedList<>();

    public synchronized void put(String alert) {
        list.add(alert);
        this.notifyAll();
    }

    public synchronized String take() throws InterruptedException {
        while (list.isEmpty()) {
            // daca nu sunt alerte, asteptam pana apare una
            this.wait();
        }
        return list.remove(0); // FIFO
    }

    public synchronized boolean isEmpty() {
        return list.isEmpty();
    }
}