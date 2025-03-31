package com.atlassian;

import java.util.ArrayList;
import java.util.Stack;

/**
 * <a href="https://www.geeksforgeeks.org/problems/stock-span-problem-1587115621/1">Problem</a>
 */
public class StockSpan {

    /**
     * Naive solution. O(n^2) time complexity.
     *
     * @param arr Stock price values.
     * @return List of spans for each stock price value, in order.
     */
    static ArrayList<Integer> calculateSpanNaive(final int[] arr) {
        final ArrayList<Integer> result = new ArrayList<>();

        // Iterate over stock prices in order.
        for (int i = 0; i < arr.length; i++) {
            int value = arr[i];

            // Start from 0 since we will include the current day's value in our loop.
            int count = 0;

            // Go backwards from i to find consecutive days where day's value <= value
            for (int j = i; j >= 0; j--) {
                if (arr[j] <= value) {
                    count++;
                } else {
                    // No need to continue. Span is broken.
                    break;
                }
            }

            result.add(count);
        }

        return result;
    }

    /**
     * Optimized solution. O(n) time complexity.
     *
     * @param arr Stock price values.
     * @return List of spans for each stock price value, in order.
     */
    static ArrayList<Integer> calculateSpanWithStack(final int[] arr) {
        final ArrayList<Integer> result = new ArrayList<>(arr.length);
        final Stack<Integer> spanStack = new Stack<>();

        // Iterate over stock prices in order.
        for (int i = 0; i < arr.length; i++) {
            int value = arr[i];

            // Remove elements from the stack while the current price is >= top price on stack.
            while (!spanStack.isEmpty() && arr[spanStack.peek()] <= value) {
                spanStack.pop();
            }

            // When stack is empty, all previous days values are less than the day's value.
            if (spanStack.isEmpty()) {
                result.add(i + 1);
            } else {
                // Top of the stack is the last greater element's index.
                result.add(i - spanStack.peek());
            }

            // Push current index to the stack.
            spanStack.push(i);
        }

        return result;
    }
}
