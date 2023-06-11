package alg_02_train_dm._12_day_滑动窗口;

/**
 * @Author Wuyj
 * @DateTime 2023-05-04 15:11
 * @Version 1.0
 */
public class _02_209_minimum_size_subarray_sum {
     /* 
        leetcode 209. 长度最小的子数组
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
        
        进阶：
        如果你已经实现 O(n) 时间复杂度的解法, 请尝试设计一个 O(n log(n)) 时间复杂度的解法。

     */

    // KeyPoint 方法一 暴力解法
    // 时间复杂度：O(n^2)
    public int minSubArrayLen1(int target, int[] nums) {
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum >= target) {
                    // i 和 j 两端都要包括，故 j - i + 1
                    ans = Math.min(ans, j - i + 1);
                }
            }
        }
        // 最后判断下，若 ans 没有更新，还是 Integer.MAX_VALUE
        // 则说明不存在符合条件的子数组，按照题目要求，返回 0
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

    // KeyPoint 方法二 滑动窗口
    // 求最小窗口，先移动 right，扩大窗口，直到满足条件，再去移动 left，缩小窗口
    // 时间复杂度：O(n)
    // 空间复杂度：O(1)
    public int minSubArrayLen2(int target, int[] nums) {
        int ans = Integer.MAX_VALUE;
        int left = 0, right = 0;
        int windowSum = 0;
        while (right < nums.length) {
            windowSum += nums[right];
            // KeyPoint 求最小窗口
            // 只要满足窗口成立条件，为了子数组长度最小，故不断 left ++，缩小窗口
            // 所以使用 while 循环，进行多次判断，而不是使用 if 只进行一次判断
            while (windowSum >= target) {
                ans = Math.min(ans, right - left + 1);
                windowSum -= nums[left];
                left++;
            }
            // 移动右边界，扩大窗口
            right++;
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

    // KeyPoint 方法三 前缀和 + 二分
    // 时间复杂度：O(nlogn)
    // 看到 logn => 联想二分，但二分要求数组有序，而本题数组是无序的
    // 1 <= nums[i] <= 10^5 => 数组元素都是正数，故其前缀和也是整数，使用前缀和二分
    public int minSubArrayLen(int target, int[] nums) {

        // nums         2 3 1 4  4  3
        // prefixSum  0 2 5 6 10 14 17
        //              i   j

        // ps[j]- ps[i] >= target，两个指针不好分析，尝试固定一个指针 i
        // 固定 i，i 从左往右遍历一遍，ps[j] >= target + ps[i]，关键找 ps[j]
        // => 在一个有序的数组中，查找大于等于某个数的第一个位置
        // => 因为是找的是第一个位置，故 j-i+1 是最小值

        int len = nums.length;
        int[] prefixSum = new int[len + 1];
        prefixSum[0] = 0;
        for (int i = 1; i <= len; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }

        int ans = Integer.MAX_VALUE;
        int n = prefixSum.length;
        // 固定 i，通过 for 循环方式，遍历所有可能
        // => 其中 i 是前缀和数组索引，而非原始数组的索引
        // => 若 i 从 0 开始，ps[0] 表示是原数组前 0 个元素之和，这个没什么意义，因为都不构成区间
        //    同时，prefixSum[i-1]，故 i 从 1 开始，避免数组越界
        for (int i = 1; i < n; i++) {

            // 说明：这里为什么是 i - 1 呢？ => 避免漏掉了第一个元素 [0]

            // 首先，明确 ps[i] 表达的是原数组中前 i 个元素之和
            // ps[j] - ps[i-1] => 原始数组 [i, j) 区间和
            // ps[j] - ps[i-1] >= target => [i, j) >= target

            // 为什么 [i, j) 是右开的呢？
            // 因为 prefixSum[j] 表示的是原始数组前 j 个元素的和，前 j 个元素中不会包括索引为 j 的元素

            // index           0 1 2 3  4  5
            // nums           [2,3,1,4, 4, 3]
            // prefixSum      [0,2,5,6,10,14,17] => 索引坐标对齐
            //                   ↑   ↑
            //                   i   j

            // i 是 从前缀和数组的第二个元素开始，即 i = 1，若 j = 3

            // 若 ps[j] - ps[i]，则 ps[3] - ps[1] = 4
            // => [0]+[1]+[2]-[0] = [1]+[2]
            // => 原始数组区间 [1, 2] => 即 [1, 3) 的区间和

            // 若 ps[j] - ps[i-1]，则 ps[3] - ps[0] = 6
            // => [0]+[1]+[2] - 0 => [0]+[1]+[2]
            // => 原始数组区间 [0, 2] => 即 [0, 3) 的区间和

            // 若 ps[j] - ps[i] 的话，就会漏掉了第一个元素 [0]，所以这里必须是 i - 1
            // 同时，i 最大值，i = n - 1，n = prefixSum.length，则 ps[n-2]，[n-3]
            // => 对应原数组 [len-2]，还要留一个位置给 j

            int t = target + prefixSum[i - 1];
            int j = firstGETargetElement(prefixSum, t);
            // 不存在，跳过，执行下轮
            if (j < 0) continue;
            // j 是开区间，所以 j == nums.length
            if (j <= nums.length) {
                ans = Math.min(ans, j - i + 1);
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

    // 二分法：大于等于某个数的第一个位置
    public int firstGETargetElement(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target <= nums[mid]) {
                // 符合下面的两个条件之一就返回 mid
                // 1. mid 是数组的第一个元素
                // 2. mid 前面的那个元素小于 target
                if (mid == 0 || nums[mid - 1] < target) return mid;
                else right = mid - 1;
            } else { // target > nums[mid]
                left = mid + 1;
            }
        }
        return -1;
    }
}
