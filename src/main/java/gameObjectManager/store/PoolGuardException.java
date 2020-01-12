package gameObjectManager.store;

/**
 * Exception class for {@link PoolGuard}
 */
public class PoolGuardException extends Exception {
    /**
     * PoolGuardException constructor
     */
    public PoolGuardException() {
    }

    /**
     * PoolGuardException constructor
     *
     * @param message an error message
     */
    public PoolGuardException(final String message) {
        super(message);
    }

    /**
     * PoolGuardException constructor
     *
     * @param message an error message
     * @param cause   exception cause
     */
    public PoolGuardException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
