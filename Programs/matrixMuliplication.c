#include <stdio.h>
#include <stdlib.h>

int multiply(int c, int d) {
    return c * d;
}

// Matrix multiplication: result = a * b
int **multiplyMatrix(int a[][2], int b[][3], int m, int n, int p) {
    int **result = malloc(m * sizeof(int *));
    for (int i = 0; i < m; i++) {
        result[i] = malloc(p * sizeof(int));
        for (int j = 0; j < p; j++) {
            result[i][j] = 0;
            for (int k = 0; k < n; k++) {
                result[i][j] += multiply(a[i][k], b[k][j]);
            }
        }
    }
    return result;
}

int main() {
    int m = 3, n = 2, p = 3; // a is m x n, b is n x p
    int a[3][2] = {
        {1, 2},
        {3, 4},
        {5, 6}
    };

    int b[2][3] = {
        {1, 2, 4},
        {5, 3, 6}
    };

    int **result = multiplyMatrix(a, b, m, n, p);

    printf("Result of matrix multiplication:\n");
    for (int i = 0; i < m; i++) {
        for (int j = 0; j < p; j++) {
            printf("%d ", result[i][j]);
        }
        printf("\n");
        free(result[i]);  // Free each row
    }
    free(result);         // Free the array of pointers

    return 0;
}
