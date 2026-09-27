package language.ds;

import java.util.LinkedList;

/**
 * DOMAIN SCENARIO 3: Historical Audit Trail Event Stream
 * 
 * Business Requirement:
 * System maintains an active log stream of audit events.
 * High-priority security breaches must jump to the front of the log stream immediately,
 * while standard logs are appended to the back. When memory constraints occur, oldest entries at the front are evicted.
 * 
 * Operational Constraint:
 * Requires efficient $O(1)$ insertion at BOTH head and tail, and $O(1)$ removal from the head.
 * 
 * TODO: Task
 * 1. Choose and instantiate the optimal doubly-linked Java collection structure.
 * 2. Implement appendLog, prependHighPriorityLog, and purgeOldestLogs.
 */
public class AuditTrailContainer {

    // TODO: Choose and initialize your chosen Java collection field here.
    private final LinkedList<String> auditStream = new LinkedList<>();

    /**
     * Append standard log entry to the end of stream.
     */
    public void appendLog(String logEntry) {
        // TODO: Append to back of stream
        auditStream.addLast(logEntry);
    }

    /**
     * Insert high-priority security log entry at the very front of stream.
     */
    public void prependHighPriorityLog(String logEntry) {
        // TODO: Prepend to front of stream
        auditStream.addFirst(logEntry);
    }

    /**
     * Purge the specified number of oldest log entries from the front of stream.
     * Returns count of actually purged entries.
     */
    public int purgeOldestLogs(int countToPurge) {
        // TODO: Remove oldest elements from front
        int purged = 0;
        while (purged < countToPurge && !auditStream.isEmpty()) {
            auditStream.removeFirst();
            purged++;
        }
        return purged;
    }

    public int getLogCount() {
        return auditStream.size();
    }

    public String peekLatestHeadLog() {
        return auditStream.peekFirst();
    }
}
