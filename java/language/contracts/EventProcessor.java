package language.contracts;

import language.exception.OrderProcessingException;

/**
 * PRACTICE TOPIC: Interfaces (Default, Static, and Private Methods)
 * 
 * Target Skills:
 * 1. Interface method declarations vs Default methods.
 * 2. Static methods in interfaces (utility access).
 * 3. Private helper methods inside interfaces (Java 9+).
 */
public interface EventProcessor<T> {

    /**
     * Abstract method contract to be implemented by processors.
     */
    void process(T event) throws OrderProcessingException;

    /**
     * TODO: Task 1 - Implement Default Log Method
     * Requirements:
     * - Write a default method 'logEvent(T event)' that prints "[EventProcessor LOG]: " + event.toString().
     * - Hint: Use private helper method below if implemented.
     */
    default void logEvent(T event) {
        // TODO: Implement default logging logic here
        System.out.println("[EventProcessor LOG]: " + event);
    }

    /**
     * TODO: Task 2 - Default Method Signature Collision Target
     * Requirements:
     * - Return header string: "HEADER: [EventProcessor Default Header]"
     */
    default String getAuditHeader() {
        // TODO: Return EventProcessor default audit header string
        return "HEADER: [EventProcessor Default Header]";
    }

    /**
     * TODO: Task 3 - Static Interface Method
     * Requirements:
     * - Provide a static method 'getSystemVersion()' returning String "v2.5.0-ENTERPRISE".
     * - Note: Static methods in interfaces belong to the interface, not implementing instances.
     */
    static String getSystemVersion() {
        // TODO: Implement static interface method return
        return "v2.5.0-ENTERPRISE";
    }
}
