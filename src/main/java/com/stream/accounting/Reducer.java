package com.stream.accounting;

import org.apache.flink.api.common.functions.ReduceFunction;

public class Reducer implements ReduceFunction<EntityEvent> {
    @Override
    public EntityEvent reduce(EntityEvent a, EntityEvent b) throws Exception {
    	a.incrementCount();
    	return a;
    }
}
