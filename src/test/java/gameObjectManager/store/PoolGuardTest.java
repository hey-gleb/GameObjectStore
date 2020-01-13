package gameObjectManager.store;

import gameObjectManager.object.IFieldName;
import gameObjectManager.object.UObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Iterator;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PoolGuardTest {

    @Mock
    private IPool pool;


    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetObjectIntId() throws PoolGuardException, PoolException {
        UObject expectedObject = mock(UObject.class);
        Iterator<UObject> iterator = mock(Iterator.class);
        int id = 12;

        when(pool.getObject(any(IFieldName.class), any(Key.class))).thenReturn(iterator);
        when(iterator.next()).thenReturn(expectedObject);

        try (IPoolGuard store = new PoolGuard(pool)) {
            UObject resultObject = store.getObjectById(new Key<>(id));
            assertEquals(expectedObject, resultObject);
        }
    }

    @Test
    public void testGetObjectStringId() throws PoolGuardException, PoolException {
        UObject expectedObject = mock(UObject.class);
        Iterator<UObject> iterator = mock(Iterator.class);
        String id = UUID.randomUUID().toString();

        when(pool.getObject(any(IFieldName.class), any(Key.class))).thenReturn(iterator);
        when(iterator.next()).thenReturn(expectedObject);

        try (IPoolGuard store = new PoolGuard(pool)) {
            UObject resultObject = store.getObjectById(new Key<>(id));
            assertEquals(expectedObject, resultObject);
        }
    }

    @Test
    public void testGetPlayerObjects() throws PoolGuardException, PoolException {
        IFieldName userIdFieldName = mock(IFieldName.class);
        Iterator<UObject> iterator = mock(Iterator.class);
        String userId = UUID.randomUUID().toString();

        when(pool.getObject(any(IFieldName.class), any(Key.class))).thenReturn(iterator);

        try (IPoolGuard store = new PoolGuard(pool)) {
            Iterator<UObject> resultIterator = store.getObjectsByField(userIdFieldName, new Key<>(userId));
            assertEquals(iterator, resultIterator);
        }
    }

    @Test
    public void testGetAllObjects() throws PoolGuardException, PoolException {
        UObject expectedObject1 = mock(UObject.class);
        UObject expectedObject2 = mock(UObject.class);

        Iterator<UObject> iterator = mock(Iterator.class);

        when(pool.getAllObjects()).thenReturn(iterator);
        when(iterator.next()).thenReturn(expectedObject1, expectedObject2);

        try (IPoolGuard store = new PoolGuard(pool)) {
            Iterator<UObject> expectedIterator = store.getObjects();
            assertEquals(expectedIterator, iterator);
            assertEquals(expectedObject1, iterator.next());
            assertEquals(expectedObject2, iterator.next());
        }
    }

    @Test
    public void testDeleteObject() throws PoolGuardException, PoolException {
        UObject expectedObject = mock(UObject.class);
        String id = UUID.randomUUID().toString();

        when(pool.deleteObject(any(Key.class))).thenReturn(expectedObject);

        try (IPoolGuard store = new PoolGuard(pool)) {
            UObject deletedObject = store.deleteObject(new Key<>(id));
            assertEquals(expectedObject, deletedObject);
        }
    }

    @Test
    public void testPutObjectToPool() throws PoolGuardException, PoolException {
        UObject uObject = mock(UObject.class);
        String id = UUID.randomUUID().toString();

        when(pool.putObject(any(UObject.class))).thenReturn(id);

        IPoolGuard store = new PoolGuard(pool);
        String actualId = (String) store.putObject(uObject);

        assertEquals(id, actualId);
    }

    @Test(expected = PoolGuardException.class)
    public void testGetObjectPoolException() throws PoolException, PoolGuardException {
        IFieldName mockFieldName = mock(IFieldName.class);
        Key mockKey = mock(Key.class);
        when(pool.getObject(any(IFieldName.class), any(Key.class))).thenThrow(new PoolException());
        try (IPoolGuard store = new PoolGuard(pool)) {
            store.getObjectsByField(mockFieldName, mockKey);
        }
    }

    @Test(expected = PoolGuardException.class)
    public void testGetObjectByIdPoolException() throws PoolException, PoolGuardException {
        Key mockKey = mock(Key.class);
        when(pool.getObject(any(IFieldName.class), any(Key.class))).thenThrow(new PoolException());
        try (IPoolGuard store = new PoolGuard(pool)) {
            store.getObjectById(mockKey);
        }
    }

    @Test(expected = PoolGuardException.class)
    public void testGetAllObjectPoolException() throws PoolException, PoolGuardException {
        when(pool.getAllObjects()).thenThrow(new PoolException());
        try (IPoolGuard store = new PoolGuard(pool)) {
            store.getObjects();
        }
    }

    @Test(expected = PoolGuardException.class)
    public void testDeleteObjectPoolException() throws PoolException, PoolGuardException {
        Key mockKey = mock(Key.class);
        when(pool.deleteObject(any(Key.class))).thenThrow(new PoolException());
        IPoolGuard store = new PoolGuard(pool);
        store.deleteObject(mockKey);
    }

    @Test(expected = PoolGuardException.class)
    public void testPutObjectPoolException() throws PoolException, PoolGuardException {
        UObject mockObject = mock(UObject.class);
        when(pool.putObject(any(UObject.class))).thenThrow(new PoolException());
        IPoolGuard store = new PoolGuard(pool);
        store.putObject(mockObject);
    }

    @Test (expected = PoolGuardException.class)
    public void testClosePoolGuardException() throws PoolGuardException, PoolException {
        UObject object = mock(UObject.class);
        Iterator<UObject> iterator = mock(Iterator.class);
        when(iterator.hasNext()).thenReturn(true,false);
        when(iterator.next()).thenReturn(object);
        when(pool.getAllObjects()).thenReturn(iterator);
        when(pool.putObject(any(UObject.class))).thenThrow(new PoolException());
        try (IPoolGuard store = new PoolGuard(pool)) {
            store.getObjects();
        }
    }
}