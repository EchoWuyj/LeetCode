package alg_01_ds_dm._05_application._03_trie;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-03-26 11:46
 * @Version 1.0
 */
public class Trie1 {

    private class Node {
        /*
            字符和节点映射关系 => 类似多叉树结构 =>
                                                     A
                                                   / | \
                                                  B  C  D
                                                / | \ \
                                               E  F G  H

            => 每个节点可以有多个子节点，每个子节点可以有多个子子节点
            => 总结：数据结构选择，影响程序操作的性能，故在不同的场景下
                    选择合适的数据结构，才能使得算法性能最好
        */

        // key => 字符
        // value => 节点
        // Map 存储 "当前节点" + "子节点(多个)"
        //     A
        //  ↙ ↓ ↘  => 这 4 个节点都能存在 Map 中
        //  B  C  D
        Map<Character, Node> map;
        // 标识这个节点是否是一个单词最后一个字符
        boolean isWord;

        Node() {
            map = new HashMap<>();
            isWord = false;
        }
    }

    // 定义 root
    private Node root;

    public Trie1() {
        this.root = new Node();
    }

    // 添加单词 -> O(n)
    public void add(String word) {
        Node curr = root;
        for (Character c : word.toCharArray()) { // O(n)
            if (!curr.map.containsKey(c)) {
                curr.map.put(c, new Node());
            }
            // cur 指针移动到字符 c 对应的节点 node
            curr = curr.map.get(c);
        }
        curr.isWord = true;
    }

    // 判断是否包含指定的单词 -> O(n)
    public boolean contains(String word) {
        Node curr = root;
        for (Character c : word.toCharArray()) { // O(n)
            if (!curr.map.containsKey(c)) {
                return false;
            }
            curr = curr.map.get(c);
        }
        return curr.isWord;
    }

    public static void main(String[] args) {
        Trie1 trie = new Trie1();
        trie.add("big");
        trie.add("pat");
        trie.add("bigger");
        trie.add("dog");
        trie.add("door");

        System.out.println(trie.contains("biggere")); // false
        System.out.println(trie.contains("dog")); // true
        System.out.println(trie.contains("dogddd")); // false
    }
}
