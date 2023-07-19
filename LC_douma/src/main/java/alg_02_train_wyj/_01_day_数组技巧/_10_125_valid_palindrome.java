package alg_02_train_wyj._01_day_数组技巧;

/**
 * @Author Wuyj
 * @DateTime 2023-04-16 21:50
 * @Version 1.0
 */
public class _10_125_valid_palindrome {
    public boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }

            if (left < right) {
                if (Character.toLowerCase(s.charAt(left))
                        != Character.toLowerCase(s.charAt(right))) {
                    return false;
                }
                left++;
                right--;
            }
        }
        return true;
    }
}
