package alg_02_train_dm._10_day_栈和队列_二刷;

import java.util.ArrayDeque;

/**
 * @Author Wuyj
 * @DateTime 2023-08-07 19:09
 * @Version 1.0
 */
public class _10_84_largest_rectangle_in_histogram3 {

    // KeyPoint 方法四 枚举高 + 单调栈优化 => 单调栈另外一种实现
    public int largestRectangleArea4(int[] heights) {
        int n = heights.length;
        // 1.计算每根柱子左边，第一个小于这根柱子的柱子 => 每根柱子的左边界
        int[] left = new int[n];
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        // KeyPoint 关键点
        // 左边第一个小于这根柱子的柱子 + 从左往右遍历
        for (int i = 0; i < n; i++) {
            int height = heights[i];
            while (!stack.isEmpty() && height <= heights[stack.peek()]) {
                // 若 heights[i] <= heights[stack.peek()]
                // 则说明： 满足循环条件，栈中索引的左边界已经找到了，故可以弹栈
                // 直到 heights[i] > heights[stack.peek()]，找到 i 的左边界
                stack.pop();
            }

            // 1.若栈为空，则说明 i 为第一个索引，没有左边界，将 left 设置为 -1
            // 2.若栈不为空，且 heights[i] > heights[stack.peek()]，则 stack.peek() 为 i 左边界，设置 left[i]
            //   左边界 => 第一个小于这根柱子的柱子
            left[i] = (stack.isEmpty() ? -1 : stack.peek());

            // 每个索引都要压栈，有可能是后面柱子的左边界
            stack.push(i);
        }
        // KeyPoint
        // 通过测试用例，再去熟悉这种单调栈实现方式，加深对代码的理解

        // 测试用例
        // nums
        // index  0 1 2 3 4 5 6 7
        // value  1 3 5 6 2 3 3 1

        // left
        // index  0  1 2 3 4 5 6 7
        // value  -1 0 1 2 0 4 4 -1

        // 2.计算每根柱子右边，第一个小于这根柱子的柱子 => 每根柱子的右边界
        int[] right = new int[n];
        stack = new ArrayDeque<>();
        // KeyPoint 关键点
        // 右边第一个小于这根柱子的柱子 + 从右往左遍历
        for (int i = n - 1; i >= 0; i--) {
            int height = heights[i];
            while (!stack.isEmpty() && height <= heights[stack.peek()]) {
                // heights[i] <= heights[stack.peek()]
                stack.pop();
            }
            // heights[i] > heights[stack.peek()] => 设置右边界
            right[i] = (stack.isEmpty() ? n : stack.peek());
            stack.push(i);
        }

        // KeyPoint
        // 通过测试用例，再去熟悉这种单调栈实现方式，加深对代码的理解

        // 测试用例
        // nums
        // index  0 1 2 3 4 5 6 7
        // value  1 3 5 6 2 3 3 1

        // right
        // index  0 1 2 3 4 5 6 7
        // value  8 4 4 4 7 7 7 8

        // KeyPoint 两种实现区别
        // 1.这种实现方式，在找左右边界时，更新当前索引 i 的边界
        // 2.之前实现方式，在找左右边界时，更新栈顶索引对应边界，即 left[stack.peek()] = i

        int res = 0;
        for (int mid = 0; mid < n; mid++) {
            int height = heights[mid];
            int width = right[mid] - left[mid] - 1;
            res = Math.max(res, height * width);
        }
        return res;
    }
}
