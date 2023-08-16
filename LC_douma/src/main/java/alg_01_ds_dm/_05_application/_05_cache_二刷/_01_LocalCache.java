package alg_01_ds_dm._05_application._05_cache_二刷;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-03-26 16:56
 * @Version 1.0
 */
// class 和 interface 都实现泛型，且两者泛型保持一致
public class _01_LocalCache<K, V> implements Cache<K, V> {

    // 定义成员属性，给后面的 get 和 put 使用
    Map<K, V> cache;

    public _01_LocalCache() {

        // KeyPoint 为什么使用 HashMap?
        // 1.HashMap 存储的数据都是放在内存中，可以作为缓存
        //   缺点：HashMap 作为缓存，但是内存是有限的，当缓存容量达到上限，应该淘汰一些缓存中的数据
        // 2.缓存最终目的为了提升数据访问(读写)性能
        //   要求 put 和 get 操作，时间复杂度不能太高，最好是常量级别的，HashMap 是可以做到的
        cache = new HashMap<>();
    }

    @Override
    public V get(K key) {
        return cache.get(key); // O(1)
    }

    @Override
    public void put(K key, V value) {
        cache.put(key, value); // O(1)
    }
}
