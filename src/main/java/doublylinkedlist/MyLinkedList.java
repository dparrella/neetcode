package doublylinkedlist;

import java.util.ArrayList;
import java.util.List;

class ListNode {

    int val;
    ListNode prev;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }
}

class MyLinkedList {

    private final ListNode left;
    private final ListNode right;

    public MyLinkedList() {
        this.left = new ListNode();
        this.right = new ListNode();

        this.left.next = this.right;
        this.right.prev = this.left;
    }

    public int get(int index) {
        ListNode current = this.left.next;

        int currentIndex = 0;
        while (current != null && currentIndex < index) {
            System.out.println("index = " + currentIndex);
            current = current.next;
            currentIndex++;
        }

        if (current != null && currentIndex == index && current != this.right) {
            return current.val;
        }

        return -1;
    }

    public void addAtHead(int val) {
        ListNode newNode = new ListNode(val);
        ListNode prev = this.left;
        ListNode next = this.left.next;

        // [prev, newNode, newNode.next]
        prev.next = newNode;
        next.prev = newNode;
        newNode.prev = prev;
        newNode.next = next;
    }

    public void addAtTail(int val) {
        ListNode newNode = new ListNode(val);
        ListNode prev = this.right.prev;
        ListNode next = this.right;

        // [prev, newNode, newNode.next]
        prev.next = newNode;
        next.prev = newNode;
        newNode.prev = prev;
        newNode.next = next;
    }

    public void addAtIndex(int index, int val) {
        ListNode current = this.left.next;

        while (current != null && index > 0) {
            current = current.next;
            index--;
        }

        if (current != null && index == 0) {
            ListNode newNode = new ListNode(val);
            ListNode prev = current.prev;
            ListNode next = current;

            // [prev, newNode, newNode.next]
            prev.next = newNode;
            next.prev = newNode;
            newNode.prev = prev;
            newNode.next = next;
        }
    }

    public void deleteAtIndex(int index) {
        ListNode current = this.left.next;

        while (current != null && index > 0) {
            current = current.next;
            index--;
        }

        if (current != null && current != this.right && index == 0) {
            ListNode prev = current.prev;
            ListNode next = current.next;

            prev.next = next;
            next.prev = prev;
        }
    }

    public String toString() {
        ListNode current = this.left.next;

        List<Integer> contents = new ArrayList<>();
        while (current != null && current != this.right) {
            contents.add(current.val);

            current = current.next;
        }

        return "" + contents;
    }
}
