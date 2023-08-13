package alg_02_train_wyj._24_day_贪心算法一;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-04-11 15:24
 * @Version 1.0
 */
public class _01_455_assign_cookies {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int m = g.length;
        int n = s.length;

        int i = 0;
        int j = 0;
        while (i < m && j < n) {
            if (g[i] <= s[j]) i++;
            j++;
        }
        return i;
    }
}
