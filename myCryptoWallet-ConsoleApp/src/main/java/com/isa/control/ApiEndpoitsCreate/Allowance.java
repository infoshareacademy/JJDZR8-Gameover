package com.isa.control.ApiEndpoitsCreate;

public class Allowance {
    private float cost;
    private float remaining;
    private String upgrade;


    // Getter Methods

    public float getCost() {
        return cost;
    }

    public float getRemaining() {
        return remaining;
    }

    public String getUpgrade() {
        return upgrade;
    }

    // Setter Methods

    public void setCost(float cost) {
        this.cost = cost;
    }

    public void setRemaining(float remaining) {
        this.remaining = remaining;
    }

    public void setUpgrade(String upgrade) {
        this.upgrade = upgrade;
    }
}
