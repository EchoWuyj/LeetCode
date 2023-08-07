package alg_01_ds_dm._05_application._05_cache;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-05-30 12:36
 * @Version 1.0
 */

public class _03_02_LRU_Cache_Java_API_LC_146 extends LinkedHashMap<Integer, Integer> {

    // 力扣 OJ 版本
    // 注意：LinkedHashMap<Integer, Integer> 泛型不能省略
    private int capacity;

    public _03_02_LRU_Cache_Java_API_LC_146(int capacity) {
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    public int get(int key) {
        // 需要对空指针的情况进行排除
        if (super.get(key) == null) return -1;
        return super.get(key);
    }

    public void put(int key, int value) {
        super.put(key, value);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        // 1.记住方法名 remove|Eldest|Entry
        // 2.若直接 return true，相当于每一次操作都会将当前最老的元素删除
        //   而当前 size()>capacity，只有在超出 capacity 容量范围时，才去做删除操作
        return size() > capacity;
    }
}
