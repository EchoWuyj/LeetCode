package alg_02_train_dm._29_day_动态规划四;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-06-12 16:32
 * @Version 1.0
 */
public class _03_91_decode_ways {
    /*
        91. 解码方法
        一条包含字母 A-Z 的消息通过以下映射进行了 编码 ：

        'A' -> 1
        'B' -> 2
        ...
        'Z' -> 26

        要 解码 已编码的消息，所有数字必须基于上述映射的方法，反向映射回字母(可能有多种方法)

        例如，"11106" 可以映射为：
        "AAJF" ，将消息分组为 (1 1 10 6)
        "KJF" ，将消息分组为 (11 10 6)

        注意，消息不能分组为  (1 11 06) ，因为 "06" 不能映射为 "F" ，
        这是由于 "6" 和 "06" 在映射中并不等价。

        给你一个只含数字的 非空 字符串 s ，请计算并返回 解码 方法的 总数 。
        题目数据保证答案肯定是一个 32 位 的整数。

        示例 1：
        输入：s = "12"
        输出：2
        解释：它可以解码为 "AB"（1 2）或者 "L"（12）。

        示例 2：
        输入：s = "226"
        输出：3
        解释：它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。

        示例 3：
        输入：s = "0"
        输出：0
        解释：没有字符映射到以 0 开头的数字。
        含有 0 的有效映射是 'J' -> "10" 和 'T'-> "20" 。
        由于没有字符，因此没有有效的方法对此进行解码，因为所有数字都需要映射。

        示例 4：
        输入：s = "06"
        输出：0
        解释："06" 不能映射到 "F" ，因为字符串含有前导 0（"6" 和 "06" 在映射中并不等价）。

        提示：
        1 <= s.length <= 100
        s 只包含数字，并且可能包含前导零。

     */

    // 本质：单词拆分，11106，在不同的位置，使用空格隔开，拆分成符合条件的子串

    // KeyPoint 方法一 dfs + 记忆化搜索
    // 1 <= s.length <= 100，不使用记忆化搜索必然超时
    public int numDecodings(String s) {
        int n = s.length();
        // memo 最大索引为 n-1，不需要 n，若 index = n，则 dfs 已经递归返回了
        int[] memo = new int[n];
        // 初始化为 -1
        Arrays.fill(memo, -1);
        return dfs(s, 0, memo);
    }

    // 以第 index 个字符开头的子串能解码的个数
    private int dfs(String s, int index, int[] memo) {

        // 模板代码，需要在其基础上修改
//        if (index == s.length()) return 1;
//        int res = 0;
        // 将 for 循环展开，进行题目条件限制
//        for (int end = index + 1; end <= index + 2; end++) {
//            res += dfs2(s, end);
//        }
//        return res;

        if (index == s.length()) return 1;
        if (memo[index] != -1) return memo[index];

        // 特判：递的过程中，凡是遇到'0'，则无法解码，返回 0
        if (s.charAt(index) == '0') return 0;

        // 每个节点，最开始 res 为 0，依赖于左右子返回值，res 进行累加
        int res = 0;

        // 由于 A-Z (1-26) 字符集限制，每次选择字符最多 2 个，否则必然超过 26 的范围
        // 1.选择 1 个字符 => 左分支
        res += dfs(s, index + 1, memo);

        // 2.选择 2 个字符 => 右分支
        // 前提得保证有两个字符，否则没有右分支，index 最多为 s.length() - 2
        if (index < s.length() - 1) {
            int one = s.charAt(index + 1) - '0';
            int ten = (s.charAt(index) - '0') * 10;
            // 剪枝
            if (one + ten <= 26) {
                res += dfs(s, index + 2, memo);
            }
        }
        // 缓存
        memo[index] = res;
        return memo[index];
    }

    // KeyPoint dfs + 记忆化搜索 => 动态规划(从右往左)
    public int numDecodings2(String s) {
        int n = s.length();
        // dp[i]：表示以第 i 个字符开头的子串能解码的个数
        int[] dp = new int[n + 1];

        // 二叉树中叶子节点
        // => 字符数组 11106 最后位置索引越界位置
        // => 对应 dp 数组 n 索引状态
        dp[n] = 1;

        for (int i = n - 1; i >= 0; i--) {
            // 注意：不等于字符 '0'，不是数字 0，多次犯错了
            if (s.charAt(i) != '0') {
                dp[i] += dp[i + 1];
                if (i < n - 1) {
                    int one = s.charAt(i + 1) - '0';
                    int ten = (s.charAt(i) - '0') * 10;
                    if (one + ten <= 26) {
                        dp[i] += dp[i + 2];
                    }
                }
            }
        }
        return dp[0];
    }

    // KeyPoint 方法二 dfs + 记忆化搜索
    public int numDecodings3(String s) {
        int[] memo = new int[s.length() + 1];
        Arrays.fill(memo, -1);
        return dfs2(s, s.length(), memo);
    }

    // 以第 i 个字符结尾的子串能解码的个数
    private int dfs2(String s, int i, int[] memo) {
        if (i == 0) return 1;
        if (memo[i] != -1) return memo[i];

        int res = 0;
        if (s.charAt(i - 1) != '0') {
            res += dfs2(s, i - 1, memo);
        }
        if (i > 1 && s.charAt(i - 2) != '0') {
            int one = s.charAt(i - 1) - '0';
            int ten = (s.charAt(i - 2) - '0') * 10;
            if (one + ten <= 26) {
                res += dfs2(s, i - 2, memo);
            }
        }

        memo[i] = res;

        return memo[i];
    }

    // KeyPoint  dfs + 记忆化搜索 => 动态规划(从左往右)
    public int numDecodings4(String s) {
        int n = s.length();
        // dp[i]：表示以第 i 个字符结尾的子串能解码的个数
        int[] dp = new int[n + 1];

        dp[0] = 1;

        for (int i = 1; i <= n; ++i) {
            if (s.charAt(i - 1) != '0') {
                dp[i] += dp[i - 1];
            }

            if (i > 1 && s.charAt(i - 2) != '0') {
                int one = s.charAt(i - 1) - '0';
                int ten = (s.charAt(i - 2) - '0') * 10;
                if (one + ten <= 26) {
                    dp[i] += dp[i - 2];
                }
            }
        }
        return dp[n];
    }
}
