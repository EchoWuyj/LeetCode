package alg_01_ds_wyj._05_application._05_cache;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-03-28 10:01
 * @Version 1.0
 */

// 取消泛型，直接使用默认
public class LRUCache1 extends LinkedHashMap {
    private int capacity;

    LRUCache1(int capacity, int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor, true);
        this.capacity = capacity;
    }

    @Override
    public boolean removeEldestEntry(Map.Entry eldest) {
        return size() > capacity;
    }

    public int get(int key) {
        // 需要对空指针的情况进行排除
        if (super.get(key) == null) return -1;
        return (int) super.get(key);
    }

    public void put(int key, int value) {
        super.put(key, value);
    }

    public static void main(String[] args) {
        LRUCache1 cache =
                new LRUCache1(3, 3, 0.75F);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3);
        cache.put(4, 4);
        System.out.println(cache.get(3)); // 3
        cache.put(2, 5);
        cache.put(5, 6);
        System.out.println(cache.get(4)); // null
    }
}
