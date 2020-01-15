package gameObjectManager.store;

import gameObjectManager.object.IGameObject;
import gameObjectManager.store.iterator.IGameIterator;

/**
 * Game object store interface
 */
public interface IGameStore {

    /**
     * Function contract to get object by its id
     *
     * @param objectId object id
     * @return object
     * @throws GameStoreException is thrown if unable to get object from game store
     */
    IGameObject getObjectById(final String objectId) throws GameStoreException;

    /**
     * Function contract to get player objects
     *
     * @param playerId player id
     * @return iterator
     * @throws GameStoreException is thrown if unable to get players objects from game store
     */
    IGameIterator getPlayerObjects(final String playerId) throws GameStoreException;

    /**
     * Function contract to get all objects
     *
     * @return all objects
     * @throws GameStoreException is thrown if unable to get all game objects
     */
    IGameIterator getObjects() throws GameStoreException;

    /**
     * Function contract to put an object to store
     *
     * @param value object
     * @return was operation success
     * @throws GameStoreException is thrown if unable to put an object to store
     */
    boolean putObject(final String userId, final IGameObject value) throws GameStoreException;

    /**
     * Function contract to delete an object from store
     *
     * @param objectId object id
     * @return was operation success
     * @throws GameStoreException is thrown if unable to delete object from store
     */
    boolean deleteObject(final String objectId) throws GameStoreException;
}
