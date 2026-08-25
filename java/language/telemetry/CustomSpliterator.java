package language.telemetry;

import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * PRACTICE TOPIC: Custom Spliterator Implementation for Parallel Data Processing
 * 
 * Target Skills:
 * 1. Implementing java.util.Spliterator<Double>.
 * 2. Division of data range via trySplit() for multi-core thread execution.
 * 3. tryAdvance() combining hasNext() and next().
 */
public class CustomSpliterator implements Spliterator<Double> {

    private final double[] data;
    private int origin;
    private final int fence;

    public CustomSpliterator(double[] data, int origin, int fence) {
        this.data = data;
        this.origin = origin;
        this.fence = fence;
    }

    /**
     * TODO: Practice Task 1 - tryAdvance
     * Requirements:
     * - If origin < fence:
     *   - Action.accept(data[origin])
     *   - Increment origin by 1
     *   - Return true
     * - Else return false
     */
    @Override
    public boolean tryAdvance(Consumer<? super Double> action) {
        // TODO: Implement tryAdvance logic combining hasNext and next
        if (origin < fence) {
            action.accept(data[origin++]);
            return true;
        }
        return false;
    }

    /**
     * TODO: Practice Task 2 - trySplit (Parallel splitting)
     * Requirements:
     * - Calculate mid point: (origin + fence) >>> 1
     * - If range is too small (e.g., (fence - origin) < 1000), return null (no split).
     * - Else:
     *   - Save current origin in oldOrigin
     *   - Set origin = mid
     *   - Return new CustomSpliterator(data, oldOrigin, mid)
     */
    @Override
    public Spliterator<Double> trySplit() {
        // TODO: Implement trySplit for multi-threading work distribution
        int lo = origin;
        int mid = (lo + fence) >>> 1;
        if (lo >= mid || (fence - lo) < 1000) {
            return null;
        }
        origin = mid;
        return new CustomSpliterator(data, lo, mid);
    }

    @Override
    public long estimateSize() {
        return (long) (fence - origin);
    }

    @Override
    public int characteristics() {
        return ORDERED | SIZED | SUBSIZED | IMMUTABLE;
    }
}
