package alg_02_train_dm._01_day_数组技巧._02_双指针;

/**
 * @Author Wuyj
 * @DateTime 2023-04-16 21:36
 * @Version 1.0
 */
public class _10_125_valid_palindrome {

    /*
        125. 验证回文串

        如果在将所有大写字符转换为小写字符、并移除所有非字母数字字符之后，
        短语正着读和反着读都一样。则可以认为该短语是一个 回文串 。

        字母和数字都属于字母数字字符。

        给你一个字符串 s，如果它是 回文串 ，返回 true ；否则，返回 false 。

        输入: s = "A man, a plan, a canal: Panama"
        输出：true
        解释："amanaplanacanalpanama" 是回文串。

        输入：s = "race a car"
        输出：false
        解释："raceacar" 不是回文串。
     */

    // 对撞指针
    public boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            // 忽略左边无效字符
            while (left < right &&
                    // 判断字符，若不是字母或数字，直接跳过，移动 left 指针
                    !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }

            // 忽略右边无效字符
            while (left < right &&
                    !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }

            if (left < right) {
                // 本题中，'a'和'A'看做对称的，即忽略字母大小写
                // 代码上需要将大写转小写，再去比较是否相等
                if (Character.toLowerCase(s.charAt(left)) !=
                        Character.toLowerCase(s.charAt(right))) {
                    return false;
                }
                left++;
                right--;
            }
        }
        return true;
    }
}
