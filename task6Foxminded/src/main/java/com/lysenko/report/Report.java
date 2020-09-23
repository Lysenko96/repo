package com.lysenko.report;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.System.lineSeparator;

public class Report {

	public static final Path PATHSTART = Paths
			.get("C:/workspace/report/src/main/resources/com/lysenko/resources/start.log");
	public static final Path PATHEND = Paths
			.get("C:/workspace/report/src/main/resources/com/lysenko/resources/end.log");
	public static final Path PATHLABEL = Paths
			.get("C:/workspace/report/src/main/resources/com/lysenko/resources/abbreviations.txt");
	public static final String PATHREPORT = "C:/workspace/report/src/main/resources/com/lysenko/resources/result.txt";

	public String createReport() {

		List<String> minutesFromStart = new ArrayList<>();
		List<String> minutesFromEnd = new ArrayList<>();
		List<String> secondsFromStart = new ArrayList<>();
		List<String> secondsFromEnd = new ArrayList<>();

		Map<String, Double> mapLabelFromStart = returnTimeFromFile(PATHSTART, secondsFromStart, minutesFromStart);

		Map<String, Double> mapLabelFromEnd = returnTimeFromFile(PATHEND, secondsFromEnd, minutesFromEnd);

		Map<String, Double> mapReport = returnNameCarTimeOfLap(PATHLABEL, mapLabelFromStart, mapLabelFromEnd);

		LinkedHashMap<String, Double> sortedMapResult = sortedDriversForTimeOfLap(mapReport);

		String reportInString = returnReportInString(sortedMapResult);

		writeInFileReport(PATHREPORT, reportInString);

		return reportInString;
	}

	private void writeInFileReport(String pathFile, String reportInString) {
		try (FileWriter writer = new FileWriter(pathFile, false)) {
			writer.write(reportInString);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String returnReportInString(Map<String, Double> sortedMapReport) {
		int idDriver = 1;
		StringBuilder reportInString = new StringBuilder();
		for (Map.Entry<String, Double> pair : sortedMapReport.entrySet()) {
			if (idDriver == 16) {
				reportInString.append(
						"-------------------------------------------------------------------------" + lineSeparator());
			}
			if (idDriver >= 10) {
				String timeReplaceSign = String.valueOf(pair.getValue()).replace(".", ":");
				String timeInReport = returnValueOfTimeInReport(timeReplaceSign);
				reportInString
						.append(idDriver + countSpace(".", 1) + pair.getKey() + " " + timeInReport + lineSeparator());

			} else {
				String timeReplaceSign = String.valueOf(pair.getValue()).replace(".", ":");
				String timeInReport = returnValueOfTimeInReport(timeReplaceSign);
				reportInString
						.append(idDriver + countSpace(".", 2) + pair.getKey() + " " + timeInReport + lineSeparator());
			}
			idDriver++;

		}
		return reportInString.toString();
	}

	private String returnValueOfTimeInReport(String timeReplaceSign) {
		return timeReplaceSign.substring(0, 4) + "." + timeReplaceSign.substring(3, 6);
	}

	private LinkedHashMap<String, Double> sortedDriversForTimeOfLap(Map<String, Double> mapReport) {
		//List<Map.Entry<String, Double>> listReport = new ArrayList<>(mapReport.entrySet());
		//LinkedHashMap<String, Double> sortedMapReport = new LinkedHashMap<>();
		
		
		LinkedHashMap<String, Double> sortedMapReport = mapReport.entrySet()
                .stream()
                .sorted((Map.Entry.<String, Double>comparingByValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
		
		//--------------------------------------------------------------------
		
//		Collections.sort(listReport, new Comparator<Entry<String, Double>>() {
//
//			@Override
//			public int compare(Entry<String, Double> time1, Entry<String, Double> time2) {
//				return time1.getValue().compareTo(time2.getValue());
//			}
//
//		});
		
		//--------------------------------------------------------------------
		
//		for (int i = 0; i < listReport.size(); i++) {
//			Map.Entry<String, Double> mapFromListReport = listReport.get(i);
//			sortedMapReport.put(mapFromListReport.getKey(), mapFromListReport.getValue());
//		}
		return sortedMapReport;
	}

	private Map<String, Double> returnNameCarTimeOfLap(Path pathLabel, Map<String, Double> mapLabelFromStart,
			Map<String, Double> mapLabelFromEnd) {
		List<String> linesLabel = new ArrayList<>();
		List<String> linesLabelForResult = new ArrayList<>();
		List<String> nameFromLabel = new ArrayList<>();
		List<String> carFromLabel = new ArrayList<>();
		List<String> counterSpaceForName = new ArrayList<>();
		List<String> counterSpaceForCar = new ArrayList<>();
		List<Integer> nameFromLabelLength = new ArrayList<>();
		List<Integer> carFromLabelLength = new ArrayList<>();
		Map<String, Double> mapResult = new LinkedHashMap<>();
		try (Stream<String> lineStream = Files.lines(pathLabel)) {
			linesLabel = lineStream.sorted().collect(Collectors.toList());
		} catch (IOException e) {
			e.getStackTrace();
		}
		Collections.sort(linesLabel);
		for (String s : linesLabel) {
			linesLabelForResult.add(s.substring(4, s.length()));
		}
		for (String line : linesLabelForResult) {
			String[] nameAndCar = line.split("_");
			nameFromLabel.add(nameAndCar[0]);
			nameFromLabelLength.add(nameAndCar[0].length());
			carFromLabel.add(nameAndCar[1]);
			carFromLabelLength.add(nameAndCar[1].length());
		}

		int nameFromLabelLengthMax = Collections.max(nameFromLabelLength);
		int carFromLabelLengthMax = Collections.max(carFromLabelLength);
		for (String line : nameFromLabel) {
			counterSpaceForName.add(countSpaceWithPipeline(line, nameFromLabelLengthMax));
		}
		for (String line : carFromLabel) {
			counterSpaceForCar.add(countSpaceWithPipeline(line, carFromLabelLengthMax));
		}
		List<String> nameAndCarFromLabel = new ArrayList<>();
		for (int i = 0; i < nameFromLabel.size(); i++) {
			nameAndCarFromLabel.add(counterSpaceForName.get(i) + " " + counterSpaceForCar.get(i));
		}
		List<Double> valuesMapLabelFromStart = new ArrayList<>(mapLabelFromStart.values());
		List<Double> valuesMapLabelFromEnd = new ArrayList<>(mapLabelFromEnd.values());
		List<Double> valuesTimeOfLap = new ArrayList<>();
		for (int i = 0; i < valuesMapLabelFromStart.size(); i++) {
			valuesTimeOfLap.add(valuesMapLabelFromEnd.get(i) - valuesMapLabelFromStart.get(i));
		}
		for (int i = 0; i < valuesTimeOfLap.size(); i++) {
			mapResult.put(nameAndCarFromLabel.get(i), valuesTimeOfLap.get(i));
		}
		return mapResult;
	}

	private Map<String, Double> returnTimeFromFile(Path path, List<String> secondsFromFile,
			List<String> minutesFromFile) {
		List<String> timeFromStart = new ArrayList<>();
		Map<String, Double> mapLabelFromFile = new LinkedHashMap<>();
		try (Stream<String> lineStream = Files.lines(path)) {
			timeFromStart = lineStream.sorted().collect(Collectors.toList());
		} catch (IOException e) {
			e.getStackTrace();
		}
		for (String line : timeFromStart) {
			minutesFromFile.add(line.substring(17, 19));
			secondsFromFile.add(line.substring(20, line.length()));
			mapLabelFromFile.put(line.substring(0, 3),
					((new Integer(line.substring(17, 19)) * 60) + (new Double(line.substring(20, line.length()))))
							/ 60);
		}
		return mapLabelFromFile;
	}

	private String countSpaceWithPipeline(String string, int maxLengthString) {
		StringBuilder stringSpaceWithPipe = new StringBuilder(string);
		while (stringSpaceWithPipe.length() < maxLengthString + 5) {
			stringSpaceWithPipe.append(" ");
		}
		stringSpaceWithPipe.append("|");
		return stringSpaceWithPipe.toString();
	}

	private String countSpace(String string, int maxLengthString) {
		StringBuilder stringSpace = new StringBuilder(string);
		while (stringSpace.length() < maxLengthString + 1) {
			stringSpace.append(" ");
		}
		return stringSpace.toString();
	}

}
