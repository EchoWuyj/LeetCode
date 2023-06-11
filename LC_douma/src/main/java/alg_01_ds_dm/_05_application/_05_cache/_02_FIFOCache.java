package alg_01_ds_dm._05_application._05_cache;

import alg_01_ds_wyj._05_application._05_cache.FIFOCache;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

/**
 * @Author Wuyj
 * @DateTime 2023-03-26 17:05
 * @Version 1.0
 */

// FIFO => 队列先进先出
// 实现：HashMap + 队列
public class _02_FIFOCache<K, V> implements Cache<K, V> {
    private Map<K, V> cache;
    // 队列存储 key
    private Queue<K> queue;
    // 表示缓存容量
    private int capacity;

    public _02_FIFOCache(int capacity) {
        // HashMap 需要指定容量
        cache = new HashMap<>(capacity);
        // 固定容量，推荐使用数组实现的队列，底层是循环队列
        queue = new ArrayDeque<>(capacity);
        this.capacity = capacity;
    }

    @Override
    public V get(K key) {
        return cache.get(key);
    }

    @Override
    public void put(K key, V value) {
        // 获取 key 对应的 value
        V oldValue = cache.get(key);
        // 缓存中没有 key
        if (oldValue == null) {
            // offer 操作之前，判断 cache 的容量，满了之后则需要淘汰
            if (cache.size() == capacity) {
                // 队列 queue 从队头出队
                K oldKey = queue.poll();
                // 从 HashMap 删除 oldKey
                cache.remove(oldKey);
            }
            // 新的 key 进队
            queue.offer(key);
        }
        // if 内外都都要执行该语句，将其提取到 if 判断外面
        cache.put(key, value);
    }

    public static void main(String[] args) {
        FIFOCache<Integer, Integer> cache = new FIFOCache<>(3);
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
