package alg_02_train_dm._29_day_动态规划四;

import java.util.ArrayDeque;
import java.util.Stack;

/**
 * @Author Wuyj
 * @DateTime 2023-06-12 16:33
 * @Version 1.0
 */
public class _04_32_longest_valid_parentheses {
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
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (isValid(s.substring(j, i))) {
                    maxLen = Math.max(maxLen, i - j);
                }
            }
        }
        return maxLen;
    }

    // 判断有效括号 => 栈
    public boolean isValid(String s) {
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

    // KeyPoint 方法二 动态规划
    // 求解最优值 + 枚举方式求解 + 存在重复计算 => 动态规划
    // 通过前面计算好的状态，推导出后面状态值，而不是从头开始再遍历一边，从而降低时间复杂度
    // 时间复杂度：O(n)
    // 空间复杂度：O(n)
    public int longestValidParentheses2(String s) {
        int n = s.length();
        if (n <= 1) return 0;

        // 1. 定义状态
        // dp[i] 表示以下标为 i 的字符结尾，最长有效子字符串的长度
        int[] dp = new int[n];

        dp[0] = 0;

        // 考虑第一个字符和第二个字符
        if (s.charAt(0) == '(' && s.charAt(1) == ')') dp[1] = 2;
        int res = dp[1];

        // 状态转移从第 3 个字符开始
        for (int i = 2; i < n; i++) {
            // i => ')'
            if (s.charAt(i) == ')') {
                // i-1 => '(' 且 i => ')'
                if (s.charAt(i - 1) == '(') {
                    // 注意：这里是 dp[i-2]，不是 dp[i-1]
                    // i 和 i-1 配对，则 dp[i] 依赖于 dp[i-2]
                    dp[i] = dp[i - 2] + 2;
                    // i-1 => ')' 且 i => ')'
                    // 涉及数组下标索引变换，需要保证数组下标索引不越界
                    // 通过测试用例，将代码处理逻辑完善
                } else {
                    if (i - dp[i - 1] >= 1 && s.charAt(i - dp[i - 1] - 1) == '(') {
                        dp[i] = dp[i - 1] + 2 + (i - dp[i - 1] >= 2 ? dp[i - dp[i - 1] - 2] : 0);
                    }
                }

                // 更新 res
                // 可以在 if 两个分支中都去更新，也可以将其提取到外面来
                res = Math.max(res, dp[i]);
            }
            // 只要是以 '(' 结尾，则必然不是有效括号，则此时 dp[i] = 0
        }
        return res;
    }

    // KeyPoint 方法三 栈
    // 具体实现类似于有效括号，区别在于栈存储的元素不再是括号，而是括号对应的索引
    // 时间复杂度：O(n)
    // 空间复杂度：O(n)
    public int longestValidParentheses3(String s) {
        int res = 0;
        Stack<Integer> stack = new Stack<>();
        // 一开始压入 -1
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            // i => '(
            if (s.charAt(i) == '(') {
                // 本题求的是：最长有效括号子串的长度，所以栈中存储索引
                stack.push(i);
                // i => ')'
            } else {
                // 因为 stack 一开始压入 -1，且 stack.isEmpty() 就会压入 i，所以不用担心栈为空
                // 先弹栈，这样在计算 res 时，使用 i - stack.peek() 不用再加 1
                stack.pop();
                if (stack.isEmpty()) {
                    // 连续有效括号从该 i 索引位置断开了，从该位置进行后续计算
                    stack.push(i);
                } else {
                    res = Math.max(res, i - stack.peek());
                }
            }
        }
        return res;
    }

    //  KeyPoint 方法四 双变量
    // 类似于：只有一种括号，使用计数变量来求解
    // 时间复杂度：O(n)
    // 空间复杂度：O(1)
    public int longestValidParentheses(String s) {

        int left = 0, right = 0, res = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') left++;
            else right++;
            if (left == right) {
                res = Math.max(res, 2 * left);
                // 遍历 i，若 right > left，即必然不是有效括号，将 right = left = 0
                // ")(" => right = 1，left = 0
                //  ↑
                //  i
            } else if (right > left) {
                left = right = 0;
            }
        }

        // 若 "( ( ( ) )"
        // for 循环结束，left = 3，right = 2，left > right，则找不到最长有效括号
        // 但是实际上是存在最长有效括号为 4，故还需要再从右往左遍历

        left = right = 0;
        // 不能通过这种方式重新赋值 left = 0, right = 0，而是使用 left = right =0
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') left++;
            else right++;
            if (left == right) {
                res = Math.max(res, 2 * left);
            } else if (left > right) {
                left = right = 0;
            }
        }
        return res;
    }
}
