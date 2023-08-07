package alg_02_train_wyj._20_day_数据结构设计;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

/**
 * @Author Wuyj
 * @DateTime 2023-05-29 23:19
 * @Version 1.0
 */
public class _07_02_FIFOCache<K, V> implements _07_Cache<K, V> {

    Map<K, V> cache;
    Queue<K> queue;
    int capacity;

    public _07_02_FIFOCache(int capacity) {
        cache = new HashMap<>();
        queue = new ArrayDeque<>();
        this.capacity = capacity;
    }

    @Override
    public V get(K key) {
        return cache.get(key);
    }

    @Override
    public void put(K key, V value) {
        V oldValue = cache.get(key);
        if (oldValue == null) {
            if (cache.size() == capacity) {
                K oldKey = queue.poll();
                cache.remove(oldKey);
            }
            queue.offer(key);
        }
        cache.put(key, value);
    }

    public static void main(String[] args) {
        _07_02_FIFOCache cache = new _07_02_FIFOCache(3);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3);
        cache.put(4, 4);
        System.out.println(cache.get(3)); // 3
        cache.put(4, 5); // 覆盖原来 (4,4)
        System.out.println(cache.get(4)); // 5
        cache.put(5, 5);
        System.out.println(cache.get(2)); // null
    }
}
