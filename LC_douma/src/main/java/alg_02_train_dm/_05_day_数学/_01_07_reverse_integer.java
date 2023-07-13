package alg_02_train_dm._05_day_数学;

/**
 * @Author Wuyj
 * @DateTime 2023-04-19 21:45
 * @Version 1.0
 */
public class _01_07_reverse_integer {

    /*
        7. 整数反转
        给你一个 32 位的 有符号整数 x ，返回将 x 中的数字部分反转后的结果。
        如果反转后整数超过 32 位的有符号整数的范围 [−2^31,  2^31 − 1] ，就返回 0。
        假设环境 不允许存储 64 位整数 (有符号或无符号) => 不能使用 long 类型存储超过范围的整数

        示例 1：
        输入：x = 123
        输出：321

        示例 2：
        输入：x = -123
        输出：-321

        示例 3：
        输入：x = 120
        输出：21

        示例 4：
        输入：x = 0
        输出：0

        提示：
        -2^31 <= x <= 2^31 - 1

     */

    // KeyPoint 方法一 先转成 String，再反转字符串
    // 存在 bug：代码中使用 Long 类型 => 违反题目要求
    public int reverse1(int x) {
        String str = String.valueOf(x);
        char[] chars = str.toCharArray();
        int left = 0, right = str.length() - 1;
        // "-123" -> "-321"
        // 判断 left 是否是数字，不是数字则 left ++
        // 直接通过 char 字符判断
        if (chars[left] < '0' || chars[left] > '9') left++;

        // 字符串反转 => 可能会导致数据越界问题
        while (left < right) {
            char tmp = chars[left];
            chars[left] = chars[right];
            chars[right] = tmp;
            left++;
            right--;
        }
        // 如果反转后，数据溢出的话，即不在 [−2^31,  2^31 − 1] 范围内
        // 使用 long类型接受，再去判断是否在范围内 => 违反题目要求
        long res = Long.parseLong(new String(chars));
        if (res > Integer.MAX_VALUE || res < Integer.MIN_VALUE) return 0;
        // KeyPoint 强制类型转换
        // Long res Long 是包装类型，是无法进行强转的 (int)res ×
        // long res 才能进行强转 (int)res √
        return (int) res;
    }

    // KeyPoint 方法二 数据溢出之前判断
    // 思路：从右往左处理数字，将原先整数的个位数，放到结果集最后一位，从而实现整数反转
    // x   5 1 3 9 2 4
    // res 4 2 9 3 1 5
    public int reverse2(int x) {
        int res = 0;
        // x = 0 结束，相反条件 x != 0 => while 循环条件
        while (x != 0) {

            // KeyPoint 1.获取个位数 => 记忆：% 形状像勺子，将个位捞出来
            int pop = x % 10;

            // KeyPoint 2.去掉个位数 => 记忆：去掉个位，使用 / 将个位划去
            x = x / 10;

            // ===================================================

            // 在计算最后一轮 res 之前，上一轮 res 可能存在使用公式  res = res * 10 + pop
            // 计算后，导致最后一轮 res 数据溢出，故上一轮 res 需要和 MAX_VALUE 和 MIN_VALUE 进行大小判断
            // MAX_VALUE = 2^31 - 1 = 2147483647
            // MIN_VALUE = -2^31 = -2147483648

            // 若 res > Integer.MAX_VALUE / 10，则 res * 10 => res > Integer.MAX_VALUE 数据溢出
            // MAX_VALUE => 比大，使用 >
            if (res > Integer.MAX_VALUE / 10
                    // res == Integer.MAX_VALUE / 10 = 214748364
                    // 若 pop > 7，则 res = res * 10 + pop = 2147483640 + > 7 => 数据溢出
                    //
                    || (res == Integer.MAX_VALUE / 10 && pop > 7)) return 0;

            // 同理
            // MIN_VALUE => 比小，使用 <
            if (res < Integer.MIN_VALUE / 10
                    || (res == Integer.MIN_VALUE / 10 && pop < -8)) return 0;

            // ===================================================

            // KeyPoint 3.拼接个位数，原先 res * 10，扩大一个 10 位
            res = res * 10 + pop;

            // KeyPoint 补充说明：
            // 若 x 是负数，pop 也是负数 => -13 % 10 = -3
            // res 负数，pop 负数，故该代码每轮操作都是负数累和，最后 res 也是负数
            // => 代码对负数成立
        }
        return res;
    }

    // KeyPoint 方法三 数据溢出之后判断
    public int reverse(int x) {
        int res = 0;
        while (x != 0) {
            int pop = x % 10;
            x = x / 10;
            // 不管三七二十一，先计算，之后再去判断是否数据溢出
            int newRes = res * 10 + pop;
            // 计算 res 之后，再去判断 res 是否溢出
            // 溢出判断：是否能原路返回，若能原路返回，则说明没有溢出
            // => 即 newRes 是否等于 res，若等于说明没有溢出
            if ((newRes - pop) / 10 != res) return 0;
            res = newRes;
        }
        return res;
    }
}
