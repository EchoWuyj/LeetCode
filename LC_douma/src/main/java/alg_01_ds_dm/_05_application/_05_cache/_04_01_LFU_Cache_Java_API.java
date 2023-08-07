package alg_01_ds_dm._05_application._05_cache;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-03-26 17:05
 * @Version 1.0
 */

public class _04_01_LFU_Cache_Java_API<K, V> implements Cache<K, V> {

    // KeyPoint LFUCache
    // 1.淘汰最少使用频率的数据，因此需要记录每个 key 的使用频率
    // 2.本题需要使用 3 个 HashMap 记录映射关系

    // 1.Map<K, V> cache
    // => 存储键值对：每个 key 对应的 value 的映射
    // => 题目中的输入 [1, 1]

    // 2.Map<K, Integer> keyToCount
    // => 记录每个 key 的使用频率 count

    // 3.Map<Integer, LinkedHashSet<K>> countToKeySet
    // key => count 表示 key 出现次数
    // value => LinkedHashSet 存储，相同 count 对应所有的 key 的先后顺序，从左往右表示先后次序

    // KeyPoint 注意事项
    // 1.在 count 相同时，淘汰最近最久未使用 (LRU) => LinkedHashSet 最左侧
    // 2.当 key 出现次数变化，则对应的 count 变化，则存储位置 LinkedHashSet 也发生变化

    Map<K, V> cache;
    Map<K, Integer> keyToCount;
    Map<Integer, LinkedHashSet<K>> countToKeySet;

    int capacity;
    // 记录整个缓存中所有 key 中使用最小的次数
    // 在容量满时，需要删除 minCount 对应 key
    int minCount;

    public _04_01_LFU_Cache_Java_API(int capacity) {
        cache = new HashMap<>();
        keyToCount = new HashMap<>();
        countToKeySet = new HashMap<>();
        this.capacity = capacity;
        minCount = 0;
    }

    @Override
    public V get(K key) {

        // 访问 key 后，需要维护和更新映射
        // 1.维护 keyToCount
        // 2.更新 countToKeySet
        // 3.更新 minCount
        // 4.将 key 添加到 count+1 对应的 KeySet 集合中

        V value = cache.get(key);
        if (value == null) return null;

        // 1.维护 keyToCount
        int count = keyToCount.get(key);
        keyToCount.put(key, count + 1);

        // 2.更新 countToKeySet
        // 从这个 key 目前对应的 count 对应的 LinkedHashSet 集合中删除掉这个 key
        // 因为 key 已经被访问过一次，不属于原来 count 对应的 LinkedHashSet 集合中了
        countToKeySet.get(count).remove(key);

        // 3.更新 minCount
        // 若当前 count 等于 minCount，并且当前 count 没有的 key，那么 minCount++

        // 初始状态
        // k1，count = 1，count => KeySet：只有 k1，minCount = 1

        // k1 被访问了
        // k1，count = 2，count => KeySet：只有 k1
        //     count = 1，count => KeySet：没有元素

        // 满足 minCount 自增条件
        // => count = minCount = 1，且 KeySet.size = 0
        // => minCount++，minCount = 2

        if (count == minCount
                && countToKeySet.get(count).size() == 0) {
            minCount++;
        }

        // 4.将 key 添加到 count+1 对应的 KeySet 集合中
        addKeyToKeySet(key, count + 1);

        // 返回 value
        return value;
    }

    // 函数功能：将 key 添加到 count+1 对应的 KeySet 集合中
    private void addKeyToKeySet(K key, int count) {
        // 1.若 countToKeySet 中没有 count 这个 key，则直接创建一对映射：count 和 LinkedHashSet
        if (!countToKeySet.containsKey(count)) {
            countToKeySet.put(count, new LinkedHashSet<>());
        }
        // 2.若 countToKeySet 有 count 这个 key，将 key 添加到 LinkedHashSet 中
        countToKeySet.get(count).add(key);
    }

    @Override
    public void put(K key, V value) {
        // 1.若 cache 存在 key
        if (cache.containsKey(key)) {
            // 更新 key 对应的 value 值
            cache.put(key, value);
            // 相当于是又访问了 key 一次，调用 get 方法，维护映射
            get(key);
        } else {
            // 2.若 cache 不存在 key，存在新增的 key 的情况
            // 2.1 若容量达到上限，先清除使用频率最小，且最久未使用的 key
            if (cache.size() == capacity) {
                // 获取 minCount 对应 KeySet 使用频率最少，且最久未使用的 key
                // 通过 iterator().next() 获取 LinkedHashSet 的第一个值
                K delKey = countToKeySet.get(minCount).iterator().next();

                // 删除 delKey，维护映射
                // minCount 对应的 LinkedHashSet 删除 delKey
                countToKeySet.get(minCount).remove(delKey);
                // cache 删除 delKey
                cache.remove(delKey);
                // keyToCount 删除 delKey
                keyToCount.remove(delKey);

                // KeyPoint 注意事项
                // 清除使用频率最小的 key 的过程中，并不需要维护 minCount
                // 后面新增一个缓存中不存在的 key，再去维护 minCount，且 minCount = 1
            }

            // 2.2 容量没有达到上限
            // 新增一个缓存中不存在的 key
            cache.put(key, value);
            // 维护 keyToCount => key 之前不存在，故 count 为 1
            keyToCount.put(key, 1);

            // 维护 minCount
            // => key 因为是新增不存在的 key，则其 count = 1 ，则 minCount 必然是 1
            minCount = 1;

            // 将 key 添加到 minCount 对应的 KeySet 集合中
            addKeyToKeySet(key, 1);
        }
    }
}
