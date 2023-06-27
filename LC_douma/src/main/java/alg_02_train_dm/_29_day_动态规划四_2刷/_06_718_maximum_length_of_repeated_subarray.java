package alg_02_train_dm._29_day_动态规划四_2刷;

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
    // 牢记：子序列默认不连续，子数组默认连续
    // 区别：LCS：最长公共子序列
    public int findLength(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        // 两个数组 或者 两个字符串形式，一般都是使用下面的方式进行定义状态
        // dp[i][j]：表示 nums1 中 前 i 个元素 中和 nums2 的 前 j 个元素 中最长公共子数组长度
        // => 这种方式定义，dp 的 i，j 索引 和 nums1 和 nums2  i-1 和 j-1 索引，差 1 的关系
        int[][] dp = new int[m + 1][n + 1];
        int res = 0;

        // 初始化省略，使用默认值 0
        // dp 二维数组，第一行，第一列都是 0
        // 前 0 个元素，可以认为没有元素，其和具体数字元素没有公共元素，故 dp[0][j] 和 dp[j][0] 都是 0
        // 注意：这里数组元素，不是字符串，没法看成空串 ""

        // index 0 1 2 3 4
        // nums1 1 2 3 2 1
        //       i => 前 0 个元素
        // nums2 3 2 1 4 7
        //       j => 前 0 个元素

        // index 0 1 2 3 4
        // nums1 1 2 3 2 1
        //         i => 前 1 个元素
        // nums2 3 2 1 4 7
        //         j => 前 1 个元素
        // 若 nums1[i - 1] != nums2[j - 1]，则 nums1 和 nums2 没有公共的元素，组成的子数组
        // 子数组是连续的，只要有一个元素不相等，就不符合题目条件，故 dp[i][j] = 0

        // nums1 1 2 3 2 1
        //           i
        // nums2 3 2 1 4 7
        //         j
        // 若 nums1[i - 1] 和 nums2[j - 1]) 则 dp[i][j] = dp[i - 1][j - 1] + 1

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // 注意：只要考虑 nums1[i - 1] 和 nums2[j - 1]) 是否相等即可
                // 若不相等，使用默认值 0
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    res = Math.max(res, dp[i][j]);
                }
            }

            // KeyPoint 区别：LCS 状态转移方程
//                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
//                    dp[i][j] = 1 + dp[i - 1][j - 1];
//                } else {
//                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
        }

        return res;
    }
}
