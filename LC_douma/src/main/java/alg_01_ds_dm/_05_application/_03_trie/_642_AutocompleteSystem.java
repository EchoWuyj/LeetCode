package alg_01_ds_dm._05_application._03_trie;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2023-03-26 13:18
 * @Version 1.0
 */
public class _642_AutocompleteSystem {
    /*
        642. 设计搜索自动补全系统
        
        为搜索引擎设计一个搜索自动补全系统。用户会输入一条语句(最少包含一个字母，以特殊字符 '#' 结尾)。
        给定一个字符串数组 sentences 和一个整数数组 times ，长度都为 n。
        其中 sentences[i] 是之前输入的句子，times[i] 是该句子输入的相应次数，
        对于除 '#' 以外的每个输入字符，返回前 3 个历史热门句子，这些句子的前缀与已经输入的句子的部分相同。

        下面是详细规则：
        1 一条句子的热度定义为历史上用户输入这个句子的总次数。
        2 返回前 3 的句子需要按照热度从高到低排序(第一个是最热门的)。
          如果有多条热度相同的句子，请按照 ASCII 码的顺序输出(ASCII 码越小排名越前)。
        3 如果满足条件的句子个数少于 3 ，将它们全部输出。
        4 如果输入了特殊字符，意味着句子结束了，请返回一个空集合。

        实现 AutocompleteSystem 类：
        AutocompleteSystem(String[] sentences, int[] times): 使用数组sentences 和 times 对对象进行初始化。
        List<String> input(char c) 表示用户输入了字符 c。
        如果 c == '#' ，则返回空数组 [] ，并将输入的语句存储在系统中。
        返回前 3 个历史热门句子，这些句子的前缀与已经输入的句子的部分相同。如果少于 3 个匹配项，则全部返回。

        例子:
        操作:AutocompleteSystem(["i love you"， "island"，"ironman"， "i love leetcode"]， [5,3,2,2])
        系统已经追踪到以下句子及其对应的时间:
        "i love you" : 5 times
        "island" : 3 times
        "ironman" : 2 times
        "i love leetcode" : 2 times

        现在，用户开始另一个搜索:
        操作:输入('i')
        输出:["i love you"，"island"，"i love leetcode"]
        解释:
        有四个句子有前缀 'i'。其中，"ironman"和 "i love leetcode" 有着相同的热度。
        既然 " " ASCII码为 32，"r" ASCII码为 114，那么 "i love leetcode" 应该在 "ironman" 前面。
        此外，我们只需要输出前 3 个热门句子，所以 "ironman" 将被忽略

        操作:输入(' ')
        输出:["i love you"，"i love leetcode"]
        解释:
        只有两个句子有前缀"i"和" "

        操作:输入('a')
        输出:[]
        解释:
        没有以"i a"为前缀的句子。

        操作:输入("#")
        输出:[]
        解释:
        用户完成输入后，在系统中将句子"i a"保存为历史句。下面的输入将被计算为新的搜索。

        注意:
        输入的句子总是以字母开头，以"#"结尾，两个单词之间只有一个空格。
        要搜索的完整句子不会超过100个。包括历史数据在内的每句话的长度不会超过100句。
        在编写测试用例时，即使是字符输入，也请使用双引号而不是单引号。
        请记住重置在 AutocompleteSystem 类中声明的类变量，因为静态/类变量是跨多个测试用例持久化的
     */

    // 封装信息
    // sentence + time => SentenceInfo
    private class SentenceInfo {
        String content;
        int time;

        SentenceInfo(String content, int time) {
            this.content = content;
            this.time = time;
        }
    }

    // 记录每个句子被搜索的次数，很明显的映射关系，使用 map 存储
    // key -> sentence
    // value -> times
    private Map<String, Integer> map;
    // 记录用户当前的输入
    private String currSentence = "";

    // KeyPoint 方法一 构造方法
    public _642_AutocompleteSystem(String[] sentences, int[] times) {
        map = new HashMap<>();
        for (int i = 0; i < sentences.length; i++) {
            map.put(sentences[i], times[i]);
        }
    }

    // KeyPoint 方法二 input 方法
    public List<String> input(char c) {
        List<String> res = new ArrayList<>();
        // 用户输入完成一个句子
        if (c == '#') {
            // 更新这个句子总的被搜索次数
            map.put(currSentence, map.getOrDefault(currSentence, 0) + 1);
            // 清除用户当前输入
            // 为了提高性能，最好使用 StringBuilder，效率比字符串拼接要高
            currSentence = "";
        } else {
            // 1. 将当前输入字符拼接到当前句子
            // 输入该字符之前，还需要记录用户之前输入字符
            currSentence += c;

            // 2. 找到所有以当前输入字符串开头的句子
            List<SentenceInfo> list = new ArrayList<>();
            // KeyPoint 性能优化
            // 在 map 中找相应的 sentence 是线性查找
            // 其中涉及前缀查找，通过 trie 前缀树来进行优化，提高性能
            // O(n*k)
            for (String sentence : map.keySet()) {
                if (sentence.startsWith(currSentence)) {
                    // 因为 sentence 涉及排名，故还需要记录 sentence 出现的次数，
                    // 为了后续方便排序，将这两个信息封装成 SentenceInfo
                    list.add(new SentenceInfo(sentence, map.get(sentence)));
                }
            }

            // 3. 拿到被搜索次数排名前 3 的句子
            // 排序不是 O(nlogn)，list 数据规模达不到 n，时间复杂度 < O(n*k)
            Collections.sort(list, (o1, o2) -> o1.time == o2.time ?
                    // ASCII 码越小排名越前
                    o1.content.compareTo(o2.content) :
                    o2.time - o1.time);

            // 通过 Math.min 实现
            // 1.不足 3 取全部
            // 2.大于等于 3，则是取 3
            for (int i = 0; i < Math.min(3, list.size()); i++) {
                res.add(list.get(i).content);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String[] sentences = {"i love you", "island", "ironman", "i love leetcode"};
        int[] times = {5, 3, 2, 2};
        _642_AutocompleteSystem test = new _642_AutocompleteSystem(sentences, times);
        System.out.println(test.input('i')); // [i love you, island, i love leetcode]
        System.out.println(test.input(' ')); // [i love you, i love leetcode]
        System.out.println(test.input('a')); // []
        System.out.println(test.input('#')); // []
    }
}
