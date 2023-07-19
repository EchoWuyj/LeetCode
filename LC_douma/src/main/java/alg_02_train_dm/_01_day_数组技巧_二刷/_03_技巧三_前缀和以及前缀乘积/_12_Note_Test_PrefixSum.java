package alg_02_train_dm._01_day_数组技巧_二刷._03_技巧三_前缀和以及前缀乘积;

/**
 * @Author Wuyj
 * @DateTime 2023-07-19 15:33
 * @Version 1.0
 */
public class _12_Note_Test_PrefixSum {

    // KeyPoint 1.前缀和 => 一轮 for 循环 => 实现一
    public int[] test1(int[] nums) {
        int n = nums.length;
        int[] prefixSum = new int[n];
        prefixSum[0] = nums[0];
        for (int i = 1; i < n; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i];
        }
        return prefixSum;

        // nums
        // index        0 1 2 3
        // value        1 2 3 4

        // prefixSum
        // index        0 1 2 3
        // value        1 3 6 10
        //                  ↑
        //                  i

        // prefixSum[i] = prefixSum[i-1] + nums[i];
        // => prefixSum[2] = prefixSum[1] + nums[2]
        // => 6 = 3 + 3

        // KeyPoint 注意事项
        // prefixSum[i] 计算的是 nums[0] + nums[1] ... nums[i-1] + nums[i] 前缀和，包括当前 nums[i]

    }

    // KeyPoint 2.前缀和 => 一轮 for 循环 => 实现二
    public int[] test2(int[] nums) {
        int n = nums.length;
        int[] prefixSum = new int[n + 1];
        prefixSum[0] = 0;
        for (int i = 1; i <= n; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }
        return prefixSum;

        // nums
        // index        0 1 2 3
        // value        1 2 3 4

        // prefixSum
        // index        0 1 2 3 4
        // prefixSum    0 1 3 6 10
        //                  ↑
        //                  i

        // prefixSum[i] = prefixSum[i-1] + nums[i-1]
        // => prefixSum[2] = prefixSum[1] + nums[1]
        // => 3 = 1 + 2

        // KeyPoint 注意事项
        // prefixSum[i] 计算是：原数组中前 i 个元素之和
        // => 即 nums[0] + nums[1] ... nums[i-1] 前缀和，不包括当前 nums[i]

        // 比如：
        // prefixSum[1] => 原数组中前 1 个元素之和
        // prefixSum[1] = prefixSum[0] + nums[0] = nums[0] => 只有 nums[0] 一个元素

    }
}
