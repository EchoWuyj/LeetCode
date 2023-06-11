package alg_02_train_dm._05_day_数学;

/**
 * @Author Wuyj
 * @DateTime 2023-04-19 21:45
 * @Version 1.0
 */
public class _01_07_reverse_integer {

    /*
        7. 整数反转
        给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
        如果反转后整数超过 32 位的有符号整数的范围 [−2^31,  2^31 − 1] ，就返回 0。
        假设环境不允许存储 64 位整数 (有符号或无符号) => 不能使用 long 类型存储超过范围的整数

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

     */

    // KeyPoint 方法一 先转成 String，再反转字符串
    // 存在 bug：代码中使用 Long 类型 => 违反题目要求
    public int reverse1(int x) {
        String str = String.valueOf(x);
        char[] chars = str.toCharArray();
        int left = 0, right = str.length() - 1;
        // "-123" -> "-321"
        // 判断 left 是否是数字，不是数字则 left ++
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
        // 使用 long类型，违反题目要求
        long res = Long.parseLong(new String(chars));
        if (res > Integer.MAX_VALUE || res < Integer.MIN_VALUE) return 0;
        return (int) res;
    }

    // KeyPoint 方法二  溢出之前判断
    // 思路：将原先整数的个位数，放到结果集最后一位，从而实现整数反转
    public int reverse2(int x) {
        int res = 0;
        // x = 0 结束，相反条件 x != 0 => while 循环条件
        while (x != 0) {
            // 获取个位数
            // 若 x 是负数，pop 也是负数，最后 res 也是负数 => 代码对负数成立
            // -3 % 10 = -3
            int pop = x % 10;
            // 去掉个位数
            x = x / 10;
            // KeyPoint 在计算 res 之前有可能溢出，需要 MAX_VALUE 和 MIN_VALUE 判断
            // MAX_VALUE = 2^31 - 1 = 2147483647

            // res * 10 => res 溢出
            if (res > Integer.MAX_VALUE / 10
                    // res = 214748364 + pop > 7 =>  res 溢出
                    || (res == Integer.MAX_VALUE / 10 && pop > 7)) return 0;

            // MIN_VALUE = -2^31 = -2147483648
            if (res < Integer.MIN_VALUE / 10
                    || (res == Integer.MIN_VALUE / 10 && pop < -8)) return 0;

            // 拼接个位数，原先 res * 10，扩大一个 10 位
            res = res * 10 + pop;
        }
        return res;
    }

    // KeyPoint 方法三：溢出之后判断
    public int reverse(int x) {
        int res = 0;
        while (x != 0) {
            int pop = x % 10;
            x = x / 10;
            int newRes = res * 10 + pop;
            // 计算 res 之后，再去判断 res 是否溢出
            if ((newRes - pop) / 10 != res) return 0;
            res = newRes;
        }
        return res;
    }
}
