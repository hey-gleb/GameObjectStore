package gameObjectManager.store;

/**
 * Exception class for {@link IPool}
 */
public class PoolException extends Exception {
    /**
     * PoolException constructor
     */
    public PoolException() {
    }

    /**
     * PoolException constructor
     * @param message an error message
     */
    public PoolException(final String message) {
        super(message);
    }

    /**
     * PoolException constructor
     * @param message an error message
     * @param cause exception cause
     */
    public PoolException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
