package alg_01_ds_dm._05_application._03_trie;

/**
 * @Author Wuyj
 * @DateTime 2023-03-26 13:09
 * @Version 1.0
 */

// 优化：充分利用题目的条件：输入都是由小写字母 a-z 构成的
// 将 HashMap 替换成固定大小的数组，单使用数组更加节省空间，因为 HashMap 是数组 + 链表结构
public class _208_implement_trie_prefix_tree1 {
    private class Node {
        // a-z 26 个字符
        // a -> 0, b -> 1,..., z -> 26
        // 字符转数组索引，a - a = 0，b - a = 1
        // 当前字符 => 数组索引
        // 数组值 => 节点
        // Node 的属性 children => 子节点
        Node[] list;
        // 标识这个节点是否是一个单词最后一个字符
        boolean isWord;

        Node() {
            // 一共就 26 字母，如果已经有字符的，不会再去创建了，而是接在后面
            list = new Node[26];
            isWord = false;
        }
    }

    private Node root;

    public _208_implement_trie_prefix_tree1() {
        root = new Node();
    }

    public void insert(String word) {
        Node curr = root;
        for (char c : word.toCharArray()) {
            if (curr.list[c - 'a'] == null) {
                // 索引表示字符，直接 new 一个 Node 即可
                curr.list[c - 'a'] = new Node();
            }
            curr = curr.list[c - 'a'];
        }
        curr.isWord = true;
    }

    public boolean search(String word) {
        Node curr = root;
        for (char c : word.toCharArray()) {
            if (curr.list[c - 'a'] == null) {
                return false;
            }
            curr = curr.list[c - 'a'];
        }
        return curr.isWord;
    }

    public boolean startsWith(String prefix) {
        Node curr = root;
        for (char c : prefix.toCharArray()) {
            if (curr.list[c - 'a'] == null) {
                return false;
            }
            curr = curr.list[c - 'a'];
        }
        return true;
    }
}
