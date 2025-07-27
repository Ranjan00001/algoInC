#include <stdio.h>
#include <stdlib.h>

#define TABLE_SIZE 1000  // A fixed size hash table

// Hash table entry
typedef struct {
    int key;     // Value from the array
    int value;   // Index of the value
    int isFilled;
} HashEntry;

// Hash table
HashEntry hashTable[TABLE_SIZE];

// Simple hash function (handles negative keys too)
int hash(int key) {
    return abs(key) % TABLE_SIZE;
}

// Insert key-value pair into hash table
void insert(int key, int value) {
    int idx = hash(key);
    while (hashTable[idx].isFilled) {
        idx = (idx + 1) % TABLE_SIZE;
    }
    hashTable[idx].key = key;
    hashTable[idx].value = value;
    hashTable[idx].isFilled = 1;
}

// Search for a key in the hash table, return index or -1
int search(int key) {
    int idx = hash(key);
    int start = idx;
    while (hashTable[idx].isFilled) {
        if (hashTable[idx].key == key) {
            return hashTable[idx].value;
        }
        idx = (idx + 1) % TABLE_SIZE;
        if (idx == start) break;  // Prevent infinite loop
    }
    return -1;
}

// Pair struct to return result
typedef struct {
    int x;
    int y;
} NumPair;

NumPair twoSum(int* array, int size, int target) {
    NumPair pair = {-1, -1};

    // Clear the hash table
    for (int i = 0; i < TABLE_SIZE; i++) {
        hashTable[i].isFilled = 0;
    }

    for (int i = 0; i < size; i++) {
        int complement = target - array[i];
        int index = search(complement);
        if (index != -1) {
            pair.x = index;
            pair.y = i;
            return pair;
        }
        insert(array[i], i);
    }

    return pair; // No valid pair found
}

int main() {
    int array[] = {1, 2, 3, 4, 5};
    int size = sizeof(array) / sizeof(array[0]);
    int target = 8;

    NumPair result = twoSum(array, size, target);

    if (result.x != -1 && result.y != -1) {
        printf("Indices: %d, %d\n", result.x, result.y);
    } else {
        printf("No valid pair found.\n");
    }

    return 0;
}
