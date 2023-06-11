package alg_01_ds_dm._05_application._03_trie;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-03-26 11:48
 * @Version 1.0
 */
public class Trie {

    // KeyPoint 场景：字符串匹配
    // => 找出以 prefix 为开头的字符串的个数
    // big  pat bigger  dog door does
    // 找出以 do 开头的字符串的个数
    // 暴力解法 O(n*k) 性能太差了
    public static int countStarWithStr(String[] words, String prefix) {
        int count = 0;
        // O(n)
        for (int i = 0; i < words.length; i++) {
            // O(k)
            // startsWith API 判断前缀是否相同
            if (words[i].startsWith(prefix)) {
                count++;
            }
        }
        return count;
    }

    public static void test() {
        String[] strArr = {"big", "pat", "bigger", "dog", "door"};
        System.out.println(countStarWithStr(strArr, "do")); // 2
    }

    // KeyPoint 优化 => 字典树(前缀树)
    // 字典树节点，每个节点包含：一个字符和若干个子节点(数量不固定)
    private class Node {
        // 一个字符
        private Character c;
        // 若干个子节点，List 泛型是 Node 节点，类似于链表
        List<Node> list;
        // 标识这个节点是否是一个单词最后一个字符 => 单词结尾
        boolean isWord;

        // 初始化只是传入一个字符
        public Node(Character c) {
            this.c = c;
            this.list = new ArrayList<>();
            this.isWord = false;
        }
    }

    // 定义字典数 root
    private Node root;

    public Trie() {
        this.root = new Node('/');
    }

    // 字典树常用两个操作
    // 1.添加单词
    // 2.判断是否包含指定的单词
    // 方法一 在字典树中添加单词 => O(n*k)
    public void add(String word) {
        // cur 遍历指针
        Node curr = root;
        // O(n) => 每个字符都需要处理，这里是没法进行优化的
        for (Character c : word.toCharArray()) {
            // 1.从子节点中查找是否包含当前字符
            // 在一个列表中是否包含字符 => 抽取成一个方法 => O(k)
            int index = containsChar(curr.list, c);
            // 没有找到
            if (index == -1) {
                // 创建一个新节点，加入个子节点集合中
                curr.list.add(new Node(c));
                // 更新 index
                index = curr.list.size() - 1;
            }
            // curr 移动到新创建的节点上
            curr = curr.list.get(index);
        }
        // 经过 for 循环，curr 已经来 word 最后一个字符，则将该 Node 的 isWord 设置为 true
        curr.isWord = true;
    }

    // 在一个列表中是否包含字符
    // O(k)，假设子节点的个数为 k
    // 存在性能瓶颈，因为只是线性查找，可以使用哈希查找 HashMap 进行优化
    // Node 节点定义 List，传入只能 List<Node>，而不能是 ArrayList
    private int containsChar(List<Node> children, Character c) {
        // children 节点中，线性查找判断，是否有 c
        for (int i = 0; i < children.size(); i++) {
            // 基本数据类型 char，可以使用 ==
            // 包装类型，可以使用 equal
            if (children.get(i).c == c) {
                return i;
            }
        }
        return -1;
    }

    // 方法二 在字典树中判断是否包含指定的单词
    // O(n*k)
    public boolean contains(String word) {
        Node curr = root;
        for (Character c : word.toCharArray()) {
            int index = containsChar(curr.list, c);
            if (index == -1) {
                // 不存在某个字符，直接返回 false
                return false;
            }
            // cur 一定要往后移动
            curr = curr.list.get(index);
        }
        // 经过上面的 for 之后，都没有返回 false，则是来到最后一个节点的位置
        // 需要判断最后一个字符是否是单词的结尾
        return curr.isWord;
    }

    public static void main(String[] args) {

        test(); // 2

        Trie trie = new Trie();
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
