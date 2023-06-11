package alg_01_ds_wyj._05_application._05_cache;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-03-28 22:53
 * @Version 1.0
 */
public class LFUCache<K, V> implements Cache<K, V> {
    // KeyPoint 提交到力扣平台上需要将对应的泛型替换掉，将 K 和 V 替换 Integer
    //          同时代码中，返回的数据类型也需要同步替换，不要遗漏
    private Map<K, V> cache;
    private Map<K, Integer> counts;
    private Map<Integer, LinkedHashSet<K>> keys;
    private int capacity;
    private int minUsedCount;

    public LFUCache(int capacity) {
        cache = new HashMap<>();
        counts = new HashMap<>();
        keys = new HashMap<>();
        this.capacity = capacity;
        minUsedCount = 0;
    }

    @Override
    public V get(K key) {
        V value = cache.get(key);
        if (value == null) return null;

        int count = counts.get(key);
        keys.get(count).remove(key);
        counts.put(key, count + 1);

        if (count == minUsedCount
                && keys.get(count).size() == 0) {
            minUsedCount++;
        }
        putUsedCount(key, count + 1);

        return value;
    }

    private void putUsedCount(K key, int count) {
        if (!keys.containsKey(count)) {
            keys.put(count, new LinkedHashSet<>());
        }

        keys.get(count).add(key);
    }

    @Override
    public void put(K key, V value) {
        if (cache.containsKey(key)) {
            cache.put(key, value);
            get(key);
            return;
        }

        if (cache.size() == capacity) {
            K removeKey = keys.get(minUsedCount).iterator().next();
            keys.get(minUsedCount).remove(removeKey);
            cache.remove(removeKey);
            counts.remove(removeKey);
        }

        cache.put(key, value);
        counts.put(key, 1);
        minUsedCount = 1;
        putUsedCount(key, minUsedCount);
    }
}
