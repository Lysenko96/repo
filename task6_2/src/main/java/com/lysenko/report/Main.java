package com.lysenko.report;

import java.io.File;

public class Main {

	public static void main(String[] args) {
		String report = new TopRacersFormatter().format(getPathInString("com/lysenko/resources/abbreviations.txt"),
				getPathInString("com/lysenko/resources/start.log"), getPathInString("com/lysenko/resources/end.log"));
		System.out.println(report);

	}

	private static String getPathInString(String path) {
		File file = new File(Main.class.getClassLoader().getResource(path).getFile());
		return file.toPath().toString();
	}
}
