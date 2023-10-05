package alg_02_train_dm._27_day_动态规划二_二刷;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-06-06 16:48
 * @Version 1.0
 */
public class _03_198_house_robber {
     /*
        198. 打家劫舍
        你是一个专业的小偷，计划偷窃沿街的房屋。
        每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是：相邻的房屋装有相互连通的防盗系统
        如果 两间相邻的房屋 在同一晚上被小偷闯入，系统会自动报警。

        给定一个代表每个房屋存放金额的 非负整数 数组，
        计算你 不触动警报装置的情况下，一夜之内能够偷窃到的最高金额。

        示例 1：
        输入：[1,2,3,1]
        输出：4
        解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
              偷窃到的最高金额 = 1 + 3 = 4 。

        示例 2：
        输入：[2,7,9,3,1]
        输出：12
        解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
              偷窃到的最高金额 = 2 + 9 + 1 = 12 。

        提示：
        1 <= nums.length <= 100
        0 <= nums[i] <= 400

        KeyPoint 数据规模与时间复杂度
        1 <= nums.length <= 100 => 单纯 dfs 必然超时

     */

    // KeyPoint 方法一 记忆化搜索(一)
    // 每个房间有两种选择，选择偷与不偷，抽象成树形结构
    public int rob1(int[] nums) {
        int n = nums.length;
        // memo[i]：存储偷盗 [i,n - 1] 区间房子得到的最大金额
        int[] memo = new int[n];
        Arrays.fill(memo, -1);
        return dfs(nums, 0, memo);
    }

    // dfs 后续遍历 => 从子节点获取统计信息，返回到父节点进行统计计算
    // 返回值 int：区间 [i,n - 1] 房子得到的最大金额，dfs 最后递归遍历到数组边界
    private int dfs(int[] nums, int i, int[] memo) {
        // i 已经越界，返回金额为 0
        if (i >= nums.length) return 0;
        if (memo[i] != -1) return memo[i];

        // 二叉树
        // 1.选择：不偷
        int left = dfs(nums, i + 1, memo);
        // 2.选择：偷
        int right = dfs(nums, i + 2, memo);

        // 从 数组开头 考虑问题，将数组第一个元素作为根节点，导致 dfs 代码 i+1 和 i+2
        // 数组 2 7 9 3 1
        // 索引 0 1 2 3 4
        //      ↑
        //      i

        // 归的过程
        // 每个房间有两种选择，选择偷与不偷，比较两种选择的最大值
        // 在归的过程，每次都是选择 Max 分支，从而保证在最终回溯到起始点 0 得到的为最大值
        memo[i] = Math.max(left, right + nums[i]);
        return memo[i];
    }

    // KeyPoint 方法二 记忆化搜索(二)
    public int rob2(int[] nums) {
        // memo[i]：存储偷盗 [0, i] 区间房子得到的最大金额
        int[] memo = new int[nums.length];
        Arrays.fill(memo, -1);
        return dfs2(nums, nums.length - 1, memo);
    }

    private int dfs2(int[] nums, int i, int[] memo) {

        // 将 0 和 1 作为叶子节点 => 最小子问题
        // 面对 0 号房间，最大金额就是将其偷了 nums[0]
        if (i == 0) return nums[0];

        // 面对 1 号房间，存在选择
        // 1.不偷 nums[1]，偷 nums[0]
        // 2.偷 nums[1]
        // 返回两者的较大值
        if (i == 1) return Math.max(nums[0], nums[1]);

        if (memo[i] != -1) return memo[i];

        // 递归调用时，需要保证函数名一致性 dfs2
        int left = dfs2(nums, i - 1, memo);
        int right = dfs2(nums, i - 2, memo);

        // 从 数组结尾 考虑问题，将数组最后一个元素作为根节点，导致 dfs 代码 i-1 和 i-2
        // 数组 2 7 9 3 1
        // 索引 0 1 2 3 4
        //              ↑
        //              i

        // 偷与不偷，考虑是是当前节点，即是否加上 nums[i]
        // 左右子树返回值 left，right 必须得有
        memo[i] = Math.max(left, right + nums[i]);
        return memo[i];
    }

    // KeyPoint 方法三 动态规划
    public int rob3(int[] nums) {
        int n = nums.length;
        // 使用 dp，存在 dp[i-2]，需要特判，避免越界
        if (n == 1) return nums[0];

        // 在写动态规划之前，一定要搞明白 memo 存储的是什么，这样后续才能使用动态规划来做
        // dp[i]：表示偷盗 [0, i] 区间房子得到的最大金额
        int[] dp = new int[n];

        // 偷 => 最高金额
        dp[0] = nums[0];
        // 偷与不偷中取最高金额
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < n; i++) {
            // dp[0]，dp[1] 已知，想推导出 dp[i]，必然是比 dp[i] 小的
            // 故只能是 dp[i-1]，dp[i-2]，而不是 dp[i+1]，dp[i+2]
            // 逻辑分析
            // 1.i 节点不偷，不加 nums[i]，则可选择相邻 i-1 节点
            // 2.i 节点偷，加上 nums[i]，则只能选择 i-2 节点
            // KeyPoint 针对 nums[i] 来判断当前的 dp[i] 状态，使用的是 nums[i]，不是 nums[i-2]
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }

        return dp[n - 1];
    }

    // KeyPoint 方法四 动态规划 + 状态压缩
    public int rob4(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0];

        int prevMax = nums[0];
        int currMax = Math.max(nums[0], nums[1]);

        for (int i = 2; i < n; i++) {
            int tmpMax = Math.max(currMax, prevMax + nums[i]);
            prevMax = currMax;
            currMax = tmpMax;
        }
        return currMax;
    }
}
