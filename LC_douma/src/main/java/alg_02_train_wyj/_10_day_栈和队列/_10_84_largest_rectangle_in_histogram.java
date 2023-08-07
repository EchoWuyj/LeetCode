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
        int n = heights.length;
        int res = 0;
        for (int left = 0; left < n; left++) {
            int minHeight = heights[left];
            for (int right = left; right < n; right++) {
                minHeight = Math.min(minHeight, heights[right]);
                int width = right - left + 1;
                res = Math.max(res, minHeight * width);
            }
        }
        return res;
    }

    public int largestRectangleArea1(int[] heights) {
        int n = heights.length;
        int res = 0;
        for (int mid = 0; mid < n; mid++) {
            int height = heights[mid];
            int left = mid, right = mid;
            while (left >= 0 && heights[left] >= height) left--;
            while (right < n && heights[right] >= height) right++;
            res = Math.max(res, (right - left - 1) * height);
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
        Arrays.fill(left, -1);
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (int i = n - 1; i >= 0; i--) {
            int height = heights[i];
            while (!stack.isEmpty() && height < heights[stack.peek()]) {
                left[stack.pop()] = i;
            }
            stack.push(i);
        }
        System.out.println(Arrays.toString(left));

        int[] right = new int[n];
        Arrays.fill(right, n);
        stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            int height = heights[i];
            while (!stack.isEmpty() && height < heights[stack.peek()]) {
                right[stack.pop()] = i;
            }
            stack.push(i);
        }
        System.out.println(Arrays.toString(right));

        int res = 0;
        for (int mid = 0; mid < n; mid++) {
            int height = heights[mid];
            int width = right[mid] - left[mid] - 1;
            res = Math.max(res, height * width);
        }
        return res;
    }

    public int largestRectangleArea4(int[] heights) {
        int n = heights.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(right, n);
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            int height = heights[i];
            while (!stack.isEmpty() && height <= heights[stack.peek()]) {
                right[stack.pop()] = i;
            }
            left[i] = (stack.isEmpty() ? -1 : stack.peek());
            stack.push(i);
        }

        int res = 0;
        for (int mid = 0; mid < n; mid++) {
            int height = heights[mid];
            int width = right[mid] - left[mid] - 1;
            res = Math.max(res, height * width);
        }
        return res;
    }
}
