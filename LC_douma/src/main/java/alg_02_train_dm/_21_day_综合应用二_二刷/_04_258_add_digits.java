package alg_02_train_dm._21_day_综合应用二_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-05-26 15:35
 * @Version 1.0
 */
public class _04_258_add_digits {
     /*
        258. 各位相加
        给定一个非负整数 num，反复将各个位上的数字相加，直到结果为一位数。

        示例:
        输入: 38
        输出：2
            3 + 8 = 11
            1 + 1 = 2
        提示：
        0 <= num <= 231 - 1

     */

    // KeyPoint 方法一 直接模拟
    // 0 <= num <= 231 - 1 直接模拟并不会超时
    // => 通过 % 10 获取个位数，通过 / 10 去掉一位，2^31-1 = 2.1* 10 ^ 9 最多循环十几次
    public int addDigits1(int num) {
        // num 每位上的值累和值，依旧是可能 >= 10，故需要使用 while 循环判断
        //
        while (num >= 10) {
            num = totalSum(num);
        }
        // 直到 num 只有一位，跳出 while 循环，最终返回
        return num;
    }

    // 计算 num 每位上的值
    private int totalSum(int num) {
        int total = 0;
        while (num != 0) {
            total += num % 10;
            num /= 10;
        }
        return total;
    }

    // KeyPoint 方法二 数学法 => 了解即可，现场想肯定想不到
    // 解释：https://blog.csdn.net/weixin_41541562/article/details/106635899
    public int addDigits(int num) {
        return (num - 1) % 9 + 1;
    }
}
