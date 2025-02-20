package io.neetcode;

import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

public class StudentsUnableToEatLunchTest {

    @Test
    public void unableToFeedAllStudents() {
        final int[] students = new int[] { 1, 1, 1, 0, 0, 1 };
        final int[] sandwiches = new int[] { 1, 0, 0, 0, 1, 1 };

        final int result = StudentsUnableToEatLunch.countStudents(students, sandwiches);
        assertThat(result, is(3));
    }

    @Test
    public void allStudentsCanBeFed() {
        final int[] students = new int[] { 1, 1, 0, 0 };
        final int[] sandwiches = new int[] { 0, 1, 0, 1 };

        final int result = StudentsUnableToEatLunch.countStudents(students, sandwiches);
        assertThat(result, is(0));
    }
}