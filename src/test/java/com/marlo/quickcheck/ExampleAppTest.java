package com.marlo.quickcheck;

import static org.junit.Assert.assertEquals;

import com.pholser.junit.quickcheck.From;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.stream.Stream;
import org.junit.Assume;
import org.junit.Test;
import org.junit.runner.RunWith;

/** The type String properties. */
@RunWith(JUnitQuickcheck.class)
public class ExampleAppTest {

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
      // redirect stdin and stdout
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
    assertEquals(3, WordCountUtil.count(new Scanner("one two three")));
    assertEquals(3, WordCountUtil.count(Stream.of("one two three")));
  }

  /** Test empty word count (i.e. 0 expected) using JUnit. */
  @Test
  public void testEmpty() {
    assertEquals(0, WordCountUtil.count(new Scanner("")));
    assertEquals(0, WordCountUtil.count(Stream.of("")));
  }

  /** Test empty word count (i.e. 0 expected) using JUnit. */
  @Property
  public void testWhitespaceScannerGenerator(
      final @From(WhiteSpaceGenerator.class) String whitespaces) {
    assertEquals(0, whitespaces.trim().length());
    assertEquals(0, WordCountUtil.count(new Scanner(whitespaces)));
  }

  /** If string contains whitespace then word count should be zero. */
  @Property
  public void testWhitespaceStreamGenerator(
      final @From(WhiteSpaceGenerator.class) String whitespaces) {
    assertEquals(0, whitespaces.trim().length());
    assertEquals(0, WordCountUtil.count(Stream.of(whitespaces)));
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
    assertEquals(words.trim().split("\\s+").length, WordCountUtil.count(Stream.of(words)));
  }

  /**
   * Test word counter is same for stream as scanner. <br>
   * Trails increased from the default of 100 to 1000.
   *
   * @param words the word stream
   */
  @Property(trials = 1000)
  public void testExampleQuickCheckMethodsGiveSameResult(final String words) {
    assertEquals(WordCountUtil.count(Stream.of(words)), WordCountUtil.count(new Scanner(words)));
  }

  /**
   * Test word counter is same for stream as scanner using Alphanumeric generator.
   *
   * @param words the word stream
   */
  @Property(trials = 1000)
  public void testAlphanumericStrings(final @From(AlphaNumericGenerator.class) String words) {
    assertEquals(WordCountUtil.count(Stream.of(words)), WordCountUtil.count(new Scanner(words)));
  }
}
