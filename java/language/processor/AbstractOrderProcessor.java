package language.processor;

import language.contracts.Auditable;
import language.contracts.EventProcessor;
import language.exception.OrderProcessingException;
import language.model.Order;
import language.model.OrderStatus;

/**
 * PRACTICE TOPIC: Abstract Class Architecture & Multiple Interface Collision Resolution
 * 
 * Target Skills:
 * 1. Abstract class constructor chaining.
 * 2. Protected state encapsulation.
 * 3. Template Method Pattern (final method orchestrating workflow, abstract hooks for subclasses).
 * 4. Resolving Diamond Problem Default Method collision between EventProcessor and Auditable interfaces.
 */
public abstract class AbstractOrderProcessor implements EventProcessor<Order>, Auditable {

    protected final String processorId;

    /**
     * PRACTICE CONCEPT: Abstract Class Constructor
     * Cannot be instantiated directly, but invoked via super(processorId) in subclasses.
     */
    protected AbstractOrderProcessor(String processorId) {
        this.processorId = processorId;
    }

    /**
     * PRACTICE CONCEPT: Template Method Pattern
     * Marked final so subclasses cannot change the processing template execution sequence.
     */
    @Override
    public final void process(Order order) throws OrderProcessingException {
        logEvent(order);
        auditRecord(order.getOrderId());

        // Step 1: Execute subclass abstract validation hook
        validateOrder(order);

        // Step 2: Execute subclass specific processing
        processOrderDetails(order);

        // Step 3: Update order status
        order.setStatus(OrderStatus.PROCESSING);
    }

    /**
     * ABSTRACT METHOD HOOK 1: Must be overridden by subclasses.
     */
    protected abstract void validateOrder(Order order) throws OrderProcessingException;

    /**
     * ABSTRACT METHOD HOOK 2: Must be overridden by subclasses.
     */
    protected abstract void processOrderDetails(Order order) throws OrderProcessingException;

    /**
     * TODO: Practice Task - Resolving Default Method Signature Collision!
     * Both EventProcessor<Order> and Auditable interfaces define 'default String getAuditHeader()'.
     * Requirements:
     * - You MUST override getAuditHeader() in this class to fix compilation error if left unhandled.
     * - Delegate to EventProcessor.super.getAuditHeader() OR Auditable.super.getAuditHeader() or custom string.
     */
    @Override
    public String getAuditHeader() {
        // TODO: Resolve collision by returning Auditable or EventProcessor parent header
        return Auditable.super.getAuditHeader() + " | Processor: " + processorId;
    }

    @Override
    public void auditRecord(String recordId) {
        System.out.println("[AUDIT RECORD]: Processor " + processorId + " auditing order ID: " + recordId);
    }
}
