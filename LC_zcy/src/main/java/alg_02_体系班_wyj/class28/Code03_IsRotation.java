package alg_02_体系班_wyj.class28;

/**
 * @Author Wuyj
 * @DateTime 2023-02-21 21:30
 * @Version 1.0
 */
public class Code03_IsRotation {
    public static boolean isRotation(String str1, String str2) {
        if (str1 == null || str2 == null || str1.length() != str2.length()) {
            return false;
        }
        String temp = str1 + str1;
        return getIndexOf(temp, str2) != -1;
    }

    public static int getIndexOf(String str1, String str2) {
        if (str1.length() < str2.length()) {
            return -1;
        }

        char[] str1Chars = str1.toCharArray();
        char[] str2Chars = str2.toCharArray();

        int[] next = getNextArray(str2);
        int x = 0;
        int y = 0;

        while (x < str1Chars.length && y < str2Chars.length) {
            if (str1Chars[x] == str2Chars[y]) {
                x++;
                y++;
            } else if (next[y] == -1) {
                x++;
            } else {
                y = next[y];
            }
        }
        return y == str2Chars.length ? x - y : -1;
    }

    public static int[] getNextArray(String str) {
        if (str.length() == 1) {
            return new int[]{-1};
        }

        char[] strChars = str.toCharArray();
        int[] next = new int[strChars.length];
        next[0] = -1;
        next[1] = 0;
        int i = 2;
        int pre = 0;

        while (i < str.length()) {
            if (strChars[pre] == strChars[i - 1]) {
                next[i++] = ++pre;
            } else if (pre > 0) {
                pre = next[pre];
            } else {
                next[i++] = 0;
            }
        }
        return next;
    }
}
