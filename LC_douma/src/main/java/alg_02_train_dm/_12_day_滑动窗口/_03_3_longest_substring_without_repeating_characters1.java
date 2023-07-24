package alg_02_train_dm._12_day_滑动窗口;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author Wuyj
 * @DateTime 2023-05-04 16:44
 * @Version 1.0
 */
public class _03_3_longest_substring_without_repeating_characters1 {
    /*
           3.无重复字符的最长子串
           给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。

           示例 1:
           输入: s = "abcabcbb"
           输出: 3
           解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。

           示例 2:
           输入: s = "bbbbb"
           输出: 1
           解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。

           示例 3:
           输入: s = "pwwkew"
           输出: 3
           解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
                 请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。

           示例 4:
           输入: s = ""
           输出: 0

           提示：
           0 <= s.length <= 5 * 10^4
           s 由英文字母、数字、符号和空格组成

           KeyPoint 注意事项
           1.无重复字符的最长子串 => 求最大值
           2.区别：子串和子序列
             2.1 子串 <=> 连续子数组
             2.2 子序列是"不必要求连续"，而子串则是"连续"的

    */

    // KeyPoint 方法一 暴力解法 => 超时
    // 遍历数组的所有的区间，然后找到最长没有重复字符的区间
    // 时间复杂度：O(n^3)
    // 空间复杂度：O(n)
    public int lengthOfLongestSubstring1(String s) {
        int n = s.length();
        // s 为 ""，返回 0
        if (n <= 1) return n;
        int maxLen = 1;
        for (int i = 0; i < n; i++) { // O(n)
            // 子串必须保证连续
            // [i,j]，其中 j 从 i+1 一直遍历到 n-1，遍历了以 i 开头的所有区间
            for (int j = i + 1; j < n; j++) { // O(n)
                // 辅助方法：allUnique
                // 判断区间 [i,j] 中字符是否都是唯一，若该区间没有重复字符，更新 maxLen
                if (allUnique(s, i, j)) {
                    maxLen = Math.max(maxLen, j - i + 1);
                }
            }
        }
        return maxLen;
    }

    // 判断区间 [i,j] 中字符是否都是唯一，若该区间没有重复字符，更新 maxLen
    private boolean allUnique(String s, int start, int end) {
        // 判重 => 联想 set 容器
        Set<Character> set = new HashSet<>();
        for (int i = start; i <= end; i++) { // O(n)
            // 判断 set 是否已经存在字符，存在返回 false
            if (set.contains(s.charAt(i))) {
                return false;
            }
            // 不存在，加入 set 中
            set.add(s.charAt(i));
        }
        return true;
    }
}
