package gameObjectManager.store;

import java.util.Objects;

/**
 * Generic key class
 *
 * @param <T> any class
 */
public class Key<T> {
    private final T key;

    /**
     * Key constructor
     *
     * @param key incoming value
     */
    public Key(final T key) {
        this.key = key;
    }

    /**
     * Function to get key
     * @return key
     */
    public T getKey() {
        return key;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Key<?> key1 = (Key<?>) o;
        return Objects.equals(key, key1.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }
}
