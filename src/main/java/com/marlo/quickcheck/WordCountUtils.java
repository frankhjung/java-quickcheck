package com.marlo.quickcheck;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

/** Provides two methods to count words. */
public final class WordCountUtils {

  private WordCountUtils() {
    // hide constructor
  }

  /**
   * Get word count from scanner.
   *
   * @param scanner words are read from this input scanner
   * @return word count as a long value
   */
  public static long count(final Scanner scanner) {
    return scanner.useDelimiter("\\s+").tokens().count();
  }

  /**
   * Get word count from stream.
   *
   * @param stream words are read from this input stream
   * @return word count as a long value
   */
  public static long count(final Stream<String> stream) {
    return stream
        .map(line -> line.trim().split("\\s+"))
        .flatMap(Arrays::stream)
        .filter(w -> w.length() > 0)
        .count();
  }
}
