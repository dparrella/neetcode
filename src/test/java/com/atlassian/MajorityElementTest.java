package com.atlassian;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class MajorityElementTest {

    @DataProvider
    Object[][] numProvider() {
        return new Object[][] {
                { new int[] { 2, 1, 3 }, -1 },
                { new int[] { 2, 13 }, -1 },
                { new int[] { 7 }, 7 },
                { new int[] { 3, 1, 3, 3, 2 }, 3 }
        };
    }

    @Test(dataProvider = "numProvider")
    public void majorityElement(final int[] arr, final int expectedResult) {
        assertThat(MajorityElement.majorityElement(arr), is(expectedResult));
    }
}