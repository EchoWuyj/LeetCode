package alg_02_train_dm._29_day_动态规划四_二刷;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author Wuyj
 * @DateTime 2023-06-22 9:52
 * @Version 1.0
 */
public class _01_139_word_break2 {

    //                       douma
    //       ↙       ↓        ↘         ↘           ↘
    // [4,5) a    [3,5) ma   [3,5) dou  [1,5)doum    [0,5) douma
    // => 父节点 start 索引 即为 子节点 end 索引

    // KeyPoint 方法二 dfs + 记忆化搜索
    //          => 从最后一个字符，从后往前，以不同位置结尾，将字符串成拆分不同字符
    public boolean wordBreak3(String s, List<String> wordDict) {
        return dfs(s, new HashSet<>(wordDict), s.length(), new HashMap<>());
    }

    // 递归函数含义：以第 index 个字符结尾的子串，是否可以被空格拆分成字典中出现的单词
    private boolean dfs(String s, Set<String> wordDict,
                        int index, HashMap<Integer, Boolean> memo) {

        // 以 0 为结束位置，"" 空串
        if (index == 0) return true;
        if (memo.containsKey(index)) return memo.get(index);
        // 传入父节点 index = 5 (结尾索引)
        // => for 循环中，使用 start 表示开始索引，[4,5)，[3,5)，[2,5)，[1,5)，[0,5)
        // => 以上都是可能的子节点区间
        // 父节点 start 索引 => 子节点 end 索引
        for (int start = index - 1; start >= 0; start--) {
            if (!wordDict.contains(s.substring(start, index))) continue;
            if (dfs(s, wordDict, start, memo)) {
                memo.put(index, true);
                return true;
            }
        }
        memo.put(index, false);
        return false;
    }

    //  KeyPoint dfs + 记忆化搜索 => 动态规划 (从左往右)
    public boolean wordBreak4(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);

        int n = s.length();

        // 1.定义状态
        // dp[i] 表示以第 i 个字符结尾的子串，是否可以被空格拆分成字典中出现的单词
        boolean[] dp = new boolean[n + 1];

        // 2.初始化
        // 因为空字符串总是字典的一部分，dp 数组剩余的元素都初始化为 false
        dp[0] = true;

        // 3.状态转移方程
        // i 从 1 开始
        // dp[0] 已知，则必然从数组中前往后推
        for (int i = 1; i <= n; i++) {
            // 通过 j 实现切割字符串成子串，有两种方向可以选择，这 2 种方式都是可以
            // 1. j 从后往前 => int j = i - 1; j >= 0; j--
            // 2. j 从前往后 => int j = 0; j < i; j++ => j 必须严格小于 i，否则截取结果为""
            for (int j = i - 1; j >= 0; j--) {
                // 若 dp[j] == true => [0,j) 在字典中，且 s[j, i) 存在于字典中
                // => 那么 dp[i] = true
                if (!dict.contains(s.substring(j, i))) continue;
                if (dp[j]) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }

    // 总结：字符串类型 dp => 一般就是以 i 字符'开头'或'结尾'
    // 1.以第 i 个字符开头的子串，分析是否有符合要求
    //  => 第 i 个字符开头的所有子串，必然在 i 字符的右边
    //  => dp 状态转移方向：从后往前，计算 右边状态 推导 左边状态 => 短字串 推导 长子串
    //  => 推荐使用

    // 2.以第 i 个字符结尾的子串，分析是否有符合要求
    //  => 同理，但是不符合常规思维，不推荐
}
