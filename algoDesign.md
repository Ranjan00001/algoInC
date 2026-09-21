# Algorithm Design Techniques & Implementations

A structured, chronological guide detailing the core algorithm design paradigms, mathematical properties, and search space optimization techniques used throughout this repository, with direct links to source code and conceptual documentation.

---

## 📚 Master Index & Navigation Table

| # | Algorithm Paradigm / Concept | Short Description | Source Implementations & References |
| :-: | :--- | :--- | :--- |
| **01** | **Incremental Technique** | Solution built incrementally by expanding the sorted/processed portion. | [`Algos/selectionSort.c`](./Algos/selectionSort.c), [`Algos/insertionSort.c`](./Algos/insertionSort.c), [`java/problems/Sorting.java`](./java/problems/Sorting.java) |
| **02** | **Divide & Conquer** | Divides problem into subproblems, solves recursively, and merges results. | [`Algos/mergeSort.c`](./Algos/mergeSort.c), [`Algos/findRoot.c`](./Algos/findRoot.c), [`Algos/medianOfMedians.c`](./Algos/medianOfMedians.c), [`Algos/toweOfHanoi.c`](./Algos/toweOfHanoi.c) |
| **03** | **Subarray Min/Max Non-Maximization** | Adding elements to a subarray never increases minimums or decreases maximums. | [`Concepts/KadaneAndArrays.md`](./Concepts/KadaneAndArrays.md) |
| **04** | **Mathematical Monotonicity (Subarrays)** | Monotonic behavior of subarray Sums, Bitwise AND/OR, and GCD under expansion. | [`Concepts/SlidingWindow.md`](./Concepts/SlidingWindow.md), [`java/maxSumAcrsAllSubArr.java`](./java/maxSumAcrsAllSubArr.java) |
| **05** | **Prefix Properties (Sum & XOR)** | $O(1)$ range queries via Prefix Sum (`P[j] - P[i-1]`) and Prefix XOR (`P[j] ^ P[i-1]`). | [`Algos/addBinary.c`](./Algos/addBinary.c), [`java/problems/PointerPattern.java`](./java/problems/PointerPattern.java#L40) |
| **06** | **Greedy Choice & Resets (Kadane)** | Resets running prefix sum to $0$ when negative to maximize future subarray sum. | [`Concepts/KadaneAndArrays.md`](./Concepts/KadaneAndArrays.md), [`java/maximumSubArray.java`](./java/maximumSubArray.java), [`java/buyAndSellStock.java`](./java/buyAndSellStock.java) |
| **07** | **Two Pointers & Search Space Pruning** | Prunes an entire row/column of candidate combinations in $O(1)$ boundary moves. | [`Concepts/PointerPattern.md`](./Concepts/PointerPattern.md), [`Algos/twoSum.c`](./Algos/twoSum.c), [`Algos/twinPointers.py`](./Algos/twinPointers.py), [`java/twoSum.java`](./java/twoSum.java), [`java/threeSum.java`](./java/threeSum.java), [`java/sortColors.java`](./java/sortColors.java) |
| **08** | **Symmetry & Center Expansion** | Expands outward from $(2N - 1)$ center positions instead of testing all $O(N^2)$ substrings. | [`Programs/palindromeNumber.c`](./Programs/palindromeNumber.c), [`java/problems/PointerPattern.java`](./java/problems/PointerPattern.java) |
| **09** | **Fast & Slow Pointers (Floyd's Cycle)** | $1\times$ and $2\times$ speed pointers mathematically guarantee meeting if a cycle exists in $O(1)$ space. | [`Concepts/PointerPattern.md`](./Concepts/PointerPattern.md), [`java/problems/PointerPattern.java`](./java/problems/PointerPattern.java), [`java/LinkedList.java`](./java/LinkedList.java) |
| **10** | **Pairwise Cancellation (Boyer-Moore)** | Pairwise discarding of distinct elements preserves strict majority ($> N/2$) element status. | [`java/majorityElements.java`](./java/majorityElements.java) |
| **11** | **Binary Search Paradigm** | $O(\log N)$ search space reduction on sorted domains. | [`Concepts/BinarySearch.md`](./Concepts/BinarySearch.md), [`Basic/linearSearch.c`](./Basic/linearSearch.c), [`Algos/findRoot.c`](./Algos/findRoot.c) |
| **12** | **C Fundamentals & Pointer Mechanics** | Memory addresses, pointer arithmetic, function pointers, mutexes & semaphores. | [`Basic/pointer.c`](./Basic/pointer.c), [`Basic/pointerArithmetic.c`](./Basic/pointerArithmetic.c), [`Basic/pointerFunction.c`](./Basic/pointerFunction.c), [`Basic/pointerToFunction.c`](./Basic/pointerToFunction.c), [`Basic/mutex.c`](./Basic/mutex.c), [`Basic/semaphore.c`](./Basic/semaphore.c) |
| **13** | **C Strings & Input Handling** | String creation, modification, formatting, and buffer input. | [`Strings/firstString.c`](./Strings/firstString.c), [`Strings/inputString.c`](./Strings/inputString.c), [`Strings/modifyString.c`](./Strings/modifyString.c), [`Basic/escapeChars.c`](./Basic/escapeChars.c) |
| **14** | **Mathematical C Programs** | Prime detection, Fibonacci sequence, Armstrong numbers, matrix multiplication. | [`Programs/prime.c`](./Programs/prime.c), [`Programs/fibonacci.c`](./Programs/fibonacci.c), [`Programs/armstrong.c`](./Programs/armstrong.c), [`Programs/matrixMuliplication.c`](./Programs/matrixMuliplication.c), [`Programs/swap.c`](./Programs/swap.c), [`Basic/swap.c`](./Basic/swap.c) |

---

## 1. Incremental Technique

* **Concept:** Every time we keep increasing our result part.
* **Example:** We use this in `insertionSort` and `selectionSort`. Every time we keep increasing our sorted portion.
* **Key Characteristics:**
  * **State Expansion:** Simple state transitions where one item is added to the sorted invariant at each pass.
  * **Time Complexity:** $O(N^2)$ average and worst-case for basic comparisons.
  * **Space Complexity:** In-place execution requiring $O(1)$ auxiliary memory.
* **Source Code References:**
  * Selection Sort (C): [`Algos/selectionSort.c`](./Algos/selectionSort.c)
  * Insertion Sort (C): [`Algos/insertionSort.c`](./Algos/insertionSort.c)
  * Sorting Suite (Java): [`java/problems/Sorting.java`](./java/problems/Sorting.java)

---

## 2. Divide and Conquer

* **Concept:** We divide the problem into smaller subproblems and then conquer it.
* **Example:** `MergeSort`
* **Key Characteristics:**
  * **Fast Run-Time Analysis:** This method's natural advantage is that it gives us a fast way to analyze the run-time via Recurrence Relations ($T(n) = aT(n/b) + f(n)$).
  * **Recursion:** Typically this solves recursive problems by breaking them into independent subproblems.
  * **Time Complexity:** Solves sorting in $O(N \log N)$ and search space reductions in $O(\log N)$.
* **Source Code References:**
  * Merge Sort: [`Algos/mergeSort.c`](./Algos/mergeSort.c)
  * Median of Medians ($O(N)$ selection): [`Algos/medianOfMedians.c`](./Algos/medianOfMedians.c)
  * Binary Root Finder ($O(\log N)$): [`Algos/findRoot.c`](./Algos/findRoot.c)
  * Tower of Hanoi (Recursion): [`Algos/toweOfHanoi.c`](./Algos/toweOfHanoi.c)

---

## 3. Subarray Min/Max Non-Maximization Principle

* **Concept:** Adding elements to a subarray doesn't maximize the minimums.
* **Key Characteristics:**
  * **Monotonic Min/Max:** Adding elements to a subarray never increases the minimum, and never decreases the maximum.
  * **Invariance:** $\min(A \cup \{x\}) \le \min(A)$ and $\max(A \cup \{x\}) \ge \max(A)$.
  * **Use Case:** Forms the theoretical foundation for monotonic queue/stack problems.
* **Conceptual Guide:** [`Concepts/KadaneAndArrays.md`](./Concepts/KadaneAndArrays.md)

---

## 4. Mathematical Monotonicity (Subarrays)

* **Concept:** Contiguous expansion of non-negative subarrays yields strictly monotonic properties:
  * **Minimums/Maximums:** Adding elements to a subarray never increases the minimum, and never decreases the maximum.
  * **Sums (Non-negative):** Expanding a subarray of non-negative integers only increases the sum (basis for Sliding Window).
  * **Bitwise AND:** Adding elements to a subarray never increases the Bitwise AND.
  * **Bitwise OR:** Adding elements to a subarray never decreases the Bitwise OR.
  * **GCD:** Adding elements to a subarray never increases the Greatest Common Divisor.
* **Key Characteristics:**
  * Enables $O(N)$ two-pointer and sliding window window resizing.
  * Guarantees single directional pointer movement without backtracking.
* **Source Code & Guides:**
  * Sliding Window Guide: [`Concepts/SlidingWindow.md`](./Concepts/SlidingWindow.md)
  * Subarray Sums: [`java/maxSumAcrsAllSubArr.java`](./java/maxSumAcrsAllSubArr.java)
  * Binary Addition: [`Algos/addBinary.c`](./Algos/addBinary.c)

---

## 5. Prefix Properties

* **Concept:** Range queries precomputed in $O(N)$ preprocessing time for $O(1)$ lookup.
  * **Subarray Sum:** The sum of any contiguous subarray `arr[i...j]` is `PrefixSum[j] - PrefixSum[i-1]`.
  * **XOR Logic:** `A ^ A = 0`. The XOR of any subarray `arr[i...j]` is `PrefixXOR[j] ^ PrefixXOR[i-1]`.
* **Key Characteristics:**
  * Turns $O(N)$ range calculations into $O(1)$ arithmetic operations.
  * Requires $O(N)$ auxiliary space for prefix arrays or running prefix state.
* **Source Code References:**
  * Range Query Logic: [`java/problems/PointerPattern.java`](./java/problems/PointerPattern.java#L40)
  * Subarray Sum Calculations: [`java/maxSumAcrsAllSubArr.java`](./java/maxSumAcrsAllSubArr.java)

---

## 6. Greedy Choice and Resets (Kadane's Algorithm Concept)

* **Concept:** If a running prefix sum becomes negative, it will only drag down the sum of any future subarray that includes it. It is optimal to reset the running sum to `0`.
* **Key Characteristics:**
  * **Local Greedy Choice:** Guarantees global maximum contiguous sum in a single $O(N)$ pass.
  * **Space Complexity:** $O(1)$ space complexity.
* **Source Code References:**
  * Kadane Note: [`Concepts/KadaneAndArrays.md`](./Concepts/KadaneAndArrays.md)
  * Maximum Subarray: [`java/maximumSubArray.java`](./java/maximumSubArray.java)
  * Stock Trading Greedy: [`java/buyAndSellStock.java`](./java/buyAndSellStock.java)

---

## 7. Two Pointers / Search Space Reduction (Sorted Arrays)

* **Concept:** If `arr[L] + arr[R] > target`, increasing `L` only makes the sum larger. We must decrease `R`. This selectively prunes an entire row of combinations in $O(1)$ time.
* **Key Characteristics:**
  * Reduces search space from $O(N^2)$ to $O(N)$.
  * Requires sorted input sequences.
* **Source Code References:**
  * Pointer Pattern Guide: [`Concepts/PointerPattern.md`](./Concepts/PointerPattern.md)
  * Two Sum (C): [`Algos/twoSum.c`](./Algos/twoSum.c)
  * Twin Pointers (Python): [`Algos/twinPointers.py`](./Algos/twinPointers.py)
  * Two Sum (Java): [`java/twoSum.java`](./java/twoSum.java)
  * Three Sum (Java): [`java/threeSum.java`](./java/threeSum.java)
  * Sort Colors / Dutch National Flag: [`java/sortColors.java`](./java/sortColors.java)

---

## 8. Symmetry (Palindromes)

* **Concept:** Every palindrome mirrors around its center. A string can be expanded from `(2N - 1)` possible centers (a character, or between two characters) rather than checking all $O(N^2)$ subarrays.
* **Key Characteristics:**
  * Reduces palindrome verification from $O(N^3)$ to $O(N^2)$.
  * Avoids auxiliary memory allocation by expanding outward from center indices.
* **Source Code References:**
  * Numeric Palindrome: [`Programs/palindromeNumber.c`](./Programs/palindromeNumber.c)
  * String Center Expansion: [`java/problems/PointerPattern.java`](./java/problems/PointerPattern.java)

---

## 9. Fast & Slow Pointers (Floyd's Cycle Finding)

* **Concept:** Moving one pointer at 1x speed and another at 2x speed will mathematically guarantee they meet if a cycle exists, without needing extra memory for a Hash Set.
* **Key Characteristics:**
  * **Time Complexity:** $O(N)$ linear time traversal.
  * **Space Complexity:** $O(1)$ space complexity (eliminates hash set storage).
* **Source Code References:**
  * Pattern Guide: [`Concepts/PointerPattern.md`](./Concepts/PointerPattern.md)
  * Cycle Detection: [`java/problems/PointerPattern.java`](./java/problems/PointerPattern.java)
  * Linked List Base: [`java/LinkedList.java`](./java/LinkedList.java)

---

## 10. Cancellation (Boyer-Moore Majority Vote)

* **Concept:** If a single element constitutes strictly more than half the array, simultaneously discarding any two *distinct* elements will preserve that element's majority status in the remaining array.
* **Key Characteristics:**
  * **Time Complexity:** $O(N)$ single-pass algorithm.
  * **Space Complexity:** $O(1)$ space complexity.
* **Source Code Reference:** [`java/majorityElements.java`](./java/majorityElements.java)

---

## 11. Binary Search Paradigm

* **Concept:** Search space reduction on sorted ranges.
* **Key Characteristics:**
  * **Time Complexity:** $O(\log N)$.
  * **Midpoint Overflow Protection:** `low + (high - low) / 2`.
* **Source References:**
  * Binary Search Guide: [`Concepts/BinarySearch.md`](./Concepts/BinarySearch.md)
  * Linear Search Baseline: [`Basic/linearSearch.c`](./Basic/linearSearch.c)
  * Root Search: [`Algos/findRoot.c`](./Algos/findRoot.c)

---

## 12. C Language Fundamentals & Memory Mechanics

* **Pointers & Pointer Arithmetic:** [`Basic/pointer.c`](./Basic/pointer.c), [`Basic/pointerArithmetic.c`](./Basic/pointerArithmetic.c)
* **Function Pointers:** [`Basic/pointerFunction.c`](./Basic/pointerFunction.c), [`Basic/pointerToFunction.c`](./Basic/pointerToFunction.c)
* **Concurrency (Mutex & Semaphore):** [`Basic/mutex.c`](./Basic/mutex.c), [`Basic/semaphore.c`](./Basic/semaphore.c)
* **Data Types & Constants:** [`Basic/dataType.c`](./Basic/dataType.c), [`Basic/constants.c`](./Basic/constants.c)
* **Signals & Alarms:** [`Basic/alarm.c`](./Basic/alarm.c)

---

## 13. String Manipulation in C

* **String Creation & Basics:** [`Strings/firstString.c`](./Strings/firstString.c)
* **Buffer Input & I/O:** [`Strings/inputString.c`](./Strings/inputString.c)
* **String Mutation & Manipulation:** [`Strings/modifyString.c`](./Strings/modifyString.c)
* **Escape Characters:** [`Basic/escapeChars.c`](./Basic/escapeChars.c)

---

## 14. Classic Mathematical Programs

* **Primality Check:** [`Programs/prime.c`](./Programs/prime.c)
* **Fibonacci Numbers:** [`Programs/fibonacci.c`](./Programs/fibonacci.c)
* **Armstrong Numbers:** [`Programs/armstrong.c`](./Programs/armstrong.c)
* **Matrix Multiplication:** [`Programs/matrixMuliplication.c`](./Programs/matrixMuliplication.c)
* **Variable Swapping:** [`Programs/swap.c`](./Programs/swap.c), [`Basic/swap.c`](./Basic/swap.c)

---

## 15 Kadane's Algorithm & Essential Array Techniques
* **When to Use:** Finding contiguous subarray with largest sum in $O(N)$ time and $O(1)$ space.
* **Decision Rule:**
  ```text
  currentSum = max(nums[i], currentSum + nums[i])
  maxSum = max(maxSum, currentSum)
  ```
* **Golden Mental Model:** *"If the sum accumulated so far drops below zero, it becomes a burden for future subarrays. Reset current sum!"*
* **Prefix Sum & Difference Arrays:**
  * Prefix Sum: `prefix[i] = prefix[i-1] + nums[i-1]`. Range Sum: `prefix[R + 1] - prefix[L]`.
  * Subarray Sum Equals K: Store running prefix sum frequencies in `HashMap`. At index `i`, check if `(runningSum - K)` exists.
* **Boyer-Moore Majority Voting:**
  * Target: Element appearing $> \lfloor N/2 \rfloor$ times in $O(N)$ time, $O(1)$ space.
  * Maintain `candidate` and `count`: `count == 0 ? candidate = nums[i] : (nums[i] == candidate ? count++ : count--)`.
  * Golden Mental Model: *"The majority element cancels out all minority elements combined and still remains at the end."*

---

## 16 Sliding Window Pattern
* **Fixed-Size Window (Size $K$):**
  * Add incoming head `nums[i]`, subtract outgoing tail `nums[i-K]`.
  * Golden Mental Model: *"Add the new head, drop the old tail."*
* **Dynamic-Size Window:**
  * Maintain `left` and `right`. Expand `right++` to add elements.
  * For Longest Window: While invalid, shrink `left++`. Record max size.
  * For Shortest Window: While valid, record min size, shrink `left++`.
  * Golden Mental Model: *"Expand right to seek solutions; shrink left to restore invariants or optimize size."*
* **Window Data Structures:**
  * Primitive `sum`: $O(1)$ add/subtract.
  * Fixed Array `int[128]`: Character frequency.
  * `HashSet`: Uniqueness check.
  * `Monotonic Deque`: Subarray max/min in $O(1)$ amortized.

---

## 17 Pointer Pattern Master Guide
* **Opposite-Direction Pointers:** `left = 0`, `right = N - 1` on sorted arrays. Advance pointer that is holding back the target/metric.
* **Same-Direction Pointers (Reader & Writer):** `fast` (Reader) explores ahead, `slow` (Writer) builds output array in-place.
* **Three-Way Partitioning (Dutch National Flag):**
  * `low = 0`, `mid = 0`, `high = N - 1`.
  * `nums[mid] == 0`: Swap(`low`, `mid`), `low++`, `mid++`.
  * `nums[mid] == 1`: `mid++`.
  * `nums[mid] == 2`: Swap(`mid`, `high`), `high--`.
* **Fast & Slow Pointers (Floyd's Cycle):** `slow` moves $1\times$, `fast` moves $2\times$. Meet point confirms cycle.
* **Trapping Rain Water:** Maintain `leftMax` and `rightMax`. Advance pointer with smaller max.
* **Interval Intersections:** Overlap `start = max(A[i][0], B[j][0])`, `end = min(A[i][1], B[j][1])`. Advance pointer whose interval ends earlier (`A[i][1] < B[j][1] ? i++ : j++`).

---

## 18 Binary Search Master Guide
* **Classical Binary Search:** `mid = left + (right - left) / 2`. `left <= right`.
* **Lower / Upper Bounds:** For lower bound (first element $\ge$ target), when `nums[mid] >= target`, record candidate `mid` and search left (`right = mid - 1`).
* **Search on Solution Space ("Search on Answer"):** Binary search on monotonic answer range `[min_possible, max_possible]` using helper function `isFeasible(mid)`.