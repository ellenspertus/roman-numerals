package edu.mills.cs180a;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class RomanNumeralTest {
  private static RomanNumeral rnX;

  @BeforeAll
  public static void setup() {
    rnX = new RomanNumeral("X");
  }

  // Start of my tests.
  @Test
  void convertFromString_ThrowsIllegalArgumentException_InputExceedsHighBound() {
    assertThrows(IllegalArgumentException.class, () -> {
      RomanNumeral.convertFromString("MMMMMMMMMMMM");
    });
  }

  @Test
  void convertFromString_DoesNotThrowIllegalArgumentException_InputValid() {
    assertDoesNotThrow(() -> {
      RomanNumeral.convertFromString("X");
    });
  }

  @Test
  void convertFromString_ThrowsIllegalArgumentException_InputInvalid() {
    assertThrows(IllegalArgumentException.class, () -> {
      RomanNumeral.convertFromString("Z");
    });
  }

  // Null case throws null pointer exception.
  @Test
  void convertFromString_ThrowsIllegalArgumentException_InputNull() {
    assertThrows(IllegalArgumentException.class, () -> {
      RomanNumeral.convertFromString(null);
    });
  }

  @Test
  void convertFromString_ThrowsIllegalArgumentException_InputEmpty() {
    assertThrows(IllegalArgumentException.class, () -> {
      RomanNumeral.convertFromString("");
    });
  }

  @ParameterizedTest
  @ValueSource(strings = {"I", "MMMMMMMMMCMXCIX"}) // 1, 9999
  void convertFromString_DoesNotThrowException_InputValid(String string) {
    assertDoesNotThrow(() -> {
      RomanNumeral.convertFromString(string);
    });
  }

  // Start of pre-existing tests.

  @Test
  public void equals_True_SameStrings() {
    RomanNumeral rn2 = new RomanNumeral("X");
    assertEquals(rnX, rn2);
    assertEquals(rn2, rnX); // test symmetry
  }

  @Test
  public void equals_False_ComparisonToInteger() {
    // Integer i = new Integer(10);
    Integer i = Integer.valueOf(10);
    assertNotEquals(rnX, i);
  }

  @Test
  public void equals_True_Reflexive() {
    assertTrue(rnX.equals(rnX));
  }

  @Test
  public void equals_True_Transitive() {
    RomanNumeral x = new RomanNumeral("IV");
    RomanNumeral y = new RomanNumeral("IV");
    RomanNumeral z = new RomanNumeral("IV");

    assertTrue(x.equals(y) && y.equals(z) && x.equals(z));
  }

  @Test
  public void equals_True_Symmetric() {
    RomanNumeral x = new RomanNumeral("IV");
    RomanNumeral y = new RomanNumeral("IV");

    assertTrue(x.equals(y) && y.equals(x));
  }

  @Test
  public void equals_False_Null() {
    RomanNumeral x = new RomanNumeral("IV");
    assertFalse(x.equals(null));
  }

  @Test
  public void equals_True_Self() {
    assertEquals(rnX, rnX);
  }

  @Test
  public void equals_False_null() {
    assertNotEquals(rnX, null);
  }

  @Test
  public void equals_True_TransitiveXXX() {
    RomanNumeral rn2 = new RomanNumeral("X");
    RomanNumeral rn3 = new RomanNumeral("X");
    assertEquals(rnX, rn2);
    assertEquals(rn2, rn3);
    assertEquals(rnX, rn3);
  }
}
