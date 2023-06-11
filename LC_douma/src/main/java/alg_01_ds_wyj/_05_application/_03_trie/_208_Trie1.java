package alg_01_ds_wyj._05_application._03_trie;

/**
 * @Author Wuyj
 * @DateTime 2023-03-27 14:42
 * @Version 1.0
 */
public class _208_Trie1 {
    class Node {
        Node[] children;
        boolean isWord;

        public Node() {
            children = new Node[26];
            isWord = false;
        }
    }

    private Node root;

    public _208_Trie1() {
        root = new Node();
    }

    public void insert(String word) {
        Node cur = root;
        for (Character c : word.toCharArray()) {
            if (cur.children[c - 'a'] == null) {
                cur.children[c - 'a'] = new Node();
            }
            cur = cur.children[c - 'a'];
        }
        cur.isWord = true;
    }

    public boolean search(String word) {
        Node cur = root;
        for (Character c : word.toCharArray()) {
            if (cur.children[c - 'a'] == null) {
                return false;
            }
            cur = cur.children[c - 'a'];
        }
        return cur.isWord;
    }

    public boolean startsWith(String prefix) {
        Node cur = root;
        for (Character c : prefix.toCharArray()) {
            if (cur.children[c - 'a'] == null) {
                return false;
            }
            cur = cur.children[c - 'a'];
        }
        return true;
    }
}
