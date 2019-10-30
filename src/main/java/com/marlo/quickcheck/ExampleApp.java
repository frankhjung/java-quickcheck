package com.marlo.quickcheck;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;
import org.slf4j.LoggerFactory;

/**
 * Example Word Count from STDIN program to evaluate junit-quickcheck.
 *
 * @see <a href="https://pholser.github.io/junit-quickcheck/">junit-quickcheck</a>
 */
class ExampleApp {

  /**
   * Function to count words.
   *
   * @param scanner the input to count words from
   * @return the long
   */
  public static long wordCount(final Scanner scanner) {
    return scanner.useDelimiter("\\s+").tokens().count();
  }

  /**
   * Word count long.
   *
   * @param stream the input stream
   * @return word count as a long value
   */
  public static long wordCount(final Stream<String> stream) {
    return stream
        .map(line -> line.trim().split("\\s+"))
        .flatMap(Arrays::stream)
        .filter(w -> w.length() > 0)
        .count();
  }

  /**
   * Count words from StdIn.
   *
   * @param args the input arguments
   */
  public static void main(final String[] args) {
    // does not expect parameters
    final var log = LoggerFactory.getLogger(ExampleApp.class);
    if (args.length > 0) {
      log.error("Found {} arguments, none expected.", args.length);
    }

    // only read from stdin
    final Scanner scanner = new Scanner(System.in);
    try {
      log.info("{} words", wordCount(scanner));
    } catch (Exception e) {
      log.error("Unknown error occurred.", e);
    } finally {
      scanner.close();
    }
  }
}
