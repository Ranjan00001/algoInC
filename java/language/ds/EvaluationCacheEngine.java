package language.ds;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * DOMAIN SCENARIO 7: High-Performance Risk Evaluation LRU Cache
 * 
 * Business Requirement:
 * Evaluating order fraud risk requires expensive network calls to credit bureaus.
 * Results must be cached in $O(1)$ time. When the cache hits maximum capacity, the LEAST RECENTLY ACCESSED entry must be automatically evicted.
 * 
 * Operational Constraint:
 * Both lookup (`get`) and insertion (`put`) must operate in $O(1)$ time, maintaining access ordering for eviction.
 * 
 * TODO: Task
 * 1. Choose and configure the Java Collection class that supports access-ordered LRU eviction (LinkedHashMap).
 * 2. Implement getCachedRiskScore and putRiskScore.
 */
public class EvaluationCacheEngine {

    private final int capacity;

    // TODO: Choose and configure your LRU Map field here (hint: accessOrder = true)
    private final Map<String, Boolean> lruCache;

    public EvaluationCacheEngine(int capacity) {
        this.capacity = capacity;
        this.lruCache = new LinkedHashMap<String, Boolean>(capacity, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<String, Boolean> eldest) {
                return size() > EvaluationCacheEngine.this.capacity;
            }
        };
    }

    /**
     * Retrieve cached risk evaluation result for orderId.
     * Accessing an entry MUST mark it as Most Recently Used.
     * Returns null if key is not present.
     */
    public Boolean getCachedRiskScore(String orderId) {
        // TODO: Read score and update access order
        return lruCache.get(orderId);
    }

    /**
     * Put risk evaluation result into cache.
     * Evicts least recently used entry if capacity is exceeded.
     */
    public void putRiskScore(String orderId, boolean passesRisk) {
        // TODO: Put entry into cache
        lruCache.put(orderId, passesRisk);
    }

    public int getCacheSize() {
        return lruCache.size();
    }
}
