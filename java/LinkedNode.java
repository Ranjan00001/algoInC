public class LinkedNode {
    public Object data;
    public LinkedNode next;

    public LinkedNode(Object data) {
        this.data = data;
        this.next = null;
    }

    public boolean hasNext() {
        return this.next != null;
    }
}
