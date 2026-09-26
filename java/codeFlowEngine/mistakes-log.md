# 📋 Code Flow Engine - Interview Review & Mistakes Log

This document tracks implementation mistakes, root causes, line-number references, and clean-code design principles identified during our interview review sessions.

---

## 💡 Key Concept: Fixed Sliding Window vs. Kadane's Algorithm

| Feature | Fixed Sliding Window ($K$) | Kadane's Algorithm | Variable 2-Pointer Window |
| :--- | :--- | :--- | :--- |
| **Window Size** | **Fixed** at length $K$ | **Variable** ($1 \dots N$) | **Variable** (Dynamic shrink/expand) |
| **Problem Type** | Max/Min sum of window size $K$ | Max sum subarray of any size | Shortest/Longest subarray meeting target |
| **Formula** | `runningSum += arr[i] - arr[i-K]` | `currSum = max(arr[i], currSum + arr[i])` | Expand `end`, shrink `start` via `while` |
| **Reset Rule** | ❌ **NEVER reset `runningSum` to 0** | ✅ Reset `currSum = 0` if negative | ❌ No reset; shrink `start` dynamically |

### How to spot the subtlety next time:
1. **Ask:** *Is the window size locked to $K$, or can it shrink/expand?*
2. If window size is **locked to $K$**, every window must contain exactly $K$ contiguous elements. Resetting `runningSum = 0` drops the elements that left the back of the window, corrupting future additions!
3. If window size is **variable**, ask: *Is it an optimization (Max Sum) or a Condition Match ($\ge$ Target)?*
   - **Max Sum:** Kadane's.
   - **Condition Match:** Standard 2-Pointer (`for(end)` + `while(valid)`).

---

## 🛠️ Module 1: `ResizableArray.java` Review

| File & Line | Mistake / Bug Description | Root Cause | Clean Fix |
| :--- | :--- | :--- | :--- |
| [`ResizableArray.java:L42`](./codeFlowEngine/ResizableArray.java#L42) | `removeAt(index)` shifted elements but forgot `size--`. | Stale size invariant: array still reported original size after element deletion. | Added `size--;` after array shift. |
| [`ResizableArray.java:L29`](./codeFlowEngine/ResizableArray.java#L29) | Bounds check `if (index >= size)` missed `index < 0`. | Unchecked lower bound allowed negative index to bypass custom exception. | Updated to `if (index < 0 \|\| index >= size)`. |
| [`ResizableArray.java:L21`](./codeFlowEngine/ResizableArray.java#L21) | `System.arraycopy(data, 0, temp, 0, capacity)` threw `ArrayIndexOutOfBoundsException`. | `capacity` was doubled before copy; tried to copy 16 elements from an array of size 8. | Changed copy length to `size` or used `Arrays.copyOf(data, capacity)`. |
| [`ResizableArray.java:L8-10`](./codeFlowEngine/ResizableArray.java#L8-L10) | Package-private internal backing array `int[] data`. | Encapsulation breach: external classes could directly overwrite `list.data = null`. | Mark backing fields `private`. |

---

## 🛠️ Module 2: `WindowMetrics.java` Review

| File & Line | Mistake / Bug Description | Root Cause | Clean Fix |
| :--- | :--- | :--- | :--- |
| [`WindowMetrics.java:L27-31`](./codeFlowEngine/WindowMetrics.java#L27-L31) | `maxSubArraySum`: reset `runningSum = 0` when `runningSum <= 0`. | Confused Fixed Window Size $K$ with Kadane's Algorithm. | Removed `if (runningSum > 0) ... else runningSum = 0` reset. Window must slide strictly by `+ arr[i] - arr[i-k]`. |
| [`WindowMetrics.java:L56-75`](./codeFlowEngine/WindowMetrics.java#L56-L75) | `smallestSubArraySum`: Custom `boolean reachedSum` state-machine loop. | Over-engineered single `while` loop with boolean toggles; double-added elements causing output `1` for `[2,1,5,2,3,2]`, target `7`. | Replaced with Canonical 2-Pointer pattern: [`WindowMetrics.java:L79-96`](./codeFlowEngine/WindowMetrics.java#L79-L96) `for (int end = 0; ...)` with nested `while (runningSum >= target)`. |

---

## 🛠️ Module 3: `LogSanitizer.java` & `SubstringAnalyzer.java` Review

| File & Line | Mistake / Bug Description | Root Cause | Clean Fix |
| :--- | :--- | :--- | :--- |
| [`LogSanitizer.java:L6`](./LogSanitizer.java#L6) | `assert str != null;` for null validation. | In Java production runtime, `assert` is disabled by default unless `-ea` flag is passed. | Use explicit `if (str == null) return false;` or `Objects.requireNonNull`. |
| [`LogSanitizer.java:L9-10`](./LogSanitizer.java#L9-L10) | `Character.isAlphabetic` filtered out digits. | Non-letter characters like numbers (`0-9`) were skipped in palindrome check. | Replaced with `Character.isLetterOrDigit` for alphanumeric support. |
| [`LogSanitizer.java:L12`](./LogSanitizer.java#L12) | Initial `str = str.toLowerCase()` heap allocation. | Created a new $O(N)$ string object on the heap prior to loop. | Replaced with $O(1)$ space on-the-fly character comparison: `Character.toLowerCase(str.charAt(start))`. |
| [`SubstringAnalyzer.java:L6-10`](./SubstringAnalyzer.java#L6-L10) | `end = str.length()` initialization & $O(N^3)$ operations. | Out-of-bounds `substring(end)` crash + linear string search/replace inside loop. | Replace with $O(N)$ 2-Pointer sliding window + ASCII frequency array `lastSeen[256]`. |

---

## 🛠️ Module 4: `ExecutionHistory.java` & `CycleDetector.java` Review

| File & Line | Mistake / Bug Description | Root Cause | Clean Fix |
| :--- | :--- | :--- | :--- |
| [`ExecutionHistory.java:L62`](./ExecutionHistory.java#L62) | `removeFront()` left `tail` pointing to deleted node when `size == 0`. | Stale tail reference memory leak after emptying list. | Added `if (size == 0) tail = null;`. |
| [`ExecutionHistory.java:L73`](./ExecutionHistory.java#L73) | `reverse()` loop `while (current.hasNext())` skipped last node. | `hasNext()` checked `current.next != null`, stopping before rewiring the final tail node. | Updated to `while (current != null)` and set `head = prev`. |
| [`ExecutionHistory.java:L92`](./ExecutionHistory.java#L92) | `append()` called `addFront()` instead of `addLast()`. | Appending inserted commands at head instead of tail. | Changed to `history.addLast(command);`. |

---

## 🛠️ Module 5: Stacks & Queues (`SyntaxValidator.java`, `MonotonicStack.java`, `CircularQueue.java`, `SlidingWindowMax.java`) Review

| File & Line | Mistake / Bug Description | Root Cause | Clean Fix |
| :--- | :--- | :--- | :--- |
| [`SyntaxValidator.java:L64`](./SyntaxValidator.java#L64) | `brackets.substring(i)` returned remaining full string instead of 1 char. | `"[{(".contains("()")` evaluated to `false`, skipping bracket pushes. | Replace with `brackets.substring(i, i + 1)` or `String.valueOf(brackets.charAt(i))`. |
| [`SyntaxValidator.java:L66`](./SyntaxValidator.java#L66) | `!s.pop().equals(brackets.charAt(i))` compared popped `'('` directly with `')'`. | Opening bracket `'('` was compared with closing `')'` instead of its pair. | Use `!s.pop().equals(getPairBracket(brackets.charAt(i)))`. |
| [`MonotonicStack.java:L12`](./MonotonicStack.java#L12) | `(char) s.top()` cast `Integer` to `Character`. | Threw `ClassCastException` at runtime when unboxing integer indices. | Cast to `(Integer) s.top()`. |
| [`CircularQueue.java:L40`](./CircularQueue.java#L40) | `isFull()` checked `head == tail && size != 0`. | Returned `true` when `size == 1` in an unbounded linked queue. | Comment out `isFull()` for linked queues or implement array-based circular queue `(rear + 1) % cap`. |
| [`SlidingWindowMax.java:L10`](./SlidingWindowMax.java#L10) | `while (i < array.length)` loop lacked `i++`. | Infinite loop froze JVM execution. | Add `i++;` inside loop and use `Deque<Integer>` for $O(N)$ sliding window max. |

---

## ✨ Rules for Writing Clean, Simple Code

1. **Use Canonical Algorithmic Patterns:**
   - Don't reinvent loop control structures with boolean flags (`reachedSum`) when standard patterns (e.g. 2-pointer `for` + `while`) exist.
2. **Maintain Strict Invariants:**
   - If a variable represents `size`, update it *immediately* when elements are removed. Reset `tail = null` when empty.
3. **Keep Variable Window & Fixed Window Logic Distinct:**
   - Fixed $K$: `runningSum += arr[i] - arr[i-K]`.
   - Variable Window: `for (end)` expands, `while (valid)` shrinks `start`.
4. **Optimize Heap Space:**
   - Avoid creating temporary String objects when on-the-fly primitive comparisons (`Character.toLowerCase`) achieve $O(1)$ auxiliary space.
5. **Floyd's 2-Phase Rule:**
   - Phase 1 (Detect Cycle): `slow` moves 1 step, `fast` moves 2 steps.
   - Phase 2 (Find Cycle Start): `slow = head`, then BOTH move **1 step at a time** until collision.
