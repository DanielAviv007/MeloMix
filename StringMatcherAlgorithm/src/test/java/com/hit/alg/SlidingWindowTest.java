package com.hit.alg;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SlidingWindowTest {
    private final StringMatcher stringMatcher = new SlidingWindow();

    @Test
    public void nullStrings_FAILURE() {
        Assertions.assertFalse(stringMatcher.contains(null, null));
    }

    @Test
    public void oneNullString_FAILURE() {
        Assertions.assertFalse(stringMatcher.contains("NotNull", null));
        Assertions.assertFalse(stringMatcher.contains(null, "NotNull"));
    }

    @Test
    public void patternLengthGreaterThanText_FAILURE() {
        Assertions.assertFalse(stringMatcher.contains("a", "aa"));
    }

    @Test
    public void patternNotInText_FAILURE() {
        Assertions.assertFalse(stringMatcher.contains("aa", "b"));
        Assertions.assertFalse(stringMatcher.contains("Hello World!", "world"));
    }

    @Test
    public void patternInText_SUCCESS() {
        Assertions.assertTrue(stringMatcher.contains("hello", "he"));     // match in the first part
        Assertions.assertTrue(stringMatcher.contains("hello", "ell"));    // match in the middle part
        Assertions.assertTrue(stringMatcher.contains("hello", "llo"));    // match in the last part
        Assertions.assertTrue(stringMatcher.contains("The quick brown fox jumps over the lazy dog.", "fox"));
        Assertions.assertTrue(stringMatcher.contains("The cat in the hat.", "in the"));
    }
}
