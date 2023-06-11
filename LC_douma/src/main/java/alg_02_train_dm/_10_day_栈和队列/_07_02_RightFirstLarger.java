package alg_02_train_dm._10_day_栈和队列;

import java.util.ArrayDeque;
import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-04-25 23:43
 * @Version 1.0
 */
public class _07_02_RightFirstLarger {
    /*
        题目：找出数组中右边第一个比我大的元素
        一个整数数组 nums，找到每个元素：右边第一个比我大的下标位置，没有则用 -1 表示。
        输入：[5, 6]
        输出：[1, -1]

        解释：
            因为元素 5 的右边离我最近且比我大的位置应该是 nums[1]，
            最后一个元素 6 右边没有比 6 小的元素，所以应该输出 -1。
     */
    public static int[] findRightLarge(int[] nums) {
        int[] ans = new int[nums.length];
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        // 时间复杂度：O(n)
        // KeyPoint 右边 => 从左往右遍历
        for (int i = 0; i < nums.length; i++) {
            int x = nums[i];
            // 单调递减栈
            // KeyPoint 根据需求来写循环条件
            // 右边第一个比我大的元素 => x > nums[stack.peek()]
            while (!stack.isEmpty() && x > nums[stack.peek()]) {
                ans[stack.peek()] = i;
                stack.pop();
            }
            stack.push(i); // 索引
        }
        while (!stack.isEmpty()) {
            ans[stack.peek()] = -1;
            stack.pop();
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {6, 4, 3, 9, 5, 0, 6};
        System.out.println(Arrays.toString(findRightLarge(arr))); // [3, 3, 3, -1, 6, 6, -1]
    }
}
