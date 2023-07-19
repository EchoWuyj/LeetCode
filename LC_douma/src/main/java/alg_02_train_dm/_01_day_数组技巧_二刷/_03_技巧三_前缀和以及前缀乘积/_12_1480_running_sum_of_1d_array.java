package alg_02_train_dm._01_day_数组技巧_二刷._03_技巧三_前缀和以及前缀乘积;

/**
 * @Author Wuyj
 * @DateTime 2023-04-17 10:09
 * @Version 1.0
 */
public class _12_1480_running_sum_of_1d_array {

    /*
        1480. 一维数组的前缀和 => 前缀和
        给你一个数组 nums 。数组「动态和」的计算公式为
        runningSum[i] = sum(nums[0]，...，ums[i]) 请返回 nums 的动态和。

        输入：nums = [1,2,3,4]
        输出：[1,3,6,10]
        解释：动态和计算过程为 [1, 1+2, 1+2+3, 1+2+3+4] 。

        输入：nums = [1,1,1,1,1]
        输出：[1,2,3,4,5]
        解释：动态和计算过程为 [1, 1+1, 1+1+1, 1+1+1+1, 1+1+1+1+1] 。

        提示
        1 <= nums.length <= 1000
        -10^6 <= nums[i] <= 10^6

        KeyPoint  数据规模 与 时间复杂度
        最多 1000 个 10^6，到达 10^9 大小，不会越界

     */

    // KeyPoint 方法一 暴力求解
    // 时间复杂度 O(n^2)
    public int[] runningSum(int[] nums) {
        int n = nums.length;
        int[] prefixSum = new int[n];
        for (int i = 0; i < n; i++) {
            int sum = 0;
            // 每次 j 都从 0 累加到 i，存在重复计算，可以优化
            for (int j = 0; j <= i; j++) {
                sum += nums[j];
            }
            prefixSum[i] = sum;
        }
        return prefixSum;
    }

    // KeyPoint 方法二 一轮 for 循环 => 实现一
    // 动态规划思想 => 利用中间状态值，推导新的状态值，从而消除重复计算
    public static int[] runningSum1(int[] nums) {
        int n = nums.length;
        int[] prefixSum = new int[n];
        prefixSum[0] = nums[0];
        // i 从 1 开始，存在 [i-1]，避免索引越界
        for (int i = 1; i < n; i++) {
            // 通过中间值推导，消除重复计算，充分利用 prefixSum[i - 1]
            prefixSum[i] = prefixSum[i - 1] + nums[i];
        }
        return prefixSum;
    }

    // KeyPoint 方法二 一轮 for 循环 => 实现二
    // 注意：本题不适用，但需要掌握，后续别的题目使用很多
    public static int[] runningSum2(int[] nums) {
        int n = nums.length;
        int[] prefixSum = new int[n + 1];
        prefixSum[0] = 0;
        for (int i = 1; i <= n; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }
        return prefixSum;
    }


}
