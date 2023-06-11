package alg_03_leetcode_top_zcy.class_14;

import sun.security.action.PutAllAction;

/**
 * @Author Wuyj
 * @DateTime 2023-03-06 14:41
 * @Version 1.0
 */

// 判断字符串是否为回文
public class Problem_0131_StringIsPalindrome {
    public static boolean isPalindrome(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        int i = 0;
        int j = str.length() - 1;
        char[] s = str.toCharArray();
        while (i < j) {
            if (s[i] != s[j]) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome("aba"));
        System.out.println(isPalindrome("abc"));
    }
}
