package alg_03_leetcode_top_wyj.class_05;

import alg_01_新手班_wyj.class02.Code01_PreSum;
import com.sun.corba.se.spi.ior.IORFactories;

/**
 * @Author Wuyj
 * @DateTime 2023-02-24 13:41
 * @Version 1.0
 */

public class Problem_0044_WildcardMatching {
    public boolean isMatch(String str, String pattern) {
        char[] s = str.toCharArray();
        char[] p = pattern.toCharArray();
        return process(s, p, 0, 0);
    }

    public boolean process(char[] s, char[] p, int si, int pi) {
        if (si == s.length) {
            if (pi == p.length) {
                return true;
            } else {
                return p[pi] == '*' && process(s, p, si, pi + 1);
            }
        }

        if (pi == p.length) {
            return si == s.length;
        }

        if (p[pi] != '?' && p[pi] != '*') {
            return s[si] == p[pi] && process(s, p, si + 1, pi + 1);
        }

        if (p[pi] == '?') {
            return process(s, p, si + 1, pi + 1);
        }

        for (int len = 0; len <= s.length - si; len++) {
            if (process(s, p, si + len, pi + 1)) {
                return true;
            }
        }
        return false;
    }

    public boolean isMatch2(String str, String pattern) {
        char[] s = str.toCharArray();
        char[] p = pattern.toCharArray();
        int n = s.length;
        int m = p.length;
        boolean[][] dp = new boolean[n + 1][m + 1];
        dp[n][m] = true;
        for (int pi = m - 1; pi >= 0; pi--) {
            dp[n][pi] = p[pi] == '*' && dp[n][pi + 1];
        }

        for (int si = n - 1; si >= 0; si--) {
            for (int pi = m - 1; pi >= 0; pi--) {
                if (p[pi] != '?' && p[pi] != '*') {
                    dp[si][pi] = s[si] == p[pi] && dp[si + 1][pi + 1];
                    continue;
                }
                if (p[pi] == '?') {
                    dp[si][pi] = dp[si + 1][pi + 1];
                    continue;
                }

                // 原始没有优化
//                for (int len = 0; len <= s.length - si; len++) {
//                    if (dp[si + len][pi + 1]) {
//                        dp[si][pi] = true;
//                        break;
//                    }
//                }

                // 斜率优化
                dp[si][pi] = dp[si][pi + 1] || dp[si + 1][pi];
            }
        }
        return dp[0][0];
    }
}
