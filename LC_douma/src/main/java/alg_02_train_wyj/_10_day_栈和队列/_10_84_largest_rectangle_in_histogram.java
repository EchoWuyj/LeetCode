package alg_02_train_wyj._10_day_栈和队列;

import java.util.ArrayDeque;
import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-04-26 18:27
 * @Version 1.0
 */
public class _10_84_largest_rectangle_in_histogram {

    public int largestRectangleArea(int[] heights) {
        int res = 0;
        int n = heights.length;
        for (int left = 0; left < n; left++) {
            int minHeight = heights[left];
            for (int right = left; right < n; right++) {
                minHeight = Math.min(minHeight, heights[right]);
                int curWidth = right - left + 1;
                res = Math.max(res, curWidth * minHeight);
            }
        }
        return res;
    }

    public int largestRectangleArea1(int[] heights) {
        int n = heights.length;
        int res = 0;
        for (int i = 0; i < n; i++) {
            int height = heights[i];
            int left = i, right = i;

            while (left >= 0 && heights[left] >= height) left--;
            while (right < n && heights[right] >= height) right++;

            res = Math.max(res, height * (right - left - 1));
        }
        return res;
    }

    public int largestRectangleArea2(int[] heights) {
        int n = heights.length;
        int res = 0;

        int[] left = new int[n];
        Arrays.fill(left, -1);
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (int i = n - 1; i >= 0; i--) {
            int x = heights[i];
            while (!stack.isEmpty() && x < heights[stack.peek()]) {
                left[stack.peek()] = i;
                stack.pop();
            }
            stack.push(i);
        }

        int[] right = new int[n];
        Arrays.fill(right, n);
        stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            int x = heights[i];
            while (!stack.isEmpty() && x < heights[stack.peek()]) {
                right[stack.peek()] = i;
                stack.pop();
            }
            stack.push(i);
        }

        for (int i = 0; i < n; i++) {
            int height = heights[i];
            int width = right[i] - left[i] - 1;
            res = Math.max(res, height * width);
        }
        return res;
    }

    public int largestRectangleArea3(int[] heights) {
        int n = heights.length;
        int[] left = new int[n];
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        // 左边 小
        for (int i = 0; i < n; i++) {
            int x = heights[i];
            while (!stack.isEmpty() && x <= heights[stack.peek()]) {
                stack.pop();
            }
            left[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }

        // 右边 小
        int[] right = new int[n];
        stack = new ArrayDeque<>();
        for (int i = n - 1; i >= 0; i--) {
            int x = heights[i];
            while (!stack.isEmpty() && x <= heights[stack.peek()]) {
                stack.pop();
            }
            right[i] = stack.isEmpty() ? n : stack.peek();
            stack.push(i);
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            int height = heights[i];
            int width = right[i] - left[i] - 1;
            ans = Math.max(ans, height * width);
        }
        return ans;
    }

    public int largestRectangleArea4(int[] heights) {
        int n = heights.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(right, n);
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            int x = heights[i];
            while (!stack.isEmpty() && x <= heights[stack.peek()]) {
                right[stack.peek()] = i;
                stack.pop();
            }
            left[i] = (stack.isEmpty()) ? -1 : stack.peek();
            stack.push(i);
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            int height = heights[i];
            int width = right[i] - left[i] - 1;
            ans = Math.max(ans, height * width);
        }
        return ans;
    }
}
