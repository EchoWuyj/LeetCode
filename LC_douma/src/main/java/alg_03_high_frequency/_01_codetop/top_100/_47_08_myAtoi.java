package alg_03_high_frequency._01_codetop.top_100;

/**
 * @Author Wuyj
 * @DateTime 2024-01-14 10:57
 * @Version 1.0
 */
public class _47_08_myAtoi {

    // 字符串转换整数 (atoi)
    // 直接模拟
    public int myAtoi(String s) {

        // 步骤
        // 1.删除前导空格
        // 2.确定符号，默认是正数
        // 3.确定数值

        // 遍历指针一定得随着移动
        int i = 0;

        // 1.删除前导空格
        while (i < s.length() && s.charAt(i) == ' ') i++;
        // i 越界，则直接返回 0，因为题目规定：没有数字返回 0
        if (i == s.length()) return 0;
        // 2.确定符号，默认是正数
        int sign = 1;

        // 判断是正还是负，注意本题中只能使用 if，不能使用 while 循环
        // 1.若使用 while 则是以最后一个符号为准，不符合测试用例要求
        // 2.测试用例要求：输入"+-12" 输出 0
        //   只要使用 if，只是判断一次符号(+)，后面的符号(-)不满足下面 while 循环条件
        //   最终返回 sign*base，其中 base 为 0
        if (s.charAt(i) == '-' || s.charAt(i) == '+') {
            sign = (s.charAt(i) == '-') ? -1 : 1;
            // i 需要移动
            i++;
        }

        // 3.确定数值
        int base = 0;
        while (i < s.length() && s.charAt(i) >= '0' && s.charAt(i) <= '9') {
            // 判断数值时，只是针对正数，没有考虑负数情况
            // 最大值 2147483647 => 以 7 结尾
            // 最小值 -2147483648 => 以 8 结尾
            if (base > Integer.MAX_VALUE / 10 ||
                    (base == Integer.MAX_VALUE / 10 && s.charAt(i) - '0' > 7)) {
                // 数值越界，通过 sign 判断正负，直接返回最大值和最小值
                if (sign > 0) return Integer.MAX_VALUE;
                else return Integer.MIN_VALUE;
            }

            // base 计算过程
            base = (base * 10) + (s.charAt(i) - '0');
            // i 需要移动
            i++;
        }

        // 最后返回：符号 * 数值
        return sign * base;
    }
}
