package adt;

import java.io.Serializable;

public class UniversalList<T> implements UniversalInterface<T>, Serializable {
    private T[] array;
    private int numberOfEntries;
    private static final int DEFAULT_CAPACITY = 10;

    public UniversalList() {
        this(DEFAULT_CAPACITY);
    }

    public UniversalList(int initialCapacity) {
        numberOfEntries = 0;
        array = (T[]) new Object[initialCapacity];
    }

    @Override
    public boolean add(T newEntry) {
        ensureCapacity();
        array[numberOfEntries++] = newEntry;
        return true;
    }

    @Override
    public T remove(int givenPosition) {
        checkIndex(givenPosition);
        T result = array[givenPosition - 1];
        removeGap(givenPosition);
        numberOfEntries--;
        return result;
    }

    @Override
    public void clear() {
        for (int index = 0; index < numberOfEntries; index++) {
            array[index] = null;
        }
        numberOfEntries = 0;
    }

    @Override
    public T getEntry(int givenPosition) {
        checkIndex(givenPosition);
        return array[givenPosition - 1];
    }

    @Override
    public boolean isEmpty() {
        return numberOfEntries == 0;
    }

    @Override
    public int getNumberOfEntries() {
        return numberOfEntries;
    }

    @Override
    public boolean contains(T entry) {
        return indexOf(entry) >= 0;
    }

    @Override
    public int indexOf(T entry) {
        for (int index = 0; index < numberOfEntries; index++) {
            if (entry.equals(array[index])) {
                return index + 1;
            }
        }
        return -1;
    }

    @Override
    public T[] toArray() {
        T[] result = (T[]) new Object[numberOfEntries];
        System.arraycopy(array, 0, result, 0, numberOfEntries);
        return result;
    }

    private void ensureCapacity() {
        if (numberOfEntries == array.length) {
            doubleArray();
        }
    }

    private void doubleArray() {
        T[] oldArray = array;
        array = (T[]) new Object[oldArray.length * 2];
        System.arraycopy(oldArray, 0, array, 0, oldArray.length);
    }

    private void checkIndex(int index) {
        if (index < 1 || index > numberOfEntries) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
    }

    private void removeGap(int givenPosition) {
        int removedIndex = givenPosition - 1;
        System.arraycopy(array, removedIndex + 1, array, removedIndex, numberOfEntries - removedIndex - 1);
        array[numberOfEntries - 1] = null; // Help garbage collection
    }
}
