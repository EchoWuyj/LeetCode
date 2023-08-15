package alg_02_train_dm._25_day_贪心算法二_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-04-13 11:06
 * @Version 1.0
 */
public class _02_674_longest_continuous_increasing_subsequence {
     /*
        674. 最长连续递增序列
        给定一个未经排序的整数数组，找到最长且连续递增的子序列，并返回该序列的长度。

        连续递增的子序列可以由两个下标 l 和 r（l < r）确定，如果对于每个 l <= i < r，
        都有 nums[i] < nums[i + 1]
        那么子序列 [nums[l], nums[l + 1], ..., nums[r - 1], nums[r]] 就是连续递增子序列。

        示例 1：
        输入：nums = [1,3,5,4,7]
        输出：3
        解释：最长连续递增序列是 [1,3,5], 长度为3。
        尽管 [1,3,5,7] 也是升序的子序列, 但它不是连续的，因为 5 和 7 在原数组里被 4 隔开。

        示例 2：
        输入：nums = [2,2,2,2,2]
        输出：1
        解释：最长连续递增序列是 [2], 长度为1。

        提示：
        1 <= nums.length <= 10^4
        -10^9 <= nums[i] <= 10^9

     */

    // KeyPoint 直接模拟，快慢指针
    // 通过 slow 和 fast 指针来界定：递增子序列 start 和 end 位置
    // slow -> start
    // fast -> end
    // KeyPoint 范围区间划分，联想双指针
    public int findLengthOfLCIS(int[] nums) {

        //   1    2   3    2    3     4   6
        //   ↑        ↑
        //  slow     fast

        //   1    2   3    2    3     4   6
        //   ↑        ↑    ↑
        //  slow   fast-1 fast
        // nums[fast-1] >= nums[fast] => 将 slow 移动到 fast 位置

        // 数组长度从 1 开始，连续递增子序列的长度必然从 1 开始
        int res = 1;
        int slow = 0;
        int n = nums.length;
        // KeyPoint while (fast < nums.length) 两种循环得能灵活使用
        for (int fast = 1; fast < n; fast++) {
            if (nums[fast - 1] >= nums[fast]) {
                // nums[fast-1] >= nums[fast] 不满足续递增子序列条件
                // 更新 slow 索引，相当于移动 slow，重新再计算下一段连续递增子序列的长度
                slow = fast;
                // 跳过本轮循环，继续执行下轮循环
                continue;
            }
            // nums[fast - 1] < nums[fast]
            // 贪心地不断向前扩展连续递增的子序列的范围，从而求得最长连续递增序列
            res = Math.max(res, fast - slow + 1);
        }
        return res;
    }
}
