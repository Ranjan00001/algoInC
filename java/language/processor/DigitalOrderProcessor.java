package language.processor;

import language.contracts.PaymentMethod;
import language.exception.OrderProcessingException;
import language.model.Order;

/**
 * PRACTICE TOPIC: Method Overriding (Run-time Polymorphism)
 * 
 * Target Skills:
 * 1. Extending Abstract Class.
 * 2. Overriding Abstract methods (@Override).
 * 3. Handling domain specific rules (Digital goods validation).
 */
public class DigitalOrderProcessor extends AbstractOrderProcessor {

    public DigitalOrderProcessor(String processorId) {
        super(processorId);
    }

    /**
     * TODO: Task 1 - Override validateOrder
     * Requirements:
     * - Check if order baseAmount is <= 0. If so, throw OrderProcessingException("ERR_DIGITAL_101", "Digital order amount must be positive").
     * - Check if order paymentMethod is Crypto or CreditCard. If null, throw OrderProcessingException("ERR_PAYMENT_NULL", "Payment method required").
     */
    @Override
    protected void validateOrder(Order order) throws OrderProcessingException {
        // TODO: Implement Digital Order Validation logic
        if (order.getBaseAmount() <= 0) {
            throw new OrderProcessingException("ERR_DIGITAL_101", "Digital order amount must be positive");
        }
        if (order.getPaymentMethod() == null) {
            throw new OrderProcessingException("ERR_PAYMENT_NULL", "Payment method required");
        }
    }

    /**
     * TODO: Task 2 - Override processOrderDetails
     * Requirements:
     * - Print "[DIGITAL PROCESSOR]: Generating digital activation key for order " + order.getOrderId().
     */
    @Override
    protected void processOrderDetails(Order order) throws OrderProcessingException {
        // TODO: Implement digital fulfillment logic
        System.out.println("[DIGITAL PROCESSOR]: Generating digital download key for " + order.getOrderId() + " (" + order.getPaymentMethod().getPaymentType() + ")");
    }
}
