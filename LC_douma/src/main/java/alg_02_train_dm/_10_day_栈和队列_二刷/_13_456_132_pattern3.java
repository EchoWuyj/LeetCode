package alg_02_train_dm._10_day_栈和队列_二刷;

import java.util.ArrayDeque;

/**
 * @Author Wuyj
 * @DateTime 2023-08-08 0:54
 * @Version 1.0
 */
public class _13_456_132_pattern3 {

    // KeyPoint 方法四 单调栈 + 前缀最小值  O(n)
    public static boolean find132pattern4(int[] nums) {

        // KeyPoint 思路分析

        // 索引 i < j < k
        // 数值 nums[i] < nums[k] < nums[j]

        // 需要保证 nums[k] > nums[i]
        // k 从后往前遍历，只要 nums[k] 大于到当前 k 位置的前缀最小值
        // 此时 nums[k] 可以作为 '132' 模式中 '2'
        // 想利用 prefixMin，只能从后往前遍历，否则不存在 prefixMin

        // 类比前缀和，求解前缀最小值
        // nums       1 0 2 -4 -3 -2 1
        // prefixMin  1 0 0 -4 -4 -4 -4

        //                           k
        //                           ↓
        // num        1 0 2 -4 -3 -2 1
        //                √     √  √ √
        // prefixMin  1 0 0 -4 -4 -4 -4

        int n = nums.length;
        if (n < 3) return false;

        // KeyPoint 本质：数据预处理
        // 维护一个前缀最小值数组 prefixMin，即到当前为止的最小值，其中 prefixMin[i] 可以看做 numsi
        int[] prefixMin = new int[n];
        prefixMin[0] = nums[0];
        // i 从 1 开始，后面有 i-1
        for (int i = 1; i < n; i++) {
            // 当前元素 nums[i] 和 i-1 前缀最小值 prefixMin[i-1] 比较
            // 1.若 nums[i] 为最小值，则更新 prefixMin[i]
            // 2.若 nums[i] 为不最小值，则维持之前 prefixMin[i-1]
            prefixMin[i] = Math.min(prefixMin[i - 1], nums[i]);
        }

        // stack 中存储的是 nums[k]
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        stack.push(nums[n - 1]);

        // 想利用 prefixMin，只能从后往前遍历，否则不存在 prefixMin
        // 1.j 从倒数第二个元素开始 => j = n-2
        // 2.j 也不可能是第一元素 => j >= 1
        // 时间复杂度 O(n) => 每个元素最多被遍历 2 次
        for (int j = n - 2; j >= 1; j--) {
            // 保证 nums[j] > prefixMin[j] ⇆ nums[i]
            // => nums[j] > nums[i]
            if (nums[j] > prefixMin[j]) {

                // nums[i] ⇆ prefixMin[j] >= stack.peek() ⇆ nums[k]
                // => nums[i] >= nums[k]，说明栈顶的 nums[k]不行，需要弹栈
                while (!stack.isEmpty() && prefixMin[j] >= stack.peek()) {
                    stack.pop();
                }
                // stack 不断弹栈，直到 nums[k] ⇆ stack.peek() > prefixMin[j] ⇆ nums[i]
                // => nums[k] > nums[i]
                if (!stack.isEmpty() && stack.peek() < nums[j]) {
                    // nums[k] ⇆ stack.peek() < nums[j]
                    // => nums[k] < nums[j]
                    // 综上：nums[i] <nums[k] < nums[j]，返回 true
                    return true;
                }
                // 在 j 从后往前遍历过程中，在 nums[j] > prefixMin[j] ⇆ nums[i] 情况下
                // 将所有 nums[j] 可以作为 num[k] 的值都压栈
                stack.push(nums[j]);
            }
        }
        return false;
    }


}
