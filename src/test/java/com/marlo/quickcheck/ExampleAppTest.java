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

  /** Word count using traditional JUnit */
  @Test
  public void testStandardJunit() {
    assertEquals(3, ExampleApp.wordCount(new Scanner("one two three")));
    assertEquals(3, ExampleApp.wordCount(Stream.of("one two three")));
  }

  /** Test empty word count (i.e. 0 expected) using JUnit. */
  @Test
  public void testEmpty() {
    assertEquals(0, ExampleApp.wordCount(new Scanner("")));
    assertEquals(0, ExampleApp.wordCount(Stream.of("")));
  }

  /** Test empty word count (i.e. 0 expected) using JUnit. */
  @Test
  public void testWhitespace() {
    assertEquals(0, ExampleApp.wordCount(new Scanner("         ")));
    assertEquals(0, ExampleApp.wordCount(Stream.of("         ")));
  }

  /**
   * Common example of concatenation of string lengths.
   *
   * @param sample1 first test string
   * @param sample2 second test string
   */
  @Property
  public void testQuickCheckConcatenationLength(final String sample1, final String sample2) {
    assertEquals(sample1.length() + sample2.length(), (sample1 + sample2).length());
  }

  /**
   * Word count from a random string.
   *
   * @param words from random string
   */
  @Property
  public void testExampleQuickCheck(final String words) {
    Assume.assumeNotNull(words);
    Assume.assumeFalse(words.trim().isEmpty());
    assertEquals(words.trim().split("\\s+").length, ExampleApp.wordCount(Stream.of(words)));
  }

  /**
   * Test word counter is same for stream as scanner.
   *
   * @param words the word stream
   */
  @Property
  public void testExampleQuickCheckMethodsGiveSameResult(final String words) {
    assertEquals(ExampleApp.wordCount(Stream.of(words)), ExampleApp.wordCount(new Scanner(words)));
  }
}
