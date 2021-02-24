package com.assignment.cashsales.model;

public class PaymentList_getter_setter {
    private String price, timestamp;

    public PaymentList_getter_setter(String price, String timestamp) {
        this.price = price;
        this.timestamp = timestamp;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
