package gameObjectManager.store;

import gameObjectManager.object.UObject;

import java.util.Iterator;

/**
 * Implementation of {@link IPoolGuard}
 */
public class PoolGuard implements IPoolGuard {
    private IPool pool;
    private Iterator<UObject> currentIterator;

    /**
     * PoolGuard constructor
     * @param pool using pool
     */
    public PoolGuard(final IPool pool) {
        this.pool = pool;
    }

    @Override
    public UObject getObject(final Key objectId) throws PoolGuardException {
        try {
            this.currentIterator = pool.getObject(objectId);
            return this.currentIterator.next();
        } catch (PoolException e) {
            throw new PoolGuardException("Unable to get object from pool", e);
        }
    }

    @Override
    public Iterator<UObject> getPlayerObjects(final Key userId) throws PoolGuardException {
        //TODO add getting objects by user id
        return null;
    }

    @Override
    public Iterator<UObject> getObjects() throws PoolGuardException {
        try {
            return pool.getAllObjects();
        } catch (PoolException e) {
            throw new PoolGuardException("Unable to get all objects from pool", e);
        }
    }

    @Override
    public Object putObject(final UObject value) throws PoolGuardException {
        try {
            return pool.putObject(value);
        } catch (PoolException e) {
            throw new PoolGuardException("Unable to put object to pool", e);
        }
    }

    @Override
    public UObject deleteObject(final Key objectId) throws PoolGuardException {
        try {
            return pool.deleteObject(objectId);
        } catch (PoolException e) {
            throw new PoolGuardException("Unable to delete object from pool",e);
        }
    }

    @Override
    public void close() throws PoolGuardException{
        try {
            if(currentIterator == null) return;
            while(currentIterator.hasNext()) {
                pool.putObject(currentIterator.next());
            }
        } catch (PoolException e) {
            throw new PoolGuardException("Unable to put objects back", e);
        }
    }
}
