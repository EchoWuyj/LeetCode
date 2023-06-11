package alg_01_新手班_zcy.class03;

import java.util.HashMap;
import java.util.TreeMap;

/**
 * @Author Wuyj
 * @DateTime 2022-09-01 22:25
 * @Version 1.0
 */
public class Code05_HashMapTreeMap {

    // 定义节点类型
    public static class Node {
        public int value;

        public Node(int value) {
            this.value = value;
        }
    }

    // HashMap相当于(KV)数据库
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();

        // HashMap表的增删改查都是常数时间O(1),和HashMap存储的数据量无关
        // O(1)前提条件:忽略单样本大小
        // 若加入的字符串很长,长度不能忽略,因为HashMap将字符串加入前,需要对字符串遍历一遍
        // 计算出该字符串的哈希值,因此则不能认为其是O(1),而是O(k)

        // 增
        map.put("zuochengyun", "我是左程云");

        // 查
        System.out.println(map.containsKey("zuochengyun")); //true
        System.out.println(map.containsKey("zuo")); //false;
        System.out.println(map.get("zuochengyun"));//我是左程云

        // 更新
        map.put("zuochengyun", "他是左程云");
        System.out.println(map.get("zuochengyun"));

        // 删除
        map.remove("zuochengyun");

        System.out.println(map.containsKey("zuochengyun"));
        System.out.println(map.get("zuochengyun"));

        //------------------------------------------------

        HashMap<Integer, String> map2 = new HashMap<>();
        map2.put(1234567, "我是1234567");

        // 包装类,是需要创建对象
        Integer a = 1234567;
        Integer b = 1234567;

        // false,比较的内存的地址,故不相同
        System.out.println(a == b);

        // Integer Double Float String Char 在HashMap中按值进行传递
        // 即HashMap中不管内存地址是否不同,只要值相同,则返回查询值存在
        System.out.println(map2.containsKey(a));
        System.out.println(map2.containsKey(b));

        //------------------------------------------------

        Node node1 = new Node(1);
        Node node2 = new Node(1);
        HashMap<Node, String> map3 = new HashMap<>();

        map3.put(node1, "我进来了！");

        // 对于自定义的数据结构,HashMap中按照引用进行传递
        // 传入的是内存地址,即值关心内存地址,不关心其内部的值
        // (注意是没有重写其HashCode值为前提)
        System.out.println(map3.containsKey(node1)); //true;
        System.out.println(map3.containsKey(node2)); //false;

        // 对于HashMap中不是按值进行传递的情况
        // 在HashMap空间占用时,HashMap<Node,Node>,只是一个地址而已,时间复杂度O(1)
        // 并不会将Node中数据全部加载到HashMap的内存中
        HashMap<Node, Node> map4 = new HashMap<>();

        //-----------------------------------------------

        // 有序表
        TreeMap<Integer, String> treeMap1 = new TreeMap<>();

        // TreeMap增删查改操作的时间复杂度都是O(logN)级别

        // 增加
        treeMap1.put(3, "我是3");
        treeMap1.put(0, "我是0");
        treeMap1.put(7, "我是7");
        treeMap1.put(2, "我是2");
        treeMap1.put(5, "我是5");
        treeMap1.put(9, "我是9");

        // 和HashMap一样
        System.out.println(treeMap1.containsKey(7));//true
        System.out.println(treeMap1.containsKey(6));//false

        // 查
        System.out.println(treeMap1.get(3));

        // 更新
        treeMap1.put(3, "他是3");
        System.out.println(treeMap1.get(3));

        // 删除
        treeMap1.remove(3);
        System.out.println(treeMap1.get(3));

        // KeyPoint 额外特性
        System.out.println(treeMap1.firstKey()); // 0
        System.out.println(treeMap1.lastKey()); // 9

        // <=5 离5最近的key告诉我
        System.out.println(treeMap1.floorKey(5));// 5

        // <=6 离6最近的key告诉我
        System.out.println(treeMap1.floorKey(6));// 5

        // >=5 离5最近的key告诉我
        System.out.println(treeMap1.ceilingKey(5));// 5

        // >=6 离6最近的key告诉我
        System.out.println(treeMap1.ceilingKey(6)); // 7

        Node node3 = new Node(3);
        Node node4 = new Node(4);

        // KeyPoint 注意事项
        // 对于自定义的数据结构需要实现比较器,定义好比较的方法
        // 即保证key一定是能够进行比较的,这样才能作为TreeMap的key,否则报错
        // TreeMap<Node, String> treeMap2 = new TreeMap<>();

    }
}
