package com.mlimavieira.paintshop.model;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private int numOfColors;
    private final List<Customer> customers = new ArrayList<>();

    public int getNumOfColors() {
        return numOfColors;
    }

    public void setNumOfColors(int numOfColors) {
        this.numOfColors = numOfColors;
    }

    public List<Customer> getCustomers() {
        return customers;
    }
}
