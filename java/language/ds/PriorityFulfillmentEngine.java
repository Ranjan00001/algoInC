package language.ds;

import language.model.Order;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * DOMAIN SCENARIO 5: Priority Fulfillment Engine
 * 
 * Business Requirement:
 * High-value orders or VIP orders cannot wait in standard FIFO queues.
 * The system must process orders based on dynamic priority rules (e.g. higher order amount = higher fulfillment priority).
 * 
 * Operational Constraint:
 * Elements must be dynamically ordered such that calling poll/extract ALWAYS yields the element with highest priority.
 * 
 * TODO: Task
 * 1. Choose and instantiate the optimal Heap-backed Java collection.
 * 2. Define a custom Comparator ordering orders by base amount descending.
 * 3. Implement enqueuePriorityOrder and pollHighestPriorityOrder.
 */
public class PriorityFulfillmentEngine {

    // Priority Record helper wrapper
    public record PrioritizedOrder(Order order, double priorityScore) {}

    // TODO: Choose and initialize your chosen Heap collection with custom Comparator
    private final PriorityQueue<PrioritizedOrder> priorityQueue = new PriorityQueue<>(
            Comparator.comparingDouble(PrioritizedOrder::priorityScore).reversed()
    );

    /**
     * Enqueue order with explicit priority score.
     */
    public void enqueuePriorityOrder(Order order, double priorityScore) {
        // TODO: Insert into priority structure
        priorityQueue.offer(new PrioritizedOrder(order, priorityScore));
    }

    /**
     * Extract the order with the highest priority score currently in the engine.
     * Returns null if engine is empty.
     */
    public Order pollHighestPriorityOrder() {
        // TODO: Poll highest priority order
        PrioritizedOrder item = priorityQueue.poll();
        return item != null ? item.order() : null;
    }

    public int getEngineQueueSize() {
        return priorityQueue.size();
    }
}
