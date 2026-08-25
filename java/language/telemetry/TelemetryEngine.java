package language.telemetry;

import language.exception.OrderProcessingException;

import java.util.ArrayList;
import java.util.List;

/**
 * PRACTICE TOPIC: Telemetry Engine & Multi-Threaded JVM Performance Mechanics
 * 
 * Target Skills:
 * 1. Primitive arrays vs Boxed object overhead analysis.
 * 2. String Pool interning (.intern() memory identity check).
 * 3. Thread UncaughtExceptionHandler for fault isolation.
 * 4. Error vs Exception recovery semantics.
 */
public class TelemetryEngine {

    /**
     * TODO: Practice Task 1 - String Pool Interning Verification
     * Requirements:
     * - Create string dynamic String s1 = new String("SUCCESS");
     * - Create string literal String s2 = "SUCCESS";
     * - Demonstrate that (s1 == s2) is FALSE (different heap objects).
     * - Verify that (s1.intern() == s2) is TRUE (points to identical String Pool reference).
     */
    public boolean verifyStringPoolInterning() {
        // TODO: Implement String Pool interning verification
        String s1 = new String("SUCCESS");
        String s2 = "SUCCESS";
        boolean rawCompare = (s1 == s2); // false
        boolean internCompare = (s1.intern() == s2); // true
        System.out.println("[TELEMETRY] String == comparison: " + rawCompare + " | String .intern() == comparison: " + internCompare);
        return internCompare;
    }

    /**
     * TODO: Practice Task 2 - Thread UncaughtExceptionHandler
     * Requirements:
     * - Attach a custom Thread.UncaughtExceptionHandler to a worker thread.
     * - Inside handler, log thread name and exception message:
     *   "[THREAD FAULT HANDLER]: Thread " + thread.getName() + " died due to: " + throwable.getMessage()
     * - Intentionally throw a RuntimeException inside worker thread run() method and observe handler catch it.
     */
    public void executeFaultTolerantThread() throws InterruptedException {
        // TODO: Implement UncaughtExceptionHandler on worker thread
        Thread worker = new Thread(() -> {
            System.out.println("[TELEMETRY WORKER]: Executing telemetry task on thread " + Thread.currentThread().getName());
            throw new IllegalStateException("Telemetry sensor malfunction simulated!");
        }, "Telemetry-Sensor-Thread-1");

        worker.setUncaughtExceptionHandler((t, e) -> {
            System.out.println("[THREAD FAULT HANDLER]: Thread " + t.getName() + " died with exception: " + e.getMessage());
        });

        worker.start();
        worker.join();
    }

    /**
     * TODO: Practice Task 3 - Benchmark Primitive Array vs Boxed Object Traversal
     * Requirements:
     * - Sum 1,000,000 double values using double[] vs List<Double>.
     * - Measure traversal duration using System.nanoTime().
     */
    public void compareMemoryLayoutSpeed() {
        int count = 1_000_000;
        double[] primitiveArray = new double[count];
        List<Double> boxedList = new ArrayList<>(count);

        for (int i = 0; i < count; i++) {
            primitiveArray[i] = i * 0.5;
            boxedList.add(i * 0.5);
        }

        long startPrimitive = System.nanoTime();
        double sumPrimitive = 0.0;
        for (int i = 0; i < count; i++) {
            sumPrimitive += primitiveArray[i];
        }
        long endPrimitive = System.nanoTime();

        long startBoxed = System.nanoTime();
        double sumBoxed = 0.0;
        for (Double val : boxedList) {
            sumBoxed += val;
        }
        long endBoxed = System.nanoTime();

        System.out.println(String.format("[TELEMETRY BENCHMARK] Primitive double[]: %d ns | Boxed List<Double>: %d ns",
                (endPrimitive - startPrimitive), (endBoxed - startBoxed)));
    }
}
