package alg_02_train_wyj._21_day_综合应用二;

/**
 * @Author Wuyj
 * @DateTime 2023-05-26 20:11
 * @Version 1.0
 */
public class _08_208_implement_trie_prefix_tree1 {

    private class Node {
        Node[] list;
        boolean isEnd;

        Node() {
            list = new Node[26];
            isEnd = false;
        }
    }

    private Node root;

    public _08_208_implement_trie_prefix_tree1() {
        root = new Node();
    }

    public void insert(String word) {
        Node cur = root;
        for (char c : word.toCharArray()) {
            if (cur.list[c - 'a'] == null) {
                cur.list[c - 'a'] = new Node();
            }
            cur = cur.list[c - 'a'];
        }
        cur.isEnd = true;
    }

    public boolean search(String word) {
        Node cur = root;
        for (char c : word.toCharArray()) {
            if (cur.list[c - 'a'] == null) {
                return false;
            }
            cur = cur.list[c - 'a'];
        }
        return cur.isEnd;
    }

    public boolean startsWith(String prefix) {
        Node cur = root;
        for (char c : prefix.toCharArray()) {
            if (cur.list[c - 'a'] == null) {
                return false;
            }
            cur = cur.list[c - 'a'];
        }
        return true;
    }
}
