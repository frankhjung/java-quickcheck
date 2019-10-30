package com.marlo.quickcheck;

import static org.junit.Assert.assertEquals;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import java.util.Scanner;
import java.util.stream.Stream;
import org.junit.Assume;
import org.junit.Test;
import org.junit.runner.RunWith;

/** The type String properties. */
@RunWith(JUnitQuickcheck.class)
public class ExampleAppTest {
  /**
   * Common example of concatenation of string lengths.
   *
   * @param s1 the s 1
   * @param s2 the s 2
   */
  @Property
  public void test_concatenationLength(String s1, String s2) {
    assertEquals(s1.length() + s2.length(), (s1 + s2).length());
  }

  /** Word count using traditional JUnit */
  @Test
  public void test_word_counter_junit() {
    String words = "one two three";
    assertEquals(3, ExampleApp.wordCount(new Scanner(words)));
    assertEquals(3, ExampleApp.wordCount(Stream.of(words)));
  }

  /** Test empty word count (i.e. 0 expected) using JUnit. */
  @Test
  public void test_word_counter_empty() {
    String empty = "";
    assertEquals(0, ExampleApp.wordCount(new Scanner(empty)));
    assertEquals(0, ExampleApp.wordCount(Stream.of(empty)));
  }

  /** Test empty word count (i.e. 0 expected) using JUnit. */
  @Test
  public void test_word_counter_whitespace() {
    String whitespace = "         ";
    assertEquals(0, ExampleApp.wordCount(new Scanner(whitespace)));
    assertEquals(0, ExampleApp.wordCount(Stream.of(whitespace)));
  }

  /**
   * Word count from a random string.
   *
   * @param words from random string
   */
  @Property
  public void test_word_counter_quick_check(final String words) {
    Assume.assumeNotNull(words);
    Assume.assumeTrue(words.trim().length() > 0);
    assertEquals(words.trim().split("\\s+").length, ExampleApp.wordCount(Stream.of(words)));
  }

  /**
   * Test word counter is same for stream as scanner.
   *
   * @param words the word stream
   */
  @Property
  public void test_word_counter_stream(final String words) {
    Stream<String> stream = Stream.of(words);
    Scanner scanner = new Scanner(words);
    assertEquals(ExampleApp.wordCount(stream), ExampleApp.wordCount(scanner));
  }
}
