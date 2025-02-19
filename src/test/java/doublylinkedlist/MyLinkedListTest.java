package doublylinkedlist;

import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

public class MyLinkedListTest {

    @Test
    public void failingScenario() {
        final MyLinkedList list = new MyLinkedList();
        list.addAtHead(1);
        list.addAtTail(3);
        list.addAtIndex(1, 2);

        assertThat(list.get(1), is(2));
        list.deleteAtIndex(1);
        assertThat(list.get(1), is(3));
    }

    @Test
    public void anotherFailingScenario() {
        final MyLinkedList list = new MyLinkedList();
        list.addAtHead(1);
        list.addAtTail(3);
        list.addAtIndex(1, 2);

        assertThat(list.get(1), is(2));
        list.deleteAtIndex(0);
        assertThat(list.get(0), is(2));
    }

    @Test
    public void deleteFromEmptyList() {
        final MyLinkedList list = new MyLinkedList();
        list.deleteAtIndex(0);
    }
}