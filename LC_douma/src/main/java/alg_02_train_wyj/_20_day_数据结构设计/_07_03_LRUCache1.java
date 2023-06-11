package alg_02_train_wyj._20_day_数据结构设计;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-05-29 23:19
 * @Version 1.0
 */
public class _07_03_LRUCache1 extends LinkedHashMap<Integer, Integer> {
    int capacity;

    public _07_03_LRUCache1(int capacity) {
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    public int get(int key) {
        if (super.get(key) == null) return -1;
        return super.get(key);
    }

    public void put(int key, int value) {
        super.put(key, value);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size() > capacity;
    }
}
