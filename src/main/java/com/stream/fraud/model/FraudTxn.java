package com.stream.fraud.model;

public class FraudTxn {

	private String userId;
	private String message;

	public FraudTxn(String userId, String txnCount, String message) {
		this.userId = userId;
		this.message = message;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "FraudTxn{" +
				"userId='" + userId + '\'' +
				", message='" + message + '\'' +
				'}';
	}
}
