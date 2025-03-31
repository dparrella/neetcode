package com.atlassian;

import java.util.*;

/**
 * <a href="https://www.geeksforgeeks.org/problems/print-anagrams-together">Problem</a>
 */
public class GroupAnagrams {

    public static ArrayList<ArrayList<String>> anagrams(final String[] arr) {
        final Map<ArrayList<Character>, ArrayList<String>> anagramsMap = new LinkedHashMap<>();
        for (String word : arr) {
            final ArrayList<Character> sortedChars = toSortedList(word);
            if (!anagramsMap.containsKey(sortedChars)) {
                final ArrayList<String> newList = new ArrayList<>();
                newList.add(word);
                anagramsMap.put(sortedChars, newList);
            } else {
                final ArrayList<String> anagramList = anagramsMap.get(sortedChars);
                anagramList.add(word);
            }
        }

        return new ArrayList<>(anagramsMap.values());
    }

    private static ArrayList<Character> toSortedList(final String input) {
        final ArrayList<Character> characters = new ArrayList<>(input.length());
        for (char c : input.toCharArray()) {
            characters.add(Character.valueOf(c));
        }

        Collections.sort(characters);
        return characters;
    }
}
