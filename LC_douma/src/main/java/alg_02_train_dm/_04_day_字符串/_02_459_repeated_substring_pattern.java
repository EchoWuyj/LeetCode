package alg_02_train_dm._04_day_字符串;

/**
 * @Author Wuyj
 * @DateTime 2023-08-16 12:08
 * @Version 1.0
 */
public class _02_459_repeated_substring_pattern {

    /*
        459. 重复的子字符串
        给定一个非空的字符串 s ，检查是否可以通过由它的一个子串重复多次构成。

        示例 1:
        输入: s = "abab"
        输出: true
        解释: 可由子串 "ab" 重复两次构成。

        示例 2:

        输入: s = "aba"
        输出: false

        示例 3:

        输入: s = "abcabcabcabc"
        输出: true
        解释: 可由子串 "abc" 重复四次构成。 (或子串 "abcabc" 重复两次构成。)

        提示：
        1 <= s.length <= 104
        s 由小写英文字母组成

     */

    // KeyPoint 方法一 字符串匹配法
    public boolean repeatedSubstringPattern(String s) {
        /*
        字符串 s 拼接的目的：可以在 s + s 中找到 s 的所有旋转后的字符串
        拼接后的字符串的头部是旋转了一圈的字符串，而尾部是没有旋转的字符串，所以需要去掉头部和尾部

        从 1 开始匹配的作用就是去掉头部

        不等于 s.length 的作用就是去掉尾部
         */
        return (s + s).indexOf(s, 1) != s.length();
    }

    // 旋转数组
    public boolean repeatedSubstringPattern2(String s) {
        for (int len = 1; len * 2 <= s.length(); len++) {
            String str = rotate(s.toCharArray(), len);
            if (s.equals(str)) return true;
        }
        return false;
    }

    public String rotate(char[] chars, int k) {
        int n = chars.length;
        k = k % n;
        reverse(chars, 0, n - 1);
        reverse(chars, 0, k - 1);
        reverse(chars, k, n - 1);
        return String.valueOf(chars);
    }

    private void reverse(char[] chars, int start, int end) {
        while (start < end) {
            char tmp = chars[start];
            chars[start] = chars[end];
            chars[end] = tmp;
            start++;
            end--;
        }
    }

    // 双指针模拟
    public boolean repeatedSubstringPattern1(String s) {
        int n = s.length();
        for (int len = 1; len * 2 <= n; len++) {
            if (n % len == 0) {
                boolean matched = true;
                int i = 0;
                for (int j = len; j < n; j++, i++) { // bug 修复：i 也需要加加
                    if (s.charAt(i) != s.charAt(j)) {
                        matched = false;
                        break;
                    }
                }
                if (matched) return true;
            }
        }
        return false;
    }
}
