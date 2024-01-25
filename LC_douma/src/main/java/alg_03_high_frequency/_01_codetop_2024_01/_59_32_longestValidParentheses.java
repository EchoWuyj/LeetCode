package alg_03_high_frequency._01_codetop_2024_01;

/**
 * @Author Wuyj
 * @DateTime 2024-01-14 23:04
 * @Version 1.0
 */
public class _59_32_longestValidParentheses {

    public int longestValidParentheses(String s) {
        int left = 0, right = 0;
        int n = s.length();
        int res = 0;

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(') left++;
            else right++;
            if (left == right) {
                res = Math.max(res, 2 * left);
            } else if (right > left) {
                left = right = 0;
            }

            // 也可以不使用 if else，而是作为单独的 if 判断
//            if (left < right) {
//                left = right = 0;
//            }

        }

        left = right = 0;

        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == ')') right++;
            else left++;
            if (left == right) {
                res = Math.max(res, 2 * left);
            } else if (left > right) {
                left = right = 0;
            }
        }
        return res;
    }
}
