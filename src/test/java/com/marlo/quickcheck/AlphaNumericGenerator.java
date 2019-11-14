package com.marlo.quickcheck;

import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.generator.Generator;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;
import java.util.stream.IntStream;

/** Generate alpha-numeric characters. */
public final class AlphaNumericGenerator extends Generator<String> {

  /** Alphanumeric characters: "0-9A-Za-z". */
  private static final String ALPHANUMERICS =
      "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

  /** Maximum string size. */
  private static final int MAXIMUM_WORD_LENGTH = 11;

  /** Inherit form super class. */
  public AlphaNumericGenerator() {
    super(String.class);
  }

  /** Generate a word. Do not create null words. */
  @Override
  public String generate(final SourceOfRandomness randomness, final GenerationStatus status) {
    final int stringSize = randomness.nextInt(MAXIMUM_WORD_LENGTH) + 1; // want non-null words
    final StringBuilder randomString = new StringBuilder(stringSize);
    IntStream.range(0, stringSize)
        .forEach(
            ignored -> {
              final int randomIndex = randomness.nextInt(ALPHANUMERICS.length());
              randomString.append(ALPHANUMERICS.charAt(randomIndex));
            });
    return randomString.toString();
  }
}
