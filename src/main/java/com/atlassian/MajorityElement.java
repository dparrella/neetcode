package com.atlassian;

import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://www.geeksforgeeks.org/problems/majority-element-1587115620">Problem</a>
 */
public class MajorityElement {

    static int majorityElement(int arr[]) {
        final int threshold = arr.length / 2;
        final Map<Integer, Integer> counts = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            final Integer current = Integer.valueOf(arr[i]);
            if (counts.containsKey(current)) {
                counts.put(current, counts.get(current) + 1);
            } else {
                counts.put(current, 1);
            }

            if (counts.get(current) > threshold) {
                return current;
            }
        }

        return -1;
    }

}
