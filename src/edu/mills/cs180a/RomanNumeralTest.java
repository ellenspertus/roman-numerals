package edu.mills.cs180a;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class RomanNumeralTest {
  private RomanNumeral rnX;

  public void setup() {
    rnX = new RomanNumeral("X");
  }

  @Test
  public void equals_True_XX() {
    RomanNumeral rn1 = new RomanNumeral("X");
    RomanNumeral rn2 = new RomanNumeral("X");
    assertEquals(rn1, rn2);
  }

  @Test
  public void equals_True_X10() {
    RomanNumeral rn1 = new RomanNumeral("X");
  }
}
