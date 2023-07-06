package alg_02_train_dm._29_day_动态规划四_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-06-12 16:37
 * @Version 1.0
 */
public class _08_152_maximum_product_subarray {
     /* 
        152. 乘积最大子数组
        给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组
        (该子数组中至少包含一个数字)，并返回该子数组所对应的乘积。

        示例 1:
        输入: [2,3,-2,4]
        输出: 6
        解释: 子数组 [2,3] 有最大乘积 6。

        示例 2:
        输入: [-2,0,-1]
        输出: 0
        解释: 结果不能为 2, 因为 [-2,-1] 不是连续子数组。
        子数组：需要保持连续

        提示:
        1 <= nums.length <= 2 * 104
        -10 <= nums[i] <= 10
        nums 的任何前缀或后缀的乘积都 保证 是一个 32-位 整数

     */

    // 类似：最大和的连续子数组
    // 定义双状态的动态规划
    public int maxProduct1(int[] nums) {

        // 最大和的连续子数组
        // 状态转移方程
//        for (int i = 1; i < n; i++) {
//            // 根据 dp[i-1] 值正负，选择是否加上 dp[i-1]，dp[i-1] 为正加上，dp[i-1]为负则不加
//            // dp[i-1] 为 i-1 位置最优解，故可以在其基础上做选择
//            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
//            maxSum = Math.max(maxSum, dp[i]);
//        }

        // 加号累和这种情况，可以使用 dp[i] = max{dp[i-1] + nums[i],nums[i]}
        // 但乘积存在：负负得正，dp[i] = max{dp[i-1] * nums[i],nums[i]} 并不是适用
        // 还需要记录：最小子数组乘积，有可能负负得正，即 nums[i] 为负数，minP[i] 为负数

        int n = nums.length;
        // maxP[i] 表示以索引为 i 的元素结尾的'最大'子数组乘积
        int[] maxP = new int[n];
        // minP[i] 表示以索引为 i 的元素结尾的'最小'子数组乘积
        int[] minP = new int[n];

        // nums 5 6  -3   4    -3
        // maxP 5 30 -3   4    1080
        // minP 5 6  -90 -360  -12

        maxP[0] = nums[0];
        minP[0] = nums[0];
        int res = nums[0];

        for (int i = 1; i < n; i++) {
            // 1.考虑乘积存在负负得正 => 最大值
            // => 若 nums[i] < 0，minP[i - 1] < 0，两者相乘，有可能变成最大值，故也需要将其放入 Math.max 进行比较
            maxP[i] = Math.max(maxP[i - 1] * nums[i], Math.max(nums[i], minP[i - 1] * nums[i]));
            // 2.考虑乘积存在负正得负 => 最小值
            // => 若 nums[i] < 0，maxP[i - 1] > 0，两者相乘，有可能变成最小值，故也需要将其放入 Math.min 进行比较
            minP[i] = Math.min(minP[i - 1] * nums[i], Math.min(nums[i], maxP[i - 1] * nums[i]));
            res = Math.max(res, maxP[i]);
        }
        return res;
    }

    // 动态规划 + 状态的压缩
    public int maxProduct(int[] nums) {
        int n = nums.length;
        // 使用 curMaxP 和 curMinP 替换 curMaxP[i] 和 curMinP[i]
        int curMaxP = nums[0];
        int curMinP = nums[0];
        int ans = nums[0];

        for (int i = 1; i < n; i++) {
            // KeyPoint 注意：分清哪些是临时变量，哪些是全局变量
            // preMax，preMin => 临时变量 => 记录上一轮 curMaxP 和 curMinP，对应 curMaxP[i-1] 和 curMinP[i-1]
            // 经验：一般dp[i-1]，dp[j-1] 这些都是临时变量，需要替换的，dp[i]，dp[j] 都是需要保存下来的
            // curMaxP，curMinP => 全局变量 => 本轮
            int preMax = curMaxP, preMin = curMinP;
            curMaxP = Math.max(preMax * nums[i], Math.max(nums[i], preMin * nums[i]));
            curMinP = Math.min(preMin * nums[i], Math.min(nums[i], preMax * nums[i]));
            ans = Math.max(ans, curMaxP);
        }

        return ans;
    }
}
