package gameObjectManager.store;

import gameObjectManager.object.IGameObject;
import gameObjectManager.store.iterator.IGameIterator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;
import java.util.function.Predicate;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GameStoreTest {
    private IGameStore gameStore;

    @Mock
    private List<IGameObject> objectList;

    @Mock
    private HashMap<String, List<IGameObject>> playerObjects;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        gameStore = new GameStore(objectList, playerObjects);
    }

    @Test
    public void testGetObjectById() throws GameStoreException {
        IGameObject object = mock(IGameObject.class);
        String objectId = UUID.randomUUID().toString();
        List<IGameObject> list = new ArrayList<>();
        list.add(object);

        when(objectList.stream()).thenReturn(list.stream());
        when(object.getObjectId()).thenReturn(objectId);

        IGameObject actualObject = gameStore.getObjectById(objectId);

        assertEquals(object, actualObject);
    }

    @Test(expected = GameStoreException.class)
    public void testGetObjectByNull() throws GameStoreException {
        IGameObject object = mock(IGameObject.class);
        List<IGameObject> list = new ArrayList<>();
        list.add(object);

        when(objectList.stream()).thenReturn(list.stream());

        gameStore.getObjectById(null);
    }

    @Test
    public void testGetPlayerObjects() throws GameStoreException {
        String playerId = UUID.randomUUID().toString();
        List<IGameObject> objectList = mock(List.class);
        IGameObject object = mock(IGameObject.class);

        when(playerObjects.get(anyString())).thenReturn(objectList);
        when(objectList.size()).thenReturn(1);
        when(objectList.get(anyInt())).thenReturn(object);

        IGameIterator actualIterator = gameStore.getPlayerObjects(playerId);
        assertTrue(actualIterator.hasNext());
        assertEquals(object, actualIterator.getNext());
    }

    @Test(expected = GameStoreException.class)
    public void testGetPlayerObjectsByNull() throws GameStoreException {
        when(playerObjects.get(anyString())).thenReturn(null);

        IGameIterator iterator = gameStore.getPlayerObjects(null);
        iterator.getNext();
    }

    @Test
    public void testGetObjects() throws GameStoreException {
        IGameObject object = mock(IGameObject.class);
        List<IGameObject> list = new ArrayList<>();
        list.add(object);

        when(this.playerObjects.values()).thenReturn(Collections.singleton(list));

        IGameIterator actualIterator = gameStore.getObjects();

        assertEquals(object, actualIterator.getNext());
    }

    @Test
    public void testPutObject() throws GameStoreException {
        IGameObject object = mock(IGameObject.class);
        String userId = UUID.randomUUID().toString();
        List<IGameObject> mockList = mock(List.class);

        when(this.objectList.add(any(IGameObject.class))).thenReturn(true);
        when(this.playerObjects.get(anyString())).thenReturn(mockList);
        when(mockList.add(any(IGameObject.class))).thenReturn(true);

        assertTrue(gameStore.putObject(userId, object));

        verify(this.objectList).add(object);
        verify(mockList).add(object);
        verify(this.playerObjects).get(userId);
    }

    @Test (expected = GameStoreException.class)
    public void testPutObjectByNullId() throws GameStoreException {
        IGameObject object = mock(IGameObject.class);

        gameStore.putObject(null, object);
    }

    @Test
    public void testDeleteObject() throws GameStoreException {
        String objectId = UUID.randomUUID().toString();

        when(objectList.removeIf(any(Predicate.class))).thenReturn(true);

        assertTrue(gameStore.deleteObject(objectId));
    }

    @Test (expected = GameStoreException.class)
    public void testDeleteObjectByNullId() throws GameStoreException {
        when(objectList.removeIf(any(Predicate.class))).thenThrow(new NullPointerException());

        assertTrue(gameStore.deleteObject(null));
    }
}
