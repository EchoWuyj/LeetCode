package alg_01_ds_dm._05_application._05_cache_二刷;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-03-26 17:29
 * @Version 1.0
 */

public class _03_02_LRU_Cache_Java_API<K, V> extends LinkedHashMap<K, V> {

    // KeyPoint 基于 Java API 来实现 LRUCache
    // HashMap + 双向链表 => Java内置 LinkedHashMap
    // 继承 LinkedHashMap
    int capacity;

    // 实际提交力扣 OJ 平台上，代码需要进行相应的修改
    // => 详细注释：力扣 OJ 版本

    // 构造方法只有一个形参，int capacity，其他参数手动写死
    public _03_02_LRU_Cache_Java_API(int capacity, int initialCapacity, float loadFactor) {
        // 调用父类 super 方法
        super(initialCapacity, loadFactor, true);
        this.capacity = capacity;
    }

    // 删除最久未用使用(最老)的数据
    // => 通过 boolean 值来控制，返回 true 则删除最老的数据，否则不删除
    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        // size > capacity，必须是严格大于，则删除最久未用使用
        return size() > capacity;

        // KeyPoint 注意事项
        // Java 自带的 LinkedHashMap，是先将数据放入，再去进行判断，是否需要删除
        // 和我们自己手动实现的不一样，先判断是否大于 capacity，再删除
    }

    public static void main(String[] args) {
        _03_02_LRU_Cache_Java_API<Integer, Integer> cache =
                new _03_02_LRU_Cache_Java_API<>(3, 3, 0.75F);
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
