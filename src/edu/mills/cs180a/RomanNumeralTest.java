package edu.mills.cs180a;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

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
  public void equals_True_Symmetric_X_VV() {
    RomanNumeral rn2 = new RomanNumeral("VV");
    assertEquals(rnX,rn2);
    assertEquals(rn2,rnX);
  }

  @Test
  public void equals_True_Transitive_X_VV_VIIIII() {
    RomanNumeral rn2 = new RomanNumeral("VV");
    RomanNumeral rn3 = new RomanNumeral("VIIIII");
    assertEquals(rnX,rn2);
    assertEquals(rn2,rn3);
    assertEquals(rnX,rn3);
  }

  @Test
  public void equals_True_Consistent_X_VIIIII() {
    RomanNumeral rn3 = new RomanNumeral("VIIIII");
    assertEquals(rnX,rn3);
  }

  @Test
  public void equals_False_Null() {
    RomanNumeral rn2 = new RomanNumeral(null);
    assertFalse(rnX,rn2);
  }

}
