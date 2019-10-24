package com.marlo.quicktheories;

import static org.quicktheories.generators.SourceDSL.lists;

import java.util.List;
import java.util.stream.Collectors;
import org.quicktheories.core.Gen;
import org.quicktheories.generators.Generate;

public class Generator {

  //  private static final int NUMERIC_FIRST_CODEPOINT = 0x0030;
  //  private static final int NUMERIC_LAST_CODEPOINT = 0x0039;
  //  private static final int ALPHA_FIRST_CODEPOINT = 0x0041;
  //  private static final int ALPHA_LAST_CODEPOINT = 0x007A;

  public static final List<Character> ALPHA_NUMERICS =
      "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
          .chars()
          .mapToObj(e -> (char) e)
          .collect(Collectors.toList());

  /**
   * A generator for alphanumeric characters.
   *
   * @return Generator a alphanumeric character.
   */
  public static Gen<Character> alphanumeric() {
    return Generate.pick(ALPHA_NUMERICS);
  }

  /**
   * A String generator for alphanumeric characters.
   *
   * @param minimumSize
   * @param maximumSize
   * @return Generator for list of alphanumeric characters.
   */
  public static Gen<List<Character>> alphanumerics(int minimumSize, int maximumSize) {
    return lists().of(alphanumeric()).ofSizeBetween(minimumSize, maximumSize);
  }
}
