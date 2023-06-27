package alg_02_train_dm._30_day_动态规划五_总结;

/**
 * @Author Wuyj
 * @DateTime 2023-06-16 19:48
 * @Version 1.0
 */
public class _01_740_delete_and_earn {
     /*
        740. 删除并获得点数
        给你一个整数数组 nums，你可以对它进行一些操作。

        每次操作中，选择任意一个 nums[i] ，删除它并获得 nums[i] 的点数。
        之后，你必须删除 所有 等于 nums[i] - 1 和 nums[i] + 1 的元素。

        开始你拥有 0 个点数。返回你能通过这些操作获得的最大点数。

        示例 1：
        输入：nums = [3,4,2]
        输出：6
        解释：
        删除 4 获得 4 个点数，因此 3 也被删除。
        之后，删除 2 获得 2 个点数。总共获得 6 个点数。

        示例 2：
        输入：nums = [2,2,3,3,3,4]
        输出：9
        解释：
        删除 3 获得 3 个点数，接着要删除 两个 2 和 4 。
        之后，再次删除 3 获得 3 个点数，再次删除 3 获得 3 个点数。
        总共获得 9 个点数。

        提示：
        1 <= nums.length <= 2 * 10^4
        1 <= nums[i] <= 10^4

     */

    /*
        选择 nums[i] ，然后删除所有的 nums[i] - 1 和 nums[i] + 1
        这个意思就是，偷了 nums[i] 后，就不能偷 nums[i] 前后相邻的两个数了
        所以，问题可以抽象为打家劫舍问题
        => 两间相邻的房屋，不能同时偷，i-1 i i+1，即：若偷了 i，则 i-1 和 i+1 不能偷

        算法步骤为：
        1. 数组转换：将本题数组 转成 打家劫舍的数组
           先对 nums 中的每个元素进行个数统计，得到 sum 数组
           存储在一个数组中，nums 中元素的值作为 sum 数组的下标，相同元素的累加和作为 sum 数组元素的值

           nums [2,2,3,3,3,4]
            ↓
           sum
           index 0 1 2 3 4
           value     4 9 4

        2. 然后对 sum 数组进行打家劫舍
     */
    public int deleteAndEarn(int[] nums) {
        int maxVal = 0;
        for (int val : nums) {
            maxVal = Math.max(maxVal, val);
        }
        // 为了满足 sum 数组索引能达到 maxVal，将 nums 中值都包括
        // 故将 sum 数组大小定义为 maxVal + 1， 则 sum 最大索引为 maxVal
        int[] sum = new int[maxVal + 1];

        for (int val : nums) {
            sum[val] += val;
        }
        return rob(sum);
    }

    // 198 号算法题：打家劫舍
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0];

        // dp[i]：表示偷盗 [0, i] 区间房子得到的最大金额
        int[] dp = new int[n];

        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }

        return dp[n - 1];
    }
}
