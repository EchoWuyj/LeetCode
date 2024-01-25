package alg_03_high_frequency._01_codetop_2024_01;

/**
 * @Author Wuyj
 * @DateTime 2024-01-17 18:24
 * @Version 1.0
 */
public class _85_674_findLengthOfLCIS {

    // 最长连续递增序列
    // 给定一个未经排序的整数数组，找到最长且连续递增的子序列，并返回该序列的长度。

    // KeyPoint 快慢指针 => 直接模拟
    public int findLengthOfLCIS(int[] nums) {
        if (nums == null) return 0;
        int n = nums.length;
        if (n <= 1) return 1;
        int slow = 0;
        int res = 1;
        for (int fast = 1; fast < n; fast++) {
            if (nums[fast - 1] >= nums[fast]) {
                slow = fast;
                continue;
            }
            res = Math.max(res, fast - slow + 1);
        }
        return res;
    }
}

