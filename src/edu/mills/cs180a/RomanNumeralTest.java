package edu.mills.cs180a;

import static org.junit.Assert.assertTrue;
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
  public void equals_IsReflexive_BHamrick() {
    assertTrue(rnX.equals(rnX));
  }

  public void equals_IsTransitive_BHamrick() {
    RomanNumeral x = new RomanNumeral("IV");
    RomanNumeral y = new RomanNumeral("IV");
    RomanNumeral z = new RomanNumeral("IV");

    assertTrue(x.equals(y) | y.equals(z) | x.equals(z));
  }

  @Test
  public void equals_IsSymmetric_BHamrick() {
    RomanNumeral x = new RomanNumeral("IV");
    RomanNumeral y = new RomanNumeral("IV");

    assertTrue(x.equals(y) | y.equals(x));
  }

  @Test
  public void equals_IsStableOverTime_BHamrick() {
    RomanNumeral x = new RomanNumeral("X");
    assertTrue(x.equals(rnX));
    try {
      Thread.sleep(1000);
      assertTrue(x.equals(rnX));
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void equals_IsNotNull_Bhamrick() {
    RomanNumeral x = new RomanNumeral("IV");
    assertFalse(x.equals(null));
  }
}
