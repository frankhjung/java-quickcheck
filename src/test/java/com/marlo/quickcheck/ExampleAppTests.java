package com.marlo.quickcheck;

import static org.junit.Assert.assertEquals;

import com.pholser.junit.quickcheck.From;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;
import org.junit.Assume;
import org.junit.Test;
import org.junit.runner.RunWith;

/** The type String properties. */
@RunWith(JUnitQuickcheck.class)
public class ExampleAppTests {

  /** Main using custom STDIN. */
  @Test
  public void testMainStdIn() throws IOException {
    // override stdin
    final InputStream originalInStream = System.in;
    final InputStream testInStream =
        new ByteArrayInputStream("one two".getBytes(StandardCharsets.UTF_8));
    // capture stdout to byte array
    final PrintStream originalOutStream = System.out;
    final ByteArrayOutputStream testByteStream = new ByteArrayOutputStream();
    final PrintStream testOutStream = new PrintStream(testByteStream, true, StandardCharsets.UTF_8);
    try {
      // redirect stdin and stdout
      System.setIn(testInStream);
      System.setOut(testOutStream);
      // call main
      ExampleApp.main(new String[] {});
      // inspect stdout
      assertEquals("2\n", testByteStream.toString(StandardCharsets.UTF_8));
    } finally {
      // restore original stdin and stdout
      testInStream.close();
      testOutStream.close();
      testByteStream.close();
      System.setIn(originalInStream);
      System.setOut(originalOutStream);
    }
  }

  /** Main does not accept parameters. */
  @Test
  public void testMainBadInput() throws IOException {
    // override stdin
    final InputStream originalInStream = System.in;
    final InputStream testInStream = new ByteArrayInputStream(new byte[0]);
    // capture stdout to byte array
    final PrintStream originalOutStream = System.out;
    final ByteArrayOutputStream testByteStream = new ByteArrayOutputStream();
    final PrintStream testOutStream = new PrintStream(testByteStream, true, StandardCharsets.UTF_8);
    try {
      // redirect stdio
      System.setIn(testInStream);
      System.setOut(testOutStream);
      // call my main
      ExampleApp.main(new String[] {"bad args"});
      // inspect stdout
      assertEquals(
          "USAGE: count words from file read from STDIN",
          testByteStream.toString(StandardCharsets.UTF_8));
    } finally {
      // restore original stdin and stdout
      testInStream.close();
      testOutStream.close();
      System.setIn(originalInStream);
      System.setOut(originalOutStream);
    }
  }

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
  public void testExampleQuickCheck(final String words) {
    Assume.assumeNotNull(words);
    Assume.assumeFalse(words.trim().isEmpty());
    assertEquals(words.trim().split("\\s+").length, WordCountUtils.count(Stream.of(words)));
  }

  /**
   * Test word counter is same for stream as scanner. <br>
   *
   * @param words a list of words from random string
   */
  @Property
  public void testExampleQuickCheckMethodsGiveSameResult(final List<String> words) {
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
