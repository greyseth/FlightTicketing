package com.smk.model;

public class Booking {
    private long id;
    private long scheduleId;
    private long pesawatId;
    private String name;
    private double price;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(long scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getPesawatId() {
        return pesawatId;
    }

    public void setPesawatId(long pesawatId) {
        this.pesawatId = pesawatId;
    }
}
