package edu.mills.cs180a;

import java.util.HashMap;
import java.util.List;
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

  private static final List<String> SYMBOLS_SORTED_BY_DECREASING_VALUE =
      List.of("M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I");

  // Don't covert the same Integer more than once.
  private static Map<Integer, RomanNumeral> numerals = new HashMap<>();
  private final int value;
  private String text;

  /**
   * Provides a RomanNumeral with the specified value.
   *
   * @param value an integer in the range {@link MIN_VALUE} to {@link MAX_VALUE}
   * @return a RomanNumeral with the specified value
   * @throws IllegalArgumentException if value is out of range
   */
  public RomanNumeral valueOf(int value) throws IllegalArgumentException {
    Integer i = Integer.valueOf(value);
    if (numerals.containsKey(Integer.valueOf(i))) {
      return numerals.get(i);
    }
    RomanNumeral rn = new RomanNumeral(value);
    numerals.put(i, rn);
    return rn;
  }

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
   * must be in the range {@link #MIN_VALUE} to {@link #MAX_VALUE}, inclusive. The only subtractive
   * forms that are accepted are "IV", "IX", "XL", "XC", "CD", and "DM".
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
    String previousSubstring = null;
    int previousValue = 0;

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

      // Make sure current value is not greater than previous value,
      // which would indicate an invalid input string.
      if (previousSubstring == null || currentValue <= previousValue) {
        // Update values.
        previousSubstring = currentSubstring;
        previousValue = currentValue;
      } else {
        // Current value is greater than last value.
        throw new IllegalArgumentException(s + " is not a valid Roman Numeral because substring "
            + currentSubstring + " has a higher value (" + currentValue
            + ") than earlier substring " + previousSubstring + " (" + previousValue + ")");
      }

      total += currentValue;
    }
    if (total > MAX_VALUE || total < MIN_VALUE) {
      throw new IllegalArgumentException(
          "Value out of bounds [" + MIN_VALUE + "..." + MAX_VALUE + "]: " + total);
    }
    return total;
  }

  private static int symbolToValue(String symbol) {
    if (symbol.length() == 1) {
      return LETTERS_TO_VALUES.get(symbol.charAt(0));
    } else {
      return SUBTRACTIVE_FORMS.get(symbol);
    }
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
    for (String symbol : SYMBOLS_SORTED_BY_DECREASING_VALUE) {
      int symbolValue = symbolToValue(symbol);
      while (n >= symbolValue) {
        sb.append(symbol);
        n -= symbolValue;
      }
    }
    return sb.toString();
  }
}
