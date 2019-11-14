package com.marlo.quickcheck;

import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.generator.Generator;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;
import java.util.stream.IntStream;

/** Generates a string of whitespace characters. */
public final class WhiteSpaceGenerator extends Generator<String> {

  /** Valid whitespace characters. */
  private static final String WHITESPACES =
      " " // SPACE
          + "\n" // LINE FEED (LF)
          + "\r" // CARRIAGE RETURN (CR)
          + "\t" // CHARACTER TABULATION
          + "\u000B" // LINE TABULATION
          + "\f" // FORM FEED (FF)
      ;

  /** Maximum string size. */
  private static final int MAXIMUM_WORD_LENGTH = 11;

  /** Inherit form super class. */
  public WhiteSpaceGenerator() {
    super(String.class);
  }

  @Override
  public String generate(final SourceOfRandomness randomness, final GenerationStatus status) {
    final int stringSize = randomness.nextInt(MAXIMUM_WORD_LENGTH);
    final StringBuilder randomString = new StringBuilder(stringSize);
    IntStream.range(0, stringSize)
        .forEach(
            ignored -> {
              final int randomIndex = randomness.nextInt(WHITESPACES.length());
              randomString.append(WHITESPACES.charAt(randomIndex));
            });
    return randomString.toString();
  }
}
