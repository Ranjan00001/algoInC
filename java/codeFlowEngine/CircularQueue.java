package codeFlowEngine;

public class CircularQueue extends ExecutionHistory {

    Node head = null;
    Node tail = null;
    int size = 0;
    
    void enqueue(Object task) {
        if (size == 0) {
            head = new Node(task, null);
            tail = head;
        } else {
            tail.next = new Node(task, null);
            tail = tail.next;
        }
        size++;
    }

    Object dequeue() {
        Node node;
        if (size == 0) {
            throw new IndexOutOfBoundsException("Not enough element remainning");
        } else if (size == 1) {
            node = head;
            head = null;
            tail = null;
        } else {
            node = head;
            head = head.next;
        }
        size--;
        return node;
    }

    Object front() {
        return head;
    }

    boolean isFull() {
        return head == tail && size != 0;
    }

    boolean isEmpty() {
        return size == 0;
    }


}
