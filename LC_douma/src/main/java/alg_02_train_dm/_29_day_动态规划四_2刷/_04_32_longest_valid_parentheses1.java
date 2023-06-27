package alg_02_train_dm._29_day_动态规划四_2刷;

import java.util.ArrayDeque;

/**
 * @Author Wuyj
 * @DateTime 2023-06-12 16:33
 * @Version 1.0
 */
public class _04_32_longest_valid_parentheses1 {
      /*
        32. 最长有效括号
        给你一个只包含 '(' 和 ')' 的字符串，找出最长有效(格式正确且连续)括号子串的长度。

        示例 1：
        输入：s = "(()"
        输出：2
        解释：最长有效括号子串是 "()"

        示例 2：
        输入：s = ")()())"
        输出：4
        解释：最长有效括号子串是 "()()"

        s = "(()))())"  --> 4

        示例 3：
        输入：s = ""
        输出：0

        提示：
        0 <= s.length <= 3 * 10^4
        s[i] 为 '(' 或 ')'

     */

    // KeyPoint 方法一 暴力解法，超时
    // 时间复杂度：O(n^3)
    // 空间复杂度：O(n)
    public int longestValidParentheses1(String s) {
        int maxLen = 0;
        // 枚举所有区间，判断该区间内，是否为有效括号
        // 区间索引，使用 start 和 end 表示，更加易懂
        for (int end = 1; end <= s.length(); end++) {
            // start 严格小于 end
            for (int start = 0; start < end; start++) {
                if (isValid(s.substring(start, end))) {
                    maxLen = Math.max(maxLen, end - start);
                }
            }
        }
        return maxLen;
    }

    // 判断有效括号 => 栈
    public boolean isValid(String s) {
        // 奇数，直接返回 false
        if (s.length() % 2 == 1) return false;
        ArrayDeque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(c);
            } else {
                // 若 stack 在 pop 之前为空，直接 return false
                if (stack.isEmpty()) return false;
                stack.pop();
            }
        }
        return stack.isEmpty();
    }

    // 判断有效括号 => 计数
    // 时间复杂度：O(n)
    // 空间复杂度：O(1)
    public boolean isValid2(String s) {
        if (s.length() % 2 == 1) return false;
        // 通过 leftBraceCnt 记录括号个数
        // 1.遇到 '('，leftBraceCnt++
        // 2.遇到 ')'，leftBraceCnt--
        int leftBraceCnt = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                leftBraceCnt++;
            } else {
                if (leftBraceCnt == 0) return false;
                leftBraceCnt--;
            }
        }
        return leftBraceCnt == 0;
    }
}
