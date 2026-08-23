# Kadane's Algorithm & Essential Array Techniques — Master Conceptual Guide

---

## 1. Kadane’s Algorithm (Maximum Subarray Sum)

### 🎯 When to Use
- Finding the contiguous subarray within a 1D numeric array which has the **largest sum** in $O(N)$ time and $O(1)$ space.

### 🔑 Conceptual Decision Rule
At each element `nums[i]`, decide whether to:
1. **Extend** the existing subarray sum (`currentSum + nums[i]`).
2. **Start fresh** with `nums[i]` alone (discarding negative accumulated prefix).

```
currentSum = max(nums[i], currentSum + nums[i])
maxSum = max(maxSum, currentSum)
```

### 💡 Golden Mental Model
> *"If the sum accumulated so far drops below zero, it becomes a burden for future subarrays. Reset current sum!"*

---

## 2. Prefix Sum & Difference Arrays

### 🎯 When to Use
- **Prefix Sum**: Frequently computing sum of contiguous elements in range `[L, R]` in $O(1)$ time after $O(N)$ preprocessing.
- **Difference Array**: Range updates (add $V$ to all elements in range `[L, R]`) in $O(1)$ time.

### 🔑 Conceptual Decision Rules
- **Prefix Sum Array**: `prefix[i] = prefix[i-1] + nums[i-1]`.
  - $\text{Sum}(L, R) = \text{prefix}[R + 1] - \text{prefix}[L]$.
- **Subarray Sum Equals K**:
  - Store running prefix sum frequencies in a `HashMap<PrefixSum, Frequency>`.
  - At each index, check if `(runningSum - K)` exists in map!

---

## 3. Boyer-Moore Majority Voting Algorithm

### 🎯 When to Use
- Finding the majority element that appears **more than $\lfloor N/2 \rfloor$ times** in $O(N)$ time and $O(1)$ space.

### 🔑 Conceptual Decision Rule
Maintain a `candidate` and a `count`:
1. If `count == 0`, set `candidate = nums[i]`.
2. If `nums[i] == candidate`, `count++`.
3. If `nums[i] != candidate`, `count--`.

### 💡 Golden Mental Model
> *"The majority element cancels out all minority elements combined and still remains at the end."*

---

## 4. Summary Matrix

| Algorithm | Main Idea | Time Complexity | Space Complexity |
| :--- | :--- | :--- | :--- |
| **Kadane's Algorithm** | Local reset when `currentSum < 0` | $O(N)$ | $O(1)$ |
| **Prefix Sum** | $O(1)$ range sum query | $O(N)$ preproc, $O(1)$ query | $O(N)$ |
| **Boyer-Moore Voting** | Cancellation mechanism for majority | $O(N)$ | $O(1)$ |
