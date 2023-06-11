package alg_01_ds_dm._05_application._05_cache;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-05-30 15:14
 * @Version 1.0
 */
public class _04_01_LFU_Cache_Java_LC {

    Map<Integer, Integer> cache;
    Map<Integer, Integer> keyToCounts;
    Map<Integer, LinkedHashSet<Integer>> countsToKeys;
    int capacity;
    int minUsedCount;

    public _04_01_LFU_Cache_Java_LC(int capacity) {
        cache = new HashMap<>();
        keyToCounts = new HashMap<>();
        countsToKeys = new HashMap<>();
        this.capacity = capacity;
        minUsedCount = 0;
    }

    public int get(int key) {
        Integer value = cache.get(key);
        if (value == null) return -1;

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

    private void putUsedCount(int key, int count) {
        if (!countsToKeys.containsKey(count)) {
            countsToKeys.put(count, new LinkedHashSet<>());
        }
        countsToKeys.get(count).add(key);
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            cache.put(key, value);
            get(key);
            return;
        }

        if (cache.size() == capacity) {
            int removeKey = countsToKeys.get(minUsedCount).iterator().next();
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
