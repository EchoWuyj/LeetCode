package alg_02_train_wyj._29_day_动态规划四;

import java.util.ArrayDeque;

/**
 * @Author Wuyj
 * @DateTime 2023-06-13 20:25
 * @Version 1.0
 */
public class _04_32_longest_valid_parentheses {
    public int longestValidParentheses1(String s) {
        int maxLen = 0;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (isValid1(s.substring(j, i))) {
                    maxLen = Math.max(maxLen, i - j);
                }
            }
        }
        return maxLen;
    }

    private boolean isValid(String str) {
        if (str.length() % 2 == 1) return false;
        ArrayDeque<Character> stack = new ArrayDeque<>();
        for (char c : str.toCharArray()) {
            if (c == '(') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                stack.pop();
            }
        }
        return stack.isEmpty();
    }

    private boolean isValid1(String str) {
        if (str.length() % 2 == 1) return false;
        int leftBraceCnt = 0;
        for (char c : str.toCharArray()) {
            if (c == '(') {
                leftBraceCnt++;
            } else {
                if (leftBraceCnt == 0) return false;
                leftBraceCnt--;
            }
        }
        return leftBraceCnt == 0;
    }

    public int longestValidParentheses2(String s) {
        int n = s.length();
        if (n <= 1) return 0;
        int[] dp = new int[n];
        dp[0] = 0;
        if (s.charAt(0) == '(' && s.charAt(1) == ')') dp[1] = 2;
        int res = dp[1];

        for (int i = 2; i < n; i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = dp[i - 2] + 2;
                    res = Math.max(res, dp[i]);
                } else {
                    if (i - dp[i - 1] - 1 >= 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                        dp[i] = dp[i - 1] + 2 + (i - dp[i - 1] - 2 >= 0 ? dp[i - dp[i - 1] - 2] : 0);
                        res = Math.max(res, dp[i]);
                    }
                }
            }
        }
        return res;
    }

    public int longestValidParentheses3(String s) {
        int res = 0;
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    res = Math.max(res, i - stack.peek());
                }
            }
        }
        return res;
    }

    public int longestValidParentheses4(String s) {
        int left = 0, right = 0, res = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') left++;
            else right++;
            if (left == right) {
                res = Math.max(res, 2 * left);
            } else if (right > left) {
                right = left = 0;
            }
        }

        left = right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') left++;
            else right++;
            if (left == right) res = Math.max(res, 2 * left);
            else if (left > right) right = left = 0;
        }
        return res;
    }
}
