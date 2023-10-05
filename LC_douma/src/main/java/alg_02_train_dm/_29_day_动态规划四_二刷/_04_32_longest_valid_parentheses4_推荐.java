package alg_02_train_dm._29_day_动态规划四_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-06-22 17:02
 * @Version 1.0
 */
public class _04_32_longest_valid_parentheses4_推荐 {

    //  KeyPoint 方法四 双变量 + 两轮循环
    // 类似于：只有一种括号，使用计数变量来求解
    // 时间复杂度：O(n)
    // 空间复杂度：O(1)
    public int longestValidParentheses(String s) {

        int left = 0, right = 0, res = 0;
        int n = s.length();

        // 从左往右，left 可以大于 right，只有后面有 right 与之匹配即可
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(') left++;
            else right++;
            if (left == right) {
                // left == right 有效括号，记录有效括号长度为 res
                res = Math.max(res, 2 * left);
            } else if (right > left) {
                // 遍历 i，若 right > left，即必然不是有效括号，重置，将 right = left = 0
                // ")(" => right = 1，left = 0
                //  ↑
                //  i
                left = right = 0;
            }
        }

        // 若遇到这种情况："( ( ( ) )"
        // for 循环中，left 先增，right 后增，导致遍历过程中都没有出现 left == right
        // 直到 left = 3，right = 2，left > right，则找不到最长有效括号，
        // 但实际上是存在最长有效括号为 4，故代码存在 bug，还需要再从右往左遍历

        // KeyPoint 注意
        // 不能通过这种方式重新赋值 left = 0, right = 0，而是使用 left = right =0
        left = right = 0;

        // 从右往左，right 可以大于 left，只有后面有 left 与之匹配即可
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') left++;
            else right++;
            if (left == right) {
                res = Math.max(res, 2 * left);
            } else if (left > right) {
                // 遍历 i，若 left > right ，即必然不是有效括号，重置，将 right = left = 0
                // ")(" => left = 1，right = 0
                //   ↑
                //   i
                left = right = 0;
            }
        }
        return res;
    }
}
