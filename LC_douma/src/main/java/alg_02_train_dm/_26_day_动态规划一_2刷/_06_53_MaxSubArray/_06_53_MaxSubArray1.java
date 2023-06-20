package alg_02_train_dm._26_day_动态规划一_2刷._06_53_MaxSubArray;

/**
 * @Author Wuyj
 * @DateTime 2023-06-04 11:12
 * @Version 1.0
 */
public class _06_53_MaxSubArray1 {
    /*
        53. 最大子序和(最大和的连续子数组)
        给定一个整数数组 nums，找到一个具有最大和的连续子数组(子数组最少包含一个元素)，返回其最大和。
        KeyPoint 注意：子数组必须连续(记忆：数组需连续)

        示例 1：
        输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
        输出：6
        解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。

        示例 2：
        输入：nums = [1]
        输出：1

        示例 3：
        输入：nums = [0]
        输出：0

        示例 4：
        输入：nums = [-1]
        输出：-1

        示例 5：
        输入：nums = [-100000]
        输出：-100000
        
        提示：
        1 <= nums.length <= 3 * 10^4  => O(n^2) 时间复杂度算法，无法通过
        -10^5 <= nums[i] <= 10^5

   */

    // 暴力解法
    // O(n^3) => 超时间
    public int maxSubArray(int[] nums) {
        int maxSum = Integer.MIN_VALUE;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                // [i, j] => 存在重复计算，可以优化
                int sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += nums[k];
                }
                maxSum = Math.max(maxSum, sum);
            }
        }
        return maxSum;
    }

    // 补充说明：
    // 同样可以抽象成树结构，每个节点相当于每一步的一次选择，找出所有可能的子数组可能性(看PPT)
    // 因为本题直接可以使用迭代方式，故没有使用 DFS 回溯来求解
}
