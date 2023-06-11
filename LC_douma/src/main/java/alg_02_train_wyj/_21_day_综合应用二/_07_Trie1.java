package alg_02_train_wyj._21_day_综合应用二;

import alg_01_ds_dm._05_application._03_trie.Trie1;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-05-26 19:21
 * @Version 1.0
 */
public class _07_Trie1 {
    private class Node {
        Map<Character, Node> children;
        boolean isWord;

        public Node() {
            children = new HashMap<>();
            isWord = false;
        }
    }

    private Node root;

    public _07_Trie1() {
        this.root = new Node();
    }

    public void add(String word) {
        Node cur = root;
        for (char c : word.toCharArray()) {
            if (!cur.children.containsKey(c)) {
                cur.children.put(c, new Node());
            }
            cur = cur.children.get(c);
        }
        cur.isWord = true;
    }

    public boolean contain(String word) {
        Node cur = root;
        for (char c : word.toCharArray()) {
            if (!cur.children.containsKey(c)) {
                return false;
            }
            cur = cur.children.get(c);
        }
        return cur.isWord;
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
