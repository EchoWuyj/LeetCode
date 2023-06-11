package alg_01_ds_wyj._05_application._03_trie;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-03-27 12:28
 * @Version 1.0
 */
public class Trie1 {
    class Node {
        Map<Character, Node> children;
        boolean isWord;

        public Node() {
            this.children = new HashMap<>();
            isWord = false;
        }
    }

    private Node root;

    public Trie1() {
        this.root = new Node();
    }

    public void add(String word) {
        Node cur = root;
        for (Character c : word.toCharArray()) {
            if (!cur.children.containsKey(c)) {
                cur.children.put(c, new Node());
            }
            cur = cur.children.get(c);
        }
        cur.isWord = true;
    }

    public boolean contains(String word) {
        Node cur = root;
        for (Character c : word.toCharArray()) {
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
        System.out.println(trie.contains("pat")); // true
        System.out.println(trie.contains("patt")); // false
    }
}
