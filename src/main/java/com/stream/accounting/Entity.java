package com.stream.accounting;

public class Entity {
	private String companyId;
	private String entityId;
	private String data;
	public enum ENTITIY_TYPE {RECUR, INVOICE, BUDGET};
	private ENTITIY_TYPE entityType;
	private long amount = -1;
	private int txTypeId = -1;

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	public ENTITIY_TYPE getEntityType() {
		return entityType;
	}

	public void setEntityType(ENTITIY_TYPE entityType) {
		this.entityType = entityType;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public int getTxTypeId() {
		return txTypeId;
	}

	public void setTxTypeId(int txTypeId) {
		this.txTypeId = txTypeId;
	}
	
	
	
}
