package alg_02_train_dm._04_day_字符串;

/**
 * @Author Wuyj
 * @DateTime 2023-08-16 12:12
 * @Version 1.0
 */
public class _09_08_string_to_integer_atoi {
    public int myAtoi(String s) {
        char[] chars = s.toCharArray();

        int i = 0;
        // 1. 丢弃前导空格
        // 注意：' ' 表示空格
        while (i < s.length() && chars[i] == ' ') i++;
        if (i == s.length()) return 0;

        // 2. 检查 + 和 - 是否存在
        int sign = 1;
        if (chars[i] == '-' || chars[i] == '+') {
            sign = chars[i] == '-' ? -1 : 1;
            i++;
        }

        // 3. 计算结果，并且检查是否溢出
        int base = 0;
        while (i < chars.length && chars[i] >= '0' && chars[i] <= '9') {
            // 检查是否溢出
            // 最大值 2147483647
            // 最小值 -2147483648
            // base 只是考虑正数，负数通过 sign 确定
            if (base > Integer.MAX_VALUE / 10 ||
                    (base == Integer.MAX_VALUE / 10 && chars[i] - '0' > 7)) {
                if (sign > 0) {
                    return Integer.MAX_VALUE;
                } else {
                    return Integer.MIN_VALUE;
                }
            }

            base = base * 10 + (chars[i] - '0');
            i++;
        }

        return sign * base;
    }
}
