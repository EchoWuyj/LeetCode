package alg_02_train_dm._26_day_动态规划一_二刷._10_516_LongestPalindromeSubseq;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-06-05 15:28
 * @Version 1.0
 */
public class _10_516_LongestPalindromeSubseq1 {
    /*
        516. 最长回文 子序列
        给你一个字符串 s ，找出其中最长的回文子序列，并返回该序列的长度。
        子序列定义为：不改变剩余字符顺序的情况下，
                     删除某些字符或者不删除任何字符形成的一个序列

        子序列：可以不连续
        子数组：必须连续(数组比连续)

        示例 1：
        输入：s = "bbbab"
        输出：4
        解释：一个可能的最长回文子序列为 "bbbb" 。

        示例 2：
        输入：s = "cbbd"
        输出：2
        解释：一个可能的最长回文子序列为 "bb" 。

        提示：
        1 <= s.length <= 1000
        s 仅由小写英文字母组成

     */

     /*
          字符串 "bbbab"
          字符数组 b b b a b
          索引     0 1 2 3 4
                                             [0,b]
                                ↙              ↙         ↘          ↘
                            [1,b]            [2,b]         [3,a]       [4,b]
                       ↙     ↓     ↘         ↓   ↘         ↓
                    [2,b]  [3,a]    [4,b]   [3,a]  [4,b]   [4,b]
                   ↙  ↘     ↓                ↓
               [3,a]  [4,b] [4,b]            [4,b]
                 ↓
               [4,b]

           1.抽象树形结构，在 "bbbab" 中选择节点时：
             因为子序列不要求连续，索引可以不连续，中间可以间断，故每次只是选择一个字符
             子序列有顺序要求，得是从前往后，故索引不能回退， 即：子节点开始索引必然大于父节点结束索引

           2.抽象树形结构中，每个节点，每一段路径，都是 "bbbab" 的一个子序列
             而不是得从 叶子节点 到 root 节点，整个路径才是一个子序列

             [b, bb, bbb, bbba, bbbab, bbbb, bba, bbab, bbb, bb, bba, bbab, bbb, ba, bab, bb,
              b, bb, bba, bbab, bbb, ba, bab, bb, b, ba, bab, bb, a, ab, b]

           3.区别：子序列和子数组不同的树形结构，节点定义的逻辑不同

     */

    // KeyPoint 准备工作：求字符串所有：子序列
    // 时间复杂度指数级别，1 <= s.length <= 1000，必然超时
    public List<String> subSeqs(String s) {
        List<String> res = new ArrayList<>();
        findSubSeq(s, 0, "", res);
        return res;
    }

    // 一个字符串子序列(一个数组子序列)
    // => 本质：求字符串(数组)，类似于：求子集
    // => 类比：09_78_Subsets1
    private void findSubSeq(String s, int index, String subSeq, List<String> res) {
        // 将 "" 排除在外
        // 此外每次添加一个子节点形成的新组合，都是一个子序列，需要将其添加到 res 中
        if (index != 0) res.add(subSeq);
        // index 参数用于剪枝
        for (int i = index; i < s.length(); i++) {
            // 子节点选择字符，必然是从 i+1 开始的，子节点开始索引必然大于父节点结束索引
            // subSeq + s.charAt(i) 每个字符拼接，都将其加入到 res 中，从而保证子集完整
            findSubSeq(s, i + 1, subSeq + s.charAt(i), res);
        }
    }

    public static void main(String[] args) {
        System.out.println(new _10_516_LongestPalindromeSubseq1().subSeqs("bbbab"));
    }
}
