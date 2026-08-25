package language.model;

import language.contracts.PaymentMethod;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * PRACTICE TOPIC: Core Class Architecture & Nested Classes
 * 
 * Target Skills:
 * 1. Method Overloading (Compile-time polymorphism).
 * 2. Static Nested Class (Builder Pattern).
 * 3. Non-Static Inner Class (Accessing outer instance private fields).
 * 4. Encapsulation & Defensive Copies.
 */
public class Order {
    private final String orderId;
    private final String customerName;
    private final double baseAmount;
    private final List<String> items;
    private OrderStatus status;
    private PaymentMethod paymentMethod;

    private Order(Builder builder) {
        this.orderId = builder.orderId;
        this.customerName = builder.customerName;
        this.baseAmount = builder.baseAmount;
        this.items = new ArrayList<>(builder.items);
        this.status = builder.status;
        this.paymentMethod = builder.paymentMethod;
    }

    public String getOrderId() { return orderId; }
    public String getCustomerName() { return customerName; }
    public double getBaseAmount() { return baseAmount; }
    public List<String> getItems() { return Collections.unmodifiableList(items); }
    public OrderStatus getStatus() { return status; }
    public PaymentMethod getPaymentMethod() { return paymentMethod; }

    public void setStatus(OrderStatus newStatus) {
        if (!this.status.canTransitionTo(newStatus)) {
            throw new IllegalArgumentException("Invalid state transition from " + this.status + " to " + newStatus);
        }
        this.status = newStatus;
    }

    // =========================================================================
    // PRACTICE SECTION: METHOD OVERLOADING (Compile-time Polymorphism)
    // =========================================================================

    /**
     * TODO: Task 1 - Basic Overloaded Method (No parameters beyond base amount)
     * Requirements: Return baseAmount as final total.
     */
    public double calculateTotalCost() {
        // TODO: Implement base calculation
        return baseAmount;
    }

    /**
     * TODO: Task 2 - Overloaded Method with Tax Rate
     * Requirements: Return baseAmount + (baseAmount * taxRate Percentage e.g. 0.15 for 15%).
     */
    public double calculateTotalCost(double taxRatePercentage) {
        // TODO: Implement calculation with tax rate
        return baseAmount + (baseAmount * (taxRatePercentage / 100.0));
    }

    /**
     * TODO: Task 3 - Overloaded Method with Tax Rate AND Flat Discount
     * Requirements: Apply flat discount first to baseAmount (min 0), then apply tax rate percentage.
     */
    public double calculateTotalCost(double taxRatePercentage, double flatDiscount) {
        // TODO: Implement calculation with discount and tax rate
        double discounted = Math.max(0, baseAmount - flatDiscount);
        return discounted + (discounted * (taxRatePercentage / 100.0));
    }

    // =========================================================================
    // PRACTICE SECTION: STATIC NESTED CLASS (Builder Pattern)
    // =========================================================================

    /**
     * Practice Focus: Static Nested Class
     * Does NOT have access to an outer Order instance because it creates one.
     */
    public static class Builder {
        private final String orderId;
        private String customerName = "Guest";
        private double baseAmount = 0.0;
        private final List<String> items = new ArrayList<>();
        private OrderStatus status = OrderStatus.PENDING;
        private PaymentMethod paymentMethod;

        public Builder(String orderId) {
            this.orderId = orderId;
        }

        public Builder customerName(String name) {
            this.customerName = name;
            return this;
        }

        public Builder baseAmount(double amount) {
            this.baseAmount = amount;
            return this;
        }

        public Builder addItem(String item) {
            this.items.add(item);
            return this;
        }

        public Builder paymentMethod(PaymentMethod method) {
            this.paymentMethod = method;
            return this;
        }

        public Order build() {
            // TODO: Practice validation in Builder before returning new Order(this)
            // Verify orderId is non-null and non-empty, otherwise throw IllegalStateException
            if (orderId == null || orderId.isBlank()) {
                throw new IllegalStateException("Order ID cannot be empty");
            }
            return new Order(this);
        }
    }

    // =========================================================================
    // PRACTICE SECTION: INNER CLASS (Non-Static Nested Class)
    // =========================================================================

    /**
     * Practice Focus: Non-Static Inner Class
     * Can access outer instance fields directly (e.g. Order.this.orderId, baseAmount, status).
     */
    public class OrderAuditTracker {
        private final List<String> auditLogs = new ArrayList<>();

        /**
         * TODO: Practice Task - Inner Class Field Dereferencing
         * Requirements:
         * - Access outer class's orderId and customerName to build audit string:
         *   "AUDIT [Order ID: " + orderId + ", Customer: " + customerName + "]: " + logMessage
         * - Add formatted string to auditLogs list.
         */
        public void recordLog(String logMessage) {
            // TODO: Implement inner class direct access to outer class instance state
            String entry = "AUDIT [Order ID: " + orderId + ", Customer: " + customerName + "]: " + logMessage;
            auditLogs.add(entry);
        }

        public List<String> getAuditLogs() {
            return Collections.unmodifiableList(auditLogs);
        }
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", customerName='" + customerName + '\'' +
                ", baseAmount=" + baseAmount +
                ", status=" + status +
                ", items=" + items.size() +
                '}';
    }
}
