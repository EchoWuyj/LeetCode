package alg_01_ds_dm._03_high_level._03_map;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author Wuyj
 * @DateTime 2023-03-17 18:12
 * @Version 1.0
 */
public class Java_Set_Map {
    public static void main(String[] args) {

        // KeyPoint  以后在做算法题时，经常使用以下 4 种数据结构
        //       其中 HashSet 和 HashMap 使用最多

        // TreeSet：红黑树，有序的 => 常见操作的时间复杂度:对数级别
        // HashSet：hash，链表法，数组 + 链表 => 在数据量不大的情况下，常见操作的时间复杂度:常数级别
        Set<Integer> set = new HashSet<>();

        // TreeMap：红黑树，key 有序的
        // HashMap：hash，链表法，数组 + 链表
        Map<Integer, Integer> map = new HashMap<>();
    }
}
