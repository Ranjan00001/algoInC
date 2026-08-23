# Two-Pointer Pattern — Master Conceptual Guide

The **Two-Pointer technique** is a fundamental algorithmic pattern used to optimize search, partition, and array manipulation problems from $O(N^2)$ brute-force solutions down to $O(N)$ linear time complexity with $O(1)$ space.

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

## 4. Summary Matrix for Quick Revision

| Pattern | Pointer Setup | Advance Condition | Key Invariant |
| :--- | :--- | :--- | :--- |
| **Two Sum Sorted** | `left=0`, `right=N-1` | `left++` if sum < target; `right--` if sum > target | Search space bounded by sorted order |
| **Container Water** | `left=0`, `right=N-1` | Move pointer with smaller height | Greedily attempt to fix bottleneck height |
| **Remove Duplicates** | `slow=1`, `fast=1` | `nums[fast] != nums[slow-1] ? write & slow++ : fast++` | `nums[0..slow-1]` is unique |
| **Sort Colors** | `low=0`, `mid=0`, `high=N-1` | `mid` inspects; swap with `low` or `high` | 3 contiguous partitions maintained |

---

## ⚠️ Common Pitfalls & Edge Cases
1. **Index Out of Bounds**: Always ensure `left < right` or `fast < N`.
2. **Duplicate Handling**: In 3Sum or similar pair problems, remember to skip identical values (`while (left < right && nums[left] == nums[left+1]) left++`) to avoid duplicate output tuples.
3. **Mid Pointer Advancement in DNF**: When swapping `nums[mid]` with `nums[high]`, do NOT increment `mid` immediately because the swapped element from `high` is unexamined and must be inspected next!
