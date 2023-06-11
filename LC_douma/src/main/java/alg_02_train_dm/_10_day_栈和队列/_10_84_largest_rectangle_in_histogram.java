package alg_02_train_dm._10_day_栈和队列;

import java.util.ArrayDeque;
import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-04-26 17:03
 * @Version 1.0
 */
public class _10_84_largest_rectangle_in_histogram {

    /*
        84. 柱状图中最大的矩形
        给定 n 个非负整数，用来表示柱状图中各个柱子的高度。
        每个柱子彼此相邻，且宽度为 1 。
        求在该柱状图中，能够勾勒出来的矩形的最大面积。

        示例 1:
        输入：heights = [2,1,5,6,2,3]
        输出：10
        解释：最大的矩形为图中红色区域，面积为 10


        提示：
        1 <= heights.length <=10^5
        0 <= heights[i] <= 10^4
     */

    // KeyPoint 方法一 枚举宽
    // 时间复杂度 O(n^2) => 超出时间限制
    public int largestRectangleArea(int[] heights) {
        int ans = 0;
        // 固定 left
        for (int left = 0; left < heights.length; left++) {
            int minHeight = heights[left];
            // 固定 right
            for (int right = left; right < heights.length; right++) {
                minHeight = Math.min(minHeight, heights[right]);
                // [left, right] => 因为两端都包括，故需要 +1
                int currWidth = right - left + 1;
                ans = Math.max(ans, minHeight * currWidth);
            }
        }
        return ans;
    }

    // KeyPoint 方法二 枚举高
    // 时间复杂度 O(n^2) => 超出时间限制
    public int largestRectangleArea2(int[] heights) {
        int n = heights.length;
        int ans = 0;
        // 将每个柱子固定成一个高度
        for (int mid = 0; mid < n; mid++) {
            int height = heights[mid];

            // 确定左右边界
            int left = mid, right = mid;
            // 找到第一个小于 height 位置才停止，此时 i 位置在有效宽度之外
            while (left >= 0 && heights[left] >= height) left--;
            while (right < n && heights[right] >= height) right++;

            // 宽度 right，left 都是不包括在内的，两端都不包括在内，故还需要减 1
            ans = Math.max(ans, height * (right - left - 1));
        }
        return ans;
    }

    // KeyPoint 方法三 枚举高 + 单调栈优化
    // 优化：针对每个柱子 i，找到左，右侧距离柱子 i 最近(第一个)比其低的柱子 => 单调栈 => 降低时间复杂度
    // 时间复杂度 O(n)
    // 空间复杂度 O(n)
    public static int largestRectangleArea3(int[] heights) {
        // 柱子高度 [1,3,5,6,2,3,3,1]
        int n = heights.length;

        // 1. 计算每根柱子左边第一个小于这根柱子的柱子(每根柱子的左边界)
        int[] left = new int[n];
        // 初值先设置为-1 => 比其小的柱子在 -1 位置
        Arrays.fill(left, -1);
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (int i = n - 1; i >= 0; i--) {
            // 比较对象始终是栈顶
            // 栈顶比较之后，弹栈后，再次判断栈顶
            // 直到成立，还要将 i 压栈
            while (!stack.isEmpty() && heights[i] < heights[stack.peek()]) {
                left[stack.peek()] = i;
                stack.pop();
            }
            stack.push(i);
        }

        // 2. 计算每根柱子右边第一个小于这根柱子的柱子(每根柱子的右边界)
        int[] right = new int[n];
        // 初值先设置为 n => 比其小的柱子在 n 位置
        Arrays.fill(right, n);
        // 引用 stack 复用，接受一个新的 new 对象
        stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && heights[i] < heights[stack.peek()]) {
                right[stack.peek()] = i;
                stack.pop();
            }
            stack.push(i);
        }

        int ans = 0;
        for (int mid = 0; mid < n; mid++) {
            int height = heights[mid];
            // 从 left 和 right 数组中获取左右边界
            // 注意：特殊情况 right[mid] = 8，left[mid] =-1
            // ans = height * width = 8 => 符合图示面积值
            // => 保证每个位置 mid，其对应面积 ans 的结果都是正确的
            int width = right[mid] - left[mid] - 1;
            ans = Math.max(ans, height * width);
        }
        return ans;
    }

    // testArea
    public static void testArea() {
        int[] arr = {1, 3, 5, 6, 2, 3, 3, 1};
        System.out.println(largestRectangleArea3(arr));
    }

    // KeyPoint 方法四 枚举高 + 单调栈优化(单调栈另外一种实现)
    public int largestRectangleArea4(int[] heights) {
        int n = heights.length;

        // 1. 计算每根柱子左边第一个小于这根柱子的柱子(每根柱子的左边界)
        int[] left = new int[n];
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        // KeyPoint 单调栈另外一种实现 => 从左往右遍历
        for (int i = 0; i < n; i++) {
            int x = heights[i];
            while (!stack.isEmpty() && x <= heights[stack.peek()]) {
                // 栈中索引对应元素的左边界已经找到了，故可以将栈中索引弹栈，
                // 直到 x > heights[stack.peek()]，找到 i 的左边界
                stack.pop();
            }
            // 若 stack.isEmpty()，则说明该元素没有左边界，将 left 设置为 -1
            // 补充说明：左边界 => 第一个小于这根柱子的柱子
            // x > heights[stack.peek()] => i 左边界就是 stack.peek()，设置 left[i]
            left[i] = (stack.isEmpty() ? -1 : stack.peek());
            // 每个柱子都要压栈，有可能是后面柱子的左边界
            stack.push(i);
        }

        // 2. 计算每根柱子右边第一个小于这根柱子的柱子(每根柱子的右边界)
        int[] right = new int[n];
        stack = new ArrayDeque<>();
        // KeyPoint 单调栈另外一种实现 => 从右往左遍历
        for (int i = n - 1; i >= 0; i--) {
            int x = heights[i];
            while (!stack.isEmpty() && x <= heights[stack.peek()]) {
                stack.pop();
            }
            // x > heights[stack.peek()] => 设置右边界
            right[i] = (stack.isEmpty() ? n : stack.peek());
            stack.push(i);
        }

        int ans = 0;
        for (int mid = 0; mid < n; mid++) {
            int height = heights[mid];
            ans = Math.max(ans, height * (right[mid] - left[mid] - 1));
        }
        return ans;
    }

    // KeyPoint 方法五 枚举高 + 单调栈优化 + 一次遍历
    // 将方法三-单调栈和方法四-单调栈结合，从而实现一次遍历，同时计算出 left 和 right
    public int largestRectangleArea5(int[] heights) {
        int n = heights.length;
        // 1. 计算每根柱子左边第一个小于这根柱子的柱子(每根柱子的左边界)
        int[] left = new int[n];
        int[] right = new int[n];
        // KeyPoint bug 修复：将右边界都初始化为 n，因为没有右边界索引位置的需要设置为 n
        Arrays.fill(right, n);
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        // KeyPoint 只是从左往右遍历，同时计算 left 和 right
        for (int i = 0; i < n; i++) {
            int x = heights[i];
            // 严格来说，应该是 x < heights[stack.peek()]，才是正确代码，但是为了兼容两种形式的单调栈
            // 若 x == heights[stack.peek()]，则表示两个柱子高度是一样的，即计算的面积是一样大的
            // 虽然包含 x == heights[stack.peek()]，导致该柱子面积计算不正确，但是没有关系，而另一个柱子
            // 面积计算正确，又因为，因为我们求的是最大面积，所以这就够了
            while (!stack.isEmpty() && x <= heights[stack.peek()]) {
                // 计算右边界
                // x <= heights[stack.peek()]
                right[stack.peek()] = i;
                stack.pop();
            }
            // 计算左边界
            // x > heights[stack.peek()]
            left[i] = (stack.isEmpty() ? -1 : stack.peek());
            stack.push(i);
        }

        int ans = 0;
        for (int mid = 0; mid < n; mid++) {
            int height = heights[mid];
            ans = Math.max(ans, height * (right[mid] - left[mid] - 1));
        }
        return ans;
    }

    public static void testStack() {
//        int[] height = {1, 2, 5, 4, 2, 6};
        int[] height = {1, 3, 5, 6, 2, 3, 3, 1};
        int n = height.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(right, n);
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            int x = height[i];
            // KeyPoint 单调栈 => 有可能执行多次 while 循环，而不是只执行一次就跳出 while 循环
            while (!stack.isEmpty() && x <= height[stack.peek()]) {
                right[stack.peek()] = i;
                stack.pop();
            }
            left[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }

        // height = {1, 2, 5, 4, 2, 6};
//        System.out.println(Arrays.toString(left)); // [-1, 0, 1, 1, 0, 4]
//        System.out.println(Arrays.toString(right)); // [6, 4, 3, 4, 6, 6]

        // height = {1, 3, 5, 6, 2, 3, 3, 1};
        System.out.println(Arrays.toString(left)); // [-1, 0, 1, 2, 0, 4, 4, -1]
        System.out.println(Arrays.toString(right)); // [7, 4, 4, 4, 7, 6, 7, 8]
    }

    public static void main(String[] args) {
        // testArea();
        testStack();
    }
}
