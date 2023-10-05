package alg_02_train_wyj._21_day_综合应用二;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-05-26 19:21
 * @Version 1.0
 */
public class _07_Trie2 {

    private class Node {
        Map<Character, Node> map;
        boolean isEnd;

        public Node() {
            map = new HashMap<>();
            isEnd = false;
        }
    }

    Node root;

    public _07_Trie2() {
        root = new Node();
    }

    public void add(String word) {
        Node cur = root;
        for (char c : word.toCharArray()) {
            if (!cur.map.containsKey(c)) {
                cur.map.put(c, new Node());
            }
            cur = cur.map.get(c);
        }
        cur.isEnd = true;
    }

    public boolean contains(String word) {
        Node cur = root;
        for (char c : word.toCharArray()) {
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
