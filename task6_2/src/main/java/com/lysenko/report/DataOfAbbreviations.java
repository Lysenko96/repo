package com.lysenko.report;

import java.util.Collection;

public class DataOfAbbreviations {

	String nameFromAbbreviations;
	String carFromAbbreviations;
	Collection<Double> valuesMapAbbreviationsFromStart;
	Collection<Double> valuesMapAbbreviationsFromEnd;

	public DataOfAbbreviations(String nameFromAbbreviations, String carFromAbbreviations,
			Collection<Double> valuesMapAbbreviationsFromStart, Collection<Double> valuesMapAbbreviationsFromEnd) {
		this.nameFromAbbreviations = nameFromAbbreviations;
		this.carFromAbbreviations = carFromAbbreviations;
		this.valuesMapAbbreviationsFromStart = valuesMapAbbreviationsFromStart;
		this.valuesMapAbbreviationsFromEnd = valuesMapAbbreviationsFromEnd;
	}

	public String getNameFromAbbreviations() {
		return nameFromAbbreviations;
	}

	public String getCarFromAbbreviations() {
		return carFromAbbreviations;
	}

	public Collection<Double> getValuesMapAbbreviationsFromStart() {
		return valuesMapAbbreviationsFromStart;
	}

	public Collection<Double> getValuesMapAbbreviationsFromEnd() {
		return valuesMapAbbreviationsFromEnd;
	}

}
