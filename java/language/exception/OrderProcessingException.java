package language.exception;

/**
 * Custom CHECKED exception representing recoverable business errors in the order workflow.
 * Practice Focus: Checked Exceptions vs RuntimeExceptions, Exception Chaining, Error Codes.
 */
public class OrderProcessingException extends Exception {
    private final String errorCode;

    public OrderProcessingException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public OrderProcessingException(String errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String toString() {
        return "OrderProcessingException [ErrorCode=" + errorCode + ", Message=" + getMessage() + "]";
    }
}
