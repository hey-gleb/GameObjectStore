package gameObjectManager.store;

import gameObjectManager.object.IFieldName;
import gameObjectManager.object.UObject;

import java.util.Iterator;

/**
 * Pool interface
 */
public interface IPool {

    /**
     * Function to get object by custom field
     *
     * @param field field name
     * @param value value
     * @return an object
     * @throws PoolException is thrown if unable to get object
     */
    Iterator<UObject> getObject(final IFieldName field, final Key value) throws PoolException;

    /**
     * Function to get all pool objects
     *
     * @return all pool objects
     * @throws PoolException is thrown if unable to get all pool objects
     */
    Iterator<UObject> getAllObjects() throws PoolException;


    /**
     * Function to put object to pool
     *
     * @param value object to put
     * @throws PoolException is thrown if unable to put object to pool
     */
    Object putObject(final UObject value) throws PoolException;

    /**
     * Function to delete object from pool
     * @param id object id
     * @return deleted object
     * @throws PoolException is thrown if unable to delete object from pool
     */
    UObject deleteObject(final Key id) throws PoolException;
}
