/*
### System Architecture Challenge: High-Frequency Telemetry Analytics Engine

#### Scenario
You are building an in-memory **High-Frequency Telemetry Engine** that ingests, filters, and calculates statistical metrics (mean, variance, max, string tag aggregations) over 10,000,000 incoming data points in real time. 

To achieve microsecond latency and multi-core scalability, the engine cannot rely on naive object allocations or single-threaded iterators. You must architect this engine from scratch and profile its JVM execution.

---

### Core Requirements & Visualization Tasks

#### Task 1: Memory Layout & Object Overhead Analysis (Primitives vs Objects)
* **Design Requirements**:
  1. Implement a data pipeline storing $10^7$ metric points using **boxed objects** (`List<Double>`, `List<Long>`).
  2. Implement an optimized payload storing the same data using **primitive flat arrays** (`double[]`, `long[]`).
* **What to Visualise & Measure**:
  * **Tool**: Java Object Layout (JOL) library (`ClassLayout.parseInstance(...)`) or JVM Memory MXBean (`Runtime.getRuntime()`).
  * **Task**: Calculate the exact byte footprint of a boxed `Double` vs primitive `double`. Observe the 16-byte Object Header overhead (Mark Word + Klass Pointer) per boxed element.
  * **Cache Locality Check**: Benchmark array traversal speed over contiguous `double[]` vs `List<Double>` pointer dereferencing.

#### Task 2: String Memory Optimization (String Pool vs Heap Objects)
* **Design Requirements**:
  1. Incoming telemetry records include status tags (e.g., `"SUCCESS"`, `"WARNING"`, `"CRITICAL"`).
  2. Ingest $10^6$ string tags created dynamically via `new String(byteBuffer)`.
  3. Compare this against deduplicating string references using string literals or `.intern()`.
* **What to Visualise & Measure**:
  * **Tool**: `System.identityHashCode(str)` and reference identity operator (`==`).
  * **Task**: Print memory addresses of incoming strings. Verify that `new String("SUCCESS") == new String("SUCCESS")` evaluates to `false` (two distinct Heap objects), whereas `str.intern() == "SUCCESS"` points to the exact same String Pool memory location. Monitor Heap memory savings in JDK Mission Control or VisualVM.

#### Task 3: Multi-Core Spliterator Parallelization
* **Design Requirements**:
  1. Implement a custom `Spliterator<Double>` for your telemetry array payload.
  2. Implement `trySplit()` to recursively divide $10^7$ items in half when data size exceeds a threshold determined by `estimateSize()`.
  3. Dispatch split segments across worker threads (`Thread` / `Runnable` pool).
* **What to Visualise & Measure**:
  * **Tool**: `Thread.currentThread().getName()` and console logging during `trySplit()`.
  * **Task**: Log every split boundary: verify that `trySplit()` returns a new Spliterator handling the lower 50% while the original Spliterator retains the upper 50%. Observe parallel CPU core execution using OS thread monitoring tools (`htop` or JConsole).

#### Task 4: Multi-Threaded Fault Isolation (`UncaughtExceptionHandler`)
* **Design Requirements**:
  1. Intentionally inject corrupted payloads (e.g., division by zero, `null` references, or out-of-bounds metrics) into a subset of thread tasks.
  2. Prevent worker thread failures from crashing the main coordinator thread.
* **What to Visualise & Measure**:
  * **Tool**: `Thread.setUncaughtExceptionHandler(...)`.
  * **Task**: Attach an uncaught exception handler to worker threads. Log the exact thread ID and stack trace when a worker thread dies unexpectedly, while verifying that the main execution loop continues processing remaining data partitions uninterrupted.

#### Task 5: Error vs Exception Strategy
* **Design Requirements**:
  1. Define custom checked and unchecked exceptions (`InvalidPayloadException`, `ThresholdExceededException`).
  2. Simulate an unrecoverable system condition (e.g., allocating a giant `int[Integer.MAX_VALUE]` triggering `java.lang.OutOfMemoryError`).
* **What to Visualise & Measure**:
  * **Tool**: `try-catch` block classification and JVM exit hooks.
  * **Task**: Demonstrate that application code catches and handles `InvalidPayloadException` to recover gracefully, while `OutOfMemoryError` bypasses standard exception recovery routines, demonstrating why `java.lang.Error` represents non-recoverable JVM failures.

#### Task 6: JIT Compiler Warm-Up & Method Inlining Visualization
* **Design Requirements**:
  1. Write a hot calculation method (e.g., `calculateMeanAndVariance(double[] data)`) and invoke it inside a loop 50,000 times.
  2. Measure execution time across initial calls (iterations 1–1,000) vs steady-state calls (iterations 10,000–50,000).
* **What to Visualise & Measure**:
  * **Tool**: JVM Diagnostic Flags `-XX:+PrintCompilation -XX:+UnlockDiagnosticVMOptions -XX:+PrintInlining`.
  * **Task**: Observe the JVM Interpreter transition to JIT C1 (Tier 3) and C2 (Tier 4) compilers. Watch console outputs print `% compile: calculateMeanAndVariance` and confirm method inlining. Measure execution speedup once native machine code takes over!

#### Task 7: JVM Bytecode & Array Immutability Verification
* **Design Requirements**:
  1. Write a small helper inspecting array access vs object method calls.
* **What to Visualise & Measure**:
  * **Tool**: `javap -c TelemetryEngine.class` (Java Disassembler).
  * **Task**: Locate the `arraylength` bytecode instruction. Verify how primitive arrays bypass class virtual method lookup (`invokevirtual`) and execute directly via low-level JVM bytecodes (`aload`, `arraylength`, `iadd`).

---

### Step-by-Step Implementation & Mastery Roadmap

```
+-----------------------------------------------------------------------------------+
| STEP 1: Memory Footprint & Object Layout Baseline                                 |
| - Build boxed vs primitive payload containers                                      |
| - Inspect object headers with JOL / Runtime memory APIs                           |
+-----------------------------------------------------------------------------------+
                                          |
                                          v
+-----------------------------------------------------------------------------------+
| STEP 2: String Pool & Reference Identity Optimization                              |
| - Profile Heap allocation for 1,000,000 repeated string tags                      |
| - Measure identity (==) vs equality (.equals()) & String.intern()                 |
+-----------------------------------------------------------------------------------+
                                          |
                                          v
+-----------------------------------------------------------------------------------+
| STEP 3: Multi-Core Parallel Spliterator Pipeline                                  |
| - Write custom Spliterator implementing trySplit(), tryAdvance(), estimateSize()  |
| - Trace thread execution & split boundaries across multi-core CPUs               |
+-----------------------------------------------------------------------------------+
                                          |
                                          v
+-----------------------------------------------------------------------------------+
| STEP 4: Thread Resilience & Fault Isolation                                       |
| - Inject fatal errors vs recoverable parsing exceptions                           |
| - Attach Thread.UncaughtExceptionHandler to isolate thread failures               |
+-----------------------------------------------------------------------------------+
                                          |
                                          v
+-----------------------------------------------------------------------------------+
| STEP 5: JIT Warm-Up & Bytecode Assembly Profiling                                 |
| - Profile Interpreter -> JIT C1/C2 compilation with -XX:+PrintCompilation          |
| - Disassemble bytecode with javap -c to inspect arraylength & primitive instructions|
+-----------------------------------------------------------------------------------+

=====================================================================================
MASTERED JVM CONCEPTS UPON COMPLETION:
1. Heap vs Stack Memory Allocation (Primitives vs Objects vs Array Headers)
2. Cache Locality & Object Header Overhead (JOL Profiling)
3. String Pool Interning & Memory Deduplication (== vs .equals())
4. Parallel Data Partitioning via Spliterator (trySplit & Multi-threading)
5. Robust Thread Exception Handling (UncaughtExceptionHandler)
6. JIT Compilation, Tiered Warm-Up & Method Inlining (-XX:+PrintCompilation)
7. Bytecode Disassembly & Hardware Execution (javap & arraylength)
=====================================================================================
*/