package alg_02_train_wyj._04_day_字符串;

/**
 * @Author Wuyj
 * @DateTime 2023-10-13 15:22
 * @Version 1.0
 */
public class _09_08_string_to_integer_atoi {
    public int myAtoi(String s) {
        char[] chars = s.toCharArray();
        int n = s.length();
        int i = 0;
        while (i < n && chars[i] == ' ') i++;
        if (i == n) return 0;

        int sign = 1;
        if (chars[i] == '+' || chars[i] == '-') {
            sign = (chars[i] == '-') ? -1 : 1;
            i++;
        }

        int base = 0;
        while (i < n && chars[i] >= '0' && chars[i] <= '9') {
            if (base > Integer.MAX_VALUE / 10
                    || (base == Integer.MAX_VALUE / 10 && chars[i] > '7')) {
                if (sign == -1) {
                    return Integer.MIN_VALUE;
                } else {
                    return Integer.MAX_VALUE;
                }
            }
            base = base * 10 + (chars[i] - '0');
            i++;
        }
        return sign * base;
    }
}
