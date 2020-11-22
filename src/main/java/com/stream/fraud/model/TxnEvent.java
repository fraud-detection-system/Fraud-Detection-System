package com.stream.fraud.model;

public class TxnEvent {

    private static final long serialVersionUID = 1L;

    private String userId;

    private Double amount;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
