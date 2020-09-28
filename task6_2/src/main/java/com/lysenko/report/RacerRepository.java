package com.lysenko.report;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RacerRepository {

	public List<String> getRacers(String abbreviationsFile, String startFile, String endFile) {
		List<TimeOfLap> timeOfLap = new ArrayList<>();
		Map<String, Double> mapAbbreviationsFromStart = returnTimeFromFile(startFile, timeOfLap);
		Map<String, Double> mapAbbreviationsFromEnd = returnTimeFromFile(endFile, timeOfLap);
		Map<String, Double> mapReport = returnNameCarTimeOfLap(abbreviationsFile, mapAbbreviationsFromStart,
				mapAbbreviationsFromEnd);
		List<Map.Entry<String, Double>> entryRacers = new ArrayList<>(sortedDriversForTimeOfLap(mapReport).entrySet());
		List<String> listRacers = new ArrayList<>();
		for (Map.Entry<String, Double> pair : entryRacers) {
			listRacers.add(pair.getKey() + " " + pair.getValue());
		}
		return listRacers;
	}

	private LinkedHashMap<String, Double> sortedDriversForTimeOfLap(Map<String, Double> mapReport) {
		return mapReport.entrySet().stream().sorted((Map.Entry.<String, Double>comparingByValue()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
	}

	private Map<String, Double> returnNameCarTimeOfLap(String abbreviationsFile,
			Map<String, Double> mapAbbreviationsFromStart, Map<String, Double> mapAbbreviationsFromEnd) {
		Map<String, Double> mapReport = new LinkedHashMap<>();
		List<DataOfAbbreviations> data = new ArrayList<>();
		for (int indexAbbreviation = 0; indexAbbreviation < sortedLinesAbbreviations(abbreviationsFile)
				.size(); indexAbbreviation++) {
			String ll = sortedLinesAbbreviations(abbreviationsFile).get(indexAbbreviation).substring(4,
					sortedLinesAbbreviations(abbreviationsFile).get(indexAbbreviation).length());
			String[] nameAndCar = ll.split("_");
			data.add(new DataOfAbbreviations(nameAndCar[0], nameAndCar[1], mapAbbreviationsFromStart.values(),
					mapAbbreviationsFromEnd.values()));
			String nameAndCarAbbreviation = countSpaceWithPipeline(data.get(indexAbbreviation).nameFromAbbreviations,
					23) + " " + countSpaceWithPipeline(data.get(indexAbbreviation).carFromAbbreviations, 23);
			Double computedTimeOfLap = new ArrayList<>(data.get(indexAbbreviation).valuesMapAbbreviationsFromEnd)
					.get(indexAbbreviation)
					- new ArrayList<>(data.get(indexAbbreviation).valuesMapAbbreviationsFromStart)
							.get(indexAbbreviation);
			mapReport.put(nameAndCarAbbreviation, computedTimeOfLap);
		}
		return mapReport;
	}

	private List<String> sortedLinesAbbreviations(String abbreviationsFile) {
		Path abbreviationsPath = Paths.get(abbreviationsFile);
		List<String> linesAbbreviations = new ArrayList<>();
		try (Stream<String> lineStream = Files.lines(abbreviationsPath)) {
			linesAbbreviations = lineStream.sorted().collect(Collectors.toList());
		} catch (IOException e) {
			e.getStackTrace();
		}
		Collections.sort(linesAbbreviations);
		return linesAbbreviations;
	}

	private Map<String, Double> returnTimeFromFile(String path, List<TimeOfLap> time) {
		time.clear();
		Path pathFile = Paths.get(path);
		List<String> timeFromFile = new ArrayList<>();
		Map<String, Double> mapAbbreviationsFromFile = new LinkedHashMap<>();
		try (Stream<String> lineStream = Files.lines(pathFile)) {
			timeFromFile = lineStream.sorted().collect(Collectors.toList());
		} catch (IOException e) {
			e.getStackTrace();
		}
		for (int i = 0; i < timeFromFile.size(); i++) {
			time.add(new TimeOfLap(timeFromFile.get(i).substring(17, 19),
					timeFromFile.get(i).substring(20, timeFromFile.get(i).length())));
			mapAbbreviationsFromFile.put(timeFromFile.get(i).substring(0, 3),
					((new Integer(time.get(i).getMinutesFromFile()) * 60)
							+ (new Double(time.get(i).getSecondsFromFile()))) / 60);
		}
		return mapAbbreviationsFromFile;
	}

	private String countSpaceWithPipeline(String string, int maxLengthString) {
		StringBuilder stringSpaceWithPipe = new StringBuilder(string);
		while (stringSpaceWithPipe.length() < maxLengthString + 5) {
			stringSpaceWithPipe.append(" ");
		}
		stringSpaceWithPipe.append("|");
		return stringSpaceWithPipe.toString();
	}
}
