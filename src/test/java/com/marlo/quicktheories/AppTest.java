package com.marlo.quicktheories;

import static com.marlo.quicktheories.Generator.ALPHA_NUMERICS;
import static com.marlo.quicktheories.Generator.alphanumeric;
import static org.quicktheories.QuickTheory.qt;

import org.junit.Test;
import org.quicktheories.core.Gen;

public class AppTest {
  @Test
  public void test_alphanumeric() {
    Gen<Character> test_c = alphanumeric();
    qt().withExamples(100000)
        .forAll(test_c)
        .describedAs(c -> "failed for value " + c)
        .check(c -> ALPHA_NUMERICS.indexOf(c) != -1);
  }
}
