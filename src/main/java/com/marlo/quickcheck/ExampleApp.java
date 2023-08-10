package com.marlo.quickcheck;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Java example to count words read from STDIN. Test cases are using the <a
 * href="https://pholser.github.io/junit-quickcheck/">junit-quickcheck</a> library.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ExampleApp {

  /**
   * Count words from STDIN.
   *
   * @param args no input arguments used
   */
  public static void main(final String[] args) {
    // does not expect parameters - read from stdin
    if (args.length == 0) {
      try (Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8)) {
        System.out.println(WordCountUtils.count(scanner));
      }
    } else {
      System.err.print("USAGE: count words from file read from STDIN");
    }
  }
}
