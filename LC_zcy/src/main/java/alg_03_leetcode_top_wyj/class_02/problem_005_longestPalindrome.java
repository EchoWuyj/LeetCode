package alg_03_leetcode_top_wyj.class_02;

/**
 * @Author Wuyj
 * @DateTime 2023-02-21 10:11
 * @Version 1.0
 */
public class problem_005_longestPalindrome {
    public static String longestPalindrome(String s) {
        if (s.length() == 0 || s == null) {
            return "";
        }

        char[] chars = s.toCharArray();
        char[] targetChar = manacherString(chars);
        int[] pArr = new int[targetChar.length];

        int r = -1;
        int c = -1;
        int flag = -1;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < targetChar.length; i++) {
            System.out.println(i);
            pArr[i] = (i < r) ? Math.min(pArr[2 * c - i], r - i) : 1;
            while (i + pArr[i] < targetChar.length && i - pArr[i] > -1) {
                if (targetChar[i + pArr[i]] == targetChar[i - pArr[i]]) {
                    pArr[i]++;
                } else {
                    break;
                }
            }

            if (i + pArr[i] > r) {
                r = i + pArr[i];
                c = i;
            }

            if (pArr[i] > max) {
                max = pArr[i];
                flag = i;
            }
        }

        StringBuilder res = new StringBuilder();
        for (int i = flag - (max - 1); i <= flag + max - 1; i++) {
            if (targetChar[i] != '#') {
                res.append(targetChar[i]);
            }
        }
        return res.toString();
    }

    public static char[] manacherString(char[] oldChars) {
        char[] newChars = new char[(oldChars.length * 2) + 1];
        int index = 0;
        for (int i = 0; i < newChars.length; i++) {
            newChars[i] = (i & 1) == 0 ? '#' : oldChars[index++];
        }
        return newChars;
    }
}
