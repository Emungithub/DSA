package adt;

public class HashMap<K, V> implements HashMapInterface<K, V> {

    // Array of Entry objects, serves as the buckets for the hash map entries.
    private final Entry<K, V>[] buckets;
    private static final int INITIAL_CAPACITY = 16; // Default initial size of the buckets array.
    private int size = 0; // Tracks the number of key-value pairs stored in the HashMap.

    public HashMap() {
        buckets = new Entry[INITIAL_CAPACITY]; // Initialize the buckets array with the initial capacity.
    }

    // Computes the index in the buckets array for a given key.
    private int getBucketIndex(K key) {
        return key == null ? 0 : Math.abs(key.hashCode() % buckets.length);
    }

    // Stores a key-value pair into the map.
    @Override
    public void put(K key, V value) {
        int bucketIndex = getBucketIndex(key); // Determine which bucket the entry should go into.
        Entry<K, V> existing = buckets[bucketIndex];
        // Check if the key already exists and update the value if it does.
        for (; existing != null; existing = existing.next) {
            if (existing.key.equals(key)) {
                existing.value = value;
                return;
            }
        }
        // If the key does not exist, create a new entry and place it in the bucket.
        Entry<K, V> entry = new Entry<>(key, value, buckets[bucketIndex]);
        buckets[bucketIndex] = entry;
        size++; // Increment size to reflect addition of a new key-value pair.
    }

    // Retrieves the value associated with the given key.
    @Override
    public V get(K key) {
        Entry<K, V> entry = findEntry(key);
        return entry != null ? entry.value : null;
    }

    // Retrieves the value associated with the given key or returns the default value if the key is not found.
    @Override
    public V getOrDefault(K key, V defaultValue) {
        V value = get(key);
        return value != null ? value : defaultValue;
    }

    // Checks if a specific key exists in the map.
    @Override
    public boolean containsKey(K key) {
        return findEntry(key) != null;
    }

    // Removes the entry for the specified key and returns its value.
    @Override
    public V remove(K key) {
        int bucketIndex = getBucketIndex(key); // Find the correct bucket.
        Entry<K, V> current = buckets[bucketIndex];
        Entry<K, V> prev = null;

        // Traverse the linked list in the bucket to find and remove the entry.
        while (current != null) {
            if (current.key.equals(key)) {
                if (prev == null) {
                    buckets[bucketIndex] = current.next; // Remove the first entry.
                } else {
                    prev.next = current.next; // Remove an entry not at the start.
                }
                size--; // Decrement size to reflect removal.
                return current.value;
            }
            prev = current;
            current = current.next;
        }
        return null;
    }

    // Returns a set of all keys contained in the map.
    @Override
    public Object[] keySet() {
        Object[] keys = new Object[size]; // Create an array to hold all keys.
        int index = 0;
        // Iterate through all buckets and their linked lists to add keys to the array.
        for (Entry<K, V> bucket : buckets) {
            for (Entry<K, V> current = bucket; current != null; current = current.next) {
                keys[index++] = current.key;
            }
        }
        return keys;
    }

    // Helper method to find an entry in the buckets.
    private Entry<K, V> findEntry(K key) {
        int bucketIndex = getBucketIndex(key);
        Entry<K, V> current = buckets[bucketIndex];
        // Traverse the linked list at the specified bucket index to find the entry.
        while (current != null) {
            if (current.key.equals(key)) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    // Inner class to represent a map entry (key-value pair).
    private static class Entry<K, V> {

        final K key;
        V value;
        Entry<K, V> next; // Pointer to the next entry in the same bucket (linked list).

        Entry(K key, V value, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
}
