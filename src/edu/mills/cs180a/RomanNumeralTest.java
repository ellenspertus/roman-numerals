package edu.mills.cs180a;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
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
  public void equals_True_Reflexivity() {
    assertTrue(rnX.equals(rnX));
  }

  @Test
  public void equals_True_Symmetric() {
    RomanNumeral rn2 = new RomanNumeral("X");
    assertEquals(rnX.equals(rn2), rn2.equals(rnX));
  }

  @Test
  public void equals_True_Transitive() {
    RomanNumeral rn2 = new RomanNumeral("X");
    RomanNumeral rn3 = new RomanNumeral("X");

    assertEquals(rnX.equals(rn3), rnX.equals(rn2) && rn2.equals(rn3));
  }

  @Test
  public void equals_False_Nonnullity() {
    assertFalse(rnX.equals(null));
  }
}
