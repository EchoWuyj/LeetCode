package alg_02_train_dm._10_day_栈和队列;

import java.util.ArrayDeque;

/**
 * @Author Wuyj
 * @DateTime 2023-08-08 0:54
 * @Version 1.0
 */
public class _13_456_132_pattern2 {

    // KeyPoint 方法四 单调栈 + 前缀最小值 -> O(n)
    public static boolean find132pattern4(int[] nums) {
        int n = nums.length;
        if (n < 3) return false;

        // 维护一个前缀最小值数组，即到当前为止的最小值，用于确定 nums[i]
        int[] minPrefix = new int[nums.length];
        minPrefix[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            // nums[i] 有最小值，则更新，没有，则维持之前 minPrefix[i - 1]
            // nums       1 0 2 -4 -3 -2 1
            // minPrefix  1 0 0 -4 -4 -4 -4
            // minPrefix[i - 1] 遇到 -4，会将 -4 一直保留下来
            minPrefix[i] = Math.min(minPrefix[i - 1], nums[i]);
        }
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        stack.push(nums[n - 1]);
        // j 从倒数第二个元素开始，j >= 1，即 j 也不可能是第一元素
        // 想利用 minPrefix，只能从后往前遍历，否则不存在 minPrefix
        for (int j = n - 2; j >= 1; j--) {
            // 保证 nums[j] > minPrefix[j](nums[i])
            if (nums[j] > minPrefix[j]) {
                // stack.peek()(nums[k]) <= minPrefix[j](nums[i])
                while (!stack.isEmpty() && stack.peek() <= minPrefix[j]) {
                    // stack 不断弹栈，直到 stack.peek()(nums[k]) > minPrefix[j](nums[i])
                    stack.pop();
                }
                if (!stack.isEmpty() && stack.peek() < nums[j]) {
                    return true;
                }
                // 在 j 从后往前遍历过程中，在 nums[j] > nums[i] 情况下
                // 将所有 nums[j] 能作为 num[k] 的值都压栈
                stack.push(nums[j]);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] arr = {1, 0, 2, -4, -3, -2, 1};
        find132pattern4(arr);
    }

    // KeyPoint 方法四 单调栈
    public boolean find132pattern5(int[] nums) {

        // -3 0 -5 -4 -3 -2 1
        // 理论上，从右往左的每个元素都有可能是 132 模式中的 2，一直到遇到第一个
        // nums[j] > nums[k]，所有的 k 才可能成为真正的 2，在所有可能成为真正
        // 2 的所有元素中找到最大的 nums[k]，这样找到 nums[i] 的机会就大点

        int n = nums.length;
        if (n < 3) return false;
        int maxk = Integer.MIN_VALUE;
        // KeyPoint 单调递减栈 => 找到小于当前值的最大值
        // 将小于当前值的最大值作为 nums[k]
        // 在当前值的左边，小于 nums[k] 的值，找到机会更大
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        stack.push(nums[n - 1]);
        // 测试数据 5 -1 0 6 3 2 1 4
        for (int i = n - 2; i >= 0; i--) {
            // nums[i] < maxk，直接满足 132 模式，返回 true
            if (nums[i] < maxk) return true;
            while (!stack.isEmpty() && nums[i] > stack.peek()) {
                // 若存在 maxk，必有 num[j] > maxk
                maxk = stack.peek();
                stack.pop();
            }
            if (nums[i] > maxk) stack.push(nums[i]);
        }
        return false;
    }
}
