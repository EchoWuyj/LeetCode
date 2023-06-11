package alg_03_leetcode_top_wyj.class_14;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-03-06 14:52
 * @Version 1.0
 */
public class Problem_0131_PalindromePartitioning {

    public static List<List<String>> partition(String s) {
        boolean[][] dp = getDP(s.toCharArray());
        List<List<String>> ans = new ArrayList<>();
        LinkedList<String> path = new LinkedList<>();
        process(s, 0, path, ans, dp);
        return ans;
    }

    public static boolean[][] getDP(char[] str) {
        int n = str.length;
        boolean[][] dp = new boolean[n][n];
        dp[n - 1][n - 1] = true;
        for (int i = 0; i < n - 1; i++) {
            dp[i][i] = true;
            dp[i][i + 1] = (str[i] == str[i + 1]) ? true : false;
        }
        for (int i = n - 3; i >= 0; i--) {
            for (int j = i + 2; j < n; j++) {
                dp[i][j] = (str[i] == str[j]) && dp[i + 1][j - 1];
            }
        }
        return dp;
    }

    public static void process(String s, int index, LinkedList<String> path, List<List<String>> ans, boolean[][] dp) {
        if (index == s.length()) {
            ans.add(copy(path));
        } else {
            for (int end = index; end < s.length(); end++) {
                if (dp[index][end]) {
                    path.addLast(s.substring(index, end + 1));
                    process(s, end + 1, path, ans, dp);
                    path.pollLast();
                }
            }
        }
    }

    public static List<String> copy(List<String> path) {
        ArrayList<String> temp = new ArrayList<>();
        for (String s : path) {
            temp.add(s);
        }
        return temp;
    }
}
