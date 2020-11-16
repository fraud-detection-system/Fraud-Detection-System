package com.stream.telecom;

import com.stream.StreamProcessingSystem;

public class TelecomSystem extends StreamProcessingSystem {
	public enum MessageType { STRING, JSON};
	public static final MessageType messageType = MessageType.JSON;
	public static final String SYSTEM_NAME = "Telecom";
	public static final int WINDOW_SIZE=2;
	public static final int LOW_THRESHOLD =  2;
	public static final int HIGH_THRESHOLD =  4;
	
	public static final String FROM_LOCATION_ALERT_FILE_DIR = "alerts-from-location";
	public static final String TO_LOCATION_ALERT_FILE_DIR = "alerts-to-location";
	public static final String BOTH_LOCATION_ALERT_FILE_DIR = "alerts-both-location";
	public static final String FROM_ALERT_FILE_DIR = "alerts-from";
	public static final String TO_ALERT_FILE_DIR = "alerts-to";

}
