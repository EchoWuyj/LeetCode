package alg_02_train_dm._21_day_综合应用二_二刷;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2023-08-03 18:25
 * @Version 1.0
 */
public class _09_642_AutocompleteSystem2 {

    class SentenceInfo {
        String sentence;
        int time;

        public SentenceInfo(String sentence, int time) {
            this.sentence = sentence;
            this.time = time;
        }
    }

    // KeyPoint 优化
    // 修改数据存储的数据结构，不将数据存储在 HashMap 中，而是将其存在字典树中，需要创建字典树的节点
    class Node {
        Map<Character, Node> map;
        // 将 times 存在结尾字符的 Node 的 times 中，标识结尾位置
        // 1.若 times = 0，说明该单词一次没有出现过，则不是结尾节点
        // 2.若 times > 0，说明该单词至少出现过一次，则是结尾字符节点
        int times = 0;

        public Node() {
            map = new HashMap<>();
        }
    }

    // 创建 root 节点
    private Node root;
    // 记录用户当前的输入
    private String input = "";

    public _09_642_AutocompleteSystem2(String[] sentences, int[] times) {
        root = new Node();
        int n = sentences.length;
        // 调用 insert 方法，构建前缀树
        for (int i = 0; i < n; i++) {
            insert(sentences[i], times[i]);
        }
    }

    // 构建前缀树
    public void insert(String input, int times) {
        Node cur = root;
        for (char c : input.toCharArray()) {
            if (!cur.map.containsKey(c)) {
                cur.map.put(c, new Node());
            }
            cur = cur.map.get(c);
        }
        // 若到 input 最后一个字符，则将 times 累加，表示出现次数
        cur.times += times;
    }

    // 利用前缀树进行查找
    public List<String> input(char c) {
        List<String> res = new ArrayList<>();
        // 用户输入完成一个句子
        if (c == '#') {
            // 更新这个句子总的被搜索次数
            insert(input, 1);
            // 清除用户当前输入
            input = "";
        } else {
            // 1. 将当前输入字符拼接到当前句子
            input += c;
            // 2. 在前缀树中，找到所有以当前输入字符串开头的句子
            // 从字典树中找到包含 currSentence 为前缀的所有句子，后续还要进行排序
            List<SentenceInfo> list = search(input);

            // 3. 拿到被搜索次数排名前 3 的句子
            Collections.sort(list, (o1, o2) -> o1.time == o2.time ?
                    o1.sentence.compareTo(o2.sentence) :
                    o2.time - o1.time);

            for (int i = 0; i < Math.min(3, list.size()); i++) {
                res.add(list.get(i).sentence);
            }
        }
        return res;
    }

    // 函数功能：从当前前缀树中搜索以 input 开头的所有的句子
    public List<SentenceInfo> search(String input) {
        List<SentenceInfo> list = new ArrayList<>();

        // 1. 找到前缀
        Node cur = root;
        for (char c : input.toCharArray()) {
            if (!cur.map.containsKey(c)) {
                // 没有找到前缀，返回 [] 集合
                return list;
            }
            cur = cur.map.get(c);
        }

        // for 循环结束，cur 就是 input 最后一个字符
        // 如：前缀 "do"，在 for 循环结束，cur 在 o 位置

        // 2. 找到以 input 为前缀的所有的字符串 => 深度优先遍历 dfs 实现
        // 如：input："do"，则以 input 为前缀的所有的字符串："do"，"door","does"，"dog"
        dfs(cur, input, list);
        return list;
    }

    // dfs 中添加 String input 形参，在深度优先遍历中不断拼接字符
    // 最后到递归边界，将满足结果 String，添加到 list 中
    // KeyPoint dfs 返回为空即可，list 是外部传入，修改之后外部是能看到的，不需要再去返回 list
    private void dfs(Node cur, String input, List<SentenceInfo> list) {
        // 当前节点是叶子节点，将其加入结果集合中
        if (cur.times > 0) {
            // list 的 add 返回为 boolean 值
            list.add(new SentenceInfo(input, cur.times));
        }

        // 遍历当前节点 cur 的子节点，当前节点字符添加到字符串中
        // KeyPoint 本质：多叉树分支遍历
        // input："do"，且 cur 在 o 位置，找 o 的子节点
        //
        for (char c : cur.map.keySet()) {
            // cur 移动到子节点位置
            // 拼接字符时注意顺序，input 在前，子节点字符 c 在后
            dfs(cur.map.get(c), input + c, list);
        }
    }

    public static void main(String[] args) {
        String[] sentences = {"i love you", "island", "ironman", "i love leetcode"};
        int[] times = {5, 3, 2, 2};
        _09_642_AutocompleteSystem2 test = new _09_642_AutocompleteSystem2(sentences, times);
        System.out.println(test.input('i')); // [i love you, island, i love leetcode]
        System.out.println(test.input(' ')); // [i love you, i love leetcode]
        System.out.println(test.input('a')); // []
        System.out.println(test.input('#')); // []
    }
}
