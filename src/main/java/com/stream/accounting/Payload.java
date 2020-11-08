package com.stream.accounting;

import org.apache.avro.generic.GenericData;

import java.util.List;

public class Payload {
    public String companyId;

    public List<GenericData.Record> records;

    public Payload() {

    }

    public Payload(String companyId, List<GenericData.Record> records) {
        this.companyId = companyId;
        this.records = records;
    }

    public String getCompanyId() {
        return companyId;
    }

    public List<GenericData.Record> getRecords() {
        return records;
    }

    @Override
    public String toString() {
        return "Payload{" +
                "companyId='" + companyId + '\'' +
                ", records=" + records +
                '}';
    }
}