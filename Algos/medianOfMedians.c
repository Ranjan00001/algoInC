
/*def partition(arr, left, right, pivot_index):
    """
    Partition the array in-place around pivot_index.
    Returns the final index of the pivot.
    """
    pivot_value = arr[pivot_index]
    arr[pivot_index], arr[right] = arr[right], arr[pivot_index]  # Move pivot to end
    store_index = left
    for i in range(left, right):
        if arr[i] < pivot_value:
            arr[i], arr[store_index] = arr[store_index], arr[i]
            store_index += 1
    arr[store_index], arr[right] = arr[right], arr[store_index]  # Move pivot to final place
    return store_index

def median_of_medians_index(arr, left=0, right=None):
    if right is None:
        right = len(arr) - 1

    n = right - left + 1
    if n <= 5:
        # Small array: sort and return median index
        sub = arr[left:right+1]
        sub_sorted = sorted((val, idx) for idx, val in enumerate(sub, start=left))
        median_idx = sub_sorted[n // 2][1]
        return median_idx

    # Step 1: Divide array into groups of 5 and find median indices
    medians = []
    for i in range(left, right + 1, 5):
        group = arr[i:min(i+5, right+1)]
        group_indices = list(range(i, min(i+5, right+1)))
        # Sort group by value and pick median index
        sorted_group = sorted((arr[idx], idx) for idx in group_indices)
        median_idx = sorted_group[len(sorted_group)//2][1]
        medians.append(median_idx)

    # Step 2: Recursively find the median of medians index
    pivot_index = median_of_medians_index(arr, left=min(medians), right=max(medians))

    # Step 3: Partition around pivot index
    pivot_final_index = partition(arr, left, right, pivot_index)

    # Step 4: Determine which side the median lies
    k = left + n // 2
    if pivot_final_index == k:
        return pivot_final_index
    elif pivot_final_index > k:
        return median_of_medians_index(arr, left, pivot_final_index - 1)
    else:
        return median_of_medians_index(arr, pivot_final_index + 1, right)

# Example usage:
arr = [12, 3, 5, 7, 4, 19, 26]
median_idx = median_of_medians_index(arr)
print("Median value:", arr[median_idx])
*/