package algorithm._11_dynamic_programming;

/**
 * @Author Wuyj
 * @DateTime 2022-03-29 13:11
 * @Version 1.0
 */
public class LeetCode_1143_LCS {

    //动态规划
    public int longestCommonSubSequence(String text1, String text2) {
        int l1 = text1.length();
        int l2 = text2.length();

        //定义一个二维数组
        //二维数组是从0开始的,所以长度需要加1
        int[][] dp = new int[l1 + 1][l2 + 1];

        //逐步扩展两个字符串,遍历所有状态,递推求解
        //for循环里面i从0开始,因为从0开始默认都是0没有必要,同时后面的i-1会导致数组越界;
        for (int i = 1; i <= l1; i++) {
            for (int j = 1; j <= l2; j++) {
                //判断两个新增字符关系,进行状态转移
                //i表示当前的长度,在字符串中的索引需要减1
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        //返回dp中的最后一个元素,即长度扩展到l1和l2时
        return dp[l1][l2];
    }

    public static void main(String[] args) {
        String str1 = "abcde";
        String str2 = "ace";

        LeetCode_1143_LCS lcs = new LeetCode_1143_LCS();
        System.out.println(lcs.longestCommonSubSequence(str1, str2));
        System.out.println(lcs.longestCommonSubSequence("abc", "def"));
    }
}
