package alg_02_体系班_zcy.class19_DP_Done;

// 这个问题leetcode上可以直接测
// 链接：https://leetcode.com/problems/longest-common-subsequence/
// 博客 https://blog.csdn.net/lsdstone/article/details/128636620
public class Code04_LongestCommonSubsequence {


    /*

        给定两个字符串text1和text2,返回这两个字符串的最长公共子序列的长度,如果不存在公共子序列,返回0
        一个字符串的子序列是指这样一个新的字符串:它是由原字符串在不改变字符的相对顺序的情况下删除某些字符(也可以不删除任何字符)后组成的新字符串
        例如:"ace"是"abcde"的子序列,但"aec"不是"abcde"的子序列,两个字符串的公共子序列是这两个字符串所共同拥有的子序列

        str1 "a12bc345def"
        str2 "mnp123qrs45z"
        => 最长公共子序列:"12345"

        KeyPoint 不要害怕尝试,多去尝试.即使尝试错了,根据测试用例及时来进行调整

     */

    // KeyPoint 方法一:暴力递归
    public static int longestCommonSubsequence1(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0) {
            return 0;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        // 尝试
        return process1(str1, str2, str1.length - 1, str2.length - 1);
    }

    // i,j表示str1和str2的结尾位置
    // 功能:str1[0...i]和str2[0...j] => 最长公共子序列
    public static int process1(char[] str1, char[] str2, int i, int j) {
        if (i == 0 && j == 0) {
            // str1[0..0]和str2[0..0],都只剩一个字符了
            // 那如果字符相等,公共子序列长度就是1,不相等就是0
            return str1[i] == str2[j] ? 1 : 0;
        } else if (i == 0) {
            // 这里的情况为:str1[0...0]和str2[0...j],str1只剩1个字符了,但是str2不只一个字符
            // 因为str1只剩一个字符了,所以str1[0...0]和str2[0...j]公共子序列最多长度为1
            // 如果str1[0] == str2[j],那么此时相等已经找到了,公共子序列长度就是1,也不可能更大了
            // 如果str1[0] != str2[j],只是此时不相等而已,那么str2[0...j-1]上有没有字符等于str1[0]呢？不知道,所以递归继续找
            if (str1[i] == str2[j]) {
                return 1;
            } else {
                return process1(str1, str2, i, j - 1);
            }
        } else if (j == 0) { // 和上面的else if同理
            // str1[0...i]和str2[0...0],str2只剩1个字符了,但是str1不只一个字符
            // 因为str2只剩一个字符了,所以str1[0...i]和str2[0...0]公共子序列最多长度为1
            // 如果str1[i] == str2[0],那么此时相等已经找到了！公共子序列长度就是1,也不可能更大了
            // 如果str1[i] != str2[0],只是此时不相等而已,那么str1[0...i-1]上有没有字符等于str2[0]呢？不知道,所以递归继续找
            if (str1[i] == str2[j]) {
                return 1;
            } else {
                return process1(str1, str2, i - 1, j);
            }
        } else {
            // 经过上面if和else if之后,剩下的的可能性只能是 i != 0 && j != 0
            //  => str1[0...i]和str2[0...j],即str1和str2两个同时都不只一个字符
            // 可能性分析 => str1[0...i]和str2[0...j],这个范围上最长公共子序列长度是多少？
            //           => KeyPoint 样本对应模型一般是讨论结尾(i和j)作为讨论的基础(该如何组织可能性)
            //           =>      样本对应模型:样本A(字符串/数组),样本B(字符串/数组)

            // 可能性分类:

            // a)最长公共子序列,[一定不以]str1[i]字符结尾,也[一定不以]str2[j]字符结尾
            //   => 因为a和b,c的可能性有重叠了,故不写入,省掉一次遍历

            // b)最长公共子序列,[可能]以str1[i]字符结尾,但是[一定不以]str2[j]字符结尾

            // c)最长公共子序列,[一定不以]str1[i]字符结尾,但是[可能]以str2[j]字符结尾

            //   => 一定不以str1[i]字符结尾,但是一定以str2[j]字符结尾,这种可能性定义太紧,导致讨论很麻烦,
            //      因为还得要验证str1[0..i]是否有j字符,而定义成可能以str2[j]字符结尾则不需要验证,比较方便
            //   => KeyPoint 上面这种划分有些题目适用,即要求:必须含有i,必须不含有j,而不是可能含有j

            // d)最长公共子序列,[必须]以str1[i]字符结尾,也[必须]以str2[j]字符结尾

            // KeyPoint 详细分析
            // 注意:a),b),c),d)并不是完全互斥的,他们可能会有重叠的情况,但是可以肯定,答案不会超过这四种可能性的范围
            // 那么我们分别来看一下,这几种可能性怎么调用后续的递归

            // a) 最长公共子序列,一定不以str1[i]字符结尾,也一定不以str2[j]字符结尾
            //    如果是这种情况,那么有没有str1[i]和str2[j]就根本不重要了,因为这两个字符一定没用
            //    所以砍掉这两个字符,最长公共子序列 = str1[0...i-1]与str2[0...j-1]的最长公共子序列长度(后续递归)

            // b) 最长公共子序列,可能以str1[i]字符结尾,但是一定不以str2[j]字符结尾
            //    如果是这种情况,那么我们可以确定str2[j]一定没有用,要砍掉；但是str1[i]可能有用所以要保留
            //    所以,最长公共子序列 = str1[0...i]与str2[0...j-1]的最长公共子序列长度(后续递归)

            // c) 最长公共子序列,一定不以str1[i]字符结尾,但是可能以str2[j]字符结尾
            //    跟上面分析过程类似,最长公共子序列 = str1[0...i-1]与str2[0...j]的最长公共子序列长度(后续递归)

            // d) 最长公共子序列,必须以str1[i]字符结尾,也必须以str2[j]字符结尾
            //    同时可以看到,可能性d)存在的条件,一定是在str1[i] == str2[j]的情况下,才成立的
            //    所以,最长公共子序列总长度 = str1[0...i-1]与str2[0...j-1]的最长公共子序列长度(后续递归) + 1(共同的结尾)

            // 综上,四种情况已经穷尽了所有可能性,在四种情况中取最大即可

            // p1 => c
            int p1 = process1(str1, str2, i - 1, j);
            // p2 => b
            int p2 = process1(str1, str2, i, j - 1);
            // p3 => d
            // 如果可能性d)存在,即str1[i] == str2[j],那么p3就求出来,后续递归,然后去参与Max比较
            // 如果可能性d)不存在,即str1[i] != str2[j],那么让p3等于0,然后去参与Max比较
            int p3 = str1[i] == str2[j] ? (1 + process1(str1, str2, i - 1, j - 1)) : 0;
            return Math.max(p1, Math.max(p2, p3));
        }
    }

    // KeyPoint 方法二:dp
    public static int longestCommonSubsequence2(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0) {
            return 0;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int N = str1.length;
        int M = str2.length;
        int[][] dp = new int[N][M];
        dp[0][0] = str1[0] == str2[0] ? 1 : 0;

        // 分析i=0和j=0时,若是无法直接得到具体值(存在if判断)填入dp表中,则根据分支中的递归调用关系
        // 分析一般位置(i,j)的依赖关系,针对同一个(i,j)依次看
        //     else if 中 process(str1, str2, i, j - 1);
        //     else if 中 process(str1, str2, i - 1, j);
        //     else中 process(str1, str2, i - 1, j - 1) => 只看p3,p1和p2和上面重复,故省略
        // (i,j)分别依赖左边一格,上面一格,左上角一格
        // 填表时先填好0行0列(来自于if条件中i=0和j=0的提示)剩下的格子按照行优先的顺序从上往下依次遍历

        // 0从1开始,0位置已经填好了,不用再填写了
        for (int i = 1; i < N; i++) {
            // KeyPoint 代码逻辑参考递归版本
            // dp[i-1][0] =>依赖上一行
            dp[i][0] = str1[i] == str2[0] ? 1 : dp[i - 1][0];
        }

        for (int j = 1; j < M; j++) {
            // dp[0][j-1] => 依赖前一列
            dp[0][j] = str1[0] == str2[j] ? 1 : dp[0][j - 1];
        }

        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                int p1 = dp[i - 1][j];
                int p2 = dp[i][j - 1];
                int p3 = str1[i] == str2[j] ? (1 + dp[i - 1][j - 1]) : 0;
                dp[i][j] = Math.max(p1, Math.max(p2, p3));
            }
        }
        return dp[N - 1][M - 1];
    }
}
