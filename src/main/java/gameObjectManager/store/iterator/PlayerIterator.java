package gameObjectManager.store.iterator;

import gameObjectManager.object.IGameObject;
import gameObjectManager.store.GameStore;

import java.util.List;

/**
 * Implementation of {@link IGameIterator}
 */
public class PlayerIterator implements IGameIterator {
    private GameStore gameStore;
    private int currentPosition = 0;
    private String playerId;
    private List<IGameObject> objectList;

    /**
     * PlayerIterator constructor
     *
     * @param gameStore - store collection
     * @param playerId  - player id
     */
    public PlayerIterator(GameStore gameStore, String playerId) {
        this.gameStore = gameStore;
        this.playerId = playerId;
    }

    /**
     * Function to load collection
     */
    private void lazyLoad() {
        if (this.objectList == null || this.objectList.size() == 0) {
            this.objectList = this.gameStore.getObjectList(playerId);
        }
    }

    @Override
    public boolean hasNext() {
        lazyLoad();
        return currentPosition < objectList.size();
    }

    @Override
    public IGameObject getNext() {
        if (!hasNext()) {
            return null;
        }

        IGameObject gameObject = this.objectList.get(this.currentPosition);
        currentPosition++;
        return gameObject;
    }
}
