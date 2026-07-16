package com.miltonrodx;

public class Expense {
    // Attributes
    private int id;
    private String description;
    private double amount;
    private String date;

    // Methods
    // Getters and setters
    // Getters
    public int getId () {
        return id;
    }
    public String getDescription () {
        return description;
    }
    public double getAmount () {
        return amount;
    }
    public String getDate () {
        return date;
    }

    // Setters
    public void setId (int id) {
        this.id = id;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public void setDate(String date) {
        this.date = date;
    }
}
