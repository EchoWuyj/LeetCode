package alg_02_train_wyj._20_day_数据结构设计;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-05-29 23:18
 * @Version 1.0
 */
public class _07_01_LocalCache<K, V> implements _07_Cache<K, V> {

    private Map<K, V> cache;

    public _07_01_LocalCache() {
        cache = new HashMap<>();
    }

    @Override
    public V get(K key) {
        return cache.get(key);
    }

    @Override
    public void put(K key, V value) {
        cache.put(key, value);
    }
}
