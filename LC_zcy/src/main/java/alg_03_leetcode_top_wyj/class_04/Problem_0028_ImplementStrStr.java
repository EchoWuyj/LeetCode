package alg_03_leetcode_top_wyj.class_04;

/**
 * @Author Wuyj
 * @DateTime 2023-02-23 12:14
 * @Version 1.0
 */
public class Problem_0028_ImplementStrStr {

    public int strStr(String s1, String s2) {
        if ((s1 == null || s2 == null) || (s1.length() < s2.length())) {
            return -1;
        }

        char[] str = s1.toCharArray();
        char[] pattern = s2.toCharArray();
        int[] next = getNextArray(pattern);

        int x = 0;
        int y = 0;

        while (x < str.length && y < pattern.length) {
            if (str[x] == pattern[y]) {
                x++;
                y++;
            } else if (next[y] == -1) {
                x++;
            } else {
                y = next[y];
            }
        }

        return y == pattern.length ? x - y : -1;
    }

    public int[] getNextArray(char[] chars) {
        if (chars.length == 1) {
            return new int[]{-1};
        }

        int[] next = new int[chars.length];
        next[0] = -1;
        next[1] = 0;
        int i = 2;
        int cn = 0;

        while (i < chars.length) {
            if (chars[cn] == chars[i - 1]) {
                next[i++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[i++] = 0;
            }
        }

        return next;
    }
}
