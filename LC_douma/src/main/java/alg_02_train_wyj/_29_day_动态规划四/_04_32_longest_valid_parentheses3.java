package alg_02_train_wyj._29_day_动态规划四;

/**
 * @Author Wuyj
 * @DateTime 2023-06-22 19:30
 * @Version 1.0
 */
public class _04_32_longest_valid_parentheses3 {
    public int longestValidParentheses4(String s) {
        int left = 0, right = 0, res = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') left++;
            else right++;

            if (left == right) {
                res = Math.max(res, left * 2);
            } else if (right > left) {
                left = right = 0;
            }
        }

        left = right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') left++;
            else right++;

            if (left == right) {
                res = Math.max(res, left * 2);
            } else if (left > right) {
                left = right = 0;
            }
        }
        return res;
    }
}

