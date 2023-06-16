package alg_02_train_dm._26_day_动态规划一._14_44_WildcardMatch;

/**
 * @Author Wuyj
 * @DateTime 2023-06-06 11:08
 * @Version 1.0
 */
public class _14_44_WildcardMatch {
    /* 
           44. 通配符匹配
           给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。
        
           '?' 可以匹配任何 单个字符。
           '*' 可以匹配任意字符串(包括空字符串)。
           两个字符串完全匹配才算匹配成功。
        
           说明:
           s 可能为空，且只包含从 a-z 的小写字母。
           p 可能为空，且只包含从 a-z 的小写字母，以及字符 ? 和 *。
        
           示例 1:
           输入:
           s = "aa"
           p = "a"
           输出: false
           解释: "a" 无法匹配 "aa" 整个字符串。
        
           示例 2:
           输入:
           s = "aa"
           p = "*"
           输出: true
           解释: '*' 可以匹配任意字符串。
        
           示例 3:
           输入:
           s = "cb"
           p = "?a"
           输出: false
           解释: '?' 可以匹配 'c', 但第二个 'a' 无法匹配 'b'。
        
           示例 4:
           输入:
           s = "adceb"
           p = "*a*b"
           输出: true
           解释: 第一个 '*' 可以匹配空字符串, 第二个 '*' 可以匹配字符串 "dce".
                 * 可以匹配任意字符串 => "dce"
        
           示例 5:
           输入:
           s = "acdcb"
           p = "a* c?b"
           输出: false

           提示：
           0 <= s.length, p.length <= 2000
           s 仅由小写英文字母组成
           p 仅由小写英文字母、'?' 或 '*' 组成

    */

    // 返回 true，false 问题，且可以通过穷举求解，故可以考虑动态规划优化
    public boolean isMatch(String s, String p) {

        int m = s.length();
        int n = p.length();

        // 状态定义：dp[i][j] 表示 s 的前 i 个字符和 p 的前 j 个字符是否匹配
        // 学习列表的格式，行列对应为字符形式，方便进行匹配
        boolean[][] dp = new boolean[m + 1][n + 1];

        // 状态初始化

        // 空字符串和空字符串是匹配的
        dp[0][0] = true;
        for (int i = 1; i <= n; i++) {
            // 空字符串和 * 是匹配的
            // '*' 可能一上来就出现，此时是匹配的，需要特判
            if (dp[0][i - 1] && p.charAt(i - 1) == '*') {
                dp[0][i] = true;
            }
            // 对于第一列，没有设置初值，默认为 false
        }

        // 分成 5 种情况讨论

        // s： a c d c b
        //       i
        // p： a * c ? b
        //       j
        // 1.若 s.charAt(i - 1) == p.charAt(j - 1)，则 dp[i][j] = dp[i - 1][j - 1];

        // s： a c d c b
        //       i
        // p： a * c ? b
        //           j
        // 2.若 s.charAt(i - 1) != p.charAt(j - 1)，则 dp[i][j] = false

        // KeyPoint 关于 '*' 的两种情况，分别讨论

        // s： a | c d c b
        //         i
        // p： a | * c ? b
        //           j
        // 3.若 p.charAt(j - 1) == '*'，则 dp[i][j] = dp[i][j - 1]

        // s： a | c d c b
        //           i
        // p： a * | c ? b
        //           j
        // 4.若 p.charAt(j - 1) == '*'，则 dp[i][j] = dp[i - 1][j]
        // 'c' 字符已经被 '*' 匹配上了，需要靠考虑 'c' 之前的字符，是否和 'a *' 匹配

        // s： a c d c b
        //       i
        // p： a * c ? b
        //             j
        // 5.若 p.charAt(j - 1) == '?'，则 dp[i][j] = dp[i - 1][j - 1]

        // 状态转移
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i - 1) == p.charAt(j - 1)
                        || p.charAt(j - 1) == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
                }
            }
        }

        return dp[m][n];
    }
}
