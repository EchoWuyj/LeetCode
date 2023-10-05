package alg_02_train_dm._26_day_动态规划一_二刷._07_647_PalindromeSubstring;

/**
 * @Author Wuyj
 * @DateTime 2023-06-04 23:25
 * @Version 1.0
 */
public class _07_647_PalindromeSubstring2_推荐 {

    // 动态规划
    // 通过动态规划，将判断是否为回文，降低成 O(1) 时间复杂度，从而整体降低成 O(n^2)
    // 时间复杂度：O(n^2)
    public int countSubstrings(String s) {
        if (s == null || s.length() == 0) return 0;
        int n = s.length();

        // 定义状态
        // dp[i][j] 表示子数组区间 [i, j] 对应的子串是否是回文
        // => 回文判断，必然是区间，故状态参数是 [i,j]
        // 二维数组：是 boolean，不是 int
        boolean[][] dp = new boolean[n][n];
        int res = 0;

        // 状态初始化
        for (int i = 0; i < n; i++) {
            // i 和 j 相等，右斜下对角线，设置为 true
            dp[i][i] = true;
            // 一个字符，肯定是回文，res 需要 ++，不要遗忘
            res++;
        }

        // 消除重复计算思路：
        // 1.先将短的子串是否为回文计算好，并将状态存储
        // 2.后续长的子串是否为回文计，通过短的子串进行推导，不需再去从头开始遍历

        // 1.多个字符 => 是否为回文
        // a  b  c  b  a
        // i i+1   j-1 j
        // 1.1 若 s.charAt(i) == s.charAt(j)，则 dp[i][j] = dp[i+1][j-1]
        // 1.2 若 s.charAt(i) != s.charAt(j)，则 dp[i][j] = false

        // 2.两个字符 => 是否为回文
        // b c
        // 只有两个字符 dp[i][j] = (s.charAt(i) == s.charAt(j))

        // 状态转移
        // => 已知推导位置
        // 通过状态转移方程：dp[i][j] = dp[i + 1][j - 1]，决定填表的顺序逻辑
        // 再决定 for 循环的遍历顺序，从而控制 i 和 j 的起始位置
        // '从下往上，从左往右'

        // 对角线上已经判断过了，故 i 直接从 n-2 开始
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                // // 只有两个字符
                if (j - i == 1) {
                    dp[i][j] = s.charAt(i) == s.charAt(j);
                    // 大于两个字符
                } else {
                    // i+1，向后移动
                    // j-1，向前移动
                    dp[i][j] = s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1];
                }
                // 对 dp[i][j] 进行判断
                // 若 dp[i][j] 为 true，则 res ++
                if (dp[i][j]) res++;
            }
        }
        return res;
    }
}
