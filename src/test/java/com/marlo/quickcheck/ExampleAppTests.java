package com.marlo.quickcheck;

import static org.junit.Assert.assertEquals;

import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import org.junit.Test;
import org.junit.runner.RunWith;

/** The type String properties. */
@RunWith(JUnitQuickcheck.class)
public class ExampleAppTests {

  /** Main using custom STDIN. */
  @Test
  public void testMainGoodInput() throws IOException {
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
    final PrintStream originalErrStream = System.err;
    final ByteArrayOutputStream testByteStream = new ByteArrayOutputStream();
    final PrintStream testErrStream = new PrintStream(testByteStream, true, StandardCharsets.UTF_8);
    try {
      // redirect stdio
      System.setIn(testInStream);
      System.setErr(testErrStream);
      // call my main
      ExampleApp.main(new String[] {"bad args"});
      // inspect stdout
      assertEquals(
          "USAGE: count words from file read from STDIN",
          testByteStream.toString(StandardCharsets.UTF_8));
    } finally {
      // restore original stdin and stdout
      testInStream.close();
      testErrStream.close();
      System.setIn(originalInStream);
      System.setErr(originalErrStream);
    }
  }
}
