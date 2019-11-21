package com.marlo.quickcheck;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * Java example to count words read from STDIN. Test cases are using the <a
 * href="https://pholser.github.io/junit-quickcheck/">junit-quickcheck</a> library.
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
      System.err.print("USAGE: count words from file read from STDIN");
    } else {
      // only read from stdin
      try (Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8)) {
        System.out.println(WordCountUtils.count(scanner));
      }
    }
  }
}
