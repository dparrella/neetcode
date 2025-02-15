package io.neetcode.singlylinkedlist;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Key takeaways:
 * - Use a dummy node at the head and always keep it at the head.
*  - This makes traversal, inserting, and removing much easier.
 */
class LinkedList {

    private LinkedListNode head;
    private LinkedListNode tail;

    public LinkedList() {
        this.head = new LinkedListNode(-1);
        this.tail = this.head;
    }

    public int get(int index) {
        LinkedListNode current = this.head.next;
        int i = 0;
        while (current != null) {
            if (i == index) {
                return current.value;
            }

            i++;
            current = current.next;
        }

        return -1;
    }

    public void insertHead(int val) {
        // Keep the dummy head node in place at the beginning of the list.
        final LinkedListNode newNode = new LinkedListNode(val);
        newNode.next = this.head.next;
        this.head.next = newNode;

        // Handle case where list was empty before inserting (head wasn't pointing to anything).
        if (Objects.isNull(newNode.next)) {
            this.tail = newNode;
        }
    }

    public void insertTail(int val) {
        // No need to handle case where list was empty.
        this.tail.next = new LinkedListNode(val, null);
        this.tail = this.tail.next;
    }

    public boolean remove(int index) {
        // Find the node right before the target node.
        LinkedListNode current = this.head;
        int i = 0;
        while (i < index && Objects.nonNull(current)) {
            i++;
            current = current.next;
        }

        if (Objects.nonNull(current) && Objects.nonNull(current.next)) {
            // Take care of removing the tail if next item is the tail.
            if (current.next == this.tail) {
                this.tail = current;
            }

            // Set pointer to next node to item following the index we wanted to remove.
            current.next = current.next.next;

            return true;
        }

        return false;
    }

    public List<Integer> getValues() {
        final List<Integer> values = new ArrayList<>();

        // Skip the dummy node at the head.
        LinkedListNode currentNode = this.head.next;
        while (currentNode != null) {
            values.add(currentNode.value);
            currentNode = currentNode.next;
        }

        return values;
    }

    public String toString() {
        return String.format("[head = %s, tail = %s]", this.head, this.tail);
    }

    class LinkedListNode {

        private int value;
        private LinkedListNode next;

        LinkedListNode(int value) {
            this(value, null);
        }

        LinkedListNode(int value, LinkedListNode next) {
            this.value = value;
            this.next = next;
        }

        public boolean isDummy() {
            return this.value == -1;
        }

        public String toString() {
            return String.format("[value = %d, next = %s]", this.value, this.next);
        }
    }
}

