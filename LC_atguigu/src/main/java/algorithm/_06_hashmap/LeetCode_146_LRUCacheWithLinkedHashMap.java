package algorithm._06_hashmap;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2022-03-18 19:33
 * @Version 1.0
 */

// LinkedHashMap对应kv都是整数,所以使用都是Integer
public class LeetCode_146_LRUCacheWithLinkedHashMap extends LinkedHashMap<Integer, Integer> {
    // 定义容量
    private int capacity;

    public LeetCode_146_LRUCacheWithLinkedHashMap(int capacity) {
        // 初始化容量使用自定义缓存容量,装载因子默认是0.75f
        // accessOrder=true按照访问次序去调整当前双向链表对应的次序
        // 即访问该数据之后,将当前该数据直接移动双向链表的末尾,底层的LinkedHashMap帮助实现了移动的操作
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    // 访问数据的get方法
    public int get(int key) {
        // 需要对空指针的情况进行排除
        if (super.get(key) == null) return -1;
        return super.get(key);
    }

    // put方法
    public void put(int key, int value) {
        super.put(key, value);
    }

    // KeyPoint 该方法在插入一个节点时进行调用,返回值为false进行扩容,true进行删除
    // 扩容:HashMap本身的扩容机制可以做resize(),若当前capacity不够,即当前的数据太多了
    // 应该触发resize()操作去做扩容操作,但是这里需要限制死其容量,不能做扩容而是做删除,
    // 所以应该设置为true

    // 重写是否删除元素的方法
    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        // 若直接return true 则相当于每一次插入节点时都会将当前最老的元素删除
        // 而本题只有在超出capacity容量范围时,才去做删除操作,所以将其设置为size() > capacity
        return size() > capacity;
    }

    public static void main(String[] args) {
        LeetCode_146_LRUCacheWithLinkedHashMap lRUCache = new LeetCode_146_LRUCacheWithLinkedHashMap(2);
        lRUCache.put(1, 1); // 缓存是 {1=1}
        lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
        System.out.println(lRUCache.get(1));   // 返回 1
        lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        System.out.println(lRUCache.get(2));    // 返回 -1 (未找到)
        lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        System.out.println(lRUCache.get(1));     // 返回 -1 (未找到)
        System.out.println(lRUCache.get(3));     // 返回 3
        System.out.println(lRUCache.get(4));     // 返回 4
    }
}
