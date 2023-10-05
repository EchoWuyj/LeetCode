package alg_02_train_dm._27_day_动态规划二_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-06-10 12:43
 * @Version 1.0
 */
public class _13_474_ones_and_zeroes {
    
      /* 
            474. 一和零
            给你一个二进制字符串 数组 strs 和 两个整数 m 和 n
            请你找出并返回 strs 的最大子集的大小，该子集中 最多 有 m 个 0 和 n 个 1。

            KeyPoint 补充：子集定义
            如果 x 的所有元素也是 y 的元素，集合 x 是集合 y 的 子集
        
            示例 1：
            输入：strs = ["10", "0001", "111001", "1", "0"], m = 5, n = 3
            输出：4
            解释：最多有 5 个 0 和 3 个 1 的最大子集是 {"10","0001","1","0"}，因此答案是 4
                  KeyPoint 注意：子集是指从 strs 中选取的
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

    // 二维 费用 背包问题
    // 1.二进制字符串数组 strs 中的字符串 => 物品
    // 2.选择每个字符串有两个代价，分别是 0 的个数和 1 的个数，分别是 m 和 n => 两个代价
    // 3.两个代价都有最大值，0 的个数最多为 m，1 的个数最多为 n => 两个背包容量
    // 4.求选择字符串得到的最大子集的大小 => 目标
    public int findMaxForm(String[] strs, int m, int n) {

        // 状态定义
        // dp[i][j] 表示包含 i 个 0 和 j 个 1 的最大子集的大小
        // 二维代价
        int[][] dp = new int[m + 1][n + 1];

        // 求解最大值，dp[i][j] 默认是 0，故不需要整体初始化
        // 背包容量为 0，则 '0' 或者 '1' 都不选，故子集个数为 0
        dp[0][0] = 0;

        for (int i = 0; i < strs.length; i++) {
            // KeyPoint
            // 每个字符串消耗的 '0' 和 '1' 个数都是不同的，故需要单独计算
            // 对每个字符串 strs[i]，计算该字符串有多少个 0 和 1
            int[] counts = countZeroOne(strs[i]);
            // strs[i] 中 0 的个数，1 的个数
            int zeroes = counts[0];
            int ones = counts[1];
            // KeyPoint 二维费用背包 => 类比：一维 0-1 背包，不可重复选择同一字符 => 都是从右往左遍历
            // 背包容量 zero 和 one 需要大于当前 strs[i] 中 zeroes 和 ones
            for (int j = m; j >= zeroes; j--) {
                for (int k = n; k >= ones; k--) {
                    // 不选，使用 dp[j][k]
                    // 选，则加 1
                    dp[j][k] = Math.max(dp[j][k], dp[j - zeroes][k - ones] + 1);
                }
            }
        }
        return dp[m][n];
    }

    // 计算该字符串有多少个 0 和 1
    private int[] countZeroOne(String str) {
        // 记录 0 和 1 的个数
        int[] count = new int[2];
        for (int i = 0; i < str.length(); i++) {
            // 一次计数即可
            count[str.charAt(i) - '0']++;

            // KeyPoint 不需要两次计数 => 通过索引坐标直接计算
            // res[c - '0']++;
            // res[c - '1']++;
        }

        // 另外一种简单写法
//        for (char c : str.toCharArray()) {
//            count[c - '0']++;
//        }

        return count;
    }
}
