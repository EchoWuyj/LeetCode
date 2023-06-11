package alg_03_leetcode_top_wyj.class_04;

/**
 * @Author Wuyj
 * @DateTime 2023-02-19 14:28
 * @Version 1.0
 */
public class Problem_0028_KMP {
    public int getIndexOf(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() < 1 || s1.length() < s2.length()) {
            return -1;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();

        int x = 0;
        int y = 0;
        int[] next = getNextArray(s2);
        while (x < str1.length && y < str2.length) {
            if (str1[x] == str2[y]) {
                x++;
                y++;
            } else if (next[y] == -1) {
                x++;
            } else {
                y = next[y];
            }
        }

        return y == str2.length ? x - y : -1;
    }

    public int[] getNextArray(String str) {
        if (str.length() == 1) {
            return new int[]{-1};
        }

        int[] next = new int[str.length()];
        next[0] = -1;
        next[1] = 0;
        char[] strChar = str.toCharArray();
        int i = 2;
        int cn = 0;
        while (i < strChar.length) {
            if (strChar[i - 1] == strChar[cn]) {
                next[i] = cn + 1;
                i++;
                cn++;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[i] = 0;
                i++;
            }
        }
        return next;
    }
}
