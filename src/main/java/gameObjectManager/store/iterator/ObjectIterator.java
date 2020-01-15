package gameObjectManager.store.iterator;

import gameObjectManager.object.IGameObject;
import gameObjectManager.store.GameStore;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of {@link IGameIterator}
 */
public class ObjectIterator implements IGameIterator {
    private GameStore gameStore;
    private int currentPosition = 0;
    private List<IGameObject> objectList;

    /**
     * ObjectIterator constructor
     *
     * @param gameStore - store collection
     */
    public ObjectIterator(GameStore gameStore) {
        this.gameStore = gameStore;
    }

    /**
     * Function to load current integrable construction
     */
    private void lazyLoad() {
        if (this.objectList == null || this.objectList.size() == 0) {
            this.objectList = this.gameStore.getObjectsCollection().stream()
                    .flatMap(List::stream)
                    .collect(Collectors.toList());
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

        IGameObject gameObject = this.objectList.get(currentPosition);
        currentPosition++;
        return gameObject;
    }
}
