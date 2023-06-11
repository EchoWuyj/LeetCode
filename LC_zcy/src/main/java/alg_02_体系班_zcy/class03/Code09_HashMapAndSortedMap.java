package alg_02_体系班_zcy.class03;

import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeMap;

/**
 * @Author Wuyj
 * @DateTime 2022-09-11 13:18
 * @Version 1.0
 */
public class Code09_HashMapAndSortedMap {
    public static class Node {
        public int value;

        public Node(int v) {
            value = v;
        }
    }

    public static class Zuo {
        public int value;

        public Zuo(int v) {
            value = v;
        }
    }

    public static void main(String[] args) {

        // 哈希表
        // 1)哈希表在使用层面上可以理解为一种集合结构
        // 2)如果只有key,没有伴随数据value,可以使用HashSet结构
        // 3)如果既有key,又有伴随数据value,可以使用HashMap结构
        // 4)有无伴随数据,是HashMap和HashSet,唯一的区别,实际结构是一回事
        // 5)使用哈希表增(put),删(remove),改(put)和查(get)的操作
        //   可以认为时间复杂度为O(1),但是常数时间比较大
        // 6)放入哈希表的东西,如果是基础类型,内部按值传递,
        //   内存占用是这个东西的大小(包括String,String在HashMap中比较特殊)
        // 7)放入哈希表的东西,如果不是基础类型,内部按引用传递,内存占用是8字节

        HashMap<Integer, String> test = new HashMap<>();
        Integer a = 19000000;
        Integer b = 19000000;
        System.out.println(a == b); // false

        test.put(a, "我是3");
        System.out.println(test.containsKey(b)); // true 比较的是值本身

        Zuo z1 = new Zuo(1);
        Zuo z2 = new Zuo(1);
        HashMap<Zuo, String> test2 = new HashMap<>();
        test2.put(z1, "我是z1");
        System.out.println(test2.containsKey(z2));  // false

        //------------------------------------------------------------

        HashMap<Integer, String> map = new HashMap<>();
        map.put(1000000, "我是1000000");
        map.put(2, "我是2");
        map.put(3, "我是3");
        map.put(4, "我是4");
        map.put(5, "我是5");
        map.put(6, "我是6");
        map.put(1000000, "我是1000001");

        System.out.println(map.containsKey(1)); // false
        System.out.println(map.containsKey(10));// false

        System.out.println(map.get(4)); // 我是4
        System.out.println(map.get(10)); // null

        map.put(4, "他是4");
        System.out.println(map.get(4)); // 他是4

        map.remove(4);
        System.out.println(map.get(4)); // null

        //------------------------------------------------------

        // key 没有value
        HashSet<String> set = new HashSet<>();
        set.add("abc");
        set.contains("abc");
        set.remove("abc");

        // 哈希表,增,删,改,查,在使用时,O(1)

        System.out.println("====================================");

        Integer c = 100000;
        Integer d = 100000;
        System.out.println(c.equals(d));// true

        Integer e = 127; // - 128 ~ 127 在该范围内使用值传递
        Integer f = 127;
        System.out.println(e == f); // true

        HashMap<Node, String> map2 = new HashMap<>();
        Node node1 = new Node(1);
        Node node2 = node1; // 两个指引用向同一块区域
        map2.put(node1, "我是node1");
        map2.put(node2, "我是node1");
        System.out.println(map2.size()); // 1 非基础类型的key,使用引用传递

        System.out.println("====================================");

        //-------------------------------------------------------

        // TreeMap 有序表：接口名
        // 红黑树,avl,sb树,跳表 -> 都是可以实现TreeMap
        // 增删改查,时间复杂度 O(logN)

        // 对于自定义的数据结构Node需要实现比较器,定义好比较的方法
        // 即保证key一定是能够进行比较的,这样才能作为TreeMap的key,否则报错
        // TreeMap<Node, String> treeMap2 = new TreeMap<>();

        System.out.println("有序表测试开始");
        TreeMap<Integer, String> treeMap = new TreeMap<>();

        treeMap.put(3, "我是3");
        treeMap.put(4, "我是4");
        treeMap.put(8, "我是8");
        treeMap.put(5, "我是5");
        treeMap.put(7, "我是7");
        treeMap.put(1, "我是1");
        treeMap.put(2, "我是2");

        System.out.println(treeMap.containsKey(1));
        System.out.println(treeMap.containsKey(10));

        System.out.println(treeMap.get(4));
        System.out.println(treeMap.get(10));

        treeMap.put(4, "他是4");
        System.out.println(treeMap.get(4));

        // treeMap.remove(4);
        System.out.println(treeMap.get(4));

        System.out.println("新鲜：");

        System.out.println(treeMap.firstKey()); // 最小key
        System.out.println(treeMap.lastKey()); // 最大key

        // <= 4
        // 地板<=4
        System.out.println(treeMap.floorKey(4));

        // >= 4
        // 天花板>=4
        System.out.println(treeMap.ceilingKey(4));
    }
}
