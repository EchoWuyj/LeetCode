package alg_02_train_dm._12_day_滑动窗口_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-05-04 15:11
 * @Version 1.0
 */
public class _02_209_minimum_size_subarray_sum1 {
     /* 
        209. 长度最小的子数组
        给定一个含有 n 个正整数的数组和一个正整数 target 。
    
        找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 
        [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。
        如果不存在符合条件的子数组，返回 0 。
    
        示例 1：
        输入：target = 7, nums = [2,3,1,2,4,3]
        输出：2
        解释：子数组 [4,3] 是该条件下的长度最小的子数组。
        示例 2：

        输入：target = 4, nums = [1,4,4]
        输出：1
    
        示例 3：
        输入：target = 11, nums = [1,1,1,1,1,1,1,1]
        输出：0
        
        提示：
        1 <= target <= 10^9
        1 <= nums.length <= 10^5
        1 <= nums[i] <= 10^5
        
        进阶：如果你已经实现 O(n) 时间复杂度的解法, 请尝试设计一个 O(n log(n)) 时间复杂度的解法。

     */

    // KeyPoint 方法一 暴力解法
    // 时间复杂度：O(n^2)
    public int minSubArrayLen1(int target, int[] nums) {
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum >= target) {
                    // i 和 j 两端都要包括，故 j - i + 1
                    res = Math.min(res, j - i + 1);
                }
            }
        }

        // 不能直接返回 minLen，minLen 有可能 Integer.MAX_VALUE，故需要判断一下
        // 若 minLen 为 Integer.MAX_VALUE，说明没有符合条件的子数组，则按照题目要求应该返回 0
        return res == Integer.MAX_VALUE ? 0 : res;
    }

    // KeyPoint 方法二 滑动窗口
    // 求最小窗口，先移动 right，扩大窗口，直到满足条件，再去移动 left，缩小窗口
    // 时间复杂度：O(n)
    // 空间复杂度：O(1)
    public int minSubArrayLen2(int target, int[] nums) {
        int res = Integer.MAX_VALUE;
        int left = 0, right = 0;
        int windowSum = 0;
        int n = nums.length;
        while (right < n) {
            windowSum += nums[right];
            // KeyPoint 求最小窗口
            // 只要满足窗口成立条件，为了子数组长度最小，故不断 left ++，缩小窗口
            // 所以使用 while 循环，进行多次判断，而不是使用 if 只进行一次判断
            // KeyPoint 使用 while 循环，处理窗口大小不固定的情况
            while (windowSum >= target) {
                // KeyPoint 注意：计算逻辑位置
                // 计算 res 逻辑在移动 left 之前，计算上轮满足 windowSum >= target 的 left 位置
                // 若计算逻辑在 left 之后，有可能在 windowSum -= nums[left] 之前满足，执行后就不满足了
                res = Math.min(res, right - left + 1);
                windowSum -= nums[left];
                left++;
            }
            // 移动右边界，扩大窗口
            right++;
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }
}
