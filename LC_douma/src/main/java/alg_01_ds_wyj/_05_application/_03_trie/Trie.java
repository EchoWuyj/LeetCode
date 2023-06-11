package alg_01_ds_wyj._05_application._03_trie;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-03-27 12:28
 * @Version 1.0
 */
public class Trie {

    public int countStarWithStr(String[] words, String prefix) {
        int count = 0;
        for (int i = 0; i < words.length; i++) {
            if (words[i].startsWith(prefix)) {
                count++;
            }
        }
        return count;
    }

    class Node {
        private Character c;
        List<Node> children;
        boolean isWord;

        public Node(Character c) {
            this.c = c;
            this.children = new ArrayList<>();
            this.isWord = false;
        }
    }

    private Node root;

    public Trie() {
        this.root = new Node('/');
    }

    public void add(String word) {
        Node cur = root;
        for (Character c : word.toCharArray()) {
            int index = containsChar(cur.children, c);
            if (index == -1) {
                cur.children.add(new Node(c));
                index = cur.children.size() - 1;
            }
            cur = cur.children.get(index);
        }
        cur.isWord = true;
    }

    public int containsChar(List<Node> children, Character character) {
        for (int i = 0; i < children.size(); i++) {
            if (children.get(i).c == character) {
                return i;
            }
        }
        return -1;
    }

    public boolean contains(String word) {
        Node cur = root;
        for (Character c : word.toCharArray()) {
            int index = containsChar(cur.children, c);
            if (index == -1) {
                return false;
            }
            cur = cur.children.get(index);
        }
        return cur.isWord;
    }

    public static void main(String[] args) {
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
