package codeFlowEngine;

import java.util.NoSuchElementException;

public class ExecutionHistory {

    LinkedList history = new LinkedList();

    class Node {
        Object element = 0;
        Node next = null;

        Node(Object element, Node next) {
            this.element = element;
            this.next = next;
        }

        boolean hasNext() {
            return next != null;
        }

    }

    class LinkedList {
        Node head = null;
        Node tail = null;
        int size = 0;

        void addLast(Object element) {
            Node node = new Node(element, null);
            if (size == 0) {
                this.head = node;
                this.tail = node;
                this.size++;
            } else {
                this.tail.next = node;
                this.tail = this.tail.next;
                this.size++;
            }
        }

        void addFront(Object element) {
            Node node = new Node(element, null);
            if (size == 0) {
                this.head = node;
                this.tail = node;
                this.size++;
            } else {
                node.next = this.head;
                this.head = node;
                this.size++;
            }
        }

        Node removeFront() {
            if (size == 0) {
                throw new NoSuchElementException("Not enough element to remove from!");
            } else {
                Node node = this.head;
                this.head = this.head.next;
                this.size--;
                return node;
            }
        }

        void reverse() {
            if (size == 0) {
                System.out.println("Zero size linkedlist");
                return;
            }

            Node prev = null, current = this.head, next;
            while (current.hasNext()) {
                next = current.next;
                current.next = prev;
                prev = current;
                current = next;
            }
        }

        Object getIthFromStart(int i) {
            int j = 0; Node current = this.head;
            while (j <= i && current.hasNext()) {
                j++;
                current = current.next;
            }
            return current.element;
        }
    }

    void append(Object command) { //— Appends a command to the history in $O(1)$ using a tail pointer.
        history.addFront(command);
    }

    void undoLast() { //— Removes the most recent command in $O(1)$ or $O(N)$.
        history.removeFront();
    }
    void reverseHistory() { //— Reverses the entire execution history in-place (rewiring next pointers without allocating new nodes).
        history.reverse();
    }
    Object findKthFromEnd(int k) { //— Returns the $k$-th command from the end in a single pass using Fast & Slow Pointers.
        int indexFromStart = history.size - k;
        return history.getIthFromStart(indexFromStart);
    }
}
