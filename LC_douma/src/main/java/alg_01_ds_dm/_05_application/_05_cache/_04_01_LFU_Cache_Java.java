package alg_01_ds_dm._05_application._05_cache;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-03-26 17:05
 * @Version 1.0
 */

// KeyPoint LFUCache 淘汰最少使用频率的数据，因此需要记录每个 key 的使用频率
public class _04_01_LFU_Cache_Java<K, V> implements Cache<K, V> {

    // KeyPoint 本题：需要使用 3 个 HashMap
    // 1 每个 key 对应的 value 的映射 => 存储键值对
    private Map<K, V> cache;

    // 2 LFU 淘汰最少使用频率的数据，因此需要记录每个 key 的使用频率
    // KeyPoint 补充说明：
    // key -> UsedCount 对应的命名方式 keyToUsedCount，这样命名方式很规范
    // 但这样有点麻烦，理解起来很费劲，故修改成 keyToCounts
    private Map<K, Integer> keyToCounts;

    // 3 每个 count(key出现次数) 对应的所有的 keys 的先后顺序 (从左往右是依次使用的顺序)
    //   在 count(key出现次数) 相同时，淘汰最久没有使用的(最左侧)，通过该数据结构来实现
    // Map 中 key -> count，value -> 按照 先后顺序 key 的集合 => LinkedHashSet
    // KeyPoint 补充说明：
    // key 出现次数发生变化，key 必然属于不同的 count
    // 不会出现 count = 2，对应的 LinkedHashSet 存在两个 k2
    private Map<Integer, LinkedHashSet<K>> countToKeys; // 原变量名 usedCountToKeys

    private int capacity;

    // 记录整个缓存中所有 key 中使用最小的次数
    // 在容量满时，需要删除 minUsedCount 对应 key
    private int minUsedCount;

    public _04_01_LFU_Cache_Java(int capacity) {
        cache = new HashMap<>();
        keyToCounts = new HashMap<>();
        countToKeys = new HashMap<>();

        this.capacity = capacity;
        minUsedCount = 0;
    }

    @Override
    public V get(K key) {
        // get key 后，3 步操作
        // 1.维护 keyToCounts
        // 2.更新 minUsedCount
        // 3.维护 countToKeys

        V value = cache.get(key);
        if (value == null) return null;

        // 1.维护这个 key 对应的 count
        // 原来变量名 usedCount
        int count = keyToCounts.get(key);
        // 从这个 key 目前对应的 count 的 LinkedHashSet 集合中删除掉这个 key
        // 因为 key 被 get 一次，已经不属于原来 count 对应的 LinkedHashSet 集合中了
        countToKeys.get(count).remove(key);
        keyToCounts.put(key, count + 1);

        // 2.更新最小使用的次数
        // 如果当前的 count 等于最小次数(minUsedCount)，并且当前的 count 没有的 key，那么将最小次数加 1
        if (count == minUsedCount
                && countToKeys.get(count).size() == 0) {
            // minUsedCount 自增 1 即可，不用考虑跳跃累加的问题
            // 如：count 为 1 的 key 没有了，此时 minUsedCount(1) + 1，为 2
            // 不用管原来 countToKeys 是否存在 count = 2，因为原来的 count = 1，已经变成了 count = 2
            minUsedCount++;
        }

        // 3. 将 key 记录到 count+1 中的集合中
        putUsedCount(key, count + 1);

        // 返回 value
        return value;
    }

    // 更新 countToKeys
    private void putUsedCount(K key, int count) {
        // 1.若 count+1 中没有 key，则直接创建一个 LinkedHashSet
        if (!countToKeys.containsKey(count)) {
            countToKeys.put(count, new LinkedHashSet<>());
        }
        // 2.若 count+1 中有 key，则将其连接在 LinkedHashSet 的表头的位置(最近刚添加的 key)
        countToKeys.get(count).add(key);
    }

    @Override
    public void put(K key, V value) {
        // 1 若 cache 存在 key
        if (cache.containsKey(key)) {
            // 更新 key 对应的 value 值
            cache.put(key, value);
            // 相当于是又访问了 key 一次，但不需要 get(key) 的返回值，
            // 调用 get 方法，只是为了维护 3 个变量
            get(key);
            return;
        }

        // 2 若 cache 不存在 key，存在新增的 key 的情况
        // 2.1 容量达到上限，先清除使用频率最小的 key
        if (cache.size() == capacity) {
            // 删除最少使用的 key，通过 iterator().next() 获取 LinkedHashSet 的第一个值
            K removeKey = countToKeys.get(minUsedCount).iterator().next();

            // KeyPoint 维护 removeKey 对应的三个变量
            // countToKeys 中 minUsedCount 对应的 LinkedHashSet 删除 key
            countToKeys.get(minUsedCount).remove(removeKey);
            // Map 映射删除
            cache.remove(removeKey);
            // keyToCounts 映射删除
            keyToCounts.remove(removeKey);
        }

        // 新增一个缓存中不存在的 key
        //  // 2.2 容量没有达到上限
        cache.put(key, value);
        keyToCounts.put(key, 1);

        // 将 key 记录到 minUsedCount 中的集合中
        // key 因为是新增不存在的 key，则其 count = 1 ，则 minUsedCount 必然是 1
        minUsedCount = 1;
        putUsedCount(key, 1);
    }
}
