package alg_02_train_dm._12_day_滑动窗口_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-07-24 15:11
 * @Version 1.0
 */
public class _02_209_minimum_size_subarray_sum2 {

    // 进阶：如果你已经实现 O(n) 时间复杂度的解法, 请尝试设计一个 O(nlogn) 时间复杂度的解法
    // 评价：没有困难制造困难也得上

    // 分析：
    // 看到 logn => 联想二分，但二分要求数组有序，而本题数组是无序的
    // 提示 1 <= nums[i] <= 10^5，则数组元素都是正数，故其前缀和必然是升序的，故使用前缀和二分

    // KeyPoint 方法三 前缀和 + 二分
    // 时间复杂度：O(nlogn)
    public int minSubArrayLen(int target, int[] nums) {

        // nums         2 3 1 4  4  3    target = 7
        // prefixSum  0 2 5 6 10 14 17
        //              i   j

        // pr[j]- pr[i] >= target，但是两个指针 i 和 j 不好分析
        // => 尝试固定一个指针 i，且指针 i 从依次左往右遍历每个位置，
        //    从而计算 pr[j] >= target + pr[i]，关键找最先的 pr[j]

        // 题意转换
        // => 在一个有序的数组中，查找大于等于某个数的第一个位置
        // => 因为是找的是第一个位置，故 j-i+1 是最小值

        int n = nums.length;
        int[] prefixSum = new int[n + 1];
        prefixSum[0] = 0;
        for (int i = 1; i < n + 1; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }
        int res = Integer.MAX_VALUE;

        // 固定 i，通过二分法寻找 j，并且通过 for 循环方式，遍历所有可能 i位置
        for (int i = 0; i < (n + 1); i++) { // O(n)

            // index            0 1 2 3 4 5
            // nums             2 3 1 4 4 3
            // prefixSum        0 2 5 6 10 14 17
            //                  ↑   ↑
            //                  i   j

            // 1.pr[j]- pr[i] >= target => pr[j] >= target + pr[i]
            //   将 pr[j] 记作 aim，通过二分查找找 aim
            // 2.pr[j] - pr[i] = nums[j-1] + ..+ nums[i] => 表示区间：[i, j)
            //  由于不包括 j，故计算子区间长度，直接使用 j-i，不需要再加 1

            int aim = target + prefixSum[i];
            int j = bs(prefixSum, aim); // O(logn)
            // 若 j = -1，即 j < 0，表示不存在，跳过，执行下轮
            if (j < 0) continue;
            if (j <= n) {
                res = Math.min(res, j - i);
            }
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }

    // 二分法：大于等于某个数的第一个位置
    public int bs(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int n = nums.length;
        int left = 0, right = n - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            // 大于等于某个数的第一个位置
            // target <= nums[mid]
            // => 两者保持一致
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
