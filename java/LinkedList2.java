import java.util.NoSuchElementException;

public class LinkedList2 extends LinkedList {

    LinkedNode tail;

    public LinkedList2(Object data) {
        super(data);
        this.tail = this.head;
    }

    public void pushFront(Object value) { // - adds an item to the front of the list
        super.pushFront(value);
        if (this.size == 1) {
            this.tail = this.head;
        }
    }

    public LinkedNode popFront() throws NoSuchElementException { // - remove the front item and return its value
        LinkedNode node = super.popFront();
        if (this.head == null) {
            this.tail = null;
        }
        return node;
    }

    public void pushBack(Object value) { // - adds an item at the end
        if (this.head == null) {
            this.head = new LinkedNode(value);
            this.tail = this.head;
            this.size++;
            return;
        }

        this.tail.next = new LinkedNode(value);
        this.tail = this.tail.next;
        this.size++;
    }

    public LinkedNode popBack() { // - removes end item and returns its value
        if (this.head == null) return null;
        if (this.head.next == null) {
            LinkedNode node = this.head;
            this.head = null;
            this.tail = null;
            this.size--;
            return node;
        }

        LinkedNode current = this.head;
        while (current.next != this.tail) {
            current = current.next;
        }
        LinkedNode node = current.next;
        this.tail = current;
        this.size--;
        current.next = null;
        return node;
    }

    public void reverse() {
        LinkedNode prev = null, current = this.head, next;
        this.tail = this.head;
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        this.head = prev;
    }

    public void erase(int index) {
        super.erase(index);
        if (this.head == null) {
            this.tail = null;
        } else if (index == this.size) { // The old tail was erased
            this.tail = super.back();   // Recalculate the new tail
        }
    }

    public void remove_value(Object value) {
        super.remove_value(value);
        if (this.head == null) {
            this.tail = null;
        } else {
            this.tail = super.back(); // Keeps tail in sync if last element was removed
        }
    }

    public LinkedNode back() { //- get the value of the end item
        return this.tail;
    }
    
    public Object value_n_from_end(int n) { //- returns the value of the node at the nth position from the end of the list
        int indexFromStart = this.size - n;
        return valueAt(indexFromStart);
    }

    public static void main(String[] args) {
        LinkedList list = new LinkedList2("A");

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