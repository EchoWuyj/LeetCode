package alg_02_train_dm._27_day_动态规划二_2刷;

/**
 * @Author Wuyj
 * @DateTime 2023-06-06 17:00
 * @Version 1.0
 */
public class _04_213_house_robber_ii {

     /*
        213. 打家劫舍 II
        你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。
        这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。
        同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，
        系统会自动报警 。

        给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，
        今晚能够偷窃到的最高金额。

        示例   1：
        输入：nums = [2,3,2]
        输出：3
        解释：你不能先偷窃 1 号房屋(金额 = 2)，然后偷窃 3 号房屋(金额 = 2), 因为他们是相邻的。

        示例 2：
        输入：nums = [1,2,3,1]
        输出：4
        解释：你可以先偷窃 1 号房屋(金额 = 1)，然后偷窃 3 号房屋(金额 = 3)。
                  偷窃到的最高金额 = 1 + 3 = 4 。

        提示：
        1 <= nums.length <= 100
        0 <= nums[i] <= 1000
     */

    /*
        分析：
        首尾相连，第一个房子和最后一个房子，只能选择偷一个，分成两种情况
        [2,7,9,3,1]
         1.不偷第一个：[7,9,3,1] 最大金额
         2.不偷最后一个：[2,7,9,3] 最大金额
        [2,7,9,3,1] = max([7,9,3,1], [2,7,9,3])
        本质：将环拆成两个队列，一个是从 0 到 n-1，另一个是从 1到 n，然后返回两个结果最大的。
     */

    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0];
        int notRobLastHouse = rob(nums, 0, n - 2);
        int notRobFirstHouse = rob(nums, 1, n - 1);
        return Math.max(notRobFirstHouse, notRobLastHouse);
    }

    // KeyPoint 方法一 状态没压缩的代码
    public int rob2(int[] nums, int start, int end) {

        // KeyPoint 易错点 => 牢记
        // 状态转移方程中，涉及 dp[i-2]，故必须对 start 和 end 位置判断
        // start 和 end 在同一个位置，数组长度为 1，则 dp[i-2] 必然越界
        // 必须判断，测试用例调用可能会出现这种情况
        if (end == start) return nums[start];

        // dp[i]：表示偷盗 [0, i] 区间房子得到的最大金额
        int[] dp = new int[nums.length];
        // 不可以替换成：int[] dp = new int[end - start + 1];
        // 可能数组越界，始终都是按照 nums 长度设置 dp 数组大小
        // 只是选择 start 和 end 区间不同

        dp[start] = nums[start];
        dp[start + 1] = Math.max(nums[start], nums[start + 1]);

        // 注意：end 是可以取等的
        for (int i = start + 2; i <= end; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[end];
    }

    // KeyPoint 方法二 动态规划 + 状态压缩
    public int rob(int[] nums, int start, int end) {
        if (start - end + 1 == 1) return nums[start];
        int n = nums.length;

        int prev = nums[start];
        int curr = Math.max(nums[start], nums[start + 1]);

        for (int i = start + 2; i <= end; i++) {
            int tmp = Math.max(curr, prev + nums[i]);
            prev = curr;
            curr = tmp;
        }
        return curr;
    }
}
