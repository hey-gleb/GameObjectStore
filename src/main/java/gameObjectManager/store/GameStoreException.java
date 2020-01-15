package gameObjectManager.store;

/**
 * Exception class for {@link GameStore}
 */
public class GameStoreException extends Exception {
    /**
     * GameStoreException constructor
     */
    public GameStoreException() {
    }

    /**
     * GameStoreException constructor
     *
     * @param message an error message
     */
    public GameStoreException(final String message) {
        super(message);
    }

    /**
     * GameStoreException constructor
     *
     * @param message an error message
     * @param cause   exception cause
     */
    public GameStoreException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
