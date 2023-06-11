package alg_02_train_wyj._21_day_综合应用二;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-05-26 19:47
 * @Version 1.0
 */
public class _08_208_implement_trie_prefix_tree {

    class Node {
        Map<Character, Node> map;
        boolean isWord;

        public Node() {
            map = new HashMap<>();
            isWord = false;
        }
    }

    Node root;

    public _08_208_implement_trie_prefix_tree() {
        root = new Node();
    }

    public void insert(String word) {
        Node cur = root;
        for (char c : word.toCharArray()) {
            if (!cur.map.containsKey(c)) {
                cur.map.put(c, new Node());
            }
            cur = cur.map.get(c);
        }
        cur.isWord = true;
    }

    public boolean search(String word) {
        Node cur = root;
        for (char c : word.toCharArray()) {
            if (!cur.map.containsKey(c)) {
                return false;
            }
            cur = cur.map.get(c);
        }
        return cur.isWord;
    }

    public boolean startsWith(String prefix) {
        Node cur = root;
        for (char c : prefix.toCharArray()) {
            if (!cur.map.containsKey(c)) {
                return false;
            }
            cur = cur.map.get(c);
        }
        return true;
    }
}
