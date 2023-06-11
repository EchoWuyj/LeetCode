package alg_02_train_dm._27_day_动态规划二;

/**
 * @Author Wuyj
 * @DateTime 2023-06-10 12:43
 * @Version 1.0
 */
public class _13_474_ones_and_zeroes {
    
      /* 
            474. 一和零
            给你一个二进制字符串数组 strs 和两个整数 m 和 n 。
            请你找出并返回 strs 的最大子集的大小，该子集中 最多 有 m 个 0 和 n 个 1 。

            注意：如果 x 的所有元素也是 y 的元素，集合 x 是集合 y 的 子集 。
        
            示例 1：
            输入：strs = ["10", "0001", "111001", "1", "0"], m = 5, n = 3
            输出：4
            解释：最多有 5 个 0 和 3 个 1 的最大子集是 {"10","0001","1","0"}，因此答案是 4 。
            其他满足题意但较小的子集包括 {"0001","1"} 和 {"10","1","0"}
            其中 {"111001"} 不满足题意，因为它含 4 个 1 ，大于 n 的值 3 。
        
            示例 2：
            输入：strs = ["10", "0", "1"], m = 1, n = 1
            输出：2
            解释：最大的子集是 {"0", "1"} ，所以答案是 2 。

            提示：
            1 <= strs.length <= 600
            1 <= strs[i].length <= 100
            strs[i]  仅由  '0' 和  '1' 组成
            1 <= m, n <= 100

     */

    // 二维费用背包问题
    // 1.二进制字符串数组 strs 中的字符串 => 物品
    // 2.选择每个字符串有两个代价，分别是 0 的个数和 1 的个数 => 两个代价
    // 3.两个代价都有最大值，0 的个数最多为 m，1 的个数最多为 n => 两个背包容量
    // 4.求选择字符串得到的最大子集的大小 => 目标
    public int findMaxForm(String[] strs, int m, int n) {
        // dp[i][j] 表示包含 i 个 0 和 j 个 1 的最大子集的大小
        int[][] dp = new int[m + 1][n + 1];

        // 求解最大值，dp[i][j] 默认是 0，故不需要整体初始化
        dp[0][0] = 0;

        for (int i = 0; i < strs.length; i++) {
            // 对每个字符串 strs[i]，计算该字符串有多少个 0 和 1
            int[] counts = help(strs[i]);
            // strs[i] 中 0 的个数，1 的个数
            int zeroes = counts[0], ones = counts[1];
            // 类比：一维 0-1 背包
            // 背包容量 zero 和 one 需要大于当前 strs[i] 中 zeroes 和 ones
            for (int zero = m; zero >= zeroes; zero--) {
                for (int one = n; one >= ones; one--) {
                    // 不选，使用 dp[zero][one]
                    dp[zero][one] = Math.max(dp[zero][one],
                            // 选，则加 1
                            dp[zero - zeroes][one - ones] + 1);
                }
            }
        }
        return dp[m][n];
    }

    // 计算该字符串有多少个 0 和 1
    private int[] help(String s) {
        // 记录 0 和 1 的个数
        int[] arr = new int[2];
        for (int i = 0; i < s.length(); i++) {
            arr[s.charAt(i) - '0']++;
        }
        return arr;
    }
}
