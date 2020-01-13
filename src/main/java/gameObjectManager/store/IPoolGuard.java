package gameObjectManager.store;

import gameObjectManager.object.IFieldName;
import gameObjectManager.object.UObject;

import java.util.Iterator;

/**
 * Pool guard interface
 */
public interface IPoolGuard extends AutoCloseable {

    /**
     * Function contract to get object by its id
     *
     * @param objectId object id
     * @return object
     * @throws PoolGuardException is thrown if unable to get object from guard pool
     */
    UObject getObjectById(final Key objectId) throws PoolGuardException;

    /**
     * Function contract to get objects by field
     *
     * @param fieldName field name
     * @param value value
     * @return iterator
     * @throws PoolGuardException is thrown if unable to get players objects from guard pool
     */
    Iterator<UObject> getObjectsByField(final IFieldName fieldName, final Key value) throws PoolGuardException;

    /**
     * Function contract to get all objects
     *
     * @return all objects
     * @throws PoolGuardException is thrown if unable to get all pool objects
     */
    Iterator<UObject> getObjects() throws PoolGuardException;

    /**
     * Function contract to put an object to pool
     *
     * @param value object
     * @return object id
     * @throws PoolGuardException is thrown if unable to put an object to pool
     */
    Object putObject(final UObject value) throws PoolGuardException;

    /**
     * Function contract to delete an object from pool
     *
     * @param objectId object id
     * @return deleted object
     * @throws PoolGuardException is thrown if unable to delete object from the pool
     */
    UObject deleteObject(final Key objectId) throws PoolGuardException;

    /**
     * Function to close pool
     *
     * @throws PoolGuardException is thrown if unable to close pool
     */
    void close() throws PoolGuardException;
}
