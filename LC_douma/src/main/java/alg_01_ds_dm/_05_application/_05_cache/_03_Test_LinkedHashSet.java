package alg_01_ds_dm._05_application._05_cache;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @Author Wuyj
 * @DateTime 2023-03-26 17:29
 * @Version 1.0
 */
public class _03_Test_LinkedHashSet {
    public static void main(String[] args) {
        test1();
        System.out.println("===================");
        test2();
    }

    private static void test1() {
        Set<Integer> set = new HashSet<>();
        set.add(3);
        set.add(1);
        set.add(10);
        set.add(1);
        set.add(3);
        System.out.println(set);
        // 数据去重 [1, 3, 10] ，但是不会维护数据 add 的顺序
    }

    private static void test2() {

        // LinkedHashSet 底层使用 LinkedHashMap 实现，但 LinkedHashSet 只是存储一个元素(key)，而不是键值对
        Set<Integer> set = new LinkedHashSet<>();
        set.add(3);
        set.add(1);
        set.add(10);
        set.add(1);
        set.add(3);

        System.out.println(set);
        // 1.数据去重 [3, 1, 10] ，同时会维护数据 add 的顺序
        // 2.其中 LinkedHashSet 在 LFU 中使用到
    }
}
