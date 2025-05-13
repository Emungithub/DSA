package adt;

public interface UniversalInterface<T> {
    boolean add(T newEntry);
    T remove(int givenPosition);
    void clear();
    T getEntry(int givenPosition);
    boolean isEmpty();
    int getNumberOfEntries();
    boolean contains(T entry);
    int indexOf(T entry);
    T[] toArray();
}
