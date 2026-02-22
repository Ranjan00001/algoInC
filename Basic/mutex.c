#include <stdio.h>
#include <pthread.h>
#include <unistd.h>

// Shared resource
int counter = 0;

// Mutex declaration
pthread_mutex_t lock;

void* increment_counter(void* arg) {
    // Lock the mutex before entering critical section
    pthread_mutex_lock(&lock);
    
    // Critical section start
    unsigned long i = 0;
    counter += 1;
    printf("Thread %ld has started\n", (long)arg);
    
    for(i=0; i<(0x00FFFFFF);i++); // Simulate some work
    
    printf("Thread %ld has finished\n", (long)arg);
    // Critical section end
    
    // Unlock the mutex after exiting critical section
    pthread_mutex_unlock(&lock);
    
    return NULL;
}

int main() {
    pthread_t t1, t2;

    // Initialize the mutex
    if (pthread_mutex_init(&lock, NULL) != 0) {
        printf("Mutex init failed\n");
        return 1;
    }

    // Create threads
    pthread_create(&t1, NULL, increment_counter, (void*)1);
    pthread_create(&t2, NULL, increment_counter, (void*)2);

    // Wait for threads to finish
    pthread_join(t1, NULL);
    pthread_join(t2, NULL);

    // Destroy the mutex
    pthread_mutex_destroy(&lock);

    return 0;
}
