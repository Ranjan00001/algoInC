#include <stdio.h>
#include <pthread.h>
#include <semaphore.h>
#include <unistd.h>

// Shared resource
int counter = 0;

// Semaphore declaration
sem_t binary_sem;

void* increment_counter(void* arg) {
    // Wait (decrement) the semaphore. 
    // If value is > 0, it proceeds and decrements.
    // If value is 0, it blocks.
    sem_wait(&binary_sem);
    
    // Critical section start
    unsigned long i = 0;
    counter += 1;
    printf("Thread %ld has started\n", (long)arg);
    
    // Simulate some work
    for(i=0; i<(0x00FFFFFF);i++);
    
    printf("Thread %ld has finished\n", (long)arg);
    // Critical section end
    
    // Post (increment) the semaphore
    // Signals that the critical section is free
    sem_post(&binary_sem);
    
    return NULL;
}

int main() {
    pthread_t t1, t2;

    // Initialize the semaphore
    // 0 = pshared (0 means shared between threads of the same process)
    // 1 = initial value (binary semaphore, behaving like a mutex)
    if (sem_init(&binary_sem, 0, 1) != 0) {
        printf("Semaphore init failed\n");
        return 1;
    }

    // Create threads
    pthread_create(&t1, NULL, increment_counter, (void*)1);
    pthread_create(&t2, NULL, increment_counter, (void*)2);

    // Wait for threads to finish
    pthread_join(t1, NULL);
    pthread_join(t2, NULL);

    // Destroy the semaphore
    sem_destroy(&binary_sem);

    return 0;
}
