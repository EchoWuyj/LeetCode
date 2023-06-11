package alg_01_新手班_wyj.class03;

import java.util.HashMap;
import java.util.TreeMap;

/**
 * @Author Wuyj
 * @DateTime 2022-09-08 10:39
 * @Version 1.0
 */
public class Code05_HashMapTreeMap {
    public static class Node {
        private int value;

        public Node(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();

        map.put("wuyongjian", "吴勇健");

        System.out.println(map.containsKey("wuyongjian"));
        System.out.println(map.get("wuyongjian"));

        map.put("wuyongjian", "小吴");
        System.out.println(map.get("wuyongjian"));

        map.remove("wuyongjian");
        System.out.println(map.containsKey("wuyongjian"));

        Node node1 = new Node(1);
        Node node2 = new Node(2);

        HashMap<Node, String> map2 = new HashMap<>();

        map2.put(node1, "node1");

        //----------------------------------------------

        TreeMap<Integer, String> treeMap = new TreeMap<>();
        // 时间复杂度 O(logN)级别

        treeMap.put(3, "3");
        treeMap.put(4, "4");
        treeMap.put(5, "5");
        treeMap.put(6, "6");
        treeMap.put(7, "7");
        treeMap.put(8, "8");

        // KeyPoint 额外特性
        System.out.println(treeMap.firstKey());
        System.out.println(treeMap.lastKey());


    }
}
