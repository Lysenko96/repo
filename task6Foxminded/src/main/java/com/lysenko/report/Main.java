package com.lysenko.report;

public class Main {

	public static void main(String[] args) {
		String report = new Report().createReport();
		System.out.println(report);
	}
}
