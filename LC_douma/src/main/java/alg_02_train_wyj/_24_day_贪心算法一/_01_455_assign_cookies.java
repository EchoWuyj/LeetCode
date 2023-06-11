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
        int i = 0;
        int j = 0;
        while (i < g.length && j < s.length) {
            if (s[j] >= g[i]) i++;
            j++;
        }
        return i;
    }
}
