package alg_02_train_dm._29_day_动态规划四;

/**
 * @Author Wuyj
 * @DateTime 2023-06-12 16:35
 * @Version 1.0
 */
public class _06_718_maximum_length_of_repeated_subarray {
    /*
        718. 最长重复子数组
        给两个整数数组 A 和 B ，返回两个数组中公共的、长度最长的子数组的长度。

        示例：
        输入：
            A: [1,2,3,2,1]
            B: [3,2,1,4,7]
        输出：3
        解释：
        长度最长的公共子数组是 [3, 2, 1] 。

        提示：
        1 <= len(A), len(B) <= 1000
        0 <= A[i], B[i] < 100

     */

    // 动态规划
    // 子序列默认不连续，子数组默认连续
    public int findLength(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        // 两个数组，或者两个字符串形式，一般都是使用下面的方式进行定义状态
        // dp[i][j]：表示 nums1 中前 i 个元素中和 nums2 的前 j 个元素中最长公共子数组长度
        int[][] dp = new int[m + 1][n + 1];

        int ans = 0;

        // nums1 1 2 3 2 1
        //       i
        // nums2 3 2 1 4 7
        //       j

        // 注意：只要考虑 nums1[i - 1] 和 nums2[j - 1]) 是否相等即可
        // 若 nums1[i - 1] == nums2[j - 1])，则 dp[i][j] = dp[i - 1][j - 1] + 1

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    ans = Math.max(ans, dp[i][j]);
                }
            }
        }

        return ans;
    }
}
