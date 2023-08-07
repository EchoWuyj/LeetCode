package alg_02_train_dm._10_day_栈和队列;

import java.util.ArrayDeque;
import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-08-07 15:07
 * @Version 1.0
 */
public class _10_84_largest_rectangle_in_histogram2 {

    // KeyPoint 方法三 枚举高 + 单调栈优化 + 两次遍历
    // 时间复杂度 O(n)
    // 空间复杂度 O(n)
    public static int largestRectangleArea3(int[] heights) {

        // KeyPoint 优化
        // 针对每个柱子 i，找到左右侧距离柱子 i 最近(第一个)比其低的柱子
        // => 联想：数组中右边第一个比我小的元素，数组中左边离我最近比我小的元素
        // => 单调栈，时间复杂度O(n)
        // => 利用单调栈进行预计算，消除方法二中枚举高 for 循环叠加

        // KeyPoint 利用单调栈进行预计算
        int n = heights.length;
        // 1.计算每根柱子左边，第一个小于这根柱子的柱子 => 每根柱子的左边界
        int[] left = new int[n];

        // KeyPoint 注意事项
        // left 和 right 数组中，若没有找到比其小的元素，不能使用数组默认值 0，
        // 因为后续需要计算面积，为了保证面积计算正确，将其默认值分别设置 -1 和 n

        // 初值先设置为 -1
        // => -1 表示：在当前数组中，没有比其小的元素
        // => 避免最后再去 while 循环遍历剩余的单调栈，提高性能
        Arrays.fill(left, -1);
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        // 从右往左遍历
        for (int i = n - 1; i >= 0; i--) {
            int height = heights[i];
            // KeyPoint 深刻理解单调栈逻辑
            // 1.在 while 循环条件中，比较对象始终是栈顶索引
            // 2.循环条件满足，单调栈弹栈后，while 循环再次判断栈顶，直到循环条件不满足
            // 3.将当前 i 索引，压入单调栈中
            // => 总结：单调栈有可能执行多次 while 循环，而不是只执行一次就跳出 while 循环
            while (!stack.isEmpty() && height < heights[stack.peek()]) {
                left[stack.pop()] = i;
            }
            stack.push(i);
        }

        // 2.计算每根柱子右边，第一个小于这根柱子的柱子 => 每根柱子的右边界
        int[] right = new int[n];

        // 初值先设置为 n
        // => n 表示：在当前数组中，没有比其小的元素
        // => 避免最后再去 while 循环遍历剩余的单调栈，提高性能
        Arrays.fill(right, n);

        // 引用 stack 复用，接受一个新的 new 对象
        stack = new ArrayDeque<>();

        // 从左往右遍历
        for (int i = 0; i < n; i++) {
            int height = heights[i];
            while (!stack.isEmpty() && height < heights[stack.peek()]) {
                right[stack.pop()] = i;
            }
            stack.push(i);
        }

        int res = 0;
        for (int mid = 0; mid < n; mid++) {
            int height = heights[mid];
            // [left,right] 左右都不包括，故还得减 1
            // 其中，最极端情况：right[mid] = 8，left[mid] =-1
            // width = right[mid]-left[mid]-1 = 8-(-1)-1 = 8 => 符合图示宽度值
            // res = height * width = 8 => 符合图示面积值
            // 最终：保证每个位置 mid，其对应面积 res 的结果都是正确的
            int width = right[mid] - left[mid] - 1;
            res = Math.max(res, height * width);
        }
        return res;
    }
}
