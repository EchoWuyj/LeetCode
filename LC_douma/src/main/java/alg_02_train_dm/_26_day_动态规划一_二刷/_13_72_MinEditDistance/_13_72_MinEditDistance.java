package alg_02_train_dm._26_day_动态规划一_二刷._13_72_MinEditDistance;

/**
 * @Author Wuyj
 * @DateTime 2023-06-05 21:18
 * @Version 1.0
 */
public class _13_72_MinEditDistance {

    /*
        72. 编辑距离
        给你两个单词 word1 和 word2
        请返回将 word1 转换成 word2 所使用的最少操作数

        你可以对一个单词进行如下三种操作：
        插入一个字符
        删除一个字符
        替换一个字符
        
        示例 1：
        输入：word1 = "horse", word2 = "ros"
        输出：3
        解释：
        horse -> horse (将 'h' 替换为 'r')
        rorse -> rose (删除 'r')
        rose -> ros (删除 'e')

        示例 2：
        输入：word1 = "intention", word2 = "execution"
        输出：5
        解释：
        intention -> inention (删除 't')
        inention -> enention (将 'i' 替换为 'e')
        enention -> exention (将 'n' 替换为 'x')
        exention -> exection (将 'n' 替换为 'c')
        exection -> execution (插入 'u')

        提示：
        0 <= word1.length, word2.length <= 500
        word1 和 word2 由小写英文字母组成
     */

    // 分析：首先是最优值问题 + 最优值问题通过穷举方式得到 + 穷举过程存在重复计算 => 动态规划
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        // KeyPoint dp 之前需要特判，避免后续 dp[][] 数组越界
        // bug 修复，如果有一个字符为空字符串，就可以提前返回另一个字符串的长度
        if (m == 0 || n == 0) return m + n;

        // 对于两个字符串的动态规划问题 => 对应二维 dp[i][j] 数组，i 和 j 分别对应一个字符串
        // KeyPoint dp[i][j] 表示 word1 前 i 个字符转换成 word2 前 j 个字符花的最少操作数(即编辑距离)
        // 特别注意：i 和 j 指的是 i 和 j 指针，前面的 i 个字符和 j 个字符，并不是当前位置字符
        // 索引从 0 开始，dp[m][n] 可以取到，故需要加 1
        int[][] dp = new int[m + 1][n + 1];

        // word1 = h o r s e
        //             i

        // word2 = r o s
        //             j

        // 状态初始化
        // dp[0][0] 表示 word1 前 0 个字符转换成 word2 前 0 个字符的编辑距离
        // 前 0 个字符的意思就是空字符串的意思

        // KeyPoint 注意：m 和 n 是可以取等，不要将等号丢了
        //          => 凡是 m + 1 和 n + 1 这种 dp 定义，m 和 n 都是能取等的

        // 第一列
        // KeyPoint 注意可以取等！
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }

        // 第一行
        for (int i = 0; i <= n; i++) {
            dp[0][i] = i;
        }

        // 根据 dp[i][j] 依赖关系进行填表，控制 for 循环的顺序
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // 调用 word1 和 word2，需要看清楚，不要搞错了
                // 写代码，注意每个单词的拼写，不容易调用错 API
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                    // word1.charAt(i - 1) != word2.charAt(j - 1)
                } else {

                    // word1 = h  o r s e
                    //        i-1 i
                    // word2 = r o s
                    //           j

                    // KeyPoint 1.插入 r

                    // word1 = h r o r s e
                    //       i-1 i i+1  => 插入元素 r，该位置索引为 i，因为 h 为 i-1 位置不变化
                    //                     从而导致 o 字符的索引位置发生变化，变成 i+1
                    // word2 = r  o s
                    //        j-1 j

                    // insertNum = dp[i][j-1] + 1(插入r)
                    // dp[i][j-1] 保证 word1 [...,h] 和 word2 [...,r) 要能对上，所需要的操作数
                    // 注意：在 dp 中，i 和 j 都不是当前位置，而是 i，j 前一个位置
                    int insertNum = 1 + dp[i][j - 1];

                    // word1 = h o r s e
                    //             i
                    // word2 = r o s
                    //           j

                    // KeyPoint 2.删除 h

                    // word1 = h   r s e
                    //        i-2    i
                    // word2 = r o s
                    //           j

                    // deleteNum = dp[i - 1][j] + 1(删除 o)
                    // 删除 o，需要 o 之前的字符，[...,h] 和 [...,r] 匹配上
                    // 需要将 h 包括在内，h 位置 i-2，则 i = i-1，故为：dp[i - 1][j]
                    int deleteNum = 1 + dp[i - 1][j];

                    // word1 = h o r s e
                    //           i
                    // word2 = r o s
                    //           j

                    // KeyPoint 3.替换 h 为 r

                    // word1 = | r o r s e
                    //             i
                    // word2 = | r o s
                    //             j

                    // replaceNum = dp[i - 1][j - 1] + 1
                    int replaceNum = 1 + dp[i - 1][j - 1];

                    // 总结：
                    // 1.通过两个字符串，需要匹配的位置，从而决定 dp[i-1][j]，dp[i][j-1]，dp[i-1][j-1]
                    //   此外，在 dp 中，i 和 j 都不是当前位置，实际匹配是 i-1，j-1 位置字符

                    // 2.增删改，对应的 dp 都是不相同的，分别是：dp[i][j - 1]，dp[i - 1][j]，dp[i - 1][j - 1]
                    //   可以通过这种规律，判断状态转移方程是否存在问题

                    // 取三者的最小值
                    dp[i][j] = Math.min(insertNum, Math.min(deleteNum, replaceNum));
                }
            }
        }

        return dp[m][n];
    }
}
