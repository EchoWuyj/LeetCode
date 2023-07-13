package alg_02_train_wyj._05_day_数学;

/**
 * @Author Wuyj
 * @DateTime 2023-04-20 12:08
 * @Version 1.0
 */
public class _02_09_palindrome_number {

    public boolean isPalindrome1(int x) {
        String str = String.valueOf(x);
        char[] chars = str.toCharArray();
        int left = 0, right = chars.length - 1;
        while (left < right) {
            if (chars[left] != chars[right]) return false;
            left++;
            right--;
        }
        return true;
    }

    public boolean isPalindrome2(int x) {
        if (x == 0) return true;
        if (x < 0 || x % 10 == 0) return false;
        int res = 0;
        int tmp = x;
        while (x != 0) {
            int pop = x % 10;
            x = x / 10;
            if (x > Integer.MAX_VALUE / 10
                    || x == Integer.MAX_VALUE && pop > 7) return false;
            res = res * 10 + pop;
        }
        return tmp == res;
    }

    public boolean isPalindrome(int x) {
        if (x == 0) return true;
        if (x < 0 || x % 10 == 0) return false;
        int res = 0;
        while (res < x) {
            int pop = x % 10;
            x = x / 10;
            res = res * 10 + pop;
        }
        return res == x || res / 10 == x;
    }
}
