package alg_02_train_dm._21_day_综合应用二_二刷;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-08-03 18:28
 * @Version 1.0
 */
public class _07_Trie2 {

      /*
         前缀树(字典树)
          1.每个节点还是 Node 类型
          2.每个节点中 Map 集合结构，维护当前字符和子节点(可能多个)关系
            Map<Character, Node>
            key => 字符 =>  A
            value => 节点 => B C D

                               A
                            ↙ ↓ ↘
                            B  C  D

          3.字符和节点映射关系 => 类似多叉树结构

                     A
                   / | \
                  B  C  D
                / | \ \
               E  F G  H

           => 每个节点可以有多个子节点，每个子节点可以有多个子子节点
           => 总结：数据结构选择，影响程序操作的性能，故在不同的场景下
                    选择合适的数据结构，才能使得算法性能最好
        */

    private class Node {
        Map<Character, Node> map;
        // 标识这个节点是否是一个单词最后一个字符
        boolean isEnd;

        Node() {
            map = new HashMap<>();
            isEnd = false;
        }
    }

    // 定义 root
    private Node root;

    public _07_Trie2() {
        // root 可以为空，只是用来标识
        this.root = new Node();
    }

    // 添加单词 -> O(n)
    public void add(String word) {
        Node cur = root;
        for (char c : word.toCharArray()) { // O(n)
            if (!cur.map.containsKey(c)) {
                cur.map.put(c, new Node());
            }
            // cur 指针移动到字符 c 对应的节点 node
            cur = cur.map.get(c);
        }
        cur.isEnd = true;
    }

    // 判断是否包含指定的单词 -> O(n)
    public boolean contains(String word) {
        Node cur = root;
        for (Character c : word.toCharArray()) { // O(n)
            if (!cur.map.containsKey(c)) {
                return false;
            }
            cur = cur.map.get(c);
        }
        return cur.isEnd;
    }

    public static void main(String[] args) {
        _07_Trie2 trie = new _07_Trie2();
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
