package language.exception;

/**
 * Custom UNCHECKED exception (RuntimeException) representing invalid state transitions.
 * Practice Focus: Unchecked Exception contract, Runtime state enforcement.
 */
public class InvalidTransactionStateException extends RuntimeException {
    public InvalidTransactionStateException(String message) {
        super(message);
    }

    public InvalidTransactionStateException(String message, Throwable cause) {
        super(message, cause);
    }
}
