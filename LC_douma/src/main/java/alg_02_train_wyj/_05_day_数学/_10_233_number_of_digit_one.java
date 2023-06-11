package alg_02_train_wyj._05_day_数学;

/**
 * @Author Wuyj
 * @DateTime 2023-04-21 10:40
 * @Version 1.0
 */
public class _10_233_number_of_digit_one {

    public int countDigitOne1(int n) {
        int res = 0;
        for (int i = 1; i <= n; i++) {
            String str = String.valueOf(i);
            for (char c : str.toCharArray()) {
                if (c == '1') res++;
            }
        }
        return res;
    }

    public int countDigitOne(int n) {
        int count = 0;
        for (long i = 1; i <= n; i *= 10) {
            long divider = i * 10;
            count += (n / divider) * i + Math.min(Math.max(n % divider - i + 1, 0L), i);
        }
        return count;
    }
}
