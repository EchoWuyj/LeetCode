package alg_02_train_wyj._24_day_贪心算法一;

/**
 * @Author Wuyj
 * @DateTime 2023-04-13 16:04
 * @Version 1.0
 */
public class _09_680_valid_palindrome_ii {

    public boolean validPalindrome1(String s) {
        for (int i = -1; i < s.length(); i++) {
            boolean isPalindrome = true;
            int left = 0, right = s.length() - 1;
            while (left < right) {
                if (left == i) left++;
                if (right == i) right--;

                if (s.charAt(left) == s.charAt(right)) {
                    left++;
                    right--;
                } else {
                    isPalindrome = false;
                    break;
                }
            }
            if (isPalindrome) return true;
        }
        return false;
    }

    public boolean validPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) == s.charAt(right)) {
                left++;
                right--;
            } else {
                return validPalindrome(s, left + 1, right) ||
                        validPalindrome(s, left, right - 1);
            }
        }
        return true;
    }

    public boolean validPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
