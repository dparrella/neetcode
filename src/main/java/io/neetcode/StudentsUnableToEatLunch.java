package io.neetcode;

// https://leetcode.com/problems/number-of-students-unable-to-eat-lunch/
public class StudentsUnableToEatLunch {

    public static int countStudents(int[] students, int[] sandwiches) {
        int studentsToFeed = students.length;

        // Array acts like a map with two keys (0 and 1).
        final int[] preferenceCounts = new int[2];
        for (int s : students) {
            preferenceCounts[s]++;
        }

        for (int s : sandwiches) {
            if (preferenceCounts[s] > 0) {
                studentsToFeed--;
                preferenceCounts[s]--;
            } else {
                return studentsToFeed;
            }
        }

        return studentsToFeed;
    }
}
