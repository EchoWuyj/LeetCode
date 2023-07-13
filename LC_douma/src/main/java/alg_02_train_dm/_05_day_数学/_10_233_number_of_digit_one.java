package alg_02_train_dm._05_day_数学;

/**
 * @Author Wuyj
 * @DateTime 2023-04-20 20:43
 * @Version 1.0
 */
public class _10_233_number_of_digit_one {

    /*
        233. 数字 1 的个数
        给定一个整数 n，计算所有小于等于 n 的 非负整数中数字 1 出现的个数。

        示例 1：
        输入：n = 13
        输出：6
        解释：1 2 3 4 5 6 7 8 9 10 11 12 13
              1 10 11 12 13 => 6 个 1

        示例 2：
        输入：n = 0
        输出：0

        提示：
        0 <= n <= 10^9

     */

    // KeyPoint 方法一 暴力解
    // 时间复杂度 O(kn) => 超时
    // 0 <= n <= 10^9 => 时间复杂度必须 O(n) 以下 => 二分 或者 数学公式(数学规律)
    public int countDigitOne1(int n) {
        int res = 0;
        for (int i = 1; i <= n; i++) {
            String s = String.valueOf(i);
            int tmp = 0;
            // 对 String 类型数字的每个字符进行判断
            for (char c : s.toCharArray()) {
                if (c == '1') tmp++;
            }
            res += tmp;
        }
        return res;
    }

    // KeyPoint 方法二 数学公式
    // O(logn)
    public int countDigitOne(int n) {

        // KeyPoint 优化思路
        // 因为数据量 0 <= n <= 10^9，故需要保证算法的时间复杂度 O(n) 以下 => 二分 或者 数学公式(数学规律)
        // => 二分需要数组有序(满足)，且排除一半区间(不满足)，故无法使用二分
        // => 通过 找数学规律 推导 数学公式
        //    最理想情况：即通过一个公式就能将数字 1 的个数求出来，从而满足时间复杂度要求

        // KeyPoint 数学规律推导
        // 1.找数据规律，一定得有章法，有规则，这样才能发现数学规律
        //   如：个位，十位，百位，千位
        // 2.找和题目要求相关的，特殊的临界位置，
        //   10，100，110 ... 119，120 ...

        // KeyPoint 详细推导过程
        // 对 1 所在位置进行分类，个位，十位，百位，千位 ...
        // 依次从个位，十位，百位，千位 分别讨论 1 的个数

        // 推导：个位上 1 的个数
        // 1 11 21 31 41 51 61 ...
        // 到 10 为止，有 1 个 1
        // 到 20 为止，有 2 个 1
        // 到 30 为止，有 3 个 1
        // 到 31 为止，有 4 个 1

        // KeyPoint 结论：
        // 给定一个整数 n，小于等于 n 中的所有数中，个位数上有 1 的个数:
        // 1.若 n % 10 == 0，那么个数为 (n/10)
        //   => 到 30 为止，有 3 个 1
        // 2.若 n % 10 != 0，那么个数为 (n/10) + 1
        //   => 到 31 为止，有 4 个 1
        // => (n/10) * 1 + min(max((n % 10 - 1 + 1)，0)，1)

        // 推导：十位上 1 的个数
        // 10 11 12 13 14 15 16 17 18 19
        // 到 100 为止，有 10 个 1
        // 到 200 为止，有 20 个 1
        // 到 300 为止，有 30 个 1
        // 到 310 为止，有 31 个 1
        // 到 31x (x>0) 为止，有 31 + x 个 1
        // 到 315 (x>0) 为止，有 31 + 5 个 1
        // ...
        // 到 1600 为止，有 160 个 1
        // 到 1610 为止，有 161 个 1
        // 到 161x (x>0) 为止，有 161 + x 个 1
        // 到 1650 (50>19) 为止，有 161 + 10 个 1

        // KeyPoint 结论：
        // 给定一个整数 n，小于等于 n 中的所有数中，十位数上有 1 的个数
        // (n / 100) * 10 + min(max((n % 100 - 10 + 1)，0)，10)
        // (n / 100) * 10 => 百位部分计算公式 => 如：200，则 (200 / 100) * 10 = 20
        //
        // 310 ~ 31x 使用  max((n % 100 - 10 + 1)，0) 记为 tmp
        // 31x ~ 399 使用 min(tmp，10)

        // 推导：百位上 1 的个数 => 基本同理于十位上 1 的个数
        // 到 1000 为止，有 100 个 1
        // 到 2000 为止，有 200 个 1
        // 到 3000 为止，有 300 个 1
        // 到 16000 为止，有 1600 个 1
        // 到 161xy (x>0,y>0) 为止:有 1601 + xy 个
        // 到 16500 为止，有 160+100 个 1

        // KeyPoint 结论：
        // 给定一个整数 n，小于等于 n 中的所有数中，百位数上有 1 的个数
        // (n/1000) * 100 + min(max((n % 1000 - 100 + 1)，0)，100)

        int count = 0;
        // KeyPoint i 应该是长整型，要不然会溢出
        for (long i = 1; i <= n; i *= 10) {
            // 个位 => (n/10) * 1 + min(max((n % 10 - 1 + 1), 0), 1)
            // 十位 => (n/100) * 10 + min(max((n % 100 - 10 + 1), 0), 10)
            // 百位 => (n/1000) * 100 + min(max((n % 1000 - 100 + 1), 0), 100)
            // divider 除数
            // 从个位，十位，百位 ... 依次判断有多少个 1
            long divider = i * 10;
            count += (n / divider) * i + Math.min(Math.max(n % divider - i + 1, 0L), i);
        }
        return count;
    }
}
