# Binary Search Pattern — Master Conceptual Guide

**Binary Search** is the ultimate $O(\log N)$ search strategy. It operates by halving the search space at every step, requiring a **monotonic property** (sorted order or a predicate that evaluates to `[false, false, ..., true, true]`).

---

## 1. Classical Binary Search (Exact Value Search)

### 🎯 When to Use
- Looking for a specific target value in a sorted array.

### 🔑 Conceptual Key & Template
```
left = 0, right = N - 1
while (left <= right):
    mid = left + (right - left) / 2
    if nums[mid] == target: return mid
    else if nums[mid] < target: left = mid + 1
    else: right = mid - 1
```

### 💡 Overflow Prevention
- Use `mid = left + (right - left) / 2` instead of `(left + right) / 2` to prevent integer overflow when `left + right > Integer.MAX_VALUE`.

---

## 2. Boundary Search (Lower & Upper Bounds / First & Last Occurrence)

### 🎯 When to Use
- Array contains duplicates, and you need to find the **first** or **last** position of a target value.
- Finding insertion points (e.g., `C++ std::lower_bound` or `std::upper_bound`).

### 🔑 Conceptual Decision Rule
- **Lower Bound (First Occurrence / First element $\ge$ target)**:
  - When `nums[mid] >= target`: Do NOT return immediately! Record `mid` as a candidate and keep searching to the left (`right = mid - 1`).
- **Upper Bound (Last Occurrence / First element $>$ target)**:
  - When `nums[mid] <= target`: Keep searching to the right (`left = mid + 1`).

```
Array: [1, 2, 4, 4, 4, 6, 7], Target: 4
- Lower Bound index: 2 (first 4)
- Upper Bound index: 5 (first element > 4)
```

---

## 3. Binary Search on Solution Space ("Search on Answer")

### 🎯 When to Use
- You are NOT searching in an array, but searching for an optimal integer answer (e.g., minimum capacity, maximum minimum distance, minimum eating speed).
- The solution space range `[min_possible, max_possible]` is monotonic:
  - If answer $X$ is feasible $\implies$ all $Y > X$ are also feasible.

### 🔑 Conceptual Key
1. Define search range: `low = min_possible`, `high = max_possible`.
2. Write a helper function `boolean isFeasible(int candidate)`:
   - Evaluates if `candidate` satisfies all problem constraints in $O(N)$ time.
3. Apply binary search:
   - If `isFeasible(mid)` is `true`: `mid` might be our answer, but try to find a smaller/better answer $\rightarrow$ `high = mid - 1` (or `mid`).
   - If `isFeasible(mid)` is `false`: `mid` is too small $\rightarrow$ `low = mid + 1`.

---

## 4. Summary Matrix & Loop Invariants

| Problem Type | Loop Condition | Right Update | Left Update | Return Value |
| :--- | :--- | :--- | :--- | :--- |
| **Exact Match** | `left <= right` | `right = mid - 1` | `left = mid + 1` | `mid` or `-1` |
| **First Occurrence** | `left <= right` | `right = mid - 1` (save ans) | `left = mid + 1` | `ans` |
| **Search on Answer** | `low <= high` | `high = mid - 1` | `low = mid + 1` | `ans` |

---

## ⚠️ Common Pitfalls
1. **Infinite Loops**: Ensure `left` or `right` is strictly updated (`mid + 1` or `mid - 1`) when using `left <= right`.
2. **Integer Overflow**: Always calculate `mid` safely.
3. **Off-by-One at Boundaries**: Check boundary conditions when `target` is smaller than `nums[0]` or larger than `nums[N-1]`.
