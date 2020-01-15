package gameObjectManager.store.iterator;

import gameObjectManager.object.IGameObject;

/**
 * Interface describes custom iterator
 */
public interface IGameIterator {

    /**
     * Function contract to check is there next element in iterator or not
     *
     * @return true if there is next element, otherwise - false
     */
    boolean hasNext();

    /**
     * Function contract to get next element from iterator
     *
     * @return game object
     */
    IGameObject getNext();
}
