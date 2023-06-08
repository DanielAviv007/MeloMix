package com.hit.alg;

public class SlidingWindow implements StringMatcher {
    @Override
    public boolean contains(String text, String pattern) {
        // Check if either input is null or if the length of the text is less than the length of the pattern
        if (text == null || pattern == null || text.length() < pattern.length())
            return false;

        // Store the length of the input strings
        int textLength = text.length();
        int patternLength = pattern.length();

        // Loop through the text string with a sliding window of size patternLength
        for (int i = 0; i <= textLength - patternLength; ++i) {
            // Assume there is a match between the text and pattern strings
            boolean matched = true;

            // Loop through the pattern string to compare each character with the corresponding character in the text string
            for (int j = 0; j < patternLength; ++j) {
                if (text.charAt(i + j) != pattern.charAt(j)) {
                    // If there is a mismatch, set matched to false and break out of the loop
                    matched = false;
                    break;
                }
            }

            // If all characters in the pattern match the corresponding characters in the text, return true
            if (matched)
                return true;
        }

        // If no match is found, return false
        return false;
    }
}
