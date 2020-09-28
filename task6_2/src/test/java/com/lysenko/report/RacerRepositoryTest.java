package com.lysenko.report;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.lysenko.report.TopRacersFormatterTest.*;

class RacerRepositoryTest {

	private RacerRepository racerRepository;

	@BeforeEach
	void setUp() {
		racerRepository = new RacerRepository();
	}

	@Test
	void giveNull_thenException() {
		assertThrows(NullPointerException.class, () -> racerRepository.getRacers(null, null, null));
	}

	@Test
	void giveEmpty_thenEmpty() {
		List<String> expected = new ArrayList<>();
		List<String> actual = racerRepository.getRacers("", "", "");
		assertEquals(expected, actual);
	}

	@Test
	void giveList_thenListDrivers() {

		List<String> expected = new ArrayList<>();
		expected.add("Sebastian Vettel            | FERRARI                     | 1.0735833333333331");
		expected.add("Daniel Ricciardo            | RED BULL RACING TAG HEUER   | 1.2002166666666678");
		expected.add("Valtteri Bottas             | MERCEDES                    | 1.2072333333333334");
		expected.add("Lewis Hamilton              | MERCEDES                    | 1.2076666666666682");
		expected.add("Stoffel Vandoorne           | MCLAREN RENAULT             | 1.2077166666666699");
		expected.add("Kimi Raikkonen              | FERRARI                     | 1.2106499999999998");
		expected.add("Fernando Alonso             | MCLAREN RENAULT             | 1.2109500000000004");
		expected.add("Sergey Sirotkin             | WILLIAMS MERCEDES           | 1.2117666666666658");
		expected.add("Charles Leclerc             | SAUBER FERRARI              | 1.2138166666666663");
		expected.add("Sergio Perez                | FORCE INDIA MERCEDES        | 1.2141333333333346");
		expected.add("Romain Grosjean             | HAAS FERRARI                | 1.2154999999999996");
		expected.add("Pierre Gasly                | SCUDERIA TORO ROSSO HONDA   | 1.2156833333333346");
		expected.add("Carlos Sainz                | RENAULT                     | 1.2158333333333333");
		expected.add("Esteban Ocon                | FORCE INDIA MERCEDES        | 1.217133333333333");
		expected.add("Nico Hulkenberg             | RENAULT                     | 1.217750000000001");
		expected.add("Brendon Hartley             | SCUDERIA TORO ROSSO HONDA   | 1.2196499999999997");
		expected.add("Marcus Ericsson             | SAUBER FERRARI              | 1.2210833333333344");
		expected.add("Lance Stroll                | WILLIAMS MERCEDES           | 1.2220499999999994");
		expected.add("Kevin Magnussen             | HAAS FERRARI                | 1.2232166666666662");
		List<String> actualList = racerRepository.getRacers(ABBREVIATIONSFILE, STARTFILE, ENDFILE);
		List<String> actual = new ArrayList<>();
		for (String racers : actualList) {
			actual.add(racers);
		}
		assertEquals(expected, actual);
	}

}