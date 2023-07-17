package alg_02_train_dm._10_day_栈和队列;

import java.util.ArrayDeque;

/**
 * @Author Wuyj
 * @DateTime 2023-07-17 21:16
 * @Version 1.0
 */
public class _09_42_trapping_rain_water4 {

    // KeyPoint 方法四 单调栈
    // 柱子在递减时 ↘，无法进行装水，只有遇到右边第一个比前面大的柱子，才能装水 => 单调递减栈
    public int trap(int[] height) {
        int n = height.length;
        if (n <= 2) return 0;
        int ans = 0;
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                int top = stack.peek();
                stack.pop();
                // stack.peek() 操作之前，保证 stack 不为空
                if (stack.isEmpty()) break;
                int leftIndex = stack.peek();
                // 宽度，两端都不包括，故还需要再减 1
                int currWidth = i - leftIndex - 1;
                int currHeight = Math.min(height[leftIndex], height[i]) - height[top];
                // 通过面积相乘的方式，计算装水区域
                ans += currWidth * currHeight;
            }

            stack.push(i);
        }
        return ans;
    }
}
