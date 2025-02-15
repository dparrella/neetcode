package io.neetcode.singlylinkedlist;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class LinkedList {

    private LinkedListNode head;
    private LinkedListNode tail;

    public LinkedList() {
        this.head = new LinkedListNode(-1);
        this.tail = this.head;
    }

    public int get(int index) {
        LinkedListNode current = this.head;
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
        logMsg("Inserting " + val + " at head.");
        if (this.head.value == - 1) {
            logMsg("Head was empty. List now = " + this);
            this.head.value = val;
            return;
        }

        final LinkedListNode oldHead = this.head;
        final LinkedListNode newHead = new LinkedListNode(val, oldHead);

        this.head = newHead;

        logMsg("Post insertHead(): " + this);
    }

    public void insertTail(int val) {
        logMsg("Inserting " + val + " at tail.");
        if (this.tail.value == -1) {
            this.tail.value = val;
            logMsg("Tail was empty. List is now: " + this);
            return;
        }

        final LinkedListNode newTail = new LinkedListNode(val, null);
        this.tail.next = newTail;
        this.tail = this.tail.next;

        logMsg("Post insertTail(): " + this);
    }

    public boolean remove(int index) {
        logMsg("Attempting to remove item at index: " + index);
        logMsg("Before removing: " + this);

        LinkedListNode lastNode = this.head;
        int i = 0;
        while (i < (index - 1) && lastNode != null) {
            i++;
            lastNode = lastNode.next;
        }

        logMsg("Ended loop with node at index " + (i - 1) + " = " + lastNode);

        if (Objects.nonNull(lastNode) && Objects.nonNull(lastNode.next)) {
            // This is a dummy Node.
            if (lastNode.value == -1) {
                return false;
            }

            // Take care of removing the tail if next item is the tail.
            if (lastNode.next == this.tail) {
                this.tail = lastNode;
            }

            // Set pointer to next node to item following the index we wanted to remove.
            lastNode.next = lastNode.next.next;

            logMsg("After removing: " + this);
            return true;
        }

        logMsg("Couldn't remove item at index " + index);
        return false;
    }

    public List<Integer> getValues() {
        final List<Integer> values = new ArrayList<>();

        LinkedListNode currentNode = this.head;
        while (currentNode != null) {
            if (currentNode.value > -1) {
                values.add(currentNode.value);
            }

            currentNode = currentNode.next;
        }

        return values;
    }

    public String toString() {
        return String.format("[head = %s, tail = %s]", this.head, this.tail);
    }

    private static void logMsg(Object msg) {
        System.out.println(msg);
    }

    class LinkedListNode {

        private int value;
        private LinkedListNode next;

        LinkedListNode(int value) {
            this(-1, null);
        }

        LinkedListNode(int value, LinkedListNode next) {
            this.value = value;
            this.next = next;
        }

        public String toString() {
            return String.format("[value = %d, next = %s]", this.value, this.next);
        }
    }
}

