package language.functional;

import language.model.Order;
import language.model.OrderStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.*;

/**
 * PRACTICE TOPIC: Functional Interfaces & Anonymous Inner Classes
 * 
 * Target Skills:
 * 1. Standard Built-in Functional Interfaces:
 *    - Predicate<T>
 *    - Consumer<T>
 *    - Supplier<T>
 *    - Function<T, R>
 *    - UnaryOperator<T>
 *    - BinaryOperator<T>
 * 2. Anonymous Inner Classes (legacy inline interface implementation).
 * 3. Lambda Expressions & Method References.
 */
public class FunctionalPracticeSuite {

    // =========================================================================
    // TASK 1: PREDICATE<T> PRACTICE
    // =========================================================================
    /**
     * TODO: Practice Task 1 - Predicate<Order>
     * Requirements:
     * - Create a Predicate<Order> named 'isHighValueOrder' that returns true if order.getBaseAmount() > 1000.0.
     * - Refactor/test both Lambda syntax and Predicate.and() composition.
     */
    public Predicate<Order> createHighValueOrderPredicate() {
        // TODO: Implement Predicate<Order> using Lambda expression
        return order -> order.getBaseAmount() > 1000.0;
    }

    // =========================================================================
    // TASK 2: CONSUMER<T> PRACTICE
    // =========================================================================
    /**
     * TODO: Practice Task 2 - Consumer<String>
     * Requirements:
     * - Create a Consumer<String> named 'auditLogger' that prints "[AUDIT SINK]: " + message.
     */
    public Consumer<String> createAuditLoggerConsumer() {
        // TODO: Implement Consumer<String>
        return message -> System.out.println("[AUDIT SINK]: " + message);
    }

    // =========================================================================
    // TASK 3: SUPPLIER<T> PRACTICE
    // =========================================================================
    /**
     * TODO: Practice Task 3 - Supplier<String>
     * Requirements:
     * - Create a Supplier<String> that generates a random transaction ID prefixing "TXN-" + UUID.randomUUID().
     */
    public Supplier<String> createTransactionIdSupplier() {
        // TODO: Implement Supplier<String>
        return () -> "TXN-" + UUID.randomUUID().toString().substring(0, 8);
    }

    // =========================================================================
    // TASK 4: FUNCTION<T, R> PRACTICE
    // =========================================================================
    /**
     * TODO: Practice Task 4 - Function<Order, String>
     * Requirements:
     * - Create a Function<Order, String> that transforms an Order into a summary string:
     *   "SUMMARY: Order #" + order.getOrderId() + " for " + order.getCustomerName() + " (Amount: $" + order.getBaseAmount() + ")"
     */
    public Function<Order, String> createOrderSummaryTransformer() {
        // TODO: Implement Function<Order, String>
        return order -> "SUMMARY: Order #" + order.getOrderId() + " for " + order.getCustomerName() + " (Amount: $" + order.getBaseAmount() + ")";
    }

    // =========================================================================
    // TASK 5: UNARYOPERATOR<T> PRACTICE
    // =========================================================================
    /**
     * TODO: Practice Task 5 - UnaryOperator<Double>
     * Requirements:
     * - Create a UnaryOperator<Double> that applies a 10% processing surcharge (multiply by 1.10).
     */
    public UnaryOperator<Double> createSurchargeOperator() {
        // TODO: Implement UnaryOperator<Double>
        return amount -> amount * 1.10;
    }

    // =========================================================================
    // TASK 6: BINARYOPERATOR<T> PRACTICE
    // =========================================================================
    /**
     * TODO: Practice Task 6 - BinaryOperator<Double>
     * Requirements:
     * - Create a BinaryOperator<Double> that combines two tax rates by returning their sum.
     */
    public BinaryOperator<Double> createTaxRateAggregator() {
        // TODO: Implement BinaryOperator<Double>
        return (rate1, rate2) -> rate1 + rate2;
    }

    // =========================================================================
    // TASK 7: ANONYMOUS INNER CLASS PRACTICE
    // =========================================================================
    /**
     * TODO: Practice Task 7 - Anonymous Inner Class Implementation
     * Requirements:
     * - Implement a Runnable using ANONYMOUS INNER CLASS syntax (new Runnable() { public void run() { ... } }).
     * - Inside run(), execute auditLogger.accept("Anonymous Runnable Executed!").
     */
    public Runnable createAnonymousRunnable(Consumer<String> logger) {
        // TODO: Practice Anonymous Inner Class syntax!
        return new Runnable() {
            @Override
            public void run() {
                logger.accept("Anonymous Inner Class Runnable Executed!");
            }
        };
    }
}
