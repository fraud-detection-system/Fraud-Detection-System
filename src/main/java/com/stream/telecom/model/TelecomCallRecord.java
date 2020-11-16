package com.stream.telecom.model;

public class TelecomCallRecord implements Cloneable{
	
	/**
	 * @return the from
	 */
	public String getFrom() {
		return from;
	}
	/**
	 * @param from the from to set
	 */
	public void setFrom(String from) {
		this.from = from;
	}
	/**
	 * @return the to
	 */
	public String getTo() {
		return to;
	}
	/**
	 * @param to the to to set
	 */
	public void setTo(String to) {
		this.to = to;
	}
	/**
	 * @return the startTime
	 */
	public String getStartTime() {
		return startTime;
	}
	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	/**
	 * @return the durationInMillis
	 */
	public String getDurationInMillis() {
		return durationInMillis;
	}
	/**
	 * @param durationInMillis the durationInMillis to set
	 */
	public void setDurationInMillis(String durationInMillis) {
		this.durationInMillis = durationInMillis;
	}
	/**
	 * @return the fromCurrentLocation
	 */
	public String getFromCurrentLocation() {
		return fromCurrentLocation;
	}
	/**
	 * @param fromCurrentLocation the fromCurrentLocation to set
	 */
	public void setFromCurrentLocation(String fromCurrentLocation) {
		this.fromCurrentLocation = fromCurrentLocation;
	}
	/**
	 * @return the toCurrentLocation
	 */
	public String getToCurrentLocation() {
		return toCurrentLocation;
	}
	/**
	 * @param toCurrentLocation the toCurrentLocation to set
	 */
	public void setToCurrentLocation(String toCurrentLocation) {
		this.toCurrentLocation = toCurrentLocation;
	}
	
	/**
	 * @return the currentLocation
	 */
	public String getCurrentLocation() {
		return currentLocation;
	}
	/**
	 * @param currentLocation the currentLocation to set
	 */
	public void setCurrentLocation(String currentLocation) {
		this.currentLocation = currentLocation;
	}
	
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
	//Phone number from which call is being made
	String from;
	//Phone number to which call is being made
	String to;
	//Start time of the call up to milli-seconds, this as epoch (long since 1970)
	String startTime;
	//Duration of calls
	String durationInMillis;
	//Geographical location of caller when call originates
	String fromCurrentLocation;
	//Geographical location of receiver when call originates
	String toCurrentLocation;
	//Internal variable for Flink workflow to store location irrespective of from or to
	String currentLocation;
	
	/**
	 * A Sample record looks like this,
	 * 
	 * 
	 			TelecomCallRecord telecomCallRecord = new TelecomCallRecord();
				telecomCallRecord.setFrom("007");
				telecomCallRecord.setFromCurrentLocation("London");
				telecomCallRecord.setTo("100");
				telecomCallRecord.setToCurrentLocation("Bengaluru");
				telecomCallRecord.setDurationInMillis(""+index);
				telecomCallRecord.setStartTime( ""+(new Date()).getTime());
	 */

}
