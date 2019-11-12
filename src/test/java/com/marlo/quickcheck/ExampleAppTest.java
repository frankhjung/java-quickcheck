package com.marlo.quickcheck;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.pholser.junit.quickcheck.From;
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
  @Property
  public void testWhitespace(final @From(WhiteSpaceGenerator.class) String whitespace) {
    assertEquals(0, ExampleApp.wordCount(new Scanner(whitespace)));
    assertEquals(0, ExampleApp.wordCount(Stream.of(whitespace)));
  }

  @Property
  public void testWhitespaceGenerator(final @From(WhiteSpaceGenerator.class) String whitespaces) {
    assertTrue(whitespaces.length() >= 0);
    assertEquals(0, whitespaces.trim().length());
    assertEquals(0, ExampleApp.wordCount(Stream.of(whitespaces)));
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
   * Test word counter is same for stream as scanner. Trails increased from the default of 100 to
   * 1000.
   *
   * @param words the word stream
   */
  @Property(trials = 1000)
  public void testExampleQuickCheckMethodsGiveSameResult(final String words) {
    assertEquals(ExampleApp.wordCount(Stream.of(words)), ExampleApp.wordCount(new Scanner(words)));
  }

  /**
   * Test word counter is same for stream as scanner using Alphanumeric generator.
   *
   * @param words the word stream
   */
  @Property(trials = 1000)
  public void testAlphanumericStrings(final @From(AlphaNumericGenerator.class) String words) {
    assertEquals(ExampleApp.wordCount(Stream.of(words)), ExampleApp.wordCount(new Scanner(words)));
  }
}
