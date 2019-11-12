package com.marlo.quickcheck;

import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.generator.Generator;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;
import java.util.stream.IntStream;

/**
 * Generates a string of whitespace characters.
 *
 * <p>@See {link https://pholser.github.io/junit-quickcheck/site/0.9/usage/configuring.html} <br>
 * @See {link https://www.veracode.com/blog/managing-appsec/property-based-testing-java} <br>
 * @See {link https://docs.oracle.com/javase/8/docs/api/java/lang/Character.html}
 */
public final class WhiteSpaceGenerator extends Generator<String> {

  private static final String WHITESPACE_CHARS =
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
    StringBuilder sb = new StringBuilder(CAPACITY);
    IntStream.range(0, CAPACITY)
        .forEach(
            ignored -> {
              int randomIndex = randomness.nextInt(WHITESPACE_CHARS.length());
              sb.append(WHITESPACE_CHARS.charAt(randomIndex));
            });
    return sb.toString();
  }
}
