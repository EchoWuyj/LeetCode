package alg_02_train_wyj._10_day_栈和队列;

import java.util.ArrayDeque;

/**
 * @Author Wuyj
 * @DateTime 2023-04-26 13:09
 * @Version 1.0
 */
public class _09_42_trapping_rain_water {
    public int trap1(int[] height) {
        if (height.length <= 2) return 0;
        int res = 0;
        for (int i = 1; i < height.length - 1; i++) {
            int leftMax = 0;
            for (int j = i - 1; j >= 0; j--) {
                leftMax = Math.max(leftMax, height[j]);
            }

            int rightMax = 0;
            for (int j = i + 1; j < height.length; j++) {
                rightMax = Math.max(rightMax, height[j]);
            }

            int maxHeight = Math.min(leftMax, rightMax);
            if (maxHeight > height[i]) {
                res += maxHeight - height[i];
            }
        }
        return res;
    }

    public int trap2(int[] height) {
        int n = height.length;
        if (n <= 2) return 0;

        int[] leftMax = new int[n];
        leftMax[0] = height[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i - 1]);
        }

        int[] rightMax = new int[n];
        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i + 1]);
        }

        int res = 0;
        for (int i = 1; i < n - 1; i++) {
            int maxHeight = Math.min(leftMax[i], rightMax[i]);
            if (maxHeight > height[i]) {
                res += maxHeight - height[i];
            }
        }
        return res;
    }

    public int trap3(int[] height) {
        int n = height.length;
        if (n < 2) return 0;
        int leftMax = 0, rightMax = 0;
        int left = 0, right = n - 1;
        int res = 0;
        while (left < right) {
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);
            if (height[left] < height[right]) {
                res += leftMax - height[left];
                left++;
            } else {
                res += rightMax - height[right];
                right--;
            }
        }
        return res;
    }

    public int trap4(int[] height) {
        int n = height.length;
        if (n < 2) return 0;
        int res = 0;
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                int top = stack.pop();
                if (stack.isEmpty()) break;
                ;
                int leftIndex = stack.peek();
                int curWidth = i - leftIndex - 1;
                int curHeight = Math.min(height[i], height[leftIndex]) - height[top];
                res += curHeight * curWidth;
            }
            stack.push(i);
        }
        return res;
    }
}
