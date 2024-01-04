package Lab8;

import Lab6.BusinessClassWagon;
import Lab6.EconomyClassWagon;
import Lab6.FirstClassWagon;
import Lab6.Wagon;
import Lab7.WagonArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class WagonListTest {

    private WagonArrayList list;
    private static EconomyClassWagon wagon1;
    private static BusinessClassWagon wagon2;
    private static FirstClassWagon wagon3;

    /**
     * Initializes the test listup before each test method.
     */
    @BeforeEach
    public void listup() {
        list = new WagonArrayList();
    }

    /**
     * Creates instances of passenger wagons before all test methods.
     */
    @BeforeAll
    public static void createWagons() {
        try {
            wagon1 = new EconomyClassWagon(30, 20);
            wagon2 = new BusinessClassWagon(35, 20);
            wagon3 = new FirstClassWagon(10, 0);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Tests the size() method of the WagonArrayList class.
     */
    @Test
    public void testSizeEmpty() {
        assertEquals(0, list.size());
    }

    @Test
    public void testSizeOneElement() {
        list = new WagonArrayList(wagon1);
        assertEquals(1, list.size());
    }

    @Test
    public void testSizeTwoElements() {
        list = new WagonArrayList(new ArrayList<>(
                Arrays.asList(wagon1, wagon2)));
        assertEquals(2, list.size());
    }

    @Test
    public void testSizeIncreasesWithAdding() {
        list = new WagonArrayList(new ArrayList<>(
                Arrays.asList(wagon1, wagon2)));
        assertEquals(2, list.size());
        list.add(wagon3);
        assertEquals(3, list.size());
    }

    @Test
    public void testSizeDecreasesWithRemoving() {
        list = new WagonArrayList(Arrays.asList(wagon1, wagon2));
        assertEquals(2, list.size());
        list.remove(wagon2);
        assertEquals(1, list.size());
    }

    /**
     * Tests the isEmpty() method of the WagonArrayList class.
     */
    @Test
    public void testEmptyListIsEmpty() {
        assertTrue(list.isEmpty());
    }

    @Test
    public void testListWithElementsIsNotEmpty() {
        list = new WagonArrayList(wagon1);
        assertFalse(list.isEmpty());
    }

    /**
     * Tests the contains() method of the WagonArrayList class.
     */
    @Test
    public void testEmptyListHasNoElements() {
        assertFalse(list.contains(wagon1));
    }

    @Test
    public void testListWithElementContains() {
        list = new WagonArrayList(wagon1);
        assertTrue(list.contains(wagon1));
    }

    /**
     * Tests the iterator() method of the WagonArrayList class.
    **/

    @Test
    public void testIteratorOfNonEmptylistHasNext() {
        list = new WagonArrayList(wagon1);
        Iterator<Wagon> iterator = list.iterator();

        assertTrue(iterator.hasNext());
        assertEquals(wagon1, iterator.next());
    }

    @Test
    public void testIteratorOflistWithTwoElementsHasNextTwice() {
        list = new WagonArrayList(List.of(wagon1, wagon2));
        Iterator<Wagon> iterator = list.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(wagon1, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(wagon2, iterator.next());
    }

    @Test
    public void testIteratorThrowsWhenNoNext() {
        list = new WagonArrayList(List.of(wagon1, wagon2));
        Iterator<Wagon> iterator = list.iterator();
        iterator.next();
        iterator.next();
        assertFalse(iterator.hasNext());
        NoSuchElementException e = assertThrows(NoSuchElementException.class, iterator::next);
        assertEquals(e.getMessage(), "No more elements in the list");
    }


    /**
     * Tests the toArray() method of the WagonArrayList class.
     */
    @Test
    public void testEmptyListToArrayIsEmptyArray() {
        Object[] array = list.toArray(new Wagon[0]);
        assertArrayEquals(new Wagon[0], array);
    }

    @Test
    public void testNonEmptylistToArray() {
        list = new WagonArrayList(new ArrayList<Wagon>(List.of(wagon1, wagon2, wagon3)));

        Object[] array = list.toArray();

        assertArrayEquals(new Object[]{wagon1, wagon2, wagon3}, array);
    }

    /**
     * Tests the add() method of the WagonArrayList class.
     */
    @Test
    public void testAddedElementIsContained() {
        assertTrue(list.add(wagon1));
        assertTrue(list.contains(wagon1));
    }

    @Test
    public void testCannotAddTheSameElementTwice() {
        assertTrue(list.add(wagon1));
        assertTrue(list.contains(wagon1));
        assertFalse(list.add(wagon1));
        assertTrue(list.contains(wagon1));
    }

    /**
     * Tests the remove() method of the WagonArrayList class.
     */
    @Test
    public void testlistDoesNotContainRemovedElement() {
        list = new WagonArrayList(Arrays.asList(wagon1, wagon2));
        assertTrue(list.remove(wagon1));
        assertFalse(list.contains(wagon1));
    }

    @Test
    public void testCannotRemoveTheSameElementTwice() {
        list = new WagonArrayList(Arrays.asList(wagon1, wagon2));
        assertTrue(list.remove(wagon1));
        assertFalse(list.contains(wagon1));
        assertFalse(list.remove(wagon1));
        assertFalse(list.contains(wagon1));
    }

    @Test
    public void testRemovingLastElementMakeslistEmpty() {
        list = new WagonArrayList(wagon1);
        assertTrue(list.remove(wagon1));
        assertTrue(list.isEmpty());
    }

    /**
     * Tests the addAll() method of the WagonArrayList class.
     */
    @Test
    public void testAddAllWithEmptyCollectionChangesNothing() {
        assertTrue(list.addAll(new ArrayList<>()));
        assertArrayEquals(new WagonArrayList().toArray(), list.toArray());
    }

    @Test
    public void testAddAllAddsAllTheElementsOfCollection() {
        assertTrue(list.addAll(Arrays.asList(wagon1, wagon2, wagon3)));
        assertTrue(list.contains(wagon1));
        assertTrue(list.contains(wagon2));
        assertTrue(list.contains(wagon3));
        assertFalse(list.addAll(Arrays.asList(wagon1, wagon2, wagon3)));
    }
    @Test
    public void testCannotAddAllIfAnyElementIsAlreadyAdded() {
        list.addAll(Arrays.asList(wagon1, wagon2, wagon3));
        assertTrue(list.contains(wagon1));
        assertTrue(list.contains(wagon2));
        assertTrue(list.contains(wagon3));
        assertFalse(list.addAll(Arrays.asList(wagon1, wagon2, wagon3)));
    }

    /**
     * Tests the removeAll() method of the WagonArrayList class.
     */
    @Test
    public void testRemoveAllWithEmptyCollectionChangesNothing() {
        assertTrue(list.removeAll(Arrays.asList()));
        assertArrayEquals(new WagonArrayList().toArray(), list.toArray());
    }

    @Test
    public void testlistDoesNotHaveElementsRemovedWithRemoveAll() {
        list = new WagonArrayList(Arrays.asList(wagon1, wagon2, wagon3));
        assertTrue(list.removeAll(Arrays.asList(wagon1, wagon3)));
        assertFalse(list.contains(wagon1));
        assertTrue(list.contains(wagon2));
        assertFalse(list.contains(wagon3));
        assertFalse(list.removeAll(Arrays.asList(wagon1, wagon3)));
    }

    @Test
    public void testCannotRemoveAllWithRemovedElements() {
        list = new WagonArrayList(Arrays.asList(wagon1, wagon2, wagon3));
        list.removeAll(Arrays.asList(wagon1, wagon3));
        assertFalse(list.removeAll(List.of(wagon1, wagon3)));
    }

    /**
     * Tests the retainAll() method of the WagonArrayList class.
     */
    @Test
    public void testRetainAllWithEmptyCollectionClearslist() {
        list = new WagonArrayList(List.of(wagon1, wagon2, wagon3));
        assertTrue(list.retainAll(List.of()));
        assertTrue(list.isEmpty());
    }

    @Test
    public void testRetainAllLeavesOnlyElementsOfCollectionInlist() {
        list = new WagonArrayList(List.of(wagon1, wagon2, wagon3));
        assertTrue(list.retainAll(Arrays.asList(List.of(wagon1, wagon3))));
        assertTrue(list.contains(wagon1));
        assertFalse(list.contains(wagon2));
        assertTrue(list.contains(wagon3));
    }

    /**
     * Tests the containsAll() method of the WagonArrayList class.
     *
     * @throws WagonOverloadedException if an error occurs during wagon creation.
     */
    @Test
    public void testlistContainsAllOfEmptyCollection() {
        list = new WagonArrayList(List.of(wagon1, wagon2, wagon3));
        assertTrue(list.containsAll(List.of()));
    }

    @Test
    public void testlistContainsAllOfCollectionWithOneItsElement() {
        list = new WagonArrayList(List.of(wagon1, wagon2, wagon3));
        assertTrue(list.containsAll(Arrays.asList((wagon1))));
    }

    @Test
    public void testlistContainsAllOfCollectionWithManyItsElements() {
        list = new WagonArrayList(List.of(wagon1, wagon2, wagon3));
        assertTrue(list.containsAll(List.of(wagon1, wagon2)));
    }

    @Test
    public void testlistContainsAllOfCollectionWithAllItsElements() {
        list = new WagonArrayList(new ArrayList<>(Arrays.asList(wagon1, wagon2, wagon3)));
        assertTrue(list.containsAll(new ArrayList<>(Arrays.asList(wagon1, wagon2, wagon3))));
    }

    @Test
    public void testContainsAllReturnsFalseIfCollectionHasElementListDoesNotContain() throws WagonOverloadedException {
        list = new WagonArrayList(List.of(wagon1, wagon2, wagon3));
        FirstClassWagon wagon4 = new FirstClassWagon(10, 10);
        assertFalse(list.containsAll(List.of(wagon4)));
    }

    @Test
    public void testContainsAllReturnsFalseIfCollectionHasElementListDoesNotContainAmongElementsItContains() throws WagonOverloadedException {
        list = new WagonArrayList(Arrays.asList(wagon1, wagon2, wagon3));
        FirstClassWagon wagon4 = new FirstClassWagon(10, 10);
        assertFalse(list.containsAll(new ArrayList<>(Arrays.asList(wagon1, wagon2, wagon3, wagon4))));
    }

    /**
     * Tests the toArray() method without an argument.
     */
    @Test
    public void testEmptyListToArrayWithoutArgumentIsEmptyArray() {
        Object[] array = list.toArray();
        assertArrayEquals(new Object[0], array);
    }

    @Test
    public void testNonEmptyListWithoutArgumentToArray() {
        list = new WagonArrayList(new ArrayList<>(Arrays.asList(wagon1, wagon2, wagon3)));
        Object[] array = list.toArray();
        assertArrayEquals(new Object[]{wagon1, wagon2, wagon3}, array);
    }

    /**
     * Tests the clear() method of the WagonArrayList class.
     */
    @Test
    public void testClearingEmptyListLeavesItEmpty() {
        list.clear();
        assertTrue(list.isEmpty());
    }

    @Test
    public void testClearingListWithOneElementMakesItEmpty() {
        list = new WagonArrayList(wagon1);
        list.clear();
        assertTrue(list.isEmpty());
    }

    @Test
    public void testClearingListWithManyElementsMakesItEmpty() {
        list = new WagonArrayList(new ArrayList<>(Arrays.asList(wagon1, wagon2, wagon3)));
        list.clear();
        assertTrue(list.isEmpty());
    }
}