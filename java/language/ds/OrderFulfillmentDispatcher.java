package language.ds;

import language.model.Order;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * DOMAIN SCENARIO 2: High-Volume Order Fulfillment Buffer
 * 
 * Business Requirement:
 * Inbound customer orders arrive dynamically. The payment gateway can only process one order at a time,
 * so incoming orders must wait in a fair dispatch staging area.
 * 
 * Operational Constraint:
 * Orders MUST be processed in strict First-In, First-Out order (oldest arrived order is dispatched first).
 * 
 * TODO: Task
 * 1. Choose and instantiate the optimal Java collection/structure for FIFO processing.
 * 2. Implement enqueueOrder, processNextOrder, and getPendingCount.
 */
public class OrderFulfillmentDispatcher {

    // TODO: Choose and initialize your chosen Java collection field here.
    private final Queue<Order> dispatchBuffer = new ArrayDeque<>();

    /**
     * Add an incoming customer order to the dispatch buffer.
     */
    public void enqueueOrder(Order order) {
        // TODO: Enqueue incoming order
        dispatchBuffer.offer(order);
    }

    /**
     * Retrieve and remove the next order to be dispatched.
     * Returns null if no orders are pending in the buffer.
     */
    public Order processNextOrder() {
        // TODO: Poll the oldest order from buffer
        return dispatchBuffer.poll();
    }

    /**
     * Get count of pending orders currently waiting in dispatch buffer.
     */
    public int getPendingCount() {
        return dispatchBuffer.size();
    }
}
