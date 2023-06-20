package alg_02_train_dm._26_day_动态规划一_2刷._06_53_MaxSubArray;

/**
 * @Author Wuyj
 * @DateTime 2023-06-04 12:11
 * @Version 1.0
 */
public class _06_53_MaxSubArray4 {

    // 动态规划 (前缀和解法)
    // 时间复杂度：O(n^2)
    // 空间复杂度：O(n)
    public int maxSubArray(int[] nums) {

        int n = nums.length;
        // 状态定义
        // prefixSum[i] 表示子数组 [0,i] 的累加和
        // KeyPoint 前缀和解法：子数组相关问题，使用非常多
        int[] prefixSum = new int[n];

        // 状态初始化
        prefixSum[0] = nums[0];
        int maxSum = prefixSum[0];

        for (int i = 1; i < n; i++) {
            // 前缀和
            prefixSum[i] = prefixSum[i - 1] + nums[i];
            maxSum = Math.max(maxSum, prefixSum[i]);
        }

        // 状态转移
        for (int i = 1; i < n; i++) {
            for (int j = i; j < n; j++) {
                // prefixSum[j] 表示子数组 [0,j] 的累加和
                // prefixSum[i-1] 表示子数组 [0,i-1] 的累加和
                // => [i,j] = prefixSum[j] - prefixSum[i - 1]
                int sum = prefixSum[j] - prefixSum[i - 1];
                // -2 1 -3 4 -1 2
                //       ↑    ↑
                //       i    j
                // sum(i,j) = sum(0,j) - sum(0,i-1)
                maxSum = Math.max(maxSum, sum);
            }
        }
        return maxSum;
    }
}
