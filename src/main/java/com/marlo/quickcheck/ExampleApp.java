package com.marlo.quickcheck;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * Example Word Count from STDIN program to evaluate junit-quickcheck.<br>
 *
 * @see <a href="https://pholser.github.io/junit-quickcheck/">junit-quickcheck</a>
 */
public final class ExampleApp {

  /**
   * Count words from StdIn.
   *
   * @param args the input arguments
   */
  public static void main(final String[] args) {
    // does not expect parameters
    if (args.length > 0) {
      System.out.printf("USAGE: count words from file read from STDIN");
    } else {
      // only read from stdin
      try (Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8)) {
        System.out.println(WordCountUtil.count(scanner));
      }
    }
  }
}
