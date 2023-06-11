package alg_02_体系班_zcy.class20_DP_Done;

/**
 * @Author Wuyj
 * @DateTime 2023-03-03 11:02
 * @Version 1.0
 */

// 测试链接:https://leetcode.com/problems/longest-palindromic-subsequence/

public class Code01_PalindromeSubsequence {

    /*
        给定一个字符串str,返回这个字符串的最长回文子序列长度
        比如:str = “a12b3c43def2ghi1kpm”
        最长回文子序列是“1234321”或者“123c321”,返回长度7

        子串:必须连续
        子序列:可以不连续
     */

    // KeyPoint 方法一:暴力递归 => 范围模型
    public static int lpsl1(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        return f(str, 0, str.length - 1);
    }

    // 函数f(str,L,R)功能是返回str[l..r]的最长回文子序列长度
    public static int f(char[] str, int L, int R) {
        // base case
        if (L == R) { // 1个字符
            return 1;
        }
        // base case
        if (L == R - 1) { // 2个字符
            // 和str[R]比较,而不是和str[R-1]比较,因为L就是R-1
            return str[L] == str[R] ? 2 : 1;
        }

        // KeyPoint 关键:范围尝试模型特别在乎开头和结尾[L..R]共同结合的可能性

        // 普遍情况:分析str[l..r]最长回文子序列的可能性
        // 1) 既不以l开头,也不以r结尾 如:a12321b => 意味着str[l...r]的回文子序列等同于str[l + 1... r - 1]的回文子序列
        int p1 = f(str, L + 1, R - 1);
        // 2) 以l开头,不以r结尾,如:12321b
        int p2 = f(str, L, R - 1);
        // 3) 不以l开头,以r结尾,如:a123b321
        int p3 = f(str, L + 1, R);
        // 4) 以l开头,且以r结尾,如:1ab23cd21 这种情况下只有l和r位置的字符相等才有可能
        int p4 = str[L] != str[R] ? 0 : (2 + f(str, L + 1, R - 1));

        // 4种可能性中选择最大值
        return Math.max(Math.max(p1, p2), Math.max(p3, p4));
    }

    // KeyPoint 方法二:严格表结构依赖dp
    public static int lpsl2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        // l的变化范围:0~n-1
        // r的变化范围:0~n-1
        int[][] dp = new int[N][N];

        // KeyPoint 范围尝试模型中,l>r的区域是无效的,所以只有一半的区域会用到

        // 最右下角的位置,单独计算,为了后面一个for循环填写两个对角线,避免使用两个for循环
        dp[N - 1][N - 1] = 1;

        // 根据递归函数中的l==r的条件,对角线全是1
        // 根据l == r-1的条件,倒数第二条对角线根据实际的字符串相邻位置字符是否相等填2或者填1
        // 填两条对角线
        for (int i = 0; i < N - 1; i++) {
            dp[i][i] = 1;
            // KeyPoint 注意=和+,没有按照着shift,输出的结果就是=,而不是+
            dp[i][i + 1] = str[i] == str[i + 1] ? 2 : 1;
        }

        // 普遍位置,分析依赖关系(l,r)依赖(l+1,r-1),(l,r-1),(l+1,r)
        // 填写(l,r)顺序时,确保(l+1,r-1),(l,r-1),(l+1,r)位置已知
        // 故填表顺序:对角线从底往上填,每行从左往右填
        for (int i = N - 3; i >= 0; i--) {
            for (int j = i + 2; j < N; j++) {
                int p1 = dp[i + 1][j - 1];
                int p2 = dp[i][j - 1];
                int p3 = dp[i + 1][j];
                int p4 = (str[i] == str[j]) ? (2 + dp[i + 1][j - 1]) : 0;
                dp[i][j] = Math.max(Math.max(p1, p2), Math.max(p3, p4));
            }
        }
        return dp[0][N - 1];
    }

    // KeyPoint 方法三:严格表结构依赖dp => 优化(oj能过就不优化了)
    public static int lpsl3(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        int[][] dp = new int[N][N];
        dp[N - 1][N - 1] = 1;
        for (int i = 0; i < N - 1; i++) {
            dp[i][i] = 1;
            dp[i][i + 1] = str[i] == str[i + 1] ? 2 : 1;
        }
        for (int i = N - 3; i >= 0; i--) {
            for (int j = i + 2; j < N; j++) {

                // 根据位置依赖关系,还可以继续优化,普遍情况中的任意一个位置(l,r)依赖于
                // (l+1,r-1)(p1左下),(l,r-1)(p2左),(l+1,r)(p3下)三个位置,还有p4=2+p1左下
                // 而(l,r)位置值不会比另外三个位置的值小,因为是在p1,p2,p3,p4这4种可能性中求最大

                // 坐标(l,r)与行列关系
                // 同一行l不变,l-1上一行,l+1下一行,上下关系
                // 同一列r不变,r-1前一列,r+1后一列,左右关系

                //              (l,r-1) p2    (l,r)
                //             (l+1,r-1) p1   (l+1,r) p3

                //  当(l,r)来到(l,r-1)位置
                //   (l,r-2)    (l,r-1)
                //  (l+1,r-2)   (l+1,r-1)  =>  (l,r-1) > (l+1,r-1) => (l,r)位置可以不依赖(l+1,r-1)

                // 当(l,r)来到(l+1,r)位置  =>  (l+1,r) > (l+1,r-1) => (l,r)位置可以不依赖(l+1,r-1)

                // => (l,r)时,将p1删除,只需要p2,p3和p4

                // 比较左和下取最大值
                dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
                // 有可能性p4,再去比较取最大值
                if (str[i] == str[j]) {
                    // 若比dp[i][j]大,则将dp[i][j]覆盖
                    dp[i][j] = Math.max(dp[i][j], dp[i + 1][j - 1] + 2);
                }
            }
        }
        return dp[0][N - 1];
    }

    // KeyPoint 方法四:利用最长公共子序列来解 => 样本对应模型
    //             生成str的逆序串,逆序串与原串求最长公共子序列即可,结果就是要求的原串的最长回文子序列
    public static int longestPalindromeSubseq1(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }
        char[] str = s.toCharArray();
        char[] reverse = reverse(str);
        return longestCommonSubsequence(str, reverse);

        // 使用自带的API实现反转
//        StringBuilder str = new StringBuilder(s);
//        StringBuilder reverseStr = str.reverse();
    }

    public static char[] reverse(char[] str) {
        int N = str.length;
        char[] reverse = new char[str.length];
        for (int i = 0; i < str.length; i++) {
            reverse[--N] = str[i];
        }
        return reverse;
    }

    public static int longestCommonSubsequence(char[] str1, char[] str2) {
        int N = str1.length;
        int M = str2.length;
        int[][] dp = new int[N][M];
        dp[0][0] = str1[0] == str2[0] ? 1 : 0;
        for (int i = 1; i < N; i++) {

            dp[i][0] = str1[i] == str2[0] ? 1 : dp[i - 1][0];
        }
        for (int j = 1; j < M; j++) {
            dp[0][j] = str1[0] == str2[j] ? 1 : dp[0][j - 1];
        }
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                if (str1[i] == str2[j]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
                }
            }
        }
        return dp[N - 1][M - 1];
    }
}
