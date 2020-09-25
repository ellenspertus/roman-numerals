package edu.mills.cs180a;

import static edu.mills.cs180a.RomanNumeral.convertFromInt;
import static edu.mills.cs180a.RomanNumeral.convertFromString;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class RomanNumeralTest {
  private static RomanNumeral rnX;

  @BeforeAll
  public static void setup() {
    rnX = new RomanNumeral("X");
  }

  @Test
  void convertFromInt_ExceptionThrown_LessThanMin_Benjamin() {
    assertThrows(IllegalArgumentException.class, () -> {
      convertFromInt(RomanNumeral.MIN_VALUE - 1);
    });
  }

  @Test
  void convertToString_ExceptionThrown_GreaterThanMax_Benjamin() {
    assertThrows(IllegalArgumentException.class, () -> {
      RomanNumeral.convertFromInt(RomanNumeral.MAX_VALUE + 1);
    });
  }

  @ParameterizedTest
  @CsvSource({"1,I", "2,II", "3,III", "5,V", "10,X", "55,LV", "108,CVIII", "1000,M",
      "9000,MMMMMMMMM"})
  void convertToString_ReturnValidNumber_AdditiveRomanNumeral_Benjamin(int input, String expected) {
    assertEquals(convertFromInt(input), expected);
  }

  @ParameterizedTest
  @CsvSource({"4,IV", "9,IX", "40,XL", "90,XC", "400,CD", "900,CM", "1009,MIX",
      "9999,MMMMMMMMMCMXCIX"})
  void convertToString_ReturnValidNumber_SubtractiveRomanNumeral_Benjamin(int input,
      String expected) {
    assertEquals(convertFromInt(input), expected);
  }

  @Test
  void convertFromString_ExceptionThrown_EmptyString_Benjamin() {
    assertThrows(IllegalArgumentException.class, () -> {
      RomanNumeral.convertFromString("");
    });
  }

  @Test
  void convertFromString_ExceptionThrown_NotRomanString_Benjamin() {
    assertThrows(IllegalArgumentException.class, () -> {
      RomanNumeral.convertFromString("abcd1234");
    });
  }

  @Test
  void convertFromString_ExceptionThrown_RomanStringSpaces_Benjamin() {
    assertThrows(IllegalArgumentException.class, () -> {
      RomanNumeral.convertFromString("X I X");
    });
  }


  @Test
  void convertFromString_ExceptionThrown_SubtractiveWrongBadPair_Benjamin() {
    assertThrows(IllegalArgumentException.class, () -> {
      RomanNumeral.convertFromString("DM");
    });
  }

  @Test
  void convertFromString_ExceptionThrown_SubtractiveLeadingValuesIIX_Benjamin() {
    assertThrows(IllegalArgumentException.class, () -> {
      RomanNumeral.convertFromString("IIX");
    });
  }

  @Test
  void convertFromString_ExceptionThrown_SubtractiveLeadingValuesILC_Benjamin() {
    assertThrows(IllegalArgumentException.class, () -> {
      RomanNumeral.convertFromString("ILC");
    });
  }

  @Test
  void convertFromString_ExceptionThrown_SubtractiveLeadingValuesIM_Benjamin() {
    assertThrows(IllegalArgumentException.class, () -> {
      RomanNumeral.convertFromString("IM");
    });
  }

  // TODO: This test might not need to exist. Ask other students!
  void convertFromString_ExceptionThrown_AdditiveTooManyIIIII_Benjamin() {
    assertThrows(IllegalArgumentException.class, () -> {
      RomanNumeral.convertFromString("IIIII");
    });
  }

  @Test
  void convertFromString_ExceptionThrown_TenThousand_Benjamin() {
    assertThrows(IllegalArgumentException.class, () -> {
      RomanNumeral.convertFromString("MMMMMMMMMM");
    });
  }

  @ParameterizedTest
  @CsvSource({"I,1", "IV,4", "V,5", "IX,9", "XL,40", "XC,90", "CD,400", "CM,900",
      "MMMMMMMMMCMXCIX,9999"})
  void convertFromString_ReturnCorrectIntegerInRange_SubtractiveCase_Benjamin(String input,
      int expected) {
    assertEquals(convertFromString(input), expected);
  }

  @ParameterizedTest
  @CsvSource({"I,1", "IIII,4", "V,5", "VIIII,9", "XXXX,40", "LXXXX,90", "CCCC,400", "DCCCC,900",
      "MMMMMMMMMCMXCIX,9999"})
  void convertFromString_ReturnCorrectIntegerInRange_AdditiveCase_Benjamin(String input,
      int expected) {
    assertEquals(convertFromString(input), expected);
  }

  @Test
  public void testConvertFromStringRejectsEmptyString_Charles() {
    assertThrows(IllegalArgumentException.class, () -> RomanNumeral.convertFromString(""));
  }

  @Test
  public void equals_True_XX() {
    RomanNumeral rn2 = new RomanNumeral("X");
    assertEquals(rnX, rn2);
  }

  void convertFromString_ThrowIllegalArguementException_WrongFormat_Charles() {
    assertThrows(IllegalArgumentException.class, () -> RomanNumeral.convertFromString("VIV"));
  }

  @ParameterizedTest
  @CsvSource({"V, 5", "VI, 6", "DCLXVI, 666", "IV, 4", "XCIX, 99", "CDXCIX, 499", "LV, 55",
      "CMXLIV, 944", "M, 1000", "C, 100", "DCC, 700", "XI, 11", "LXX, 70", "VII, 7", "VIIi, 8",
      "LX, 60", "LXX, 70", "LXXX, 80", "DC, 600", "DCC, 700", "DCCC, 800"})
  void convertFromStringReturnsCorrectInt_Charles(String input, int expected) {
    int actual = RomanNumeral.convertFromString(input);
    assertEquals(expected, actual);
  }

  @ParameterizedTest
  @CsvSource({"6, VI", "666, DCLXVI", "4, IV", "99, XCIX", "499, CDXCIX", "55, LV", "944, CMXLIV"})
  void convertFromIntReturnsCorrectRomanNumeral_Charles(int input, String expected) {
    String actual = RomanNumeral.convertFromInt(input);
    assertEquals(expected, actual);
  }

  @ParameterizedTest
  @CsvSource({"I,1", "II,2", "III,3", "IV,4", "V,5", "VI,6", "VII,7", "VIII,8", "IX,9", "X,10"})
  void convertFromInt_assertEquals_InttoString1through10_Irene(String RN, String num) {
    assertEquals(RN, convertFromInt(Integer.parseInt(num)));
  }

  // checking that convertFromInt correctly outputs markers
  @ParameterizedTest
  @CsvSource({"I,1", "V,5", "X,10", "L,50", "C,100", "D,500", "M,1000"})
  void convertFromInt_assertEquals_InttoStringMarkers_Irene(String RN, String num) {
    assertEquals(RN, convertFromInt(Integer.parseInt(num)));
  }

  // checking for all numbers comprised of 9s just up to 500
  @ParameterizedTest
  @CsvSource({"IX,9", "XIX,19", "XXIX,29", "XXXIX,39", "XLIX,49", "LIX,59", "LXIX,69", "LXXIX,79",
      "LXXXIX,89", "XCIX,99", "CIX,109", "CXIX,119", "CXXIX,129", "CXXXIX,139", "CXLIX,149",
      "CLIX,159", "CLXIX,169", "CLXXIX,179", "CLXXXIX,189", "CXCIX,199", "CCIX,209", "CCXIX,219",
      "CCXXIX,229", "CCXXXIX,239", "CCXLIX,249", "CCLIX,259", "CCLXIX,269", "CCLXXIX,279",
      "CCLXXXIX,289", "CCXCIX,299", "CCCIX,309", "CCCXIX,319", "CCCXXIX,329", "CCCXXXIX,339",
      "CCCXLIX,349", "CCCLIX,359", "CCCLXIX,369", "CCCLXXIX,379", "CCCLXXXIX,389", "CCCXCIX,399",
      "CDIX,409", "CDXIX,419", "CDXXIX,429", "CDXXXIX,439", "CDXLIX,449", "CDLIX,459", "CDLXIX,469",
      "CDLXXIX,479", "CDLXXXIX,489", "CDXCIX,499"})
  void convertFromInt_assertEquals_OneBeforeTens_Irene(String RN, String num) {
    assertEquals(RN, convertFromInt(Integer.parseInt(num)));
  }

  // checking against additives
  @ParameterizedTest
  @CsvSource({"IV,4", "IX,9", "XL,40", "XC,90", "CD,400", "CM,900"})
  void convertFromInt_assertEquals_StandardOverAdditive_Irene(String RN, String num) {
    assertEquals(RN, convertFromInt(Integer.parseInt(num)));
  }

  // checking in 10s
  @ParameterizedTest
  @CsvSource({"X,10", "XX,20", "XXX,30", "XL,40", "L,50", "LX,60", "LXX,70", "LXXX,80", "XC,90",
      "C,100", "CX,110", "CXX,120", "CXXX,130", "CXL,140", "CL,150", "CLX,160", "CLXX,170",
      "CLXXX,180", "CXC,190", "CC,200", "CCX,210", "CCXX,220", "CCXXX,230", "CCXL,240", "CCL,250",
      "CCLX,260", "CCLXX,270", "CCLXXX,280", "CCXC,290", "CCC,300", "CCCX,310", "CCCXX,320",
      "CCCXXX,330", "CCCXL,340", "CCCL,350", "CCCLX,360", "CCCLXX,370", "CCCLXXX,380", "CCCXC,390",
      "CD,400", "CDX,410", "CDXX,420", "CDXXX,430", "CDXL,440", "CDL,450", "CDLX,460", "CDLXX,470",
      "CDLXXX,480", "CDXC,490", "D,500", "DX,510", "DXX,520", "DXXX,530", "DXL,540", "DL,550",
      "DLX,560", "DLXX,570", "DLXXX,580", "DXC,590", "DC,600", "DCX,610", "DCXX,620", "DCXXX,630",
      "DCXL,640", "DCL,650", "DCLX,660", "DCLXX,670", "DCLXXX,680", "DCXC,690", "DCC,700",
      "DCCX,710", "DCCXX,720", "DCCXXX,730", "DCCXL,740", "DCCL,750", "DCCLX,760", "DCCLXX,770",
      "DCCLXXX,780", "DCCXC,790", "DCCC,800", "DCCCX,810", "DCCCXX,820", "DCCCXXX,830",
      "DCCCXL,840", "DCCCL,850", "DCCCLX,860", "DCCCLXX,870", "DCCCLXXX,880", "DCCCXC,890",
      "CM,900", "CMX,910", "CMXX,920", "CMXXX,930", "CMXL,940", "CML,950", "CMLX,960", "CMLXX,970",
      "CMLXXX,980", "CMXC,990", "M,1000"})
  void convertFromInt_assertEquals_Tens_Irene(String RN, String num) {
    assertEquals(RN, convertFromInt(Integer.parseInt(num)));
  }

  // checking random numbers
  @ParameterizedTest
  @CsvSource({"CDXXIII,423", "CCCLXXIX,379", "CCXXXIV,234", "CDXXV,425", "CMLVI,956", "CCXLI,241",
      "CCCXCVIII,398", "DCCLXVI,766", "XXXVIII,38", "CLXXXVII,187", "XCIII,93", "II,2"})
  void convertFromInt_assertEquals_CorrectNotationForRandInts_Irene(String RN, String num) {
    assertEquals(RN, convertFromInt(Integer.parseInt(num)));
  }

  // checking that convertFromString correctly outputs numbers 1 through 10
  @ParameterizedTest
  @CsvSource({"1,I", "2,II", "3,III", "4,IV", "5,V", "6,VI", "7,VII", "8,VIII", "9,IX", "10,X"})
  void convertFromString_assertEquals_StringtoInt1through10_Irene(String num, String RN) {
    assertEquals(Integer.parseInt(num), convertFromString(RN.toLowerCase()));
  }

  // checking that convertFromString correctly outputs numbers 1 through 10
  @ParameterizedTest
  @CsvSource({"11,XI", "12,XII", "13,XIII", "14,XIV", "15,XV", "16,XVI", "17,XVII", "18,XVIII",
      "19,XIX", "20,XX"})
  void convertFromString_assertEquals_StringtoInt11through20_Irene(String num, String RN) {
    assertEquals(Integer.parseInt(num), convertFromString(RN.toLowerCase()));
  }


  @ParameterizedTest
  @ValueSource(ints = {0, -1, 10000})
  void convertFromIntShouldThrowErrorForIntsOutsideRange_Katie(int input) {
    assertThrows(IllegalArgumentException.class, () -> {
      RomanNumeral.convertFromInt(input);
    });
  }

  @ParameterizedTest
  @CsvSource({"1,I", "6,VI", "4,IV", "14,XIV", "9,IX", "9999,MMMMMMMMMCMXCIX"})
  void convertFromIntWorksForValidInput_Katie(int input, String expected) {
    String actualValue = RomanNumeral.convertFromInt(input);
    assertEquals(expected, actualValue);
  }

  // convertFromString

  @ParameterizedTest
  @ValueSource(strings = {"", "RM", "7", "MzX", "MXz", "0", "10000"})
  void convertFromStringShouldThrowErrorForInvalidStrings_Katie(String input) {
    assertThrows(IllegalArgumentException.class, () -> {
      RomanNumeral.convertFromString(input);
    });
  }

  @Test
  void convertFromStringShouldThrowErrorForInvalidRomanNumeral_Katie() {
    assertThrows(IllegalArgumentException.class, () -> {
      RomanNumeral.convertFromString("MXMMXM");
    });
  }

  @ParameterizedTest
  @CsvSource({"I,1", "VI,6", "IV,4", "IIII,4", "IX,9", "XIIII,14", "CMXCIX, 999", "MMMMMMMMM,9000",
      "MMMMMMMMMCMXCIX,9999", "mcd,1400"})
  void convertFromStringWorksForValidInput_Katie(String input, int expected) {
    int actualValue = RomanNumeral.convertFromString(input);
    assertEquals(expected, actualValue);
  }

  @Test
  void testConvertFromStringExceptionTest_Null_Maki() {
    Exception e = assertThrows(Exception.class, () -> {
      convertFromString(null);
    });
    assertTrue(e instanceof NullPointerException || e instanceof IllegalArgumentException);
  }

  // inValid data (inValid alphabet & character)
  @ParameterizedTest
  @ValueSource(strings = {"A", "B", "E", "F", "G", "H", "J", "K", "N", "O", "P", "Q", "R", "S", "T",
      "U", "W", "Y", "Z", "?", "1", "MMMMMMMMMM"})

  void testConvertFromStringExceptionTest_InValid_Maki(String strings) {
    assertThrows(IllegalArgumentException.class, () -> {
      convertFromString(strings);
    });
  }

  // inValid combination data
  @ValueSource(strings = {"DM", "DD", "LM", "LD", "LC", "LL", "XM", "XD", "VM", "VD", "VC", "VL",
      "VX", "VV", "IM", "ID", "IC", "IL"})
  void testConvertFromStringExceptionTest_InValidCombination_Maki(String strings) {
    assertThrows(IllegalArgumentException.class, () -> {
      convertFromString(strings);
    });
  }

  // Valid alphabet1 (Single Symbol)
  @ParameterizedTest
  @CsvSource({"I,1", "V,5,", "X,10,", "L,50", "C,100", "D,500,", "M,1000,"})
  void testConvertFromStringValidData_Single_Maki(String romanText, int num) {
    assertEquals(num, convertFromString(romanText),
        "convertFromString( " + romanText + " ) can not be converted correctly. ");
  }

  // Valid alphabet2 (subtractive notation)
  @ParameterizedTest
  @CsvSource({"IV,4", "IX,9", "XL,40", "XC,90", "CD,400", "CM,900", "MMMCMXCIX,3999",
      "MMMMMMMMMCMXCIX,9999"})
  void testConvertFromStringValidData_Subtractive_Maki(String romanText, int num) {
    assertEquals(num, convertFromString(romanText),
        "convertFromString( " + romanText + " ) can not be converted correctly. ");
  }

  // Valid alphabet 3 (additive notation)
  // 39 = XXX + IX = XXXIX
  //   246 = CC + XL + VI = CCXLVI
  //   789 = DCC + LXXX + IX = DCCLXXXIX
  // 2,421 = MM + CD + XX + I = MMCDXXI
  // 9,999 = IX + CM + XC +IX = IXCMXCIX
  @ParameterizedTest
  @CsvSource({"XXXIX,39", "CCXLVI,246", "DCCLXXXIX,789,", "MMCDXXI,2421", "IIII,4"})
  void testConvertFromStringValidData_Additive_Maki(String romanText, int num) {
    assertEquals(num, convertFromString(romanText),
        "convertFromString( " + romanText + " ) can not be converted correctly. ");
  }

  @ParameterizedTest
  @CsvSource({"xxxix,39", "ccxlvi,246", "dcclXXXix,789,", "MMcdxxi,2421", "Viiii,9", "iiii,4",
      "iiiiI,5", "IIIIII,6", "iiiiiiiII,9"})
  void testConvertFromStringValidData_Additive_lowercase_Maki(String romanText, int num) {
    assertEquals(num, convertFromString(romanText),
        "convertFromString( " + romanText + " ) can not be converted correctly. ");
  }

  ///////////////////////////////////////////////////
  // test for convertFromInt(number -> String)
  ///////////////////////////////////////////////////

  // inValid data (out of range (valid number from 1 to 9999))
  @ParameterizedTest
  @ValueSource(ints = {-1, 0, 10000})
  void testconvertFromIntExceptionTest_InValid_Maki(int ints) {
    assertThrows(IllegalArgumentException.class, () -> {
      convertFromInt(ints);
    });
  }

  // Valid alphabet1 (Single Symbol)
  @ParameterizedTest
  @CsvSource({"1,I", "5,V", "10,X", "50,L", "100,C", "500,D", "1000,M"})
  void testconvertFromIntValidData_Single_Maki(int num, String romanText) {
    assertEquals(romanText, convertFromInt(num),
        "convertFromInt( " + num + " ) can not be converted correctly. ");
  }

  // Valid alphabet2 (subtractive notation)
  @ParameterizedTest
  @CsvSource({"13,XIII", "4,IV", "9,IX", "40,XL", "90,XC", "400,CD", "900,CM",
      "9999,MMMMMMMMMCMXCIX"})
  void testconvertFromIntValidData_Subtractive_Maki(int num, String romanText) {
    assertEquals(romanText, convertFromInt(num),
        "convertFromInt( " + num + " ) can not be converted correctly. ");
  }

  // Valid alphabet 3 (additive notation)
  // 39 = XXX + IX = XXXIX
  //   246 = CC + XL + VI = CCXLVI
  //   789 = DCC + LXXX + IX = DCCLXXXIX
  // 2,421 = MM + CD + XX + I = MMCDXXI
  // 9,999 = IX + CM + XC +IX = IXCMXCIX
  @ParameterizedTest
  @CsvSource({"39,XXXIX", "246,CCXLVI", "789, DCCLXXXIX", "2421,MMCDXXI"})
  void testconvertFromIntValidData_Additive_Maki(int num, String romanText) {
    assertEquals(romanText, convertFromInt(num),
        "convertFromInt( " + num + " ) can not be converted correctly. ");
  }

  /*
   * numeral gives corresponding digit
   */
  @Test
  void convertFromString_IsStandard_Omoremi() {
    assertEquals(5, convertFromString("V"));
  }


  /**
   * string input only contains char that are roman numerals that are captilized
   *
   * @param roman
   */
  @ParameterizedTest
  @ValueSource(strings = {"A", "Z", "a", "BJ"})
  void convertFromString_IsAllRoman_Omoremi(String roman) {
    assertThrows(IllegalArgumentException.class, () -> convertFromString(roman));
  }

  /*
   * converts romans to digits NOTE value = {"input, expected" } iv got 4 assertEquals(expected,
   * actualValue)
   */
  @CsvSource(value = {"IV,4", "IL,49", "XIII,13", "CM,900", "ICM,899", "ILM,949", "VDM,495"})
  void convertFromString_IsEquivalent_Omoremi(String roman, String digit) {
    assertEquals(Integer.parseInt(digit), convertFromString(roman));
  }

  /*
   * converts roman to digits even if additive, may combine w ^^
   */
  @ParameterizedTest
  @CsvSource(value = {"IIIII,5", "VIIIII,10", "XIIII,14"})
  void convertFromString_NotStandard_Omoremi(String roman, String digit) {
    assertEquals(Integer.parseInt(digit), convertFromString(roman));
  }


  /*
   * digit gives standard numeral
   */
  @Test
  void convertFromInt_IsRoman_Omoremi() {
    assertEquals("IV", convertFromInt(4));
  }

  /*
   * converts digit-input to standard roman-expected
   */
  @CsvSource(value = {"5,V", "11,XI", "50,L", "900,CM", "899,ILM", "495,VDM"})
  void convertFromInt_IsStandardRoman_Omoremi(String digit, String roman) {
    assertEquals(roman, convertFromInt(Integer.parseInt(digit)));
  }

  @Test
  void convertFromString_IllegalArgumentException_EmptyString_Sarah() {
    assertThrows(IllegalArgumentException.class, () -> {
      convertFromString("");
    });

  }

  @ParameterizedTest
  @ValueSource(strings = {"N", "r", "FGH"})
  void convertFromString_IllegalArgumentException_NotRomanNumeral_Sarah(String input) {
    assertThrows(IllegalArgumentException.class, () -> {
      convertFromString(input);
    });
  }

  @ParameterizedTest
  @ValueSource(strings = {"0", "MMMMMMMMMM", "-V"})
  void convertFromString_IllegalArgumentException_OutOfBounds_Sarah(String input) {
    assertThrows(IllegalArgumentException.class, () -> {
      convertFromString(input);
    });
  }

  @ParameterizedTest
  @ValueSource(ints = {0, 10000, -5})
  void convertFromInt_IllegalArgumentException_OutOfBounds_Sarah(int input) {
    assertThrows(IllegalArgumentException.class, () -> {
      convertFromInt(input);
    });
  }

  @ParameterizedTest
  @CsvSource(value = {"III,3", "IX,9", "XXXV,35", "LXXXVIII,88", "CI,101", "DCCXCII,792",
      "MMMMMMMMMCMXCIX,9999"})
  void convertFromString_IntegerOutput_ValidRomanNumeralInput_Sarah(String input, int expected) {
    int actual = convertFromString(input);
    assertEquals(expected, actual);
  }

  @ParameterizedTest
  @CsvSource(value = {"3,III", "9,IX", "35,XXXV", "88,LXXXVIII", "101,CI", "792,DCCXCII",
      "9999,MMMMMMMMMCMXCIX"})
  void convertFromInt_StringOutput_ValidIntInput_Sarah(int input, String expected) {
    String actual = convertFromInt(input);
    assertEquals(expected, actual);
  }

  @Test
  void RomanNumeral_ThrowsIllegalArgumentException_StringInputTooHigh_Zoe() {
    assertThrows(IllegalArgumentException.class, () -> {
      new RomanNumeral("T");
    });
  }

  @ParameterizedTest
  @ValueSource(strings = {"I"})
  void RomanNumeral_DoesNotThrowException_StringInputInBounds_Zoe(String string) {
    assertDoesNotThrow(() -> {
      new RomanNumeral(string);
    });
  }

  @ParameterizedTest
  @ValueSource(ints = {RomanNumeral.MIN_VALUE, RomanNumeral.MAX_VALUE})
  void RomanNumeral_DoesNotThrowException_IntInputInBounds_Zoe(int number) {
    assertDoesNotThrow(() -> {
      new RomanNumeral(number);
    });
  }

  @Test
  void getValue_ReturnsCorrectNumericValue_RomanNumeral_Zoe() {
    assertEquals(new RomanNumeral("V").getValue(), 5);
    assertEquals(new RomanNumeral(5).getValue(), 5);
  }

  @Test
  void toString_ReturnsCorrectStringValue_RomanNumeral_Zoe() {
    assertEquals(new RomanNumeral(5).toString(), "V");
    assertEquals(new RomanNumeral("V").toString(), "V");
  }

  @Test
  void convertFromInt_ReturnsCorrectIntToStringConversion_ValidIntInput_Zoe() {
    assertEquals("V", convertFromInt(5));
  }

  @ParameterizedTest
  @CsvSource({"5,V", "10,X", "6,VI", "58,LVIII", "101,CI", "842,DCCCXLII"})
  void convertFromInt_ReturnsCorrectIntToStringConversion_ValidCsvIntInputsLessThan1000_Zoe(
      int input, String actual) {
    assertEquals(actual, convertFromInt(input));
  }

  @ParameterizedTest
  @CsvSource({"1994,MCMXCIV", "3549,MMMDXLIX", "1200,MCC", "2000,MM", "3289,MMMCCLXXXIX"})
  void convertFromInt_ReturnsCorrectIntToStringConversion_ValidCsvIntInputs1000To4000_Zoe(int input,
      String actual) {
    assertEquals(actual, convertFromInt(input));
  }

  @Test
  public void equals_False_X10() {
    // Integer i = new Integer(10);
    Integer i = Integer.valueOf(10);
    assertNotEquals(rnX, i);
  }
}
