package language.ds;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * DOMAIN SCENARIO 1: Order State Reversal System
 * 
 * Business Requirement:
 * In the order processing pipeline, actions taken on an order (e.g. "STATUS_CHANGED_TO_PROCESSING",
 * "PAYMENT_CAPTURED", "SHIPPING_LABEL_GENERATED") may need to be reverted sequentially if an error occurs.
 * 
 * Operational Constraint:
 * Reversal MUST occur in Last-In, First-Out order (the most recent action executed must be the first action reverted).
 * 
 * TODO: Task
 * 1. Choose and instantiate the optimal standard Java collection/structure to back this feature.
 * 2. Implement recordAction, undoLastAction, and peekLastAction.
 */
public class OrderActionTracker {

    // TODO: Choose and initialize your chosen Java collection field here.
    private final Deque<String> actionHistory = new ArrayDeque<>();

    /**
     * Record a new action executed on the order.
     */
    public void recordAction(String actionDescription) {
        // TODO: Store action description for potential undo operations
        actionHistory.push(actionDescription);
    }

    /**
     * Revert and return the most recently executed action.
     * Return null if no actions exist to undo.
     */
    public String undoLastAction() {
        // TODO: Retrieve and remove the last executed action
        return actionHistory.isEmpty() ? null : actionHistory.pop();
    }

    /**
     * Inspect the most recent action without removing it.
     */
    public String peekLastAction() {
        // TODO: Inspect top element
        return actionHistory.peek();
    }

    public int getRecordedActionCount() {
        return actionHistory.size();
    }
}
