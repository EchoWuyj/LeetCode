package alg_02_train_dm._29_day_动态规划四;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author Wuyj
 * @DateTime 2023-06-12 16:30
 * @Version 1.0
 */
public class _01_139_word_break {
     /* 
        139. 单词拆分
        给定一个非空字符串 s 和一个包含非空单词的列表 wordDict，
        判定 s 是否可以被空格拆分为 一个 或 多个 在字典中出现的单词。
    
        说明：
        拆分时可以重复使用字典中的单词。
        你可以假设字典中没有重复的单词。
    
        示例 1：
        输入: s = "leetcode", wordDict = ["leet", "code"]
        输出: true
        解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
    
        示例 2：
        输入: s = "applepenapple", wordDict = ["apple", "pen"]
        输出: true
        解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
              注意你可以重复使用字典中的单词。
    
        示例 3：
        输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
        输出: false

        提示：
        1 <= s.length <= 300
        1 <= wordDict.length <= 1000
        1 <= wordDict[i].length <= 20
        s 和 wordDict[i] 仅有小写英文字母组成
        wordDict 中的所有字符串 互不相同

     */

    // 对原字符串进拆分成若干个单词，对于每个单词去 wordDict 中查找，查找是否存在
    // "douma" 每次选择字符，可以是 1，2，..，5 字符，划分成多叉树
    // 从 root 到 叶子节点 的路径节点，则是一种字符串的拆分方式
    // 每个节点都是原字符串的字串，通过开始 start 索引和 结束索引 end 标记

    //                                           douma
    //                          ↙         ↓      ↘         ↘              ↘
    //                  [0,1) d      [0,2) do    [0,3) dou  [0,4)doum   [0,5) douma
    //             ↙     ↓     ↓   ↘      ↘
    //    [1,2)  o       ou   oum  ouma
    //       ↙  ↓  ↘   ↓ ↘
    //      u   um  uma  m ma    ...
    //   ↙  ↘  ↓       ↓
    //   m  ma   a       a
    //   ↓
    //   a
    // 父节点 end 索引 => 子节点 start 索引

    // KeyPoint 方法一 dfs + 记忆化搜索 => 从前往后，拆分字符串成字符
    //          => 推荐使用方法一，更加好理解
    public boolean wordBreak1(String s, List<String> wordDict) {
        // List 转 HashSet，直接将 List 传入 HashSet 构造方法中
        return dfs(s, new HashSet<>(wordDict), 0, new HashMap<>());
    }

    // 递归含义：以第 index 个字符开头的子串，是否可以被空格拆分成字典中出现的单词 => 单个节点判断
    // 若从 root 到 叶子节点的路径节点，返回值都是 true，则存在一种字符串的拆分方式
    // 这里 memo 使用 HashMap 更好，若是使用 boolean 数组，默认为 false，和真的 false 混淆，存在歧义
    private boolean dfs(String s, Set<String> wordDict,
                        int index, HashMap<Integer, Boolean> memo) {

        // 叶子节点，截取空字符串 "" => 在 wordDict 中，即在 wordDict 中什么都不选
        if (index == s.length()) return true;
        // 查询缓存
        if (memo.containsKey(index)) return memo.get(index);
        // 父节点 end 索引，遍历范围：从 index + 1 到 s.length()
        // 因为截取，故可以取到 s.length()
        for (int end = index + 1; end <= s.length(); end++) {
            // 如 "d" 不在 wordDict ["dou","ma"]中，该子树直接剪枝，避免无效递归
            // 一定需要提前剪枝，提高 dfs 的性能
            // 注意：若 substring 的 start 和 end 一样，则截取空字符串 ""
            if (!wordDict.contains(s.substring(index, end))) continue;

            // 在递的过程中，存储缓存
            // => 经过上面 if 判断，wordDict 中存在 [index,end) 子串
            // => 存储：以第 index 个字符开头的子串，是否可以被空格拆分成字典中出现的单词
            memo.put(index, true);

            // 父节点 end 索引 => 子节点 start 索引
            if (dfs(s, wordDict, end, memo)) {
                return true;
            }
        }

        // 在归的过程中，存储缓存
        memo.put(index, false);
        return false;
    }

    // KeyPoint  dfs + 记忆化搜索 => 动态规划 (从右往左)
    // 从叶子节点到 root 节点 => 自底向上 => 动态规划转移方向
    public boolean wordBreak2(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        int n = s.length();

        // 1.定义状态
        // dp[i] 表示以第 i 个字符开头的子串，是否可以被空格拆分成字典中出现的单词
        boolean[] dp = new boolean[n + 1];

        // 2.初始化
        // 因为空字符串总是字典的一部分，dp 数组剩余的元素都初始化为 false
        dp[n] = true;

        // 3.状态转移方程 => 通过填表推导
        // dp[n] 已知，则必然从数组中后往前推
        for (int i = n - 1; i >= 0; i--) {
            // 通过 j 实现字符串子串的切割
            for (int j = i + 1; j <= n; j++) {
                // 若 s[i, j) 存在于字典中，且 dp[j] == true，则 dp[i] = true
                if (!dict.contains(s.substring(i, j))) continue;
                if (dp[j]) {
                    // 存在 [i,j) 保证前一半 + dp[j] 为 true 保证后一半
                    //  => 第 i 个字符开头的子串，存在可被空格拆分成字典中出现的单词
                    dp[i] = true;
                    // 跳出内层 for 循环，执行外层 for 循环，进行 i 下轮判断
                    break;
                }
            }
        }
        // 4.返回值
        return dp[0];
    }

    // KeyPoint 方法二 记忆化搜索 => 从后往前，拆分字符串成字符
    public boolean wordBreak3(String s, List<String> wordDict) {
        return dfs2(s, new HashSet<>(wordDict), s.length(), new HashMap<>());
    }

    // 以第 index 个字符结尾的子串，是否可以被空格拆分成字典中出现的单词
    private boolean dfs2(String s, Set<String> wordDict,
                         int index, HashMap<Integer, Boolean> memo) {
        if (index == 0) return true;
        if (memo.containsKey(index)) return memo.get(index);
        // 传入父节点 index = 5，for循环依次执行 [4,5)，[3,5)，[2,5)，[1,5)，[0,5) => 子节点
        // 父节点 start 索引 => 子节点 end 索引
        for (int start = index - 1; start >= 0; start--) {
            if (!wordDict.contains(s.substring(start, index))) continue;
            if (dfs2(s, wordDict, start, memo)) {
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
            // 通过 j 实现字符串子串的切割
            // 有两种方向可以选择，这 2 种方式都是可以
            // 1.j 从后往前 => int j = i - 1; j >= 0; j--
            // 2.j 从前往后 => int j = 0; j < i; j++
            for (int j = i - 1; j >= 0; j--) {
                // 如果 dp[j] == true 且 s(i, j] 存在于字典中，那么 dp[i] = true
                if (!dict.contains(s.substring(j, i))) continue;
                if (dp[j]) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }

    // 总结：字符串类型 dp => 一般就是以 i 字符开头或结尾

    // 1. 以第 i 个字符开头的子串，分析是否有符合要求
    //    => 第 i 个字符开头的所有子串，必然在 i 字符的右边
    //    => dp 状态转移方向：从后往前，计算右边状态推导左边状态
    //    => 推荐使用

    // 2. 以第 i 个字符结尾的子串，分析是否有符合要求
}


