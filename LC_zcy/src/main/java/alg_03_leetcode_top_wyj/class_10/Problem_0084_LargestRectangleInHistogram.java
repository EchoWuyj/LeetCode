package alg_03_leetcode_top_wyj.class_10;

import java.util.Stack;

/**
 * @Author Wuyj
 * @DateTime 2023-02-28 12:17
 * @Version 1.0
 */

//  柱状图中最大的矩形
public class Problem_0084_LargestRectangleInHistogram {
    /*
        给定n个非负整数,用来表示柱状图中各个柱子的高度,每个柱子彼此相邻,且宽度为1
        求在该柱状图中,能够勾勒出来的矩形的最大面积

     */
    public static int largestRectangleArea(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int maxArea = 0;
        // 只放下标
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[i] <= height[stack.peek()]) {
                int j = stack.pop();
                int k = stack.isEmpty() ? -1 : stack.peek();
                int curArea = (i - k - 1) * height[j];
                maxArea = Math.max(maxArea, curArea);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int j = stack.pop();
            int k = stack.isEmpty() ? -1 : stack.peek();
            int curArea = (height.length - k - 1) * height[j];
            maxArea = Math.max(maxArea, curArea);
        }
        return maxArea;
    }
}
