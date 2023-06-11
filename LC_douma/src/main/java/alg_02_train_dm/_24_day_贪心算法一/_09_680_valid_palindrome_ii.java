package alg_02_train_dm._24_day_贪心算法一;

/**
 * @Author Wuyj
 * @DateTime 2023-04-12 16:19
 * @Version 1.0
 */
public class _09_680_valid_palindrome_ii {
      /*
        680. 验证回文字符串 Ⅱ
        给定一个非空字符串 s，最多删除一个字符。判断是否能成为回文字符串。

        示例 1:
        输入: "aba"
        输出: true

        示例 2:
        输入: "abca"
        输出: true

        注意:
        字符串只包含从 a-z 的小写字母。
        字符串的最大长度是 50000 => 5 * 10^4 => 算法时间复杂度 < O(n^2)

     */

    // KeyPoint 方法一 暴力解法 => 每次尝试删除一个字符，判断是否是回文串
    // 时间复杂度：O(n^2) 超时
    // 空间复杂度：O(1)
    public boolean validPalindrome1(String s) {
        // 删除逐个字符，然后判断是否为回文串
        // i = -1，表示不删除字符情况，0 ~ s.length() - 1 表示在不同位置上去删除字符
        // O(n)
        for (int i = -1; i < s.length(); i++) {
            boolean isPalindrome = true;
            int left = 0, right = s.length() - 1;
            // 判断字符串是否是回文
            // O(n)
            while (left < right) {
                // 删除索引 i 对应的字符，注意不是真的删除，只是跳过 i 位置字符，不考虑
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

            // 存在一个为回文串即可
            if (isPalindrome) return true;
        }
        return false;
    }

    // KeyPoint 方法二 贪心
    // 暴力优化：每次逐一去尝试删除一个字符 => 但其实有些字符可以不用删除判断的
    //           因为即使删除了该字符，剩余的字符串也不可能是回文串
    // 贪心策略：只有在开头和结尾两个字符不相等时，才去尝试删除开头或者结尾任一个字符，从而判断剩余字符串是否是回文串，
    //          没有必要从字符串内部删除一个字符， 因为开头和结尾字符始终是不相等的，即字符串整体依旧不是回文串
    // a b a c => 只尝试删除 '开头 a' 和 '结尾 c'
    // 时间复杂度：O(n)
    // 空间复杂度：O(1)
    public boolean validPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) { // O(n)
            // 头尾相等，直接跳过，往字符串内部判断
            if (s.charAt(left) == s.charAt(right)) {
                left++;
                right--;
            } else {
                // left != right
                // 要么删除 left 指向的字符，要么删除 right 指向的字符，然后再判断剩余的字符是否是回文
                // 同样是没有真正删除 left 或者 right(避免数组元素移动，增加开销)，只是移动指针，而是跳过该位置的判断
                return validPalindrome(s, left + 1, right) ||
                        validPalindrome(s, left, right - 1);
            }
        }
        // 如果上面 return 没有返回，则是循环执行 if 代码，则 left 和 right 字符都是相等的，最终返回 true
        return true;
    }

    // 验证回文串
    private boolean validPalindrome(String s, int left, int right) {
        while (left < right) {
            // 先判断字符不等
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
