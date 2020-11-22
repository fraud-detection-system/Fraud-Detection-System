package com.stream.fraud.model;

public class FraudTxn {

	private String userId;
	private String txnCount;
	private String message;

	public FraudTxn(String userId, String txnCount, String message) {
		this.userId = userId;
		this.txnCount = txnCount;
		this.message = message;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTxnCount() {
		return txnCount;
	}

	public void setTxnCount(String txnCount) {
		this.txnCount = txnCount;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
