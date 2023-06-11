package alg_03_leetcode_top_wyj.class_02;

/**
 * @Author Wuyj
 * @DateTime 2023-02-21 13:05
 * @Version 1.0
 */
public class problem_005_manacher {
    public static int manacher(String s) {
        if (s.length() == 0 || s == null) {
            return 0;
        }

        char[] chars = s.toCharArray();
        char[] targetChar = manacherString(chars);
        int[] pArr = new int[targetChar.length];

        int r = -1;
        int c = -1;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < targetChar.length; i++) {
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
            }
        }

        return max - 1;
    }

    public static char[] manacherString(char[] oldChars) {
        char[] newChars = new char[(oldChars.length * 2) + 1];
        int index = 0;
        for (int i = 0; i < newChars.length; i++) {
            newChars[i] = (i & 1) == 0 ? '#' : oldChars[index++];
        }
        return newChars;
    }

    // for test
    public static int right(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = manacherString(s.toCharArray());
        int max = 0;
        for (int i = 0; i < str.length; i++) {
            int L = i - 1;
            int R = i + 1;
            while (L >= 0 && R < str.length && str[L] == str[R]) {
                L--;
                R++;
            }
            max = Math.max(max, R - L - 1);
        }
        return max / 2;
    }

    // for test
    public static String getRandomString(int possibilities, int size) {
        char[] ans = new char[(int) (Math.random() * size) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (char) ((int) (Math.random() * possibilities) + 'a');
        }
        return String.valueOf(ans);
    }

    public static void main(String[] args) {
        int possibilities = 5;
        int strSize = 20;
        int testTimes = 5000000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            String str = getRandomString(possibilities, strSize);
            if (manacher(str) != right(str)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");
    }
}
