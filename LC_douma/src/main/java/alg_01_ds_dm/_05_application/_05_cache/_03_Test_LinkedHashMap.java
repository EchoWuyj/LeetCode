package alg_01_ds_dm._05_application._05_cache;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-03-26 17:28
 * @Version 1.0
 */
public class _03_Test_LinkedHashMap {

    public static void main(String[] args) {
        test1(); // HashMap
        System.out.println("===================");
        test2(); // LinkedHashMap
    }

    private static void test1() {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(5, 5);
        map.put(2, 2);
        map.put(9, 9);

        // KeyPoint Map.Entry 不加泛型也是可以的，故将其省略
        for (Map.Entry entry : map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
        /*
            2 2
            5 5
            9 9
            => 输出打印顺序和 put 顺序是不一样，因为 HashMap 的底层是基于 Hash 函数的，因此没有任何顺序可言
         */
    }

    private static void test2() {
        // KeyPoint
        // 1.LinkedHashMap 会维护 put 的键值对(K,V)的相对顺序，输入顺序和显示顺序一致(双向链表可以维护节点之间的顺序)
        // 2.默认情况下 LinkedHashMap 是不会维护：最近使用 / 最久未使用的数据位置
        //   但可以通过构造方法的参数设置，accessOrder 设置为 true，将最近使用的键值对放在表头，最久未使用的键值对放在表尾
        Map<Integer, Integer> map = new LinkedHashMap<>(3, 0.75F, true);

        map.put(5, 5);
        map.put(2, 2);
        map.put(9, 9);

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

        /*
            5 5
            2 2
            9 9
            LinkedHashMap 会维护 put 的键值对(KV)的相对顺序，输入顺序和显示顺序一致(双向链表可以维护节点之间的顺序)
         */

        System.out.println("===================");

        map.put(2, 10);
        map.get(5);
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
        /*
            9 9 -> 表尾 -> 最久未使用的数据
            2 10
            5 5 -> 表头 -> 最近使用的数据

         */
    }
}
