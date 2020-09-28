package com.lysenko.report;

import static java.lang.System.lineSeparator;

import java.util.List;

public class TopRacersFormatter {

	public String format(String abbreviataionsFile, String startFile, String endFile) {
		return returnReportInString(new RacerRepository().getRacers(abbreviataionsFile, startFile, endFile));
	}

	private String returnReportInString(List<String> sortedListReport) {
		int idDriver = 1;
		StringBuilder reportInString = new StringBuilder();
		for (String racer : sortedListReport) {
			String timeReplaceSign = racer.replace(".", ":");
			String timeInReport = returnValueOfTimeInReport(timeReplaceSign);
			if (idDriver == 16) {
				reportInString.append(
						"------------------------------------------------------------------------" + lineSeparator());
			}
			if (idDriver >= 10) {
				reportInString.append(idDriver + countSpace(".", 0) + " " + timeInReport + lineSeparator());
			} else {
				reportInString.append(idDriver + countSpace(".", 1) + " " + timeInReport + lineSeparator());
			}
			idDriver++;
		}
		return reportInString.toString();
	}

	private String returnValueOfTimeInReport(String timeReplaceSign) {
		return timeReplaceSign.substring(0, 64) + "." + timeReplaceSign.substring(63, 66);
	}

	private String countSpace(String string, int maxLengthString) {
		StringBuilder stringSpace = new StringBuilder(string);
		while (stringSpace.length() < maxLengthString + 1) {
			stringSpace.append(" ");
		}
		return stringSpace.toString();
	}
}
