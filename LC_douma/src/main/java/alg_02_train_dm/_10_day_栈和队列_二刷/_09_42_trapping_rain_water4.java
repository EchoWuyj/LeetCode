package alg_02_train_dm._10_day_栈和队列_二刷;

import java.util.ArrayDeque;

/**
 * @Author Wuyj
 * @DateTime 2023-07-17 21:16
 * @Version 1.0
 */
public class _09_42_trapping_rain_water4 {

    // KeyPoint 方法四 单调栈
    public int trap(int[] heights) {

        // 单调递减栈
        // 1.柱子在递减时 ↘，无法进行装水
        // 2.只有从左往右，依次遍历，遇到第一个柱子 i，其高度 [i] > [i-1]，这样才能装水
        // 3.根据柱子高度[i]，再去依次计算，前面每个柱子 0,1,...i-2 能装多少水

        int n = heights.length;
        if (n <= 2) return 0;
        int res = 0;
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            int height = heights[i];
            while (!stack.isEmpty() && height > heights[stack.peek()]) {
                int top = stack.pop();
                // 在 stack.peek() 操作之前，保证 stack 不为空，否则跳出 while 循环
                if (stack.isEmpty()) break;
                // 因为栈是单调递减，栈中 top 下面一个索引 leftIndex ，为 i 的左侧柱子索引
                int leftIndex = stack.peek();
                // 宽度：因为两端都不包括，故还需要再减 1
                int width = i - leftIndex - 1;
                // 高度差：取左右两侧柱子较低一个，再去减去自身高度
                int diff = Math.min(heights[leftIndex], heights[i]) - heights[top];
                // 本题：横向方式计算面积，区别之前纵向计算面积
                res += width * diff;
            }
            stack.push(i);
        }
        return res;
    }
}
