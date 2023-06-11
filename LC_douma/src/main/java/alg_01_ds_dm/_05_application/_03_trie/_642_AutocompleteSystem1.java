package alg_01_ds_dm._05_application._03_trie;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2023-03-26 13:18
 * @Version 1.0
 */
public class _642_AutocompleteSystem1 {
    class SentenceInfo {
        String content;
        int time;

        SentenceInfo(String content, int time) {
            this.content = content;
            this.time = time;
        }
    }

    // KeyPoint 优化
    // 修改数据存储的数据结构，不将数据存储在 HashMap 中，而是将其存在字典树中，需要创建字典树的节点
    class TrieNode {
        Map<Character, TrieNode> map;
        // 将 times 存在结尾字符的 TrieNode 的 times 中，标识结尾位置
        // 1.若 times = 0，说明该单词一次没有出现过，则不是结尾节点
        // 2.若 times > 0，说明该单词至少出现过一次，则是结尾字符节点
        int times = 0;

        public TrieNode() {
            map = new HashMap<>();
        }
    }

    // 创建 root 节点
    private TrieNode root;
    // 记录用户当前的输入
    private String currSentence = "";

    public _642_AutocompleteSystem1(String[] sentences, int[] times) {
        root = new TrieNode();
        // 构建前缀树，调用 insert 方法
        for (int i = 0; i < sentences.length; i++) {
            insert(sentences[i], times[i]);
        }
    }

    // 构建前缀树
    public void insert(String str, int times) {
        TrieNode curr = root;
        for (char c : str.toCharArray()) {
            if (!curr.map.containsKey(c)) {
                curr.map.put(c, new TrieNode());
            }
            curr = curr.map.get(c);
        }
        // 若到 str 最后一个字符，则将 times 累加，表示出现次数
        curr.times += times;
    }

    // 利用前缀树进行查找
    public List<String> input(char c) {
        List<String> res = new ArrayList<>();
        // 用户输入完成一个句子
        if (c == '#') {
            // 更新这个句子总的被搜索次数
            insert(currSentence, 1);
            // 清除用户当前输入
            currSentence = "";
        } else {
            // 1. 将当前输入字符拼接到当前句子
            currSentence += c;

            // 2. 在前缀树中，找到所有以当前输入字符串开头的句子
            // 从字典树中找到包含 currSentence 为前缀的所有句子，后续还要进行排序
            List<SentenceInfo> list = search(currSentence);

            // 3. 拿到被搜索次数排名前 3 的句子
            Collections.sort(list, (o1, o2) -> o1.time == o2.time ?
                    o1.content.compareTo(o2.content) :
                    o2.time - o1.time);

            for (int i = 0; i < Math.min(3, list.size()); i++) {
                res.add(list.get(i).content);
            }
        }
        return res;
    }

    // 从当前前缀树中搜索以 s 开头的所有的句子
    public List<SentenceInfo> search(String s) {
        List<SentenceInfo> list = new ArrayList<>();

        // 1. 找到前缀
        TrieNode curr = root;
        for (char c : s.toCharArray()) {
            if (!curr.map.containsKey(c)) {
                // 没有找到前缀，返回 [] 集合
                return list;
            }
            curr = curr.map.get(c);
        }

        // for 循环结束，curr 就是前缀的最后一个字符，包括最后一个位置 cur
        // 如：前缀 "do"，for 循环结束，cur 在 o 位置

        // 2. 找到以前缀结尾的所有的字符串 => 深度优先遍历 dfs 实现
        // 若前缀 s："do"，则前缀结尾的所有的字符串："do"，"door","does"，"dog"
        dfs(curr, s, list);
        return list;
    }

    // dfs 中添加 String s 形参，在深度优先遍历中不断拼接字符
    // 最后到递归边界，将满足结果 String，添加到 list 中
    // dfs 返回为空即可，list 是外部传入，修改之后外部是能看到的，不需要再去返回
    private void dfs(TrieNode curr, String s, List<SentenceInfo> list) {
        // 当前节点是叶子节点，将其加入结果集合中
        if (curr.times > 0) {
            // KeyPoint add 返回为 boolean 值
            list.add(new SentenceInfo(s, curr.times));
        }

        // 遍历当前节点 curr 的子节点，当前节点字符添加到字符串中
        // 本质：多叉树分支遍历
        for (char c : curr.map.keySet()) {
            // curr 移动到子节点
            // 拼接字符时，注意顺序，s 在前，c 在后
            dfs(curr.map.get(c), s + c, list);
        }
    }

    public static void main(String[] args) {
        String[] sentences = {"i love you", "island", "ironman", "i love leetcode"};
        int[] times = {5, 3, 2, 2};
        _642_AutocompleteSystem1 test = new _642_AutocompleteSystem1(sentences, times);
        System.out.println(test.input('i')); // [i love you, island, i love leetcode]
        System.out.println(test.input(' ')); // [i love you, i love leetcode]
        System.out.println(test.input('a')); // []
        System.out.println(test.input('#')); // []
    }
}
