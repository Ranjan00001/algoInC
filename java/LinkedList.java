import java.lang.IndexOutOfBoundsException;
import java.util.NoSuchElementException;
import java.util.Objects;

public class LinkedList {

    LinkedNode head;
    int size;

    public LinkedList(Object data) {
        this.head = new LinkedNode(data);
        this.size = 1;
    }

    public int size() {
        return this.size;
    }

    public boolean empty() {
        return this.size == 0;
    }

    public Object valueAt(int index) throws IndexOutOfBoundsException {
        int current = 0;
        LinkedNode currentNode = this.head;
        while (currentNode.hasNext() && current < index) {
            currentNode = currentNode.next;
            current++;
        }
        if (current == index) {
            return currentNode.data;
        } else {
            throw new IndexOutOfBoundsException("The asked index doesn't exists in this Linked List");
        }
    }

    public void pushFront(Object value) { // - adds an item to the front of the list
        LinkedNode node = new LinkedNode(value);
        node.next = this.head;
        this.head = node;
        this.size++;
    }

    public LinkedNode popFront() throws NoSuchElementException { // - remove the front item and return its value
        if (this.head != null) {
            LinkedNode node = this.head;
            this.head = this.head.next;
            this.size--;
            return node;
        } else {
            throw new NoSuchElementException("Not enough element");
        }
    }

    public void pushBack(Object value) { // - adds an item at the end
        if (this.head == null) {
            this.head = new LinkedNode(value);
            this.size++;
            return;
        }

        LinkedNode lastNode = this.head;
        while (lastNode.hasNext()) {
            lastNode = lastNode.next;
        }
        lastNode.next = new LinkedNode(value);
        this.size++;
    }

    public LinkedNode popBack() { // - removes end item and returns its value
        if (this.head == null) return null;
        if (this.head.next == null) {
            LinkedNode node = this.head;
            this.head = null;
            this.size--;
            return node;
        }
        LinkedNode secondLastNode = this.head;
        while (secondLastNode.next.hasNext()) {
            secondLastNode = secondLastNode.next;
        }
        LinkedNode node = secondLastNode.next;
        secondLastNode.next = null;
        this.size--;
        return node;
    }

    public LinkedNode front() { //- get the value of the front item
        return this.head;
    }

    public LinkedNode back() { //- get the value of the end item
        if (this.head == null) return null;
        LinkedNode lastNode = this.head;
        while (lastNode.hasNext()) {
            lastNode = lastNode.next;
        }
        return lastNode;
    }

    public void insert(int index, Object value) throws IndexOutOfBoundsException { // - insert value at index, so the current item at that index is pointed to by the new item at the index
        if (index < 0 || index > this.size) throw new IndexOutOfBoundsException();
        if (index == 0) { pushFront(value); return; }

        LinkedNode curr = this.head;
        for (int i = 0; i < index - 1; i++) curr = curr.next;

        LinkedNode node = new LinkedNode(value);
        node.next = curr.next;
        curr.next = node;
        this.size++;
    }

    public void erase(int index) { //- removes node at given index
        if (this.head == null || index < 0 || index >= this.size) return;
        if (index == 0) { popFront(); return; }

        LinkedNode curr = this.head;
        for (int i = 0; i < index - 1; i++) curr = curr.next;

        curr.next = curr.next.next;
        this.size--;

    }
    
    public Object value_n_from_end(int n) { //- returns the value of the node at the nth position from the end of the list
        int indexFromStart = this.size - n;
        return valueAt(indexFromStart);
    }

    public void reverse() { //- reverses the list
        LinkedNode prev = null, curr = this.head, next;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        this.head = prev;

    }

    public void remove_value(Object value) { //- removes the first item in the list with this value
        if (this.head == null) return;
        if (Objects.equals(this.head.data, value)) {
            this.head = this.head.next;
            this.size--;
            return;
        }

        LinkedNode curr = this.head;
        while (curr.hasNext()) {
            if (java.util.Objects.equals(curr.next.data, value)) {
                curr.next = curr.next.next;
                this.size--;
                return;
            }
            curr = curr.next;
        }

    }

    public static void main(String[] args) {
        LinkedList list = new LinkedList("A");

        list.pushFront("B");
        list.pushBack("C");

        System.out.println(list.size());
        System.out.println(list.empty());
        System.out.println(list.front().data);
        System.out.println(list.back().data);
        System.out.println(list.valueAt(0));
        System.out.println(list.value_n_from_end(1));

        list.insert(1, "D");
        list.erase(1);

        list.popFront();
        list.popBack();

        list.remove_value("A");
        list.reverse();
    }

}