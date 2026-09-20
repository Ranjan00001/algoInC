

public class Queue {

    LinkedList2 data;

    public Queue(Object data) {
        this.data = new LinkedList2(data);
    }
    
    
    public void enqueue(Object value) { //- adds value at a position at the tail
        this.data.pushBack(value);
    }

    public LinkedNode dequeue() { //- returns value and removes least recently added element (front)
        return this.data.popFront();
    }

    public int size() {
        return this.data.size;
    }

    public boolean empty() {
        return this.size() == 0;
    }
}
