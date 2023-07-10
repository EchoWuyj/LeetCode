package alg_02_train_dm._09_day_哈希查找_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-04-15 15:36
 * @Version 1.0
 */
public class _04_389_find_the_difference {
    /*
        389 号算法题：找不同
        给定两个字符串 s 和 t，它们只包含小写字母。
        字符串 t 由字符串 s 随机重排，然后在随机位置添加一个字母。
        请找出在 t 中被添加的字母。

        输入：s = "abcd", t = "baedc"
        输出：e

        输入：s = "ae", t = "aea"
        输出： a

        提示：
        0 <= s.length <= 1000
        t.length == s.length + 1
        s 和 t 只包含小写字母
     */

    // KeyPoint 方法一 统计词频
    public char findTheDifference1(String s, String t) {
        // s 和 t 只包含小写字母，使用数组统计词频，性能更好
        int[] countS = new int[26];
        for (char c : s.toCharArray()) countS[c - 'a']++;
        for (char c : t.toCharArray()) {
            countS[c - 'a']--;
            // t 中有，而 s 中没有
            if (countS[c - 'a'] < 0) return c;
        }
        // 空字符，''中间有个空格，区别于 ""
        return ' ';

        // KeyPoint 区别：字符 '' 和 字符串 ""

        // 1.字符
//        char c1 = '';  × 错误写法 => '' 里面不可以什么都不放
//        char c2 = ' '; √ 正确写法 => ' ' 里面放了空格

        // 2.字符串
//        String str1 = "";
//        String str2 = " ";
    }

    // KeyPoint 方法二 计算 ASCII 值
    public char findTheDifference2(String s, String t) {
        // 记录 s，t 的 ASCII 的值
        int as = 0, at = 0;
        for (char c : s.toCharArray()) as += c;
        for (char c : t.toCharArray()) at += c;
        // ASCII 相减，再将其转成 char
        return (char) (at - as);
    }

    // KeyPoint 方法三 异或
    public char findTheDifference(String s, String t) {
        int res = 0;
        // 将 s 和 t 中所有字符进行异或
        // 利用'异或'性质
        // 1.交换律：a ^ b ^ c <=> a ^ c ^ b
        // 2.任何数与 0 异或为任何数 0 ^ n => n
        // 3.相同的数异或为 0: n ^ n => 0

        // char 类型在异或运算中会被转换为 int 类型
        for (char c : s.toCharArray()) res ^= c;
        for (char c : t.toCharArray()) res ^= c;
        return (char) res;
    }
}
