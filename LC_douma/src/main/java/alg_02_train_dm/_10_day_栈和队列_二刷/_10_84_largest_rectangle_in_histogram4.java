package alg_02_train_dm._10_day_栈和队列_二刷;

import java.util.ArrayDeque;
import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-08-07 15:07
 * @Version 1.0
 */
public class _10_84_largest_rectangle_in_histogram4 {

    // KeyPoint 方法五 枚举高 + 单调栈优化 + 一次遍历
    // 核心：方法三-单调栈 + 方法四-单调栈，从而实现一次遍历，同时计算出 left 和 right
    public int largestRectangleArea5(int[] heights) {
        int n = heights.length;
        int[] left = new int[n];
        int[] right = new int[n];

        // KeyPoint 注意事项
        // 别忘了需要将右边界都初始化为 n => 单调栈实现一需要手动赋初值
        Arrays.fill(right, n);
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        // 只从左往右遍历，同时计算 left 和 right
        for (int i = 0; i < n; i++) {
            int height = heights[i];

            while (!stack.isEmpty() && height <= heights[stack.peek()]) {
                // 1.计算右边界
                // height <= heights[stack.peek()]
                right[stack.pop()] = i;
            }
            // 2.计算左边界
            // height > heights[stack.peek()]
            left[i] = (stack.isEmpty() ? -1 : stack.peek());
            stack.push(i);
        }

        // 测试用例
        // nums
        // index  0 1 2 3 4 5 6 7
        // value  1 3 5 6 2 3 3 1

        // left
        // index  0  1 2 3 4 5 6 7
        // value  -1 0 1 2 0 4 4 -1

        // while 循环实现偏向：单调栈第二种实现，故 left 数组不存在瑕疵

        // right
        // index  0 1 2 3 4 5 6 7
        // value  8 4 4 4 7 6 7 8
        //                  ↑
        //               存在瑕疵 => height <= heights[stack.peek()] 所引起的

        // right
        // index  0 1 2 3 4 5 6 7
        // value  8 4 4 4 7 7 7 8
        //                  ↑
        //                正确索引

        // 对于计算右边界，严格来说，应该是 height < heights[stack.peek()] 才是标准代码
        // 但是为了兼容两种形式的单调栈，修改成 height <= heights[stack.peek()]

        // 虽然相邻且高度相同柱子，其中一个柱子面积计算不对，但能保证另一个柱子面积计算正确
        // 并且本题求解为最大面积，故对最后结果没有影响

        int res = 0;
        for (int mid = 0; mid < n; mid++) {
            int height = heights[mid];
            int width = right[mid] - left[mid] - 1;
            res = Math.max(res, height * width);
        }
        return res;
    }
}
