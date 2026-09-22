package codeFlowEngine;

public class CycleDetector extends ExecutionHistory { // can we have a more natural way to use Node from ExecutionHistory?
    boolean isCyclic(Node headNode) {
        Node slow = headNode, fast = headNode;
        while (fast.hasNext()) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    boolean getCycleStart(Node headNode) {
        Node slow = headNode, fast = headNode;
        while (fast.hasNext()) {
            // Need hint for this one
        }

        return false;
    }
}
