package com.marlo.quickcheck;

import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.generator.Generator;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;
import java.util.stream.IntStream;

/** Generate alpha-numeric characters. */
public final class AlphaNumericGenerator extends Generator<String> {

  /** Alphanumeric characters: a-zA-Z0-9 */
  private static final String ALPHANUMERICS =
      "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

  /** Maximum string size. */
  private static final int CAPACITY = 10;

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
              final int randomIndex = randomness.nextInt(ALPHANUMERICS.length());
              randomString.append(ALPHANUMERICS.charAt(randomIndex));
            });
    return randomString.toString();
  }
}
