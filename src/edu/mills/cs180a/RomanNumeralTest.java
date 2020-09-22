package edu.mills.cs180a;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RomanNumeralTest {
  private static RomanNumeral rnX;

  @BeforeAll
  public static void setup() {
    rnX = new RomanNumeral("X");
  }

  @Test
  public void equals_True_XX() {
    RomanNumeral rn2 = new RomanNumeral("X");
    assertEquals(rnX, rn2);
  }

  @Test
  public void equals_False_X10() {
    // Integer i = new Integer(10);
    Integer i = Integer.valueOf(10);
    assertNotEquals(rnX, i);
  }

  @Test
  public void equals_True_XV_15() {
    Integer i = Integer.valueOf(15);
    assertTrue(i.equals(RomanNumeral.convertFromString("XV")));
  }

  @Test
  public void equals_False_XV_10() {
    Integer i = Integer.valueOf(10);
    assertFalse(i.equals(RomanNumeral.convertFromString("XX")));
  }

  @Test
  public void equals_True_CI_101() {
    Integer i = Integer.valueOf(101);
    assertTrue(i.equals(RomanNumeral.convertFromString("CI")));
  }

  @ParameterizedTest
  @CsvSource({
    "V, 5",
    "VI, 6",
    "DCLXVI, 666",
    "IV, 4",
    "XCIX, 99",
    "CDXCIX, 499",
    "LV, 55",
    "CMXLIV, 944",
    "M, 1000",
    "C, 100",
    "DCC, 700",
    "XI, 11",
    "LXX, 70",
    "VII, 7",
    "VIIi, 8",
    "LX, 60",
    "LXX, 70",
    "LXXX, 80",
    "DC, 600",
    "DCC, 700",
    "DCCC, 800"
  })
  void convertFromStringReturnsCorrectInt(String input, Integer expected) {
    int actual = RomanNumeral.convertFromString(input);
    assertTrue(expected.equals(actual));
  }
}
