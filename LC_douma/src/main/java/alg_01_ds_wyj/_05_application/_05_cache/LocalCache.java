package alg_01_ds_wyj._05_application._05_cache;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-03-27 23:45
 * @Version 1.0
 */
public class LocalCache<K, V> implements Cache<K, V> {

    private Map<K, V> cache;

    public LocalCache() {
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
