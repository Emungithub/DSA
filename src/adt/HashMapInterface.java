package adt;

public interface HashMapInterface<K, V> {
    void put(K key, V value);
    V get(K key);
    V remove(K key);
    boolean containsKey(K key);
    V getOrDefault(K key, V defaultValue);
    Object[] keySet();
}
