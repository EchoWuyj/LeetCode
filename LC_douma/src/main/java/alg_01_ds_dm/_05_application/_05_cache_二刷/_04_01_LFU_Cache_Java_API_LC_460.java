package alg_01_ds_dm._05_application._05_cache_二刷;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-05-30 15:14
 * @Version 1.0
 */
public class _04_01_LFU_Cache_Java_API_LC_460 {

    Map<Integer, Integer> cache;
    Map<Integer, Integer> keyToCount;
    Map<Integer, LinkedHashSet<Integer>> countToKeySet;
    int capacity;
    int minCount;

    public _04_01_LFU_Cache_Java_API_LC_460(int capacity) {
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

        // KeyPoint 规律：维护顺序 KCMC
        // 维护 keyToCount
        int count = keyToCount.get(key);
        keyToCount.put(key, count + 1);

        countToKeySet.get(count).remove(key);

        // 维护 minCount
        if (count == minCount &&
                countToKeySet.get(minCount).size() == 0) {
            minCount++;
        }

        // 维护 countToKeySet
        addKeyToCountToKeySet(key, count + 1);

        // 返回 value
        return value;
    }

    private void addKeyToCountToKeySet(int key, int count) {
        if (!countToKeySet.containsKey(count)) {
            countToKeySet.put(count, new LinkedHashSet<>());
        }
        // 将 count 对应 key 加入 KeySet 中
        countToKeySet.get(count).add(key);
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            cache.put(key, value);
            // 相当于是又访问了 key 一次，调用 get 方法，维护映射
            get(key);
        } else {
            if (cache.size() == capacity) {
                // 获取 countToKeySet 要删除  delKey
                int delKey = countToKeySet.get(minCount).iterator().next();

                // KeyPoint 规律：维护顺序 KCC
                // 删除 keyToCount，cache，countToKeySet 对应 key
                keyToCount.remove(delKey);
                cache.remove(delKey);
                countToKeySet.get(minCount).remove(delKey);
            }

            // KeyPoint 规律：维护顺序 KMCC
            // 维护出现频次为 1 对应 key
            keyToCount.put(key, 1);
            cache.put(key, value);
            minCount = 1;
            addKeyToCountToKeySet(key, 1);
        }
    }
}
