#include <stdio.h>

int towerOfHanoi(int n, char from_rod, char to_rod, char aux_rod, int count) {
    if (n == 1) {
        printf("Moving disk %d from rod %c to rod %c\n", n, from_rod, to_rod);
        return count + 1;
    }

    count = towerOfHanoi(n - 1, from_rod, aux_rod, to_rod, count);
    printf("Moving disk %d from rod %c to rod %c\n", n, from_rod, to_rod);
    count++;
    count = towerOfHanoi(n - 1, aux_rod, to_rod, from_rod, count);

    return count;
}

int main() {
    int totalMoves = towerOfHanoi(4, 'A', 'B', 'C', 0);
    printf("Total moves: %d\n", totalMoves);
    return 0;
}

/*Pseudocode:
1. Move n-1 disk from source to aux
2. Move the nth disk from source to target
3. Move n - 1 disk from aux to target
*/
