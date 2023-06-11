package alg_03_leetcode_top_wyj.class_02;

import sun.font.GlyphLayout;

/**
 * @Author Wuyj
 * @DateTime 2023-02-22 17:59
 * @Version 1.0
 */

public class problem_010_isMatch {
    // KeyPoint 方法一
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }

        char[] str = s.toCharArray();
        char[] pattern = p.toCharArray();
        return isValid(str, pattern) && process(str, pattern, 0, 0);
    }

    public static boolean isValid(char[] str, char[] pattern) {
        for (char c : str) {
            if (c == '.' || c == '*') {
                return false;
            }
        }

        for (int i = 0; i < pattern.length; i++) {
            if (pattern[i] == '*' && (i == 0 || pattern[i - 1] == '*')) {
                return false;
            }
        }

        return true;
    }

    public boolean process(char[] str, char[] pattern, int si, int pi) {

        // 递归边界
        if (si == str.length) {
            if (pi == pattern.length) {
                return true;
            }
            if (pi + 1 < pattern.length && pattern[pi + 1] == '*') {
                return process(str, pattern, si, pi + 2);
            }
            return false;
        }

        if (pi == pattern.length) {
            return si == str.length;
        }

        // 一般情况
        if (pi + 1 >= pattern.length || pattern[pi + 1] != '*') {
            return (str[si] == pattern[pi] || pattern[pi] == '.')
                    && process(str, pattern, si + 1, pi + 1);
        }

        if (pattern[pi] != '.' && str[si] != pattern[pi]) {
            return process(str, pattern, si, pi + 2);
        }

        if (process(str, pattern, si, pi + 2)) {
            return true;
        }

        while (si < str.length && (str[si] == pattern[pi] || pattern[pi] == '.')) {
            if (process(str, pattern, si + 1, pi + 2)) {
                return true;
            }
            si++;
        }
        return false;
    }


    public boolean isMatch2(String s, String p) {
        if (s == null || p == null) {
            return false;
        }

        char[] str = s.toCharArray();
        char[] pattern = p.toCharArray();
        int[][] dp = new int[str.length + 1][pattern.length + 1];

        for (int si = 0; si <= str.length; si++) {
            for (int pi = 0; pi <= pattern.length; pi++) {
                dp[si][pi] = -1;
            }
        }

        return isValid(str, pattern) && process2(str, pattern, 0, 0, dp);
    }

    public boolean process2(char[] str, char[] pattern, int si, int pi, int[][] dp) {

        if (dp[si][pi] != -1) {
            return dp[si][pi] == 1;
        }

        // 递归边界
        if (si == str.length) {
            if (pi == pattern.length) {
                dp[si][pi] = 1;
                return true;
            }
            if (pi + 1 < pattern.length && pattern[pi + 1] == '*') {
                boolean ans = process2(str, pattern, si, pi + 2, dp);
                dp[si][pi] = ans ? 1 : 0;
                return ans;
            }
            dp[si][pi] = 0;
            return false;
        }

        if (pi == pattern.length) {
            boolean ans = si == str.length;
            dp[si][pi] = ans ? 1 : 0;
            return ans;
        }

        // 一般情况
        if (pi + 1 >= pattern.length || pattern[pi + 1] != '*') {
            boolean ans = (str[si] == pattern[pi] || pattern[pi] == '.')
                    && process2(str, pattern, si + 1, pi + 1, dp);
            dp[si][pi] = ans ? 1 : 0;
            return ans;
        }

        if (pattern[pi] != '.' && str[si] != pattern[pi]) {
            boolean ans = process2(str, pattern, si, pi + 2, dp);
            dp[si][pi] = ans ? 1 : 0;
            return ans;
        }

        if (process2(str, pattern, si, pi + 2, dp)) {
            dp[si][pi] = 1;
            return true;
        }

        while (si < str.length && (str[si] == pattern[pi] || pattern[pi] == '.')) {
            if (process2(str, pattern, si + 1, pi + 2, dp)) {
                dp[si][pi] = 1;
                return true;
            }
            si++;
        }

        dp[si][pi] = 0;
        return false;
    }
}
