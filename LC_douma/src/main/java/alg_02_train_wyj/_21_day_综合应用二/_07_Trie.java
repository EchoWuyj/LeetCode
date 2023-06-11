package alg_02_train_wyj._21_day_综合应用二;

import alg_01_ds_dm._05_application._03_trie.Trie;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-05-26 18:42
 * @Version 1.0
 */
public class _07_Trie {
    public static int countStarWithStr(String[] words, String prefix) {
        int count = 0;
        for (String word : words) {
            if (word.startsWith(prefix)) {
                count++;
            }
        }
        return count;
    }

    public static void test() {
        String[] strArr = {"big", "pat", "bigger", "dog", "door"};
        System.out.println(countStarWithStr(strArr, "do")); // 2
    }

    class Node {
        private Character c;
        private List<Node> children;
        private boolean isWord;

        public Node(Character c) {
            this.c = c;
            this.children = new ArrayList<>();
            this.isWord = false;
        }
    }

    private Node root;

    public _07_Trie() {
        this.root = new Node('/');
    }

    public void add(String word) {
        Node cur = root;
        for (char c : word.toCharArray()) {
            int index = containChar(cur.children, c);
            if (index == -1) {
                cur.children.add(new Node(c));
                index = cur.children.size() - 1;
            }
            cur = cur.children.get(index);
        }
        cur.isWord = true;
    }

    private int containChar(List<Node> children, char c) {
        int n = children.size();
        for (int i = 0; i < n; i++) {
            if (children.get(i).c == c) {
                return i;
            }
        }
        return -1;
    }

    public boolean contain(String word) {
        Node cur = root;
        for (char c : word.toCharArray()) {
            int index = containChar(cur.children, c);
            if (index == -1) return false;
            cur = cur.children.get(index);
        }
        return cur.isWord;
    }

    public static void main(String[] args) {
        test();

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
