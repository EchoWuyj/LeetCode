package alg_02_train_wyj._10_day_栈和队列;

import java.util.ArrayDeque;

/**
 * @Author Wuyj
 * @DateTime 2023-08-07 14:27
 * @Version 1.0
 */
public class _09_42_trapping_rain_water4 {
    public int trap(int[] heights) {
        int n = heights.length;
        if (n <= 2) return 0;
        int res = 0;
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            int height = heights[i];

            while (!stack.isEmpty() && height > heights[stack.peek()]) {
                int top = stack.pop();
                if (stack.isEmpty()) break;
                int leftIndex = stack.peek();
                int width = i - leftIndex - 1;
                int diff = Math.min(heights[leftIndex], heights[i]) - heights[top];
                res += width * diff;
            }
            stack.push(i);
        }
        return res;
    }
}
