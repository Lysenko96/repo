package com.lysenko.report;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import static java.lang.System.lineSeparator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TopRacersFormatterTest {

	private TopRacersFormatter topRacersFormatter;

	public static final String STARTFILE = getPathInString("com/lysenko/resources/start.log");
	public static final String ENDFILE = getPathInString("com/lysenko/resources/end.log");
	public static final String ABBREVIATIONSFILE = getPathInString("com/lysenko/resources/abbreviations.txt");

	@BeforeEach
	void setUp() {
		topRacersFormatter = new TopRacersFormatter();

	}

	@Test
	void givenNull_thenException() {
		assertThrows(NullPointerException.class, () -> topRacersFormatter.format(null, null, null));
	}

	@Test
	void givenEmpty_thenEmpty() {
		String expected = "";
		String actual = topRacersFormatter.format("", "", "");
		assertEquals(expected, actual);
	}

	@Test
	void givenFile_thenListDrivers() {
		StringBuilder expected = new StringBuilder();
		expected.append("1.  Sebastian Vettel            | FERRARI                     | 1:07.735")
				.append(lineSeparator())
				.append("2.  Daniel Ricciardo            | RED BULL RACING TAG HEUER   | 1:20.002")
				.append(lineSeparator())
				.append("3.  Valtteri Bottas             | MERCEDES                    | 1:20.072")
				.append(lineSeparator())
				.append("4.  Lewis Hamilton              | MERCEDES                    | 1:20.076")
				.append(lineSeparator())
				.append("5.  Stoffel Vandoorne           | MCLAREN RENAULT             | 1:20.077")
				.append(lineSeparator())
				.append("6.  Kimi Raikkonen              | FERRARI                     | 1:21.106")
				.append(lineSeparator())
				.append("7.  Fernando Alonso             | MCLAREN RENAULT             | 1:21.109")
				.append(lineSeparator())
				.append("8.  Sergey Sirotkin             | WILLIAMS MERCEDES           | 1:21.117")
				.append(lineSeparator())
				.append("9.  Charles Leclerc             | SAUBER FERRARI              | 1:21.138")
				.append(lineSeparator())
				.append("10. Sergio Perez                | FORCE INDIA MERCEDES        | 1:21.141")
				.append(lineSeparator())
				.append("11. Romain Grosjean             | HAAS FERRARI                | 1:21.154")
				.append(lineSeparator())
				.append("12. Pierre Gasly                | SCUDERIA TORO ROSSO HONDA   | 1:21.156")
				.append(lineSeparator())
				.append("13. Carlos Sainz                | RENAULT                     | 1:21.158")
				.append(lineSeparator())
				.append("14. Esteban Ocon                | FORCE INDIA MERCEDES        | 1:21.171")
				.append(lineSeparator())
				.append("15. Nico Hulkenberg             | RENAULT                     | 1:21.177")
				.append(lineSeparator())
				.append("------------------------------------------------------------------------")
				.append(lineSeparator())
				.append("16. Brendon Hartley             | SCUDERIA TORO ROSSO HONDA   | 1:21.196")
				.append(lineSeparator())
				.append("17. Marcus Ericsson             | SAUBER FERRARI              | 1:22.210")
				.append(lineSeparator())
				.append("18. Lance Stroll                | WILLIAMS MERCEDES           | 1:22.220")
				.append(lineSeparator())
				.append("19. Kevin Magnussen             | HAAS FERRARI                | 1:22.232")
				.append(lineSeparator());
		String actual = topRacersFormatter.format(ABBREVIATIONSFILE, STARTFILE, ENDFILE);
		assertEquals(expected.toString(), actual);
	}

	private static String getPathInString(String path) {
		File file = new File(Main.class.getClassLoader().getResource(path).getFile());
		return file.toPath().toString();
	}

}
