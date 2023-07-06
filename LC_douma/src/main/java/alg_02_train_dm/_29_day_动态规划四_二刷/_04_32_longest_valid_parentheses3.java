package alg_02_train_dm._29_day_动态规划四_二刷;

import java.util.ArrayDeque;

/**
 * @Author Wuyj
 * @DateTime 2023-06-22 17:01
 * @Version 1.0
 */
public class _04_32_longest_valid_parentheses3 {

    // KeyPoint 方法三 栈
    // 具体实现类似于'有效括号'
    // 本题求的是：最长有效括号子串的长度，所以栈中存储索引
    // 区别在于：栈存储的元素不再是括号，而是括号对应的索引
    // 时间复杂度：O(n)
    // 空间复杂度：O(n)
    public int longestValidParentheses3(String s) {

        // 0 1 2 3 4
        // ( ( ) ) )
        // 先遇到'('，希望后面有')'与之匹配，故先将'('记住
        // => 先进来元素，后操作 => 使用'栈'实现

        int res = 0;
        ArrayDeque<Integer> stack = new ArrayDeque<>();

        // 一开始压入 -1
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            // i => '(
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                // i => ')'，直接删除栈顶，即先弹栈，这样在计算 res 时，使用 i - stack.peek() 不用再加 1
                // 因为 stack 一开始压入 -1，且 stack.isEmpty() 就会压入 i，所以不用担心栈为空
                stack.pop();
                if (stack.isEmpty()) {
                    // 栈为空，相当于之前括号都已匹配完了，且此时遇到 ')'
                    // 连续有效括号从该 i 索引位置断开了，从该位置进行后续计算
                    stack.push(i);
                } else {
                    res = Math.max(res, i - stack.peek());
                }
            }
        }
        return res;
    }
}
