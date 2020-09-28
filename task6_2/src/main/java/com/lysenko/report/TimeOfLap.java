package com.lysenko.report;

public class TimeOfLap {

	private String minutesFromFile;
	private String secondsFromFile;

	public TimeOfLap(String minutesFromFile, String secondsFromFile) {
		this.minutesFromFile = minutesFromFile;
		this.secondsFromFile = secondsFromFile;
	}

	public String getMinutesFromFile() {
		return minutesFromFile;
	}

	public String getSecondsFromFile() {
		return secondsFromFile;
	}
}