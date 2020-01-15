package gameObjectManager.store;

import gameObjectManager.object.IGameObject;
import gameObjectManager.store.iterator.IGameIterator;
import gameObjectManager.store.iterator.ObjectIterator;
import gameObjectManager.store.iterator.PlayerIterator;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * Implementation of {@link IGameStore}
 */
public class GameStore implements IGameStore {
    private volatile List<IGameObject> objectList;
    private volatile HashMap<String, List<IGameObject>> playerObjects;

    /**
     * GameStore constructor
     *
     * @param objectList    - object collection
     * @param playerObjects - players objects collection
     */
    public GameStore(final List<IGameObject> objectList, final HashMap<String, List<IGameObject>> playerObjects) {
        this.objectList = objectList;
        this.playerObjects = playerObjects;
    }

    public Collection<List<IGameObject>> getObjectsCollection() {
        return this.playerObjects.values();
    }

    public List<IGameObject> getObjectList(final String playerId) {
        return this.playerObjects.get(playerId);
    }

    @Override
    public IGameObject getObjectById(final String objectId) throws GameStoreException {
        try {
            return objectList.stream()
                    .filter(object -> objectId.equals(object.getObjectId()))
                    .findAny()
                    .orElse(null);
        } catch (NullPointerException e) {
            throw new GameStoreException("Unable to get object by null", e);
        }
    }

    @Override
    public IGameIterator getPlayerObjects(final String playerId) throws GameStoreException {
        if (playerId == null) throw new GameStoreException("Unable to get players objects by null");
        return new PlayerIterator(this, playerId);
    }

    @Override
    public IGameIterator getObjects() {
        return new ObjectIterator(this);
    }

    @Override
    public synchronized boolean putObject(final String userId, final IGameObject value) throws GameStoreException {
        try {
            playerObjects.get(userId).add(value);
            return objectList.add(value);
        } catch (NullPointerException e) {
            throw new GameStoreException("Unable to add object by null", e);
        }
    }

    @Override
    public synchronized boolean deleteObject(final String objectId) throws GameStoreException {
        try {
            playerObjects.values().forEach(list -> list.removeIf(object -> objectId.equals(object.getObjectId())));
            return objectList.removeIf(object -> objectId.equals(object.getObjectId()));
        } catch (NullPointerException e) {
            throw new GameStoreException("Unable to remove object by null id", e);
        }
    }
}
