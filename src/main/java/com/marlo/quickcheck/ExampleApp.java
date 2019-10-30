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
public class ExampleApp {

  /**
   * Function to count words.
   *
   * @param scanner the input to count words from
   * @return the long
   */
  public static long wordCount(Scanner scanner) {
    return scanner.useDelimiter("\\s+").tokens().count();
  }

  public static long wordCount(Stream<String> stream) {
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
  public static void main(String[] args) {
    // does not expect parameters
    var log = LoggerFactory.getLogger(ExampleApp.class);
    if (args.length > 0) {
      log.error("Found {} arguments, none expected.", args.length);
    }

    // only read from stdin
    Scanner scanner = new Scanner(System.in);
    System.out.println(wordCount(scanner));
    scanner.close();
  }
}
