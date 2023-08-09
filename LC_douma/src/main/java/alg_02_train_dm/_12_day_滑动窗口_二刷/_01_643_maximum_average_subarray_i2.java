package alg_02_train_dm._12_day_滑动窗口_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-07-24 11:33
 * @Version 1.0
 */
public class _01_643_maximum_average_subarray_i2 {

    // KeyPoint 方法二 前缀和优化 => 空间换时间
    // 时间复杂度：O(n)
    // 空间复杂度：O(n)
    public double findMaxAverage2(int[] nums, int k) {

        // KeyPoint 前缀和
        // 消除重复计算，降低时间复杂度

        // num          1 12 -5 -6 50 3 -6
        // prefixSum  0 1 13  8  2 52
        //            ↑          ↑
        //            i         i+k

        // KeyPoint 前缀和最长使用形式 => 需要掌握
        int[] prefixSum = new int[nums.length + 1];
        prefixSum[0] = 0;
        for (int i = 1; i <= nums.length; i++) {
            // prefixSum[i] 表达的是原数组中前 i 个元素之和
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }
        int maxSum = Integer.MIN_VALUE;
        int n = nums.length;
        // 一共是 n-k+1 个窗口
        for (int i = 0; i < n - k + 1; i++) {
            // KeyPoint 公式推导
            // pr[i+k] - pr[i] = [i] + [i+1] + ... + [i+k-1]
            // 当 i = 0 时，[0] + [1] + ... + [k-1]，包括 [0]
            int sum = prefixSum[i + k] - prefixSum[i];
            maxSum = Math.max(maxSum, sum);
        }
        return (double) maxSum / k;
    }

    // 前缀和另外一种方式 => 了解即可，不推荐使用
    public double findMaxAverage3(int[] nums, int k) {
        int n = nums.length;
        int[] prefixSum = new int[n];
        // 从 nums[0]，开始而不是从 0 开始
        prefixSum[0] = nums[0];
        for (int i = 1; i < n; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i];
        }

        // index      0  1  2  3
        // nums       1  2  3  4
        // prefixSum  1  3  6  10
        //
        // 若 k = 2，prefixSum[i+k] - prefixSum[i] 没法计算第一组两个数
        // 即 i = 0，pr[2] - pr[0] = [1] + [2]，而想求的是却是 [0] + [1]
        // => 故这种方式存在问题，不推荐使用

        // 因为第一组 k 个数，不使用 prefixSum[i+k]-prefixSum[i] 计算
        // 故循环次数减 1，即为 n - k
        // => 初始值定义为 pr[k-1] 表示：[0] + [1] + ... +[k-1]
        int maxSum = prefixSum[k - 1];

        for (int i = 0; i < n - k; i++) {
            // KeyPoint 公式推导
            // pr[i+k] - pr[i] = [i+1] + [i+2] + ... + [i+k]
            // 当 i = 0 时，[1] + [2] + ... + [k]，关键缺少 [0]
            int sum = prefixSum[i + k] - prefixSum[i];
            maxSum = Math.max(maxSum, sum);
        }

        return (double) maxSum / k;
    }
}
