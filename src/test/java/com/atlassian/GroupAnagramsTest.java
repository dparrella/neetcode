package com.atlassian;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class GroupAnagramsTest {

    @DataProvider
    Object[][] anagramsProvider() {
        return new Object[][] {
                // Input contains two anagrams.
                {
                        new String[] { "act", "god", "cat", "dog", "tac" },
                        List.of(List.of("act", "cat", "tac"), List.of("god", "dog")),
                },

                // Input contains no anagrams.
                {
                        new String[] { "plane", "seat", "ticket" },
                        List.of(List.of("plane"), List.of("seat"), List.of("ticket")),
                },

                // Input contains one anagram.
                {
                        new String[] { "act", "cat" },
                        List.of(List.of("act", "cat")),
                },
        };
    }

    @Test(dataProvider = "anagramsProvider")
    public void anagrams(final String[] input, final List<List<String>> expectedResult) {
        assertThat(GroupAnagrams.anagrams(input), is(expectedResult));
    }
}