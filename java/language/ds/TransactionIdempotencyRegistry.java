package language.ds;

import language.model.Order;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * DOMAIN SCENARIO 4: Transaction Idempotency Guard & Unique Aggregator
 * 
 * Business Requirement:
 * Payment gateways frequently re-try webhooks under network latency.
 * System must prevent double-processing by checking if a transaction ID has already been seen in $O(1)$ time.
 * Additionally, system must extract unique customer names from a bulk batch of orders.
 * 
 * Operational Constraint:
 * Lookup, addition, and uniqueness enforcement MUST operate in $O(1)$ expected time complexity.
 * 
 * TODO: Task
 * 1. Choose and instantiate the optimal set-based hashing Java structure.
 * 2. Implement registerTransaction, isDuplicate, and extractUniqueCustomers.
 */
public class TransactionIdempotencyRegistry {

    // TODO: Choose and initialize your chosen Java collection field here.
    private final Set<String> processedTransactionIds = new HashSet<>();

    /**
     * Check if a transaction ID has already been recorded.
     */
    public boolean isDuplicate(String transactionId) {
        // TODO: Perform O(1) membership check
        return processedTransactionIds.contains(transactionId);
    }

    /**
     * Attempt to register a transaction ID.
     * Returns true if newly registered, or false if it was already processed.
     */
    public boolean registerTransaction(String transactionId) {
        // TODO: Add to set and return true if new
        return processedTransactionIds.add(transactionId);
    }

    /**
     * Given a list of orders, extract and return a unique set of customer names.
     */
    public Set<String> extractUniqueCustomers(List<Order> orders) {
        // TODO: Collect unique customer names from orders
        Set<String> uniqueCustomers = new HashSet<>();
        for (Order order : orders) {
            if (order.getCustomerName() != null) {
                uniqueCustomers.add(order.getCustomerName());
            }
        }
        return uniqueCustomers;
    }

    public int getProcessedCount() {
        return processedTransactionIds.size();
    }
}
