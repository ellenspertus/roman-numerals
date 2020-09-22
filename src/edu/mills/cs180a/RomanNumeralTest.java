package edu.mills.cs180a;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    RomanNumeral rnVV = new RomanNumeral("VV");
    assertEquals(rnX,rnVV);
    assertEquals(rnVV,rnX);
  }

  @Test
  public void equals_True_Transitive_X_VV_VIIIII() {
    RomanNumeral rnVV = new RomanNumeral("VV");
    RomanNumeral rnV5I = new RomanNumeral("VIIIII");
    assertEquals(rnX,rnVV);
    assertEquals(rnVV,rnV5I);
    assertEquals(rnX,rnV5I);
  }

  @Test
  public void equals_True_Consistent_X_VIIIII() {
    RomanNumeral rnV4I = new RomanNumeral("VIIIII");
    assertEquals(rnX,rnV4I);
  }

  //  @Test
  //  public void equals_False_Null() {
  //    RomanNumeral rnNull = new RomanNumeral(null);
  //    assertFalse(rnX,rnNull);
  //  }

}
