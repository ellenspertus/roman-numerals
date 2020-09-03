package edu.mills.cs180a;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import static edu.mills.cs180a.RomanNumeral.convertFromString;
import static edu.mills.cs180a.RomanNumeral.convertToText;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class RomanNumeralTest {

	@Test
	void testConvertFromStringRejectsTrivialInput() {
		assertThrows(Exception.class,
				() -> convertFromString(null));
		assertThrows(IllegalArgumentException.class,
				() -> convertFromString(""));
	}
	
	@ParameterizedTest
	@ValueSource(strings = { "1", "foo", "moo" })
	void testConvertFromStringRejectsIllegalCharacters(String s) {
		assertThrows(IllegalArgumentException.class,
				() -> convertFromString(s));
	}

	@Test
	void testConvertToTextHandlesIndividualLetters() {
		for (Map.Entry<Character, Integer> entry : RomanNumeral.LETTERS_TO_VALUES.entrySet()) {
			assertEquals(entry.getValue(), convertFromString(entry.getKey().toString()));
		}
	}
	
	@ParameterizedTest
	@CsvSource(value = { "II,2", "VI,6", "MCXVI,1116" })
	void testConvertToTextHandlesAdditiveForms(String input, String value) {
		assertEquals(Integer.parseInt(value), convertFromString(input));
	}
		
	@ParameterizedTest
	@CsvSource(value = { "IV,4", "IX,9", "XL,40", "XC, 90", "CD,400", "CM,900" })
	void testConvertToTextHandlesSubtractiveForms(String input, String value) {
		assertEquals(Integer.parseInt(value), convertFromString(input));
	}

}
