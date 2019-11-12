package com.marlo.quickcheck;

import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.generator.Generator;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;
import java.util.stream.IntStream;

/**
 * Generate alpha-numeric characters. @See <br>
 * {link https://pholser.github.io/junit-quickcheck/site/0.9/usage/configuring.html}
 */
public class AlphaNumericGenerator extends Generator<String> {

  private static final String ALPHANUMERIC_CHARS = // NOPMD
      "abcdefghijklmnopqrstuvwxyz" + "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789";

  /** Maximum string size. */
  private static final int CAPACITY = 100;

  /** Inherit form super class. */
  public AlphaNumericGenerator() {
    super(String.class);
  }

  @Override
  public String generate(SourceOfRandomness randomness, GenerationStatus status) {

    StringBuilder randomString = new StringBuilder(CAPACITY);
    IntStream.range(0, CAPACITY)
        .forEach(
            ignored -> {
              int randomIndex = randomness.nextInt(ALPHANUMERIC_CHARS.length());
              randomString.append(ALPHANUMERIC_CHARS.charAt(randomIndex));
            });
    return randomString.toString();
  }
}
