# Pointer Pattern — Master Conceptual Guide

The **Pointer technique** is a fundamental algorithmic pattern used to optimize search, partition, dynamic windows, and pointer traversal from $O(N^2)$ brute-force solutions down to $O(N)$ linear time complexity with $O(1)$ auxiliary space.

---

## 1. Opposite-Direction Pointers (Boundary Squeezing)

### 🎯 When to Use
- You are searching for a pair or container boundary in a **sorted array** or contiguous sequence.
- You want to find an optimal pair that maximizes or minimizes a calculation (e.g., area, target sum).

### 🔑 Conceptual Decision Rule
Initialize `left = 0` and `right = N - 1`. At each step, evaluate the metric/bottleneck:

1. **Target Sum in Sorted Array**:
   - $\text{currentSum} < \text{target} \implies$ Value is too small $\rightarrow$ `left++` (increases sum).
   - $\text{currentSum} > \text{target} \implies$ Value is too large $\rightarrow$ `right--` (decreases sum).
   - $\text{currentSum} == \text{target} \implies$ Match found!

2. **Container With Most Water (Area Bottleneck)**:
   - $\text{Area} = \min(\text{height}[left], \text{height}[right]) \times (right - left)$.
   - Shrinking the width $(right - left)$ always decreases width by 1.
   - **Greedy Rule**: To have any chance of finding a larger area, move the pointer at the **shorter line** (`height[left] < height[right] ? left++ : right--`). Moving the taller line's pointer can *never* increase the area because height remains limited by the shorter line.

### 💡 Golden Mental Model
> *"Which pointer is currently holding back our metric from improving? Advance THAT bottleneck pointer."*

---

## 2. Same-Direction Pointers (Reader & Writer / Fast & Slow)

### 🎯 When to Use
- Array modifications **in-place** without using extra memory (e.g., removing duplicates, moving zeros, filtering elements).
- Linked List cycle detection (Floyd’s Tortoise and Hare).
- Partitioning arrays based on a boolean condition.

### 🔑 Conceptual Decision Rule
Maintain two pointers moving in the same direction:
- **`fast` (Reader)**: Scans through raw input elements sequentially.
- **`slow` (Writer)**: Tracks the next available write position for valid output.

```
[0, 0, 1, 1, 1, 2]
 s  f               -> duplicate found, move fast
 s     f            -> new value found! write to nums[s], then s++
```

- **Rule**:
  - When `nums[fast]` meets the valid criterion (e.g., `nums[fast] != nums[slow - 1]`): write `nums[slow] = nums[fast]` and increment `slow++`.
  - Otherwise, skip writing and simply advance `fast++`.

### 💡 Golden Mental Model
> *"The slow pointer builds the pristine output array in-place, while the fast pointer explores ahead."*

---

## 3. Three-Way Partitioning (Dutch National Flag)

### 🎯 When to Use
- Partitioning an array into 3 distinct regions (e.g., 0s, 1s, 2s or `< pivot`, `== pivot`, `> pivot`) in a single pass $O(N)$ time and $O(1)$ space.

### 🔑 Conceptual Decision Rule
Maintain 3 pointers: `low = 0`, `mid = 0`, `high = N - 1`.
- `[0 ... low - 1]` contains 0s (Red)
- `[low ... mid - 1]` contains 1s (White)
- `[mid ... high]` contains unclassified elements
- `[high + 1 ... N - 1]` contains 2s (Blue)

```
Iteration Step (Inspect element at `mid`):
1. nums[mid] == 0: Swap(nums[low], nums[mid]), low++, mid++
2. nums[mid] == 1: mid++
3. nums[mid] == 2: Swap(nums[mid], nums[high]), high-- (do NOT increment mid!)
```

### 💡 Golden Mental Model
> *"Mid is the explorer. Low pulls zeroes behind it, High pushes twos ahead of it."*

---

## 4. Fast & Slow Pointers (Floyd’s Cycle Detection / Tortoise & Hare)

### 🎯 When to Use
- Detecting cycles in Linked Lists, circular arrays, or sequence transformations.
- Finding the middle element of a Linked List in a single pass.

### 🔑 Conceptual Decision Rule
- **`slow`**: Moves 1 step at a time (`slow = slow.next`).
- **`fast`**: Moves 2 steps at a time (`fast = fast.next.next`).

```
1. Cycle Detection: If fast meets slow (fast == slow), a cycle is present.
2. Cycle Start: Reset slow = head. Advance both slow and fast 1 step at a time until they meet.
3. Midpoint Finding: When fast reaches end (null), slow points to middle node.
```

### 💡 Golden Mental Model
> *"If two runners race on a circular track at different speeds, the faster runner WILL eventually lap and meet the slower runner."*

---

## 5. Dynamic Window Pointers (Expanding & Contracting Window)

### 🎯 When to Use
- Finding contiguous sub-arrays or substrings that satisfy a condition (e.g., max/min length, target sum, at most $k$ distinct elements).

### 🔑 Conceptual Decision Rule
Define dynamic boundary pointers `left` and `right`:
1. **Expand**: Increment `right` to include elements until window condition is met or broken.
2. **Contract**: Increment `left` to shrink window from the left while maintaining or restoring the valid condition.

```
Subarray Sum >= Target:
Expand right -> Add nums[right] to windowSum
While windowSum >= target:
  Update minLength = min(minLength, right - left + 1)
  Subtract nums[left] from windowSum, left++
```

### 💡 Golden Mental Model
> *"Right expands to explore and fulfill requirements; Left contracts to minimize or optimize the window size."*

---

## 6. Multi-Pointer & Trapping Pointers (3Sum / Trapping Rain Water)

### 🎯 When to Use
- **N-Sum Problems (3Sum, 4Sum)**: Reducing $O(N^k)$ brute-force to $O(N^{k-1})$ by fixing outer elements with loops and running Two-Pointers on the inner range.
- **Trapping Rain Water**: Calculating bounded volume between two dynamic height envelopes in $O(N)$ time and $O(1)$ space.

### 🔑 Conceptual Decision Rule
1. **3Sum**: Fix index `i`, then run `left = i + 1` and `right = N - 1` opposite-direction pointers to find `nums[i] + nums[left] + nums[right] == 0`. Skip duplicate values for `i`, `left`, and `right`.
2. **Trapping Rain Water**: Maintain `leftMax` and `rightMax`. At any point, water trapped at `left` or `right` is determined by $\min(\text{leftMax}, \text{rightMax}) - \text{height}[p]$. Advance the pointer with the smaller boundary max.

### 💡 Golden Mental Model
> *"Fix outer dimensions to simplify high-dimensional search into 2D pointer boundaries."*

---

## 7. Two-Array Parallel Scanning & Overlaps (Interval Intersections)

### 🎯 When to Use
- You have two separate sorted arrays or interval lists (`A` and `B`) and need to find common elements, minimum differences, or interval intersections.

### 🔑 Conceptual Decision Rule
Maintain two pointers `i = 0` (for `A`) and `j = 0` (for `B`):
1. **Interval Overlap Condition**:
   - $\text{start} = \max(A[i][0], B[j][0])$
   - $\text{end} = \min(A[i][1], B[j][1])$
   - If $\text{start} \le \text{end}$, an intersection exists: record $[\text{start}, \text{end}]$.

2. **Pointer Advance Rule**:
   - Compare end points: **advance the pointer whose current interval ends first**!
   - `if (A[i][1] < B[j][1]) i++; else j++;`
   - *Why?* The interval that ends earlier cannot possibly intersect with any future intervals in the other list.

### 💡 Golden Mental Model
> *"Determine overlap using max-start & min-end, then discard the interval that expires first."*

---

## 8. Summary Matrix for Quick Revision

| Pattern | Pointer Setup | Advance Condition | Key Invariant |
| :--- | :--- | :--- | :--- |
| **Two Sum Sorted** | `left=0`, `right=N-1` | `left++` if sum < target; `right--` if sum > target | Search space bounded by sorted order |
| **Container Water** | `left=0`, `right=N-1` | Move pointer with smaller height | Greedily attempt to fix bottleneck height |
| **Remove Duplicates** | `slow=1`, `fast=1` | `nums[fast] != nums[slow-1] ? write & slow++ : fast++` | `nums[0..slow-1]` is unique |
| **Sort Colors** | `low=0`, `mid=0`, `high=N-1` | `mid` inspects; swap with `low` or `high` | 3 contiguous partitions maintained |
| **Floyd's Cycle** | `slow=head`, `fast=head` | `slow = slow.next`, `fast = fast.next.next` | Distance gap closes by 1 node each step |
| **Dynamic Window** | `left=0`, `right=0` | Expand `right` to include, shrink `left` to optimize | `[left..right]` tracks valid dynamic range |
| **Trapping Water** | `left=0`, `right=N-1` | Advance pointer with smaller `leftMax`/`rightMax` | Trapped water bounded by current minimum envelope |
| **Interval Intersections** | `i=0`, `j=0` | Advance pointer whose interval ends earlier (`A[i][1] < B[j][1] ? i++ : j++`) | Expired intervals can never overlap future candidates |

---

## Common Pitfalls & Edge Cases
1. **Index Out of Bounds**: Always ensure `left < right` or `fast < N` or `i < A.length` && `j < B.length`.
2. **Duplicate Handling**: In 3Sum or similar pair problems, remember to skip identical values (`while (left < right && nums[left] == nums[left+1]) left++`) to avoid duplicate output tuples.
3. **Mid Pointer Advancement in DNF**: When swapping `nums[mid]` with `nums[high]`, do NOT increment `mid` immediately because the swapped element from `high` is unexamined and must be inspected next!
4. **Window State Maintenance**: Ensure element additions (`right`) and removals (`left`) correctly update window aggregate state (sum, counts, hash tables).
5. **Interval Point Intersections**: When two intervals meet at a single point (e.g., `[1, 5]` and `[5, 8]`), `start == end == 5`, which is a valid closed interval `[5, 5]`.

