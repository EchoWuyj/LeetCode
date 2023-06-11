package alg_03_leetcode_top_zcy.class_14;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-03-06 10:53
 * @Version 1.0
 */

// 分割回文串 => 全排列类似
public class Problem_0131_PalindromePartitioning {

    /*
        给你一个字符串s,请你将s分割成一些子串,使每个子串都是回文串,返回s所有可能的分割方案
        回文串是正着读和反着读都一样的字符串

       思路:枚举检查str每个前缀是否是回文,在前缀是回文的情况下,加上剩下的rest是回文情况
     */
    public static List<List<String>> partition(String s) {
        // 通过查询dp[L][R],判断str[L..R]是不是回文
        boolean[][] dp = getDP(s.toCharArray());
        // 方便从尾部加入和删除
        LinkedList<String> path = new LinkedList<>();
        List<List<String>> ans = new ArrayList<>();
        // 递归每步都是需要dp加速判断是否是回文
        process(s, 0, path, dp, ans);
        return ans;
    }

    // 构建判断是否为回文的dp表
    public static boolean[][] getDP(char[] str) {
        int N = str.length;
        // 回文二维dp表
        boolean[][] dp = new boolean[N][N];
        dp[N - 1][N - 1] = true;

        // 右下方向两条对角线初始化
        for (int i = 0; i < N - 1; i++) {
            dp[i][i] = true;
            dp[i][i + 1] = str[i] == str[i + 1];
        }

//        for (int j = 2; j < N; j++) {
//            int row = 0;
//            int col = j;
//            while (row < N && col < N) {
//                dp[row][col] = str[row] == str[col] && dp[row + 1][col - 1];
//                row++;
//                col++;
//            }
//        }
        // 推荐这样填表
        // 从下往上,从左往右,依次填表
        for (int i = N - 3; i >= 0; i--) {
            for (int j = i + 2; j < N; j++) {
                dp[i][j] = (str[i] == str[j]) && dp[i + 1][j - 1];
            }
        }

        return dp;
    }

    // s 字符串
    // s[0...index-1] 已经做过的决定,放入了path中
    // 在index开始做属于这个位置的决定
    // index == s.length() path之前做的决定(一种分割方法),放进总答案ans里
    public static void process(String s, int index, LinkedList<String> path,
                               boolean[][] dp, List<List<String>> ans) {
        if (index == s.length()) {
            // index一次到递归边界,path后续还要继续使用,不能直接加入ans中,所以使用copy的方式
            ans.add(copy(path));
        } else {
            // 依次判断:index ~ index
            //         index ~ index+1
            //         index ~ end
            for (int end = index; end < s.length(); end++) {
                // 只是index~end真的是回文,再去执行后面的操作
                if (dp[index][end]) {
                    path.addLast(s.substring(index, end + 1));
                    // 深度优先
                    process(s, end + 1, path, dp, ans);
                    // 清理现场,保证不同分支不同情况
                    path.pollLast();
                }
            }
        }
    }

    public static List<String> copy(List<String> path) {
        List<String> ans = new ArrayList<>();
        for (String p : path) {
            ans.add(p);
        }
        return ans;
    }
}
