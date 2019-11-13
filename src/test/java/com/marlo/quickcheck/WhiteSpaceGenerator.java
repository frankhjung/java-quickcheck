package com.marlo.quickcheck;

import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.generator.Generator;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;
import java.util.stream.IntStream;

/** Generates a string of whitespace characters. */
public final class WhiteSpaceGenerator extends Generator<String> {

  /** Valid whitespace characters. */
  static final String WHITESPACE_CHARS =
      " " // SPACE
          + "\n" // LINE FEED (LF)
          + "\r" // CARRIAGE RETURN (CR)
          + "\t" // CHARACTER TABULATION
          + "\u000B" // LINE TABULATION
          + "\f" // FORM FEED (FF)
      ;

  /** Maximum string size. */
  private static final int CAPACITY = 100;

  /** Inherit form super class. */
  public WhiteSpaceGenerator() {
    super(String.class);
  }

  @Override
  public String generate(final SourceOfRandomness randomness, final GenerationStatus status) {
    final StringBuilder randomString = new StringBuilder(CAPACITY);
    IntStream.range(0, CAPACITY)
        .forEach(
            ignored -> {
              final int randomIndex = randomness.nextInt(WHITESPACE_CHARS.length());
              randomString.append(WHITESPACE_CHARS.charAt(randomIndex));
            });
    return randomString.toString();
  }
}
