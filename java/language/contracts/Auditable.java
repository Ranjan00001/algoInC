package language.contracts;

/**
 * Secondary interface to practice Multiple Interface Inheritance and Default Method Collision Resolution.
 */
public interface Auditable {

    void auditRecord(String recordId);

    /**
     * PRACTICE CONCEPT: Default Method Collision (Diamond Problem in Interfaces)
     * Both EventProcessor and Auditable declare getAuditHeader().
     * Any class implementing BOTH interfaces MUST explicitly override getAuditHeader()
     * and specify which parent interface to call (e.g., Auditable.super.getAuditHeader()).
     */
    default String getAuditHeader() {
        return "HEADER: [Auditable Interface Standard Header]";
    }
}
