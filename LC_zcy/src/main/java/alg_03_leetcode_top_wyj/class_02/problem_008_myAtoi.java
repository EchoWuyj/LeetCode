package alg_03_leetcode_top_wyj.class_02;

/**
 * @Author Wuyj
 * @DateTime 2023-02-22 10:38
 * @Version 1.0
 */
public class problem_008_myAtoi {
    public static int myAtoi(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        String s1 = removeZero(s.trim());
        if (s1.length() == 0) {
            return 0;
        }

        boolean flag = isValid(s1);
        if (flag) {
            return 0;
        }

        char[] chars = s1.toCharArray();
        boolean posi = chars[0] != '-';

        int minq = Integer.MIN_VALUE / 10;
        int minr = Integer.MIN_VALUE % 10;

        int res = 0;
        int cur = 0;

        for (int i = (chars[0] == '-' || chars[0] == '+' ? 1 : 0); i < chars.length; i++) {
            cur = '0' - chars[i];
            if (res < minq || (res == minq && cur < minr)) {
                return posi ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            res = res * 10 + cur;
        }

        if (posi && res == Integer.MIN_VALUE) {
            return Integer.MAX_VALUE;
        }

        return posi ? -res : res;
    }

    private static boolean isValid(String s1) {
        return (s1.charAt(0) == '-' || s1.charAt(0) == '+') && s1.length() == 1;
    }

    public static String removeZero(String str) {
        boolean r = str.startsWith("-") || str.startsWith("+");
        int start = r ? 1 : 0;

        for (; start < str.length(); start++) {
            if (str.charAt(start) != '0') {
                break;
            }
        }

        int end = str.length() - 1;
        int index = -1;
        for (; end >= start; end--) {
            if (str.charAt(end) > '9' || str.charAt(end) < '0') {
                index = end;
            }
        }
        return (r ? String.valueOf(str.charAt(0)) : "") + str.substring(start, index == -1 ? str.length() : index);
    }

    public static void main(String[] args) {
        System.out.println(myAtoi("-2147483649"));
    }
}
