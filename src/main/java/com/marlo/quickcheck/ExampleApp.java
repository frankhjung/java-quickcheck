package com.marlo.quickcheck;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;
import org.slf4j.LoggerFactory;

/** Word Count from stdin. */
public class ExampleApp {

  /**
   * Function to count words.
   *
   * @param stream the input stream to count words from
   * @return the long
   */
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
    var log = LoggerFactory.getLogger(ExampleApp.class);
    if (args.length > 0) {
      log.error("Found {} arguments, none expected.", args.length);
    }

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    System.out.println(wordCount(reader.lines()));
  }
}
