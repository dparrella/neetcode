package com.atlassian;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class StockSpanTest {

    @DataProvider
    Object[][] spanProvider() {
        return new Object[][] {
                {
                        new int[] { 100, 80, 60, 70, 60, 75, 85 },
                        List.of(1, 1, 1, 2, 1, 4, 6),
                },

                {
                        new int[] { 10, 4, 5, 90, 120, 80 },
                        List.of(1, 1, 2, 4, 5, 1),
                },
        };
    }

    @Test(dataProvider = "spanProvider")
    public void calculateSpanNaive(final int[] input, final List<Integer> expectedResult) {
        assertThat(StockSpan.calculateSpanNaive(input), is(expectedResult));
    }
}