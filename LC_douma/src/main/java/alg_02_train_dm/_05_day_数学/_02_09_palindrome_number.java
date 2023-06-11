package alg_02_train_dm._05_day_数学;

/**
 * @Author Wuyj
 * @DateTime 2023-04-19 21:49
 * @Version 1.0
 */
public class _02_09_palindrome_number {
    /*
        9. 回文数
        给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
        回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
        例如，121 是回文，而 123 不是。

        提示
        -2^31 <= x <= 2^31 - 1

        示例 1：
        输入：x = 121
        输出：true

        示例 2：
        输入：x = -121
        输出：false
        解释：从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。



     */

    // KeyPoint 方法一 将整数转字符串
    public boolean isPalindrome1(int x) {
        String s = String.valueOf(x);
        int left = 0, right = s.length() - 1;
        while (left < right) {
            // 判断过程不相等，返回 false
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    // KeyPoint 方法二 借鉴'整数反转'思路
    // 先对 x 整数反转 x'，判断 x 是否等于 x'，若相等，则 x 是回文数
    public boolean isPalindrome2(int x) {
        if (x == 0) return true;
        // 负数 或者 整数个位为 0，必然不是回文数
        if (x < 0 || x % 10 == 0) return false;

        int res = 0;
        int y = x;
        // x 在不断循环过程，x 最后变成 0，x 是 while 循环判断条件
        while (x != 0) {
            int pop = x % 10;
            x = x / 10;
            // 若 x 是回文数，且输入 x 没有数据溢出，则反转后 x 也必然不会数据溢出
            // 若 x 不是回文数，则有可能数据溢出，故还是需要判断数据是否溢出
            // 反转整数的时候，可能出现溢出
            if (res > Integer.MAX_VALUE / 10
                    || (res == Integer.MAX_VALUE / 10 && pop > 7)) return false;

            // KeyPoint 因为 x 不可能为负数，故负数的数据溢出可以省略
//            if (res < Integer.MIN_VALUE / 10
//                    || (res == Integer.MIN_VALUE / 10 && pop < -8)) return false;
            res = res * 10 + pop;
        }

        return y == res;
    }

    // KeyPoint 方法三 整数反转 + 回文数特点
    // 回文数特点：前一半和后一半相等，故只需要反转一半即可，从而提高效率
    public boolean isPalindrome(int x) {
        if (x == 0) return true;
        if (x < 0 || x % 10 == 0) return false;

        int res = 0;
        // x 为偶数长度，res = x => 反转中间位置 => 结束 while 循环
        // x 为奇数长度，res > x => 结束 while 循环
        while (res < x) {
            // 反转一半，故不涉及数据溢出问题，故不需要考虑数据溢出
            res = res * 10 + x % 10;
            x = x / 10;
        }
        // 若 x 长度为偶数时，while 循环结束，res 和 x 长度一样，故只需要判断 res 和 x 是否相等即可
        // 若 x 长度为奇数时，通过 res / 10 去除处于个位数字，再去比较 res 和 x 是否相等
        // 例如：x = 12321 ，while 结束，x = 12，res = 123
        return res == x || x == res / 10;
    }
}
