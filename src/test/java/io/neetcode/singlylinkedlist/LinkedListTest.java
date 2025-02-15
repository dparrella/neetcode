package io.neetcode.singlylinkedlist;

import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class LinkedListTest {

    @Test
    public void scenarioOne() {
        final LinkedList list = new LinkedList();
        list.insertHead(1);
        list.insertTail(2);
        list.insertHead(0);

        assertThat(list.remove(1), is(true));
        assertThat(list.getValues(), contains(0, 2));
    }

    @Test
    public void scenarioFive() {
        final LinkedList list = new LinkedList();
        list.insertHead(1);

        assertThat(list.remove(2), is(false));
        assertThat(list.remove(1), is(false));
    }
}