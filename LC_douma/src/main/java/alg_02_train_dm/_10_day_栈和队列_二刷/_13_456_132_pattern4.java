package alg_02_train_dm._10_day_栈和队列_二刷;

import java.util.ArrayDeque;

/**
 * @Author Wuyj
 * @DateTime 2023-08-08 15:33
 * @Version 1.0
 */
public class _13_456_132_pattern4 {

    // KeyPoint 方法四 单调栈
    public boolean find132pattern5(int[] nums) {

        // KeyPoint 思路分析

        //                k     k'
        //                ↓     ↓
        // nums：-3 0 -5 -4 -3 -2 1
        //          ↑
        //          j

        // 基本规律：
        // 1.理论上，从右往左遍历，每个元素都有可能是 '132' 模式中的 '2'
        // 2.一直到遇到第一个 nums[j] > nums[k]，所有的 k 才可能成为真正的 '2'
        // 3.在所有可能成为真正 '2' 的所有元素中找到最大的 nums[k]，这样找到 nums[i] < nums[k] 的机会就大点

        // 实现
        // i 指针，从右往左遍历过程中，通过维护单调递减栈，以及变量 maxK
        // => 找到小于当前值 nums[i] 最大值 maxK
        // => 将 maxK 作为 nums[k]

        //           i' i
        //            ↓ ↓
        // nums：5 -1 0 6 3 2 1 4
        //            ↑
        //         nums[i]
        //                          |   |
        //               maxK = 4   |_6_|
        //                 ↑        stack
        //              nums[k]       ↑
        //                         nums[j]

        // 当 i 移动到 i' 位置
        // 栈底元素：6 => nums[j]
        // maxK：4 => nums[k]
        // nums[i'] => nums[i]

        // 索引 i < j < k
        // 数值 nums[i] < nums[k] < nums[j]

        int n = nums.length;
        if (n < 3) return false;
        int maxK = Integer.MIN_VALUE;

        // 在当前值的左边，小于 nums[k] 的值，找到机会更大
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        // 最后一个元素，需要单独 push 到 stack 中
        stack.push(nums[n - 1]);
        for (int i = n - 2; i >= 0; i--) {
            // nums[i] < maxK ⇆ nums[k]
            // => nums[i] < nums[k] < nums[j] 直接满足 132 模式，返回 true
            // 注意：存在 maxK，则栈中必然存在 nums[j] > maxK，栈不会为空的
            if (nums[i] < maxK) return true;

            // 为了保证：找到小于当前值 nums[i] 最大值 maxK
            // 故 nums[i] > stack.peek() 时，必然需要入栈
            while (!stack.isEmpty() && nums[i] > stack.peek()) {
                // 将 stack.peek() 赋值给 maxK
                // 自然维护了 当前值 nums[i] 最大值 maxK
                maxK = stack.peek();
                stack.pop();
            }
            // nums[j] ⇆ nums[i] > maxK ⇆ nums[k]
            if (nums[i] > maxK) stack.push(nums[i]);
        }
        return false;
    }
}
