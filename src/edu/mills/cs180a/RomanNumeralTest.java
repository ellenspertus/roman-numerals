package edu.mills.cs180a;

import static edu.mills.cs180a.RomanNumeral.convertFromIntToString;
import static edu.mills.cs180a.RomanNumeral.convertFromStringToInt;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class RomanNumeralTests {

    @ParameterizedTest
	@CsvSource( {"I, 1", "V , 5", "X , 10", "L , 50", "C , 100", "D, 500", "M , 1000"} )
	void testEqualityConvertFromStringToInt(String input, int expected) {
    	int actual = convertFromStringToInt(input);
		assertEquals(expected, actual);
	}
	
	@ParameterizedTest
	@ValueSource(ints = {0, -1, -2, -4}) 
	void testIllegalArgumentForConvertIntToString(int n) {
		assertThrows(IllegalArgumentException.class, () -> convertFromIntToString(n));	
	}
	
	@Test
	void testConvertFromIntToStringIsNotNull() {
		assertNotNull(convertFromIntToString(0));
	}
	
	
	@ParameterizedTest
	@CsvSource({"1 , I", "5 , V", " 10 , X", "50 , L", " 100 , C", "500 , D", "1000 , M"})
	void testEqualityConvertFromIntToString(int input, String expected) {
		String actual = convertFromIntToString(input);
		assertEquals(expected, actual);
	}

	@ParameterizedTest
	@ValueSource(strings = {" ", "U", "$"})
	void testConvertFromStringToIntThrowsExceptionForInvalidEntry(String s) {
		assertThrows(IllegalArgumentException.class,
				() -> convertFromStringToInt(s));
	}

	@ParameterizedTest
	@CsvSource({"L,50","XCIV,94","D,500","X,10", "IV,4", "CD,400", "v,5", "xl,40", "IM,1001", "CCCXCVIII,398", "MMMMMMMMM,9000","MMMMMMMMMCMXCIX,9999"})
	void convertFromStringToIntShouldGenerateTheExpectedIntValue(String input, int expected) {
		int actualValue = convertFromStringToInt(input);
		assertEquals(expected, actualValue);
	}

	@ParameterizedTest
	@CsvSource({"50,L","94,XCIV","100,C", "9,IX", "40,XL", "1,I", "324,CCCXXIV", "9000,MMMMMMMMM", "9999,MMMMMMMMMCMXCIX","500,D"})
	void convertFromIntToStringShouldGenerateTheExpectedStringValue(int input, String expected) {
		String actualValue = convertFromIntToString(input);
		assertEquals(expected, actualValue);
	}

	//String to int tests
	@ParameterizedTest
	@ValueSource(ints = {-100, 0, 5, 10000})
	void testIntInputIsOutOfBounds(int invalidInt) {
		assertThrows(IllegalArgumentException.class,
				() -> new RomanNumeral(invalidInt));
	}

	// Testing for convertFromStringToInt
	@ParameterizedTest
	@CsvSource({"I,1", "V,5", "X,10", "L,50", "C,100", "D,500", "M,1000"})
	void testStringToIntValidRomanSymbol(String romanNumeral, int expected) {
		int actual = convertFromStringToInt(romanNumeral);
		assertEquals(expected, actual);
	}
	
	@ParameterizedTest
	@CsvSource({"i,1", "v,5", "x,10", "l,50", "c,100", "d,500", "m,1000"})
	void testStringToIntValidLowercaseRomanSymbol(String romanNumeral, int expected) {
		int actual = convertFromStringToInt(romanNumeral);
		assertEquals(expected, actual);
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"@", "V!", "123", "ten", "vi"})
	void testStringToIntInvalidRomanNumeral(String invalidInput) {
		assertThrows(IllegalArgumentException.class,
				() -> convertFromStringToInt(invalidInput));
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"IIII", "VIIII", "xxxx", "CMXCIX", "IM"})
	void testStringToIntNonStandardRomanNumeral(String invalidInput) {
		assertThrows(IllegalArgumentException.class,
				() -> convertFromStringToInt(invalidInput));
	}

	@ValueSource(ints = {0, -1, 10000})
	void testConvertFromIntToStringThrowsExceptionForOutOfRange(int n) {
		assertThrows(IllegalArgumentException.class,
				() -> convertFromIntToString(n));
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"DCCCC", "IIII", "VIIII", "XXXX"})
	void testThrowsExceptionForNonStandardForm(String s) {
		assertThrows(IllegalArgumentException.class,
				() -> convertFromStringToInt(s));
	}
	
	@ParameterizedTest
	@ValueSource(ints = {-1, 0, 10000})
	void testOutOfBoundsNumbers(int num) {
		assertThrows(IllegalArgumentException.class,
				() -> convertFromIntToString(num));
	}
	
	@ParameterizedTest
	@CsvSource({"II,2", "III,3", "XI,11", "lii,52", "DCXIII,613"})
	void testStringToIntAdditivePrinciple(String additiveNumeral, int expected) {
		int actual = convertFromStringToInt(additiveNumeral);
		assertEquals(expected, actual);
	}
	
	@ParameterizedTest
	@CsvSource({"iv, 4", "IV,4", "IX,9", "XL,40", "XC,90", "CD,400", "CM,900"})
	void testStringToIntSubtractivePrinciple(String subtractiveNumeral, int expected) {
		int actual = convertFromStringToInt(subtractiveNumeral);
		assertEquals(expected, actual);
	}
	
	// Testing for convertFromIntToString
	@ParameterizedTest
	@CsvSource({"1,I", "5,V", "10,X", "50,L", "100,C", "500,D", "1000,M"})
	void testIntToStringIsValidRomanSymbol(int n, String expected) {
		String actual = convertFromIntToString(n);
		assertEquals(expected, actual);
	}
	
	@ParameterizedTest
	@CsvSource({"4,IV", "9,IX", "40,XL", "90,XC", "400,CD", "900,CM"})
	void testIntToStringSubtractivePrinciple(int n, String expected) {
		String actual = convertFromIntToString(n);
		assertEquals(expected, actual);
	}
	
	@ParameterizedTest
	@CsvSource({"2,II", "3,III", "11,XI", "1507,MDVII"})
	void testIntToStringAdditivePrinciple(int n, String expected) {
		String actual = convertFromIntToString(n);
		assertEquals(expected, actual);
	}


    @Test
    void stringToInt() {
        assertEquals(5, convertFromStringToInt("V"));
        assertEquals(10, convertFromStringToInt("X"));
        assertEquals(50, convertFromStringToInt("L"));
    }
        
    @ParameterizedTest
    @CsvSource({"x,10", "v,5", "L,50"})
    void ParamStringToInt(String input, int expected) {
        String actual = input.toUpperCase();
        assertEquals(expected, convertFromStringToInt(actual));
    }
    
    @Test
    void intToString() {
        assertEquals("V", convertFromIntToString(5));
        assertEquals("X", convertFromIntToString(10));
        assertEquals("L", convertFromIntToString(50));
    }
    
    @ParameterizedTest
    @CsvSource({"1,I", "5,V", "10,X"})
    void ParamIntToString(int input, String expected) {
        assertEquals(expected, convertFromIntToString(input));
    }

}

