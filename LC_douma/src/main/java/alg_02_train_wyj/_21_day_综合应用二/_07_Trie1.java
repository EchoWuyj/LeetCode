package alg_02_train_wyj._21_day_综合应用二;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-05-26 18:42
 * @Version 1.0
 */
public class _07_Trie1 {

    public static int countStr(String[] words, String prefix) {
        int count = 0;
        int n = words.length;
        for (int i = 0; i < n; i++) {
            if (words[i].startsWith(prefix)) {
                count++;
            }
        }
        return count;
    }

    // for test
    public static void test() {
        String[] strArr = {"big", "pat", "bigger", "dog", "door"};
        System.out.println(countStr(strArr, "do")); // 2
    }

    private class Node {
        Character c;
        List<Node> list;
        boolean isEnd;

        public Node(Character c) {
            this.c = c;
            this.list = new ArrayList<>();
            this.isEnd = false;
        }
    }

    private Node root;

    public _07_Trie1() {
        this.root = new Node('/');
    }

    public void add(String word) {
        Node cur = root;
        for (char c : word.toCharArray()) {
            int index = containsChar(cur.list, c);
            if (index == -1) {
                cur.list.add(new Node(c));
                index = cur.list.size() - 1;
            }
            cur = cur.list.get(index);
        }
        cur.isEnd = true;
    }

    private int containsChar(List<Node> list, char c) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (list.get(i).c.equals(c)) {
                return i;
            }
        }
        return -1;
    }

    public boolean contains(String word) {
        Node cur = root;
        for (char c : word.toCharArray()) {
            int index = containsChar(cur.list, c);
            if (index == -1) {
                return false;
            }
            cur = cur.list.get(index);
        }
        return cur.isEnd;
    }

    public static void main(String[] args) {
        test();

        _07_Trie1 trie = new _07_Trie1();
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
