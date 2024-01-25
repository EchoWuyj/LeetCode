package alg_03_high_frequency._01_codetop_2024_01;

/**
 * @Author Wuyj
 * @DateTime 2024-01-14 10:57
 * @Version 1.0
 */
public class _47_08_myAtoi {
    public int myAtoi(String s) {
        // 字符串转换整数 (atoi)
        int i = 0;
        // 删除前导空格
        while (i < s.length() && s.charAt(i) == ' ') i++;
        if (i == s.length()) return 0;

        // 确定符号，默认是正数
        int sign = 1;
        // 判断是正还是负
        // KeyPoint 注意：本题中只能使用 if，不能使用 while
        // 1.若使用 while 则是以最后一个符号为准，不符合测试用例要求，输入"+-12" 输出 0
        // 2.若使用 if，只是判断一次符号，后面的符号不去判读，同时不执行下面的 while 循环，最终结果返回 0
        if (s.charAt(i) == '-' || s.charAt(i) == '+') {
            sign = (s.charAt(i) == '-') ? -1 : 1;
            // i 需要移动
            i++;
        }

        // 确定数值
        int base = 0;
        while (i < s.length() && s.charAt(i) >= '0' && s.charAt(i) <= '9') {
            // 判断数值时，只是针对正数，没有考虑负数情况
            if (base > Integer.MAX_VALUE / 10 ||
                    (base == Integer.MAX_VALUE / 10 && s.charAt(i) - '0' > 7)) {
                // 通过 sign 判断正负，给定最大值和最小值
                if (sign > 0) return Integer.MAX_VALUE;
                else return Integer.MIN_VALUE;
            }
            base = (base * 10) + (s.charAt(i) - '0');
            // i 需要移动
            i++;
        }
        return sign * base;
    }
}
