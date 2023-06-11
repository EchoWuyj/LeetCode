package alg_01_ds_dm._05_application._03_trie;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-03-26 13:05
 * @Version 1.0
 */
public class _208_implement_trie_prefix_tree {
    /*
        208. 实现 Trie (前缀树)
        Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。
        这一数据结构有相当多的应用情景，例如自动补完和拼写检查

        请你实现 Trie 类：
        Trie() 初始化前缀树对象
        void insert(String word) 向前缀树中插入字符串 word
        boolean search(String word) 如果字符串 word 在前缀树中，返回 true(即在检索之前已经插入)，否则，返回 false
        boolean startsWith(String prefix) 如果之前已经插入的字符串 word 的前缀之一为 prefix ，返回 true，否则，返回 false

        示例：
        输入
        ["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
        [[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
        输出
        [null, null, true, false, true, null, true]

        解释
        Trie trie = new Trie();
        trie.insert("apple");
        trie.search("apple");   // 返回 True
        trie.search("app");     // 返回 False
        trie.startsWith("app"); // 返回 True
        trie.insert("app");
        trie.search("app");     // 返回 True

        提示：
        1 <= word.length, prefix.length <= 2000
        word 和 prefix 仅由小写英文字母组成
        insert、search 和 startsWith 调用次数 总计 不超过 3 * 104 次
     */

    // Node 自己定义
    private class Node {
        Map<Character, Node> map;
        boolean isWord;

        Node() {
            map = new HashMap<>();
            isWord = false;
        }
    }

    private Node root;

    public _208_implement_trie_prefix_tree() {
        root = new Node();
    }

    public void insert(String word) {
        Node curr = root;
        for (Character c : word.toCharArray()) {
            if (!curr.map.containsKey(c)) {
                curr.map.put(c, new Node());
            }
            curr = curr.map.get(c);
        }
        curr.isWord = true;
    }

    public boolean search(String word) {
        Node curr = root;
        for (Character c : word.toCharArray()) {
            if (!curr.map.containsKey(c)) {
                return false;
            }
            curr = curr.map.get(c);
        }
        return curr.isWord;
    }

    public boolean startsWith(String prefix) {
        Node curr = root;
        for (Character c : prefix.toCharArray()) { // O(n)
            // 从子节点中，查找是否包含当前字符 c
            if (!curr.map.containsKey(c)) {
                return false;
            }
            curr = curr.map.get(c);
        }
        // 经过几轮 for 循环，将 prefix 的每个字符都判断之后
        // 不需要判断最后一个字符为单词结尾，而是直接返回 true
        return true;
    }
}
