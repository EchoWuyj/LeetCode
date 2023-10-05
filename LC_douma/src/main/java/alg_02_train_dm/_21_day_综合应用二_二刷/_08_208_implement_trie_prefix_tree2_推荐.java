package alg_02_train_dm._21_day_综合应用二_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-08-03 18:22
 * @Version 1.0
 */

// KeyPoint 优化
// 1.充分利用题目的条件：输入都是由小写字母 a-z 构成的
// 2.将 HashMap 替换成固定大小的数组，单使用数组更加节省空间，因为 HashMap 是数组 + 链表结构
public class _08_208_implement_trie_prefix_tree2_推荐 {
    private class Node {
        // a-z 26 个字符
        // a -> 0, b -> 1,..., z -> 26
        // 字符转数组索引，a - a = 0，b - a = 1

        // index => 子字符 -'a' 对应索引位置
        // value => Node 节点
        // 注意： Node[c-'a'] != null，说明子字符存在，否则是不存在的
        Node[] list;
        // 标识这个节点是否是一个单词最后一个字符
        boolean isEnd;

        Node() {
            // 一共就 26 字母，如果已经有字符的，不会再去创建了，而是接在后面
            list = new Node[26];
            isEnd = false;
        }
    }

    private Node root;

    public _08_208_implement_trie_prefix_tree2_推荐() {
        root = new Node();
    }

    public void insert(String word) {
        Node cur = root;
        for (char c : word.toCharArray()) {
            if (cur.list[c - 'a'] == null) {
                // 索引表示字符，直接 new 一个 Node 即可
                // 只要 Node[c-'a'] != null，说明子字符存在
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
