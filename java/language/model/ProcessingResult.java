package language.model;

import java.util.List;

/**
 * PRACTICE TOPIC: Generics & Bounded Wildcards (PECS Principle)
 * 
 * Target Skills:
 * 1. Generic type parameters <T>.
 * 2. Producer Extends (? extends T) - covariance / reading data.
 * 3. Consumer Super (? super T) - contravariance / writing data.
 * 4. Bounded generic method signatures.
 */
public class ProcessingResult<T> {
    private final boolean success;
    private final T payload;
    private final String message;
    private final long timestamp;

    public ProcessingResult(boolean success, T payload, String message) {
        this.success = success;
        this.payload = payload;
        this.message = message;
        this.timestamp = System.currentTimeMillis();
    }

    public boolean isSuccess() { return success; }
    public T getPayload() { return payload; }
    public String getMessage() { return message; }
    public long getTimestamp() { return timestamp; }

    // =========================================================================
    // PRACTICE SECTION: GENERIC METHODS & BOUNDED WILDCARDS
    // =========================================================================

    /**
     * TODO: Task 1 - Producer Extends (? extends T)
     * Requirements:
     * - Write a static method 'extractAllPayloads(List<? extends ProcessingResult<T>> results, List<T> destination)'
     * - Iterate through results list (producer) and add payloads of successful results into destination list.
     * - Why '? extends'? Because we are READING / PRODUCING values from the list!
     */
    public static <T> void extractAllPayloads(List<? extends ProcessingResult<T>> results, List<T> destination) {
        // TODO: Implement generic payload extraction using producer extends list
        for (ProcessingResult<T> res : results) {
            if (res.isSuccess() && res.getPayload() != null) {
                destination.add(res.getPayload());
            }
        }
    }

    /**
     * TODO: Task 2 - Consumer Super (? super T)
     * Requirements:
     * - Write a static method 'copyResults(List<ProcessingResult<T>> source, List<? super ProcessingResult<T>> destination)'
     * - Add all elements from source into destination.
     * - Why '? super'? Because destination is CONSUMING / WRITING elements!
     */
    public static <T> void copyResults(List<ProcessingResult<T>> source, List<? super ProcessingResult<T>> destination) {
        // TODO: Implement copying source into destination consumer list
        destination.addAll(source);
    }

    @Override
    public String toString() {
        return "ProcessingResult{" +
                "success=" + success +
                ", payload=" + payload +
                ", message='" + message + '\'' +
                '}';
    }
}
