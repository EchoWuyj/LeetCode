package alg_02_train_dm._26_day_动态规划一_2刷._07_647_PalindromeSubstring;

/**
 * @Author Wuyj
 * @DateTime 2023-06-04 23:24
 * @Version 1.0
 */
public class _07_647_PalindromeSubstring1 {

    /*
        647. 回文子串
        给你一个字符串 s，请你统计并返回这个字符串中 回文子串 的数目。
        回文字符串 是正着读和倒过来读一样的字符串。

        子串(字符类型子数组) 是字符串中的由 连续字符组 成的一个序列。

        具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。

        示例 1：
        输入：s = "abc"
        输出：3
        解释：3 个回文子串: "a", "b"，"c"

        示例 2：
        输入：s = "babad"
        输出：7
        解释：7 个回文子串: "b", "bab", "a", "aba", "b", "a", "d"

        示例 3：
        输入：s = "aaa"
        输出：6
        解释：6 个回文子串: "a", "a", "a", "aa", "aa", "aaa"

        提示：
        1 <= s.length <= 1000 => 10^3 => O(n^3)
        s 由小写英文字母组成

        KeyPoint 注意：1000 => 10^3 => O(n^3) 勉强可以通过
     */

    // KeyPoint 方法一 暴力
    // 穷举所有的子串，判断子串是否是回文
    // 时间复杂度：O(n^3)
    public int countSubstrings(String s) {
        if (s == null || s.length() == 0) return 0;
        int res = 0;
        // 穷举所有的子串
        // 子串 和 子数组 是同一个概念，子串就是字符类型数组，要求连续
        int n = s.length();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                // [j, i]
                String subStr = s.substring(j, i + 1);
                if (isPalindrome(subStr)) res++;
            }
        }
        return res;
    }

    // 2.判断子串是否是回文
    // 时间复杂度：O(n/2) -> O(n)
    private boolean isPalindrome(String s) {
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) return false;
            i++;
            j--;
        }
        return true;

        // 性能瓶颈：存在重复计算

        // a b c b a
        // i       j

        // a b c b a
        //   i   j

        // 严格来说，本题并不是求解最优值问题
        // 1.判断子串是否是回文 => 求解 True / False 问题
        // 2.并且在穷举过程中，存在重复计算，可以使用动态规划优化，将每个子串对应是否为回文，作为状态存储

    }
}
