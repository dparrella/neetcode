package io.neetcode.singlylinkedlist;

import io.neetcode.test.NeetcodeTestHarness;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class LinkedListTest {

    @Test
    public void scenarioOne() {
        final LinkedList list = new LinkedList();
        list.insertHead(1);
        list.insertTail(2);
        list.insertHead(0);

        // [0, 1, 2] -> [0, 2]
        assertThat(list.remove(1), is(true));
        assertThat(list.getValues(), contains(0, 2));
    }

    @Test
    public void scenarioFive() {
        final LinkedList list = new LinkedList();
        list.insertHead(1);

        // In both cases, the index is invalid. Why is it failing for 1 and not 2?
        assertThat(list.remove(2), is(false));
        assertThat(list.remove(1), is(false));
    }

    @Test
    public void insertHeadAndRemove() {
        final LinkedList list = new LinkedList();
        list.insertHead(0);

        assertThat(list.remove(0), is(true));
        assertThat(list.getValues(), is(empty()));
    }

    // ["insertTail", 1, "insertTail", 2, "get", 1, "remove", 1, "insertTail", 2, "get", 1, "get", 0]
    // Expected: [null,null,2,true,null,2,1]
    @Test
    public void insertTailAndRemoveScenarios() {
        final LinkedList list = new LinkedList();
        list.insertTail(1);
        list.insertTail(2);

        assertThat(list.get(1), is(2));

        assertThat(list.remove(1), is(true));

        list.insertTail(2);
        assertThat(list.get(1), is(2));
        assertThat(list.get(0), is(1));
    }

    @Test
    public void testWithHarness() throws IOException {
        final String testScriptJson = "[\"insertTail\", 1, \"insertTail\", 2, \"get\", 1, \"remove\", 1, \"insertTail\", 2, \"get\", 1, \"get\", 0]";
        final String expectationsJson = "[null,null,2,true,null,2,1]";

        final LinkedList list = new LinkedList();
        NeetcodeTestHarness.getInstance().runTest(list, testScriptJson, expectationsJson);
    }

    @DataProvider
    Object[][] linkedListProvider() {
        final LinkedList emptyList = new LinkedList();

        final LinkedList listWithItemsAddedAtHeadAndTail = new LinkedList();
        listWithItemsAddedAtHeadAndTail.insertHead(0);
        listWithItemsAddedAtHeadAndTail.insertTail(1);

        final LinkedList listWithItemAddedAtHead = new LinkedList();
        listWithItemAddedAtHead.insertHead(6);

        final LinkedList listWithItemAddedAtTail = new LinkedList();
        listWithItemAddedAtTail.insertTail(10);

        return new Object[][] {
                {
                        emptyList,
                        "[head = [value = -1, next = null], tail = [value = -1, next = null]]",
                },
                {
                        listWithItemsAddedAtHeadAndTail,
                        "[head = [value = -1, next = [value = 0, next = [value = 1, next = null]]], tail = [value = 1, next = null]]",
                },
                {
                        listWithItemAddedAtHead,
                        "[head = [value = -1, next = [value = 6, next = null]], tail = [value = 6, next = null]]",
                },
                {
                        listWithItemAddedAtTail,
                        "[head = [value = -1, next = [value = 10, next = null]], tail = [value = 10, next = null]]"
                }
        };
    }

    @Test(dataProvider = "linkedListProvider")
    public void toStringReturnsExpectedValue(final LinkedList linkedList, final String expectedOutput) {
        assertThat(linkedList.toString(), is(expectedOutput));
    }
}