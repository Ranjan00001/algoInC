package codeFlowEngine;

public class CycleDetector extends ExecutionHistory { // can we have a more natural way to use Node from ExecutionHistory?
    Node isCyclic(Node headNode) {
        Node slow = headNode, fast = headNode;
        while (fast != null && fast.hasNext()) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                slow = headNode;
                break;
            }
        }
        while (fast != null && fast.hasNext()) {
            if (fast = slow) {
                return slow;
            }
            fast = fast.next;
            slow = slow.next;
        }

        return null;
    }
}
