package com.stream.accounting;

import org.apache.flink.api.common.functions.ReduceFunction;

public class AmountAggregator implements ReduceFunction<EntityEvent> {
    @Override
    public EntityEvent reduce(EntityEvent a, EntityEvent b) throws Exception {
    	long amountA = a.getEntity().getAmount();
    	long amountB = b.getEntity().getAmount();
    	if(amountA != -1 && amountB != -1) {
//    		if(a.getEntity().getTxTypeId() == b.getEntity().getTxTypeId()) {
    			a.getEntity().setAmount(amountA+amountB);
//    		 else {
//    			Object obj = a.getProperty("TxTypeId="+b.getEntity().getTxTypeId());
//    			Long val = b.getEntity().getAmount();
//    			if(obj != null) {
//    				val +=Long.parseLong((String) obj);
//    			}
//    			a.setProperty("TxTypeId="+b.getEntity().getTxTypeId(), val);
//    		}
    	}
    	if(amountA == -1) {
    		return b;
    	}
    	return a;
    }
}
