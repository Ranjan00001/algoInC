package language.processor;

import language.exception.OrderProcessingException;
import language.model.Order;

/**
 * PRACTICE TOPIC: Method Overriding & Custom Subclass Rules
 */
public class PhysicalOrderProcessor extends AbstractOrderProcessor {

    private final double shippingWeightKg;

    public PhysicalOrderProcessor(String processorId, double shippingWeightKg) {
        super(processorId);
        this.shippingWeightKg = shippingWeightKg;
    }

    /**
     * TODO: Task 1 - Override validateOrder
     * Requirements:
     * - Check if shippingWeightKg > 50.0. If so, throw OrderProcessingException("ERR_WEIGHT_EXCEEDED", "Heavy weight freight required").
     */
    @Override
    protected void validateOrder(Order order) throws OrderProcessingException {
        // TODO: Implement physical order validation logic
        if (shippingWeightKg > 50.0) {
            throw new OrderProcessingException("ERR_WEIGHT_EXCEEDED", "Heavy weight freight required for weight: " + shippingWeightKg + "kg");
        }
    }

    /**
     * TODO: Task 2 - Override processOrderDetails
     * Requirements:
     * - Print "[PHYSICAL PROCESSOR]: Creating shipping label for weight " + shippingWeightKg + "kg".
     */
    @Override
    protected void processOrderDetails(Order order) throws OrderProcessingException {
        // TODO: Implement physical order shipping label logic
        System.out.println("[PHYSICAL PROCESSOR]: Creating shipping warehouse dispatch label for order " + order.getOrderId() + " (Weight: " + shippingWeightKg + "kg)");
    }
}
