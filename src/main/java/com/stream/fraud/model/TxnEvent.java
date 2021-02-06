package com.stream.fraud.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TxnEvent {

    private static final long serialVersionUID = 1L;

    private static final String USER_ID = "userId";
    private static final String AMOUNT = "amount";
    private static final String MILES_FROM_LAST_TXN = "milesFromLastTxn";
    private static final String HOURS_FROM_LAST_TXN = "hoursFromLastTxn";

    private String userId;

    private Double amount;

    private Integer milesFromLastTxn;

    private Integer hoursFromLastTxn;

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

    public int getMilesFromLastTxn() {
        return milesFromLastTxn;
    }

    public void setMilesFromLastTxn(int milesFromLastTxn) {
        this.milesFromLastTxn = milesFromLastTxn;
    }

    public int getHoursFromLastTxn() {
        return hoursFromLastTxn;
    }

    public void setHoursFromLastTxn(int hoursFromLastTxn) {
        this.hoursFromLastTxn = hoursFromLastTxn;
    }

    public Map<String, Double> toMap() {
        Map<String, Double> eventAsMap = new HashMap<>();
        eventAsMap.put(USER_ID, Double.parseDouble(this.userId));
        eventAsMap.put(AMOUNT, this.amount);
        eventAsMap.put(MILES_FROM_LAST_TXN, this.milesFromLastTxn.doubleValue());
        eventAsMap.put(HOURS_FROM_LAST_TXN, this.hoursFromLastTxn.doubleValue());
        return eventAsMap;
    }

    public static List<String> COL_NAMES = Arrays.asList(USER_ID, AMOUNT, MILES_FROM_LAST_TXN, HOURS_FROM_LAST_TXN);
}
