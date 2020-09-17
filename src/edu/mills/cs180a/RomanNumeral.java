package edu.mills.cs180a;

import java.util.Map;

/**
 * Immutable Roman Numeral, representing a value between {@link #MIN_VALUE} and {@link #MAX_VALUE},
 * inclusive.
 *
 * @author Ellen Spertus
 */
public class RomanNumeral {
  /**
   * The lowest number than can be represented.
   */
  public static final int MIN_VALUE = 1;

  /**
   * The highest number that can be represented.
   */
  public static final int MAX_VALUE = 9999;

  @VisibleForTesting
  protected static final Map<Character, Integer> LETTERS_TO_VALUES =
      Map.of('I', 1, 'V', 5, 'X', 10, 'L', 50, 'C', 100, 'D', 500, 'M', 1000);

  private static final Map<String, Integer> SUBTRACTIVE_FORMS =
      Map.of("IV", 4, "IX", 9, "XL", 40, "XC", 90, "CD", 400, "CM", 900);

  private final int value;
  private String text;

  /**
   * Constructs a newly-allocated {@code RomanNumeral} object that represents the specified value.
   * The argument must be in the range {@link #MIN_VALUE} to {@link #MAX_VALUE}, inclusive.
   *
   * @param value the value to be represented
   * @throws IllegalArgumentException if the argument is out of bounds
   */
  public RomanNumeral(int value) throws IllegalArgumentException {
    this.value = value;
    text = convertFromInt(value);
  }

  /**
   * Constructs a newly-allocated {@code RomanNumeral} object for the specified text. The argument
   * must be in the range {@link #MIN_VALUE} to {@link #MAX_VALUE}, inclusive.
   *
   * @param text the Roman Numeral
   * @throws IllegalArgumentException if the argument is out of bounds
   */
  public RomanNumeral(String text) throws IllegalArgumentException {
    this.text = text;
    value = convertFromString(text);
  }


  /**
   * Returns the numeric value of this {@code RomanNumeral}.
   *
   * @return the numeric value of this {@code RomanNumeral}
   */
  public int getValue() {
    return value;
  }

  @Override
  public String toString() {
    return text;
  }

  @Override
  public boolean equals(Object other) {
    return other instanceof RomanNumeral && value == ((RomanNumeral) other).value;
  }

  /**
   * Returns the number corresponding to the given string representing a Roman Numeral.
   *
   * @param s the Roman Numeral
   * @return the corresponding number
   * @throws IllegalArgumentException if the argument is not a valid Roman Numeral or if it is out
   *         of range
   */
  @VisibleForTesting
  protected static int convertFromString(String s) {
    s = s.toUpperCase();
    if (s.isEmpty()) {
      throw new IllegalArgumentException("The empty string is not a valid Roman Numeral");
    }

    int total = 0;
    String highestSubstringSeen = null; // highest valued symbol or subtractive form
    int highestValueSeen = 0; // value of highestSubstringSeen

    for (int i = 0; i < s.length(); i++) {
      Integer currentValue = null;
      String currentSubstring = null; // subtractive form or individual symbol starting at index i

      // Check if this character starts a subtractive form.
      if (i + 1 < s.length()) {
        currentSubstring = s.substring(i, i + 2);
        if (SUBTRACTIVE_FORMS.containsKey(currentSubstring)) {
          currentValue = SUBTRACTIVE_FORMS.get(currentSubstring);
          i++; // Skip next character, which is part of the current substring.
        }
      }

      // If it wasn't a subtractive form, look up its value.
      if (currentValue == null) {
        char currentChar = s.charAt(i);
        currentValue = LETTERS_TO_VALUES.get(currentChar);
        currentSubstring = Character.toString(currentChar);
      }


      // If we couldn't find its value, the character was illegal.
      if (currentValue == null) {
        throw new IllegalArgumentException("Not a valid Roman Numeral: " + currentSubstring);
      }

      // Keep track of highest substring seen, which should always be the first.
      if (highestSubstringSeen == null) {
        // Initialize on first iteration.
        highestSubstringSeen = currentSubstring;
        highestValueSeen = currentValue;
      } else {
        // Make sure current substring is no higher than earlier substrings.
        if (currentValue > highestValueSeen) {
          throw new IllegalArgumentException(s + " is not a valid Roman Numeral because substring "
              + currentSubstring + " has a higher value (" + currentValue
              + ") than earlier substring " + highestSubstringSeen + " (" + highestValueSeen + ")");
        }
      }

      total += currentValue;
    }
    if (total > MAX_VALUE || total < MIN_VALUE) {
      throw new IllegalArgumentException(
          "Value out of bounds [" + MIN_VALUE + "..." + MAX_VALUE + "]: " + total);
    }
    return total;
  }

  /**
   * Returns the Roman Numeral representation of the given number.
   *
   * @param n the number to convert
   * @return the Roman Numeral representation
   * @throws IllegalArgumentException if the number cannot be represented by a Roman Numeral in the
   *         given range
   */
  @VisibleForTesting
  protected static String convertFromInt(int n) throws IllegalArgumentException {
    if (n > MAX_VALUE || n < MIN_VALUE) {
      throw new IllegalArgumentException(
          "Value out of bounds [" + MIN_VALUE + "..." + MAX_VALUE + "]: " + n);
    }

    StringBuilder sb = new StringBuilder();

    while (n >= 1000) {
      sb.append("M");
      n -= 1000;
    }

    if (n >= 900) {
      sb.append("CM");
      n -= 900;
    }
    if (n >= 500) {
      sb.append("D");
      n -= 500;
    }
    if (n >= 400) {
      sb.append("CD");
      n -= 400;
    }
    while (n >= 100) {
      sb.append("C");
      n -= 100;
    }
    if (n >= 90) {
      sb.append("XC");
      n -= 90;
    }
    if (n >= 50) {
      sb.append("L");
      n -= 50;
    }
    if (n >= 40) {
      sb.append("XL");
      n -= 40;
    }
    while (n >= 10) {
      sb.append("X");
      n -= 10;
    }
    if (n >= 9) {
      sb.append("IX");
      n -= 9;
    }
    if (n >= 5) {
      sb.append("V");
      n -= 5;
    }
    if (n >= 4) {
      sb.append("IV");
      n -= 4;
    }
    while (n > 0) {
      sb.append("I");
      n--;
    }
    return sb.toString();
  }
}
