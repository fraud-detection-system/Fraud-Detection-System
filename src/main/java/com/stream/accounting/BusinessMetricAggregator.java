package com.stream.accounting;

import org.apache.flink.api.common.functions.FoldFunction;

public class BusinessMetricAggregator implements FoldFunction<EntityEvent, BusinessMetric> {
	@Override
	public BusinessMetric fold(BusinessMetric acc, EntityEvent value) {
		if (acc == null) {
			return new BusinessMetric("amount for transaction type ID = "+ value.getEntity().getTxTypeId(), value.getEntity().getAmount());
		}

		long amountA = acc.getLongValue();
		long amountB = value.getEntity().getAmount();
		if (amountA != -1 && amountB != -1) {
			acc.setLongValue(amountA + amountB);
		}
		if (amountA == -1) {
			return new BusinessMetric("amount for transaction type ID = " + value.getEntity().getTxTypeId(), value.getEntity().getAmount());
		}
		return acc;
	}
}
