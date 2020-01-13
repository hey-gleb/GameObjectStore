package gameObjectManager.store;

import gameObjectManager.object.FieldName;
import gameObjectManager.object.IFieldName;
import gameObjectManager.object.UObject;

import java.util.Iterator;

/**
 * Implementation of {@link IPoolGuard}
 */
public class PoolGuard implements IPoolGuard {
    private IPool pool;
    private volatile Iterator<UObject> currentIterator;
    private final IFieldName idFieldName;

    /**
     * PoolGuard constructor
     *
     * @param pool using pool
     */
    public PoolGuard(final IPool pool) {
        this.pool = pool;
        this.idFieldName = new FieldName("ObjectId");
    }

    @Override
    public synchronized UObject getObjectById(final Key value) throws PoolGuardException {
        try {
            this.currentIterator = pool.getObject(idFieldName, value);
            return this.currentIterator.next();
        } catch (PoolException e) {
            throw new PoolGuardException("Unable to get object from pool", e);
        }
    }

    @Override
    public synchronized Iterator<UObject> getObjectsByField(final IFieldName fieldName, final Key value) throws PoolGuardException {
        try {
            this.currentIterator = pool.getObject(fieldName, value);
            return this.currentIterator;
        } catch (PoolException e) {
            throw new PoolGuardException("Unable to get object from pool", e);
        }
    }

    @Override
    public synchronized Iterator<UObject> getObjects() throws PoolGuardException {
        try {
            return pool.getAllObjects();
        } catch (PoolException e) {
            throw new PoolGuardException("Unable to get all objects from pool", e);
        }
    }

    @Override
    public synchronized Object putObject(final UObject value) throws PoolGuardException {
        try {
            return pool.putObject(value);
        } catch (PoolException e) {
            throw new PoolGuardException("Unable to put object to pool", e);
        }
    }

    @Override
    public synchronized UObject deleteObject(final Key objectId) throws PoolGuardException {
        try {
            return pool.deleteObject(objectId);
        } catch (PoolException e) {
            throw new PoolGuardException("Unable to delete object from pool", e);
        }
    }

    @Override
    public void close() throws PoolGuardException {
        try {
            if (currentIterator == null) return;
            while (currentIterator.hasNext()) {
                pool.putObject(currentIterator.next());
            }
        } catch (PoolException e) {
            throw new PoolGuardException("Unable to put objects back", e);
        }
    }
}
