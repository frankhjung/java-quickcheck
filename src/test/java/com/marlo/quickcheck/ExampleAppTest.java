package com.marlo.quickcheck;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Stream;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.Test;
import org.junit.runner.RunWith;

/** The type String properties. */
@RunWith(JUnitQuickcheck.class)
public class ExampleAppTest {
  /**
   * Concatenation length.
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
    assertEquals(words.trim().split("\\s+").length, ExampleApp.wordCount(Stream.of(words)));
  }

  /** Test empty word count (i.e. 0 expected) using JUnit. */
  @Test
  public void test_word_counter_empty() {
    assertEquals(0, ExampleApp.wordCount(Stream.of("")));
  }

  /**
   * Word countr from stream.
   *
   * @param words the input stream
   */
  @Property
  public void test_word_counter_quickcheck(final ArrayList<String> words) {
    words.removeAll(Collections.singleton(""));
    assertEquals(words.size(), ExampleApp.wordCount(words.stream()));
  }
}
