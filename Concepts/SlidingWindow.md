# Sliding Window Pattern — Master Conceptual Guide

The **Sliding Window** technique converts $O(N^2)$ or $O(N^3)$ nested loop operations on contiguous subarrays or substrings into efficient $O(N)$ linear time algorithms by reusing work from the previous window state.

---

## 1. Fixed-Size Sliding Window

### 🎯 When to Use
- Problem specifies a contiguous window of **exact fixed size $K$** (e.g., maximum sum of subarray of size $K$, average of all subarrays of size $K$).

### 🔑 Conceptual Decision Rule
1. Compute the sum/metric of the first window `[0 ... K-1]`.
2. Slide the window right one element at a time from `i = K` to `N - 1`:
   - **Add** the incoming element: `windowSum += nums[i]`
   - **Subtract** the outgoing element: `windowSum -= nums[i - K]`
   - Update `maxSum = max(maxSum, windowSum)`

```
Window size K = 3:
[ 1  3 -1 ] -3  5  3  6  7   -> sum = 3
  1 [ 3 -1  -3 ] 5  3  6  7   -> sum = 3 + (-3) - (1) = -1
```

---

## 2. Dynamic-Size Sliding Window (Flexible Bounds)

### 🎯 When to Use
- Problem asks for the **longest** or **shortest** contiguous subarray/substring that satisfies a condition (e.g., longest substring without repeating characters, smallest subarray with sum $\ge S$).

### 🔑 Conceptual Decision Rule
Maintain two pointers `left` and `right` defining a dynamic window `[left, right]`:

1. **Expanding Stage**: Increment `right++` to include new elements into window state.
2. **State Invariant Check**:
   - **For Longest Window**: While window state is **invalid** (e.g., contains duplicate chars or sum too big), shrink by `left++`. Record max length when valid.
   - **For Shortest Window**: While window state is **valid** (e.g., sum $\ge S$), record min length and shrink by `left++` to see if a smaller valid window exists.

```
Longest Substring Without Repeating Chars:
- Right pointer moves forward, adding chars to frequency map/set.
- If char frequency > 1 (duplicate!):
  Move left pointer rightward, decrementing frequencies until duplicate is removed.
- Record maxWindowLen = max(maxWindowLen, right - left + 1).
```

---

## 3. Data Structures Used with Sliding Window

| State Requirement | Best Data Structure | Operations & Time |
| :--- | :--- | :--- |
| **Sum / Count Tracking** | Simple Primitive Variable (`int sum`) | $O(1)$ add/subtract |
| **Character Frequency** | Fixed Array `int[26]` or `int[128]` | $O(1)$ lookup/update |
| **Element Uniqueness** | `HashSet` | $O(1)$ insertion/deletion |
| **Subarray Max/Min** | `Monotonic Deque` (Sliding Window Maximum) | $O(1)$ amortized max access |

---

## 💡 Golden Mental Models

> **Fixed Window**: *"Add the new head, drop the old tail."*

> **Dynamic Window**: *"Expand `right` to seek solutions; shrink `left` to restore invariants or optimize size."*

---

## ⚠️ Common Pitfalls
1. **Window Size Calculation**: The size of window `[left, right]` (inclusive) is `right - left + 1`.
2. **Forgetting to Update State on Shrink**: When advancing `left++`, remember to update your sum/frequency map *before* incrementing `left`.
