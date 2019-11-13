package com.marlo.quickcheck;

import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.generator.Generator;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;
import java.util.stream.IntStream;

/** Generate alpha-numeric characters. */
public final class AlphaNumericGenerator extends Generator<String> {

  static final String ALPHANUMERIC_CHARS =
      "abcdefghijklmnopqrstuvwxyz"
          + "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
          + "0123456789"
          + WhiteSpaceGenerator.WHITESPACE_CHARS;

  /** Maximum string size. */
  private static final int CAPACITY = 100;

  /** Inherit form super class. */
  public AlphaNumericGenerator() {
    super(String.class);
  }

  @Override
  public String generate(final SourceOfRandomness randomness, final GenerationStatus status) {
    final StringBuilder randomString = new StringBuilder(CAPACITY);
    IntStream.range(0, CAPACITY)
        .forEach(
            ignored -> {
              final int randomIndex = randomness.nextInt(ALPHANUMERIC_CHARS.length());
              randomString.append(ALPHANUMERIC_CHARS.charAt(randomIndex));
            });
    return randomString.toString();
  }
}
