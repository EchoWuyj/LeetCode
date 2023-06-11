package alg_02_train_dm._10_day_栈和队列;

import java.util.ArrayDeque;

/**
 * @Author Wuyj
 * @DateTime 2023-04-26 13:09
 * @Version 1.0
 */
public class _09_42_trapping_rain_water {
    /*
        42. 接雨水
        给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，
        计算按此排列的柱子，下雨之后能接多少雨水。


        输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
        输出：6
        解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，
        在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。


        提示：
        n == height.length
        1 <= n <= 2 * 10^4
        0 <= height[i] <= 10^5

     */

    // KeyPoint 方法一 暴力
    // 找左右两边最大值中小者，减去本身高度
    // 时间复杂度 O(n^2)
    public int trap1(int[] height) {
        if (height.length <= 2) return 0;

        int ans = 0;
        // 第一个柱子和最后一个柱子没法接雨水，故只需要从第二个柱子遍历到倒数第二个柱子即可
        for (int i = 1; i < height.length - 1; i++) {
            // 求左边的最大值 => 从 i-1 向左遍历
            int leftMax = 0;
            // 最大值是包括第一个柱子和最后一个柱子的
            for (int j = i - 1; j >= 0; j--) {
                leftMax = Math.max(leftMax, height[j]);
            }

            // 求右边的最大值 => 从 i+1 向右遍历
            int rightMax = 0;
            // 最大值是包括第一个柱子和最后一个柱子的
            for (int j = i + 1; j < height.length; j++) {
                rightMax = Math.max(rightMax, height[j]);
            }

            // 当前这个柱子能装的水的单位数等于 min(leftMax, rightMax) - height[i]
            // 木桶效应
            int maxHeight = Math.min(leftMax, rightMax);
            // 确保 maxHeight > height[i]，否则 maxHeight - height[i] 为负数
            if (maxHeight > height[i])
                ans += maxHeight - height[i];
        }
        return ans;
    }

    // KeyPoint 方法二 优化 leftMax 和 rightMax 求解
    // 时间复杂度 O(n)
    public int trap2(int[] height) {
        int n = height.length;
        if (n <= 2) return 0;

        // KeyPoint 常用优化方式 => 数据预处理
        // leftMax 表示每个柱子左边最大值
        int[] leftMax = new int[n];
        leftMax[0] = height[0];
        for (int i = 1; i < n; i++) {
            // 计算第 i 根柱子的前面(左边 => 不包括当前 i根柱子)所有柱子的最大值
            // 从左往右遍历，i-1 位置的比较结果 => i 位置的值 => 前面决定后面
            leftMax[i] = Math.max(leftMax[i - 1], height[i - 1]);

            // arr = [1, 2, 1, 3, 4, 2, 5, 3]
            // leftMax = [1, 1, 2, 2, 3, 4, 4, 5]
        }

        int[] rightMax = new int[n];
        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            // 计算第 i 根柱子的后面(右边 => 不包括当前 i根柱子)所有柱子的最大值
            // 从右往左遍历，i+1 位置的比较结果 => i 位置的值 => 后面决定前面
            rightMax[i] = Math.max(rightMax[i + 1], height[i + 1]);

            // arr = [1, 2, 1, 3, 4, 2, 5, 3]
            // rightMax = [5, 5, 5, 5, 5, 5, 3, 3]
        }

        int ans = 0;
        for (int i = 1; i < height.length - 1; i++) {
            // 当前这个柱子能装的水的单位数等于 min(leftMax, rightMax) - height[i]
            int maxHeight = Math.min(leftMax[i], rightMax[i]);
            if (maxHeight > height[i])
                ans += maxHeight - height[i];
        }
        return ans;
    }

    // KeyPoint 方法三 双指针代替 leftMax 和 rightMax
    // 计算装的水的单位数时，只是使用了一次 leftMax[i], rightMax[i]，
    // 故考虑使用两个指针 leftMax，rightMax 将优化掉，节省空间
    public int trap3(int[] height) {
        int n = height.length;
        if (n <= 2) return 0;

        // left 之前最大值 => 不包括 left
        // right 之后最大值 => 不包括 right
        int leftMax = 0, rightMax = 0;
        // 注意：left 必须从 0 开始，right 必须从 n - 1 开始
        // 原因：第一根柱子或者最后一根柱子有可能是最大值
        int left = 0, right = n - 1;
        int ans = 0;
        while (left < right) {
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);
            // left 柱子低，则结算 left 柱雨水，装水量取决于短板
            if (height[left] < height[right]) {
                ans += leftMax - height[left];
                left++;
            } else {
                ans += rightMax - height[right];
                right--;
            }
        }
        return ans;
    }

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
