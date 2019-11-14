package com.marlo.quickcheck;

import static org.junit.Assert.assertEquals;

import com.pholser.junit.quickcheck.From;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assume;
import org.junit.Test;
import org.junit.runner.RunWith;

/** WordCountUtil tests using both JUnit and junit-quickcheck. */
@RunWith(JUnitQuickcheck.class)
@Slf4j
public class WordCountTests {

  /** Word count using traditional JUnit. */
  @Test
  public void testWordCounts() {
    assertEquals(3, WordCountUtils.count(new Scanner("one two three")));
    assertEquals(3, WordCountUtils.count(Stream.of("one two three")));
  }

  /** Test empty word count (i.e. 0 expected) using JUnit. */
  @Test
  public void testEmpty() {
    assertEquals(0, WordCountUtils.count(new Scanner("")));
    assertEquals(0, WordCountUtils.count(Stream.of("")));
  }

  /**
   * Test scanner with empty word count (0 expected). Trails increased from the default of 100 to
   * 1000.
   */
  @Property(trials = 1000)
  public void testWhitespaceScannerGenerator(
      final @From(WhiteSpaceGenerator.class) String whitespaces) {
    assertEquals(0, whitespaces.trim().length());
    assertEquals(0, WordCountUtils.count(new Scanner(whitespaces)));
  }

  /**
   * Test stream with empty word count (0 expected). Trails increased from the default of 100 to
   * 1000.
   */
  @Property(trials = 1000)
  public void testWhitespaceStreamGenerator(
      final @From(WhiteSpaceGenerator.class) String whitespaces) {
    assertEquals(0, whitespaces.trim().length());
    assertEquals(0, WordCountUtils.count(Stream.of(whitespaces)));
  }

  /**
   * Count words from a random string and compare with naive word count algorithm.
   *
   * @param words a word from random string
   */
  @Property
  public void testCountUsingSplit(final String words) {
    Assume.assumeNotNull(words);
    Assume.assumeFalse(words.trim().isEmpty());
    final long count = WordCountUtils.count(Stream.of(words));
    log.debug("testCountUsingSplit: {}", words);
    assertEquals(words.trim().split("\\s+").length, count);
  }

  //  /**
  //   * Example of failed test.
  //   *
  //   * @param words a word from random string
  //   */
  //  @Property
  //  public void testFailedTest(final String words) {
  //    Assume.assumeNotNull(words);
  //    Assume.assumeFalse(words.trim().isEmpty());
  //    log.debug("testFailedTest: {}", words);
  //    assertEquals(words.trim().split("\\n+").length, WordCountUtils.count(Stream.of(words)));
  //  }

  /**
   * Test word counter is same for stream as scanner. <br>
   *
   * @param words a list of words from random string
   */
  @Property
  public void testCountStreamSameAsScanner(final List<String> words) {
    final String sentence = String.join("", words);
    assertEquals(
        WordCountUtils.count(Stream.of(sentence)), WordCountUtils.count(new Scanner(sentence)));
  }

  /**
   * Test alphanumeric word is same for stream as scanner using Alphanumeric generator. Trails
   * increased from the default of 100 to 1000.
   *
   * @param word a random alphanumeric word
   */
  @Property(trials = 1000)
  public void testAlphanumericWord(final @From(AlphaNumericGenerator.class) String word) {
    assertEquals(1, WordCountUtils.count(new Scanner(word)));
    assertEquals(1, WordCountUtils.count(Stream.of(word)));
  }

  /**
   * Test a sentence of alphanumeric words. A sentence is a list of words separated by a space.
   *
   * @param words the word stream
   */
  @Property
  public void testAlphanumericSentence(
      final List<@From(AlphaNumericGenerator.class) String> words) {
    final String sentence = String.join(" ", words);
    assertEquals(
        WordCountUtils.count(new Scanner(sentence)), WordCountUtils.count(Stream.of(sentence)));
  }
}
