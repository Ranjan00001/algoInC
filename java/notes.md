# Java Core Concepts & Implementation Notes

A comprehensive, indexed revision guide mapping Java language concepts, memory models, JVM internals, and data structure implementations.

---

## 📚 Master Concept Index & Lookup Table

Use this table during revision to quickly navigate to concepts and their corresponding code implementations in this repository.

| # | Concept / Topic | Code Implementation & Reference Links | Key Operations / Classes |
| :-: | :--- | :--- | :--- |
| **01** | **Singly Linked List** | [`LinkedList.java`](./LinkedList.java), [`LinkedNode.java`](./LinkedNode.java) | Node creation, `pushFront`, `pushBack`, `popFront`, `popBack`, `insert`, `erase`, `reverse`, `valueAt`, `value_n_from_end` |
| **02** | **Tail-Optimized Linked List** | [`LinkedList2.java`](./LinkedList2.java) | $O(1)$ tail insertion & tracking (`pushBack`, `back`), overriding `pushFront`, `popFront`, `popBack`, `reverse` |
| **03** | **Queue ADT (Adapter)** | [`Queue.java`](./Queue.java) | FIFO operations (`enqueue`, `dequeue`, `size`, `empty`) delegating to `LinkedList2` |
| **04** | **Dynamic Resizable Array** | [`Vector.java`](./Vector.java) | Growth factor, capacity resizing, array dynamic allocation |
| **05** | **Two Pointers & Array Sorting** | [`twoSum.java`](./twoSum.java), [`threeSum.java`](./threeSum.java), [`sortColors.java`](./sortColors.java), [`problems/Sorting.java`](./problems/Sorting.java) | Dutch National Flag, sorted 2-pointer pruning, 3-sum $O(N^2)$ traversal |
| **06** | **Subarray & Dynamic Programming** | [`maximumSubArray.java`](./maximumSubArray.java), [`maxSumAcrsAllSubArr.java`](./maxSumAcrsAllSubArr.java), [`buyAndSellStock.java`](./buyAndSellStock.java), [`majorityElements.java`](./majorityElements.java) | Kadane's algorithm, sliding window sums, Boyer-Moore majority vote |
| **07** | **Observability & Telemetry** | [`Prime.java`](./Prime.java), [`Prime.jfr`](./Prime.jfr), [`problems/TelemetryEngine.java`](./problems/TelemetryEngine.java) | Java Flight Recorder (JFR), runtime performance benchmarking |
| **08** | **Multithreading Visualizer** | [`MultiThread.html`](./MultiThread.html) | Thread state visualizer |
| **09** | **Problems & Patterns Suite** | [`problems/Array.java`](./problems/Array.java), [`problems/Searching.java`](./problems/Searching.java), [`problems/PointerPattern.java`](./problems/PointerPattern.java), [`problems/Helper.java`](./problems/Helper.java), [`Pattern.java`](./Pattern.java) | Array utilities, searching, pointer pattern algorithms |

---

## 1. Java Core Principles & Class Mechanics

* **Hybrid Language:** Java is a hybrid (non-pure) Object-Oriented Language because it retains primitive types (`int`, `boolean`, `char`, etc.) alongside Objects.
* **Execution Scope:** Never execute action statements (like printing, loops, or math) directly inside the body of a class. They must live inside a method or constructor.
* **Multiple Inheritance:** Doesn't support multiple inheritance of classes, but supports multiple implementation of interfaces.
* **Interface Default Method Resolution:** In default method execution from an interface, the implementing class needs to specify which interface's default method is to be executed (`InterfaceName.super.methodName()`).

> 💡 **Preparation Tip:** If Class `C` implements Interface `A` and Interface `B`, and both contain `default void show()`, Class `C` **must** override `show()` and explicitly resolve the ambiguity using `A.super.show();` or `B.super.show();`.

---

## 2. Java Polymorphism

Doing a single operation in multiple ways:
* **a. Compile-time Polymorphism (Method Overloading):** Multiple methods in the same class with identical names but different parameter lists/signatures.
* **b. Run-time Polymorphism (Method Overriding):** Subclass provides a specific implementation of a method declared in its parent class.
  * *Code Implementation:* [`LinkedList2.java:L12-L25`](./LinkedList2.java#L12-L25) overrides `pushFront`, `popFront`, `pushBack`, `popBack`, and `reverse` from [`LinkedList.java`](./LinkedList.java).

---

## 3. Java Multi-threading

* **a. Thread vs. Process:** Every thread comes under a process. A process can have multiple threads, but not vice-versa.
* **b. Java `Runnable` Interface:** In Java, `java.lang.Runnable` is a functional interface that a class must implement if its instances are to be executed by a thread.
* **c. Java `Thread` Class:** Thread programming is possible with Java’s `Thread` class. The `Thread` class contains constructors and methods for creating and operating on threads. `Thread` is a subclass of `Object` that implements the `Runnable` interface.
* **d. True Parallelism:** Java threads bring true parallelism rather than Python that just uses 1 core at a time (due to GIL).
* **e. Thread Error Isolation:** Java threads are self-contained, so an unhandled error on a thread does not affect the main thread. To catch the unhandled error, we must use `setUncaughtExceptionHandler`.
* *Telemetry & Visualizer Reference:* [`MultiThread.html`](./MultiThread.html), [`Prime.java`](./Prime.java)

---

## 4. `Collection.spliterator()`

While `.iterator` is strictly sequential and limited to a single thread, `Spliterator` is specifically optimized to break datasets into smaller segments and allows multi-threading.

Three methods in `Spliterator`:
* **a. `trySplit()`:** Divides the data exactly in half. The original spliterator retains the remaining half, while the newly created spliterator is handed off to a separate thread.
* **b. `tryAdvance(Consumer action)`:** Combines the traditional `hasNext()` and `next()` iterator methods into a single process. It checks if an element exists, executes a defined action on it, and returns `true`. If the collection is empty or fully consumed, it returns `false`.
* **c. `estimateSize()`:** Helps internal frameworks determine if a dataset is large enough to warrant splitting into more threads or if it should continue running sequentially.

---

## 5. Error vs. Exception: What is the Difference?

* **`Error`:** An error implies that there is a major problem (e.g. `OutOfMemoryError`, `StackOverflowError`) that a reasonable program should not attempt to solve.
* **`Exception`:** An exception denotes a set of circumstances that a reasonable program would attempt to catch.
  * **Checked Exceptions:** Inherit from `Exception` (must be declared in `throws` or caught in `try-catch`, e.g. `IOException`).
  * **Unchecked Exceptions:** Inherit from `RuntimeException` (e.g. `NullPointerException`, `IndexOutOfBoundsException`, `NoSuchElementException`).
  * *Visual Map:* See [`Hierarchy_of_Exceptions.png`](./Hierarchy_of_Exceptions.png).

---

## 6. String Pool & Memory

* **String Literals:** If the String already exists in a literal, the new reference variable will point to the currently existing literal. It uses the **String Constant Pool**.
* **String Objects (`new String(...)`):** If the String is created via `new String(...)`, it always creates a new Object in Heap.

---

## 7. `StringBuffer` vs. `StringBuilder`

* **a.** Both are used for growable and writable Strings.
* **b.** `StringBuffer` is thread-safe while `StringBuilder` is not because it's not synchronized after string updates.
* **c.** `StringBuffer` is less efficient than `StringBuilder` due to synchronization lock overhead.

---

## 8. Abstract Class Rules

* **a.** An abstract class instance cannot be created.
* **b.** The use of constructors is permitted (used for constructor chaining).
* **c.** There is no need for an abstract method in an abstract class.
* **d.** Final methods aren't allowed in abstract classes since they can't be overridden, but abstract methods are designed to be overridden.
* **e.** We are prohibited from creating objects for any abstract class.
* **f.** In an abstract class, we can define static methods.

---

## 9. Interface vs. Abstract Class Comparison

| Property | `interface` | `abstract class` |
| :--- | :--- | :--- |
| **a. Inheritance Model** | A class can implement multiple interfaces. | A class can extend only one abstract class. |
| **b. State / Variables** | Can only have `public static final` constants. | Can have regular instance variables (state). |
| **c. Constructors** | Prohibited. | Permitted (used for constructor chaining). |
| **d. Methods** | Originally abstract only; can have `default`, `static`, and `private` methods. | Can have a mix of abstract (no body) and concrete (with body) methods. |
| **e. Access Modifiers** | Everything is `public` by default. | Can use `public`, `protected`, `private`, or default. |
| **f. Relationship Type** | Establishes an *"is-capable-of"* relationship. | Establishes an *"is-a"* relationship. |
| **g. Inter-operability** | An interface can be implemented using an abstract class. | — |

---

## 10. Types in Java & Memory Allocation

* Object type and primitive types are treated differently.
* Object is stored in Heap and Primitive type is stored in Stack.
* Static memory is allocated at compile time and is stored in Stack/Metaspace.
* Primitive variables are stored directly in the Stack. Object variables are stored by reference in the Heap.

| Item | Where is it declared? | Memory Location |
| :--- | :--- | :--- |
| **Object** | Anywhere using `new` | **Heap** |
| **Local Variable (Primitive or Reference)** | Inside a method / block | **Stack** |
| **Instance Field (Primitive or Reference)** | Class level (non-static) | **Heap** (Inside parent object payload) |
| **Static Variable** | Class level (`static`) | **Heap / Metaspace** |

---

## 11. Memory Structure

```text
+-------------------------------------------------------------+
|                        HEAP MEMORY                          |
|  (Shared across all threads, managed by Garbage Collector)  |
|                                                             |
|  - All Objects (e.g., String, LinkedList, Vector)           |
|  - All Arrays (e.g., int[], Object[])                       |
|  - Instance fields of objects                               |
+-------------------------------------------------------------+
                            |
            Reference Pointers (Addresses)
                            |
+---------------------------v---------------------------------+
|                       STACK MEMORY                          |
|            (Per-thread, fast method execution)              |
|                                                             |
|  [ Stack Frame for Method A ]                               |
|   - Primitive local variables (int x = 10;)                 |
|   - Local reference variables (MyClass obj, int[] arr)      |
|  [ Stack Frame for Method B ]                               |
+-------------------------------------------------------------+
```

* The objects live on the Heap, while its reference variable lives on the Stack.
* Primitives location depends on scope:
  * Local variable inside method $\to$ **Stack**
  * Instance field inside class $\to$ **Heap** (inside parent object payload)
  * Static variable $\to$ **Heap / Metaspace**

---

## 12. Why Distinction Between Object and Primitive Types?

1. **Object overhead:** Every object maintains an object header (Mark Word + Klass Word = 12-16 bytes overhead). Primitive types have **0 overhead**.
2. Primitive types are stored in contiguous raw memory, while Objects require dereferencing to fetch.
3. Primitive variables live inside stack that is cleaned off immediately after method execution, while objects live inside heap until GC cleans them off.
4. If we look at the JVM, it only supports 2 data types:
   * **Primitive Type**
   * **Reference Type**

---

## 13. Compiler, Interpreter, Profiler & JIT Compiler

* **Compiler (`javac`):** Converts `.java` code into `.class` bytecode.
* **Interpreter:** Executes bytecode line-by-line immediately upon startup (zero startup delay).
* **Profiler:** Counts execution frequency of methods/loops to detect "Hot Spots".
* **JIT Compiler (C1 / C2):** Translates "Hot" bytecode into native CPU machine code at runtime.
* **Key Optimizations:**
  * **Method Inlining:** Replaces method calls with body code.
  * **Escape Analysis & Scalar Replacement:** Replaces non-escaping Heap objects with primitive registers in CPU.
  * **Profile-Guided Optimization (PGO):** Uses runtime telemetry to make speculative jumps faster than static C code.

---

## 14. JAR Files & Bundling

* Creating Java Archive using a list of class files... application bundling.
* **Modular JAR vs. Traditional JAR:**
  * *Traditional JAR:* Just a bundle of packages with no formal declaration of what it requires or shares with other code.
  * *Modular JAR:* Self-describing package that works directly with the module path for strong encapsulation and clean boundaries.
* **Multi-Release JAR:** Contains versioned classes in `META-INF/versions/` for different JDK versions.

---

## 15. JVM Diagnostic & Observability Tools

* `javap`: Disassembles `.class` files to inspect JVM bytecode.
* `jfr`: Java Flight Recorder for low-overhead runtime profiling (e.g. [`Prime.jfr`](./Prime.jfr)).
* `jcmd`: Sends diagnostic commands to running JVM processes.
* `javadoc`: Generates HTML API documentation.
* Telemetry Engine: [`problems/TelemetryEngine.java`](./problems/TelemetryEngine.java)

---

## 16. `this` Keyword Usage Scenarios

`this` is only required for 3 scenarios:

1. **Variable Shadowing:** When local parameter names match field names (`this.size = size;`).
2. **Constructor Chaining:** `this(...)` matches any constructor signature, must be the **VERY first statement** inside a constructor.
3. **Passing or Returning the Current Object:** Returning `return this;` or passing `this` to external methods.

---

## 🌐 Reference Links

1. [Java Developer Skills (InterviewBit)](https://www.interviewbit.com/blog/java-developer-skills/)
2. [Java Projects (InterviewBit)](https://www.interviewbit.com/blog/java-projects/)
3. [Java Frameworks (InterviewBit)](https://www.interviewbit.com/blog/java-frameworks/)
4. [Java MCQ (InterviewBit)](https://www.interviewbit.com/java-mcq/)
5. [Java 11 Features (InterviewBit)](https://www.interviewbit.com/blog/java-11-features/)
6. [Java 8 Features (InterviewBit)](https://www.interviewbit.com/blog/java-8-features/)
7. [Java Books (InterviewBit)](https://www.interviewbit.com/blog/java-books/)
8. [Java Developer Resume (InterviewBit)](https://www.interviewbit.com/blog/java-developer-resume/)
9. [Top Java Applications (InterviewBit)](https://www.interviewbit.com/blog/top-java-applications/)
10. [Characteristics of Java (InterviewBit)](https://www.interviewbit.com/blog/characteristics-of-java/)
11. [Error vs Exception in Java (Scaler)](https://www.scaler.com/topics/java/error-vs-exception-in-java/)
