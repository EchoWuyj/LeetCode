package alg_02_train_wyj._20_day_数据结构设计;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-05-29 23:19
 * @Version 1.0
 */
public class _07_04_LFUCache<K, V> implements _07_Cache<K, V> {

    Map<K, V> cache;
    Map<K, Integer> keyToCounts;
    Map<Integer, LinkedHashSet<K>> countsToKeys;
    int capacity;
    int minUsedCount;

    public _07_04_LFUCache(int capacity) {
        cache = new HashMap<>();
        keyToCounts = new HashMap<>();
        countsToKeys = new HashMap<>();

        this.capacity = capacity;
        minUsedCount = 0;
    }

    @Override
    public V get(K key) {
        V value = cache.get(key);
        if (value == null) return null;

        int count = keyToCounts.get(key);
        countsToKeys.get(count).remove(key);
        keyToCounts.put(key, count + 1);

        if (count == minUsedCount
                && countsToKeys.get(count).size() == 0) {
            minUsedCount++;
        }

        putUsedCount(key, count + 1);
        return value;
    }

    private void putUsedCount(K key, int count) {
        if (!countsToKeys.containsKey(count)) {
            countsToKeys.put(count, new LinkedHashSet<>());
        }
        countsToKeys.get(count).add(key);
    }

    @Override
    public void put(K key, V value) {
        if (cache.containsKey(key)) {
            cache.put(key, value);
            get(key);
            return;
        }

        if (cache.size() == capacity) {
            K removeKey = countsToKeys.get(minUsedCount).iterator().next();
            countsToKeys.get(minUsedCount).remove(removeKey);
            cache.remove(removeKey);
            keyToCounts.remove(removeKey);
        }
        cache.put(key, value);
        keyToCounts.put(key, 1);
        minUsedCount = 1;
        putUsedCount(key, 1);
    }
}
