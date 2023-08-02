package alg_02_train_dm._13_day_综合应用一_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-03-17 20:08
 * @Version 1.0
 */
public class _01_01_TwoSum1 {

    /*
        1. 两数之和
        给定一个整数数组 nums 和一个整数目标值 target，
        请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。

        你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
        你可以按任意顺序返回答案。

        示例 1：
        输入：nums = [2,7,11,15], target = 9
        输出：[0,1]
        解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。

        提示：
        2 <= nums.length <= 10^4
        -10^9 <= nums[i] <= 10^9
        -10^9 <= target <= 10^9
        只会存在一个有效答案
     */

    // KeyPoint 思考方式
    // 面对算法题，一开始一般都是使用最简单，最暴力解法
    // 然后再去不断优化，所以不要想着一步就想出最优解，除非之前你之前遇到过 [doge]

    // KeyPoint 方法一 线性查找
    // 时间复杂度：O(n^2)
    // 空间复杂度：O(1)
    public int[] twoSum1(int[] nums, int target) {
        if (nums == null || nums.length == 0) return null;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            // 固定一个元素
            int x = nums[i];
            // KeyPoint 优化
            // 一个一根找 => 线性查找 => 性能瓶颈 => 思考别的查找方式(二分，哈希，堆)
            // 同一个元素在答案里不能重复出现 => j 必然是从 i+1 开始
            for (int j = i + 1; j < n; j++) {
                if (nums[j] == target - x) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }
}
