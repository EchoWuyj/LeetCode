package alg_02_train_wyj._20_day_数据结构设计;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-08-06 22:04
 * @Version 1.0
 */
public class _07_04_LFUCache_LC {

    Map<Integer, Integer> cache;
    Map<Integer, Integer> keyToCount;
    Map<Integer, LinkedHashSet<Integer>> countToKeySet;
    int capacity;
    int minCount;

    public _07_04_LFUCache_LC(int capacity) {
        cache = new HashMap<>();
        keyToCount = new HashMap<>();
        countToKeySet = new HashMap<>();
        this.capacity = capacity;
        minCount = 0;
    }

    public int get(int key) {
        // 因为需要判断空，只能使用 Integer 类型接受
        Integer value = cache.get(key);
        if (value == null) return -1;

        int count = keyToCount.get(key);
        keyToCount.put(key, count + 1);
        countToKeySet.get(count).remove(key);
        if (count == minCount &&
                countToKeySet.get(minCount).size() == 0) {
            minCount++;
        }
        addKeyToKeySet(key, count + 1);
        return value;
    }

    private void addKeyToKeySet(int key, int count) {
        if (!countToKeySet.containsKey(count)) {
            countToKeySet.put(count, new LinkedHashSet<>());
        }
        countToKeySet.get(count).add(key);
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            cache.put(key, value);
            get(key);
        } else {
            if (cache.size() == capacity) {
                int delKey = countToKeySet.get(minCount).iterator().next();
                cache.remove(delKey);
                countToKeySet.get(minCount).remove(delKey);
                keyToCount.remove(delKey);
            }
            cache.put(key, value);
            keyToCount.put(key, 1);
            minCount = 1;
            addKeyToKeySet(key, 1);
        }
    }
}
