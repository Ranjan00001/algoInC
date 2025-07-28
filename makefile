# Compiler and compiler flags
CC = gcc
CFLAGS = -Wall -Iheader      # -Wall: show all warnings, -Iheader: add 'header/' to include path

# Find all helper .c files and generate corresponding .o file names
HELPER_SRCS := $(wildcard helpers/*.c)       # Get all .c files in helpers/
HELPER_OBJS := $(HELPER_SRCS:.c=.o)          # Replace .c with .o to get object files

# Name of the static library
STATIC_LIB = libhelpers.a

# Default target (what runs when you just type `make`)
all: $(STATIC_LIB)            # Build the static library by default

# Rule to create the static library from all helper object files
$(STATIC_LIB): $(HELPER_OBJS)
	ar rcs $@ $^              # $@ = target (libhelpers.a), $^ = dependencies (.o files)

# Rule to compile each .c file in helpers/ into a .o file
helpers/%.o: helpers/%.c
	$(CC) $(CFLAGS) -c $< -o $@  # $< = source .c file, $@ = output .o file

# Example program that uses the static library
insertionSort: Algos/insertionSort.c $(STATIC_LIB)
	$(CC) $(CFLAGS) $< -L. -lhelpers -o $@.out  # Link insertionSort.c with libhelpers.a

# Generic rule: if you type `make <name>`, it looks for Algos/<name>.c
%: Algos/%.c $(STATIC_LIB)
	$(CC) $(CFLAGS) $< -L. -lhelpers -o $@.out

# Clean rule to remove all generated files
clean:
	rm -f helpers/*.o *.a insertionSort    # Remove object files, static lib, and output binary
