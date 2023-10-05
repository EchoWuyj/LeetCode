package alg_02_train_dm._21_day_综合应用二_二刷;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-05-26 17:04
 * @Version 1.0
 */

// 没有具体题目，标记为 Trie
public class _07_Trie1 {

    // KeyPoint 前缀树(字典树)
    // 应用场景：字符串匹配
    // => 找出以 prefix 为开头的字符串的个数

    // 如：big  pat bigger dog door does
    // 找出以 do 开头的字符串的个数

    // KeyPoint 方法一 暴力解法
    // O(n*k) 性能太差了
    public static int countStr(String[] words, String prefix) {
        int count = 0;
        int n = words.length;
        for (int i = 0; i < n; i++) { // O(n)
            // startsWith API 判断前缀是否相同
            if (words[i].startsWith(prefix)) { // O(k)
                count++;
            }
        }
        return count;
    }

    // for test
    public static void test() {
        String[] strArr = {"big", "pat", "bigger", "dog", "door"};
        System.out.println(countStr(strArr, "do")); // 2
    }

    // ===========================================================

    // KeyPoint 优化 字典树(前缀树)
    // 字典树节点
    // 每个节点包含：一个字符和若干个子节点(数量不固定)
    private class Node {
        // 一个字符
        Character c;
        // 若干个子节点，List 泛型是 Node 节点，类似于链表
        List<Node> list;
        // 标识这个节点是否是一个单词最后一个字符 => 单词结尾
        boolean isEnd;

        // 初始化只是传入一个字符
        public Node(Character c) {
            this.c = c;
            this.list = new ArrayList<>();
            this.isEnd = false;
        }
    }

    // 定义字典数 root
    private Node root;

    public _07_Trie1() {
        this.root = new Node('/');
    }

    // KeyPoint 字典树常用两个操作
    // 1.添加单词
    // 2.判断是否包含指定的单词

    // KeyPoint 方法一 在字典树中添加单词
    // O(n*k)
    public void add(String word) {
        // cur 遍历指针，且从 root 节点开始
        Node cur = root;
        // word 每个字符都需要处理，这里是没法进行优化的
        for (char c : word.toCharArray()) { // O(n)
            // 从子节点集合中查找是否包含当前字符
            // 在一个列表中是否包含字符 => 抽取成一个方法
            int index = containsChar(cur.list, c); // O(k)
            // 没有找到
            if (index == -1) {
                // 创建一个新节点，加入个子节点集合中
                cur.list.add(new Node(c));
                // 更新 index 索引
                index = cur.list.size() - 1;
            }
            // cur 移动到新创建的节点上
            cur = cur.list.get(index);
        }
        // 经过 for 循环，cur 已经来 word 最后一个字符
        // 将该 Node 的 isEnd 设置为 true，表示一个单词的结束
        cur.isEnd = true;
    }

    // 函数功能：在一个列表中是否包含字符
    // => 时间复杂度 O(k)，其中假设子节点的个数为 k
    // KeyPoint 缺点：存在性能瓶颈，因为只是线性查找，可以使用哈希查找 HashMap 进行优化
    // 注意：Node 节点定义 List，传入只能 List<Node>，而不能是 ArrayList
    private int containsChar(List<Node> list, Character c) {
        // 在 list 集合中，线性查找判断，是否有 c
        int size = list.size();
        // 线性查找 => 可以优化
        for (int i = 0; i < size; i++) {
            // KeyPoint 关于 == 和 equal 方法选择
            // 1.基本数据类型 char，可以使用 ==
            // 2.包装类型，可以使用 equal 方法
            if (list.get(i).c == c) {
                return i;
            }
        }
        return -1;
    }

    // KeyPoint 方法二 在字典树中判断是否包含指定的单词
    // O(n*k)
    public boolean contains(String word) {
        Node cur = root;
        for (char c : word.toCharArray()) {
            int index = containsChar(cur.list, c);
            if (index == -1) {
                // 不存在某个字符，直接返回 false
                return false;
            }
            // index 不为 -1，cur 往后移动 index 位置
            cur = cur.list.get(index);
        }
        // 经过上面的 for 之后，都没有返回 false，则是来到最后一个节点的位置
        // 需要判断最后一个字符是否是单词的结尾
        return cur.isEnd;
    }

    // for test
    public static void main(String[] args) {
        test(); // 2
        _07_Trie1 trie = new _07_Trie1();
        trie.add("big");
        trie.add("pat");
        trie.add("bigger");
        trie.add("dog");
        trie.add("door");

        System.out.println(trie.contains("biggere")); // false
        System.out.println(trie.contains("dog")); // true
        System.out.println(trie.contains("pat")); // true
        System.out.println(trie.contains("patt")); // false
    }
}
