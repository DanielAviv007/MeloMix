package com.hit.alg;

import java.util.List;
import java.util.ArrayList;

public class KMP implements StringMatcher {
    @Override
    public boolean contains(String text, String pattern) {
        // Check if either input is null or if the length of the text is less than the length of the pattern
        if (text == null || pattern == null || text.length() < pattern.length())
            return false;

        // Store the length of the input strings
        int textLength = text.length();
        int patternLength = pattern.length();

        // Compute the prefix table for the pattern
        List<Integer> prefixTable = computePrefixTable(pattern);

        // Use the prefix table to match the pattern within the text
        for (int i = 0, j = 0; i < textLength; ++i) {
            // Move j back to the previous prefix if a mismatch occurs
            while (j > 0 && text.charAt(i) != pattern.charAt(j))
                j = prefixTable.get(j - 1);

            // Increment j if a match occurs
            if (text.charAt(i) == pattern.charAt(j))
                ++j;

            // If j is equal to the length of the pattern, the pattern has been found in the text
            if (j == patternLength)
                return true;
        }

        // The pattern was not found in the text
        return false;
    }

    private List<Integer> computePrefixTable(String pattern) {
        // Initialize the prefix table
        List<Integer> prefixTable = new ArrayList();
        prefixTable.add(0);

        int patternLength = pattern.length();

        // Compute the prefix for each index in the pattern
        for (int i = 1, j = 0; i < patternLength; ++i) {
            // Move j back to the previous prefix if a mismatch occurs
            while (j > 0 && pattern.charAt(j) != pattern.charAt(i))
                j = prefixTable.get(j - 1);

            // Increment j if a match occurs
            if (pattern.charAt(j) == pattern.charAt(i))
                ++j;

            // Store the value of j as the prefix for the current index i
            prefixTable.add(j);
        }

        // Return the prefix table
        return prefixTable;
    }
}
