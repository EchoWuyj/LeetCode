package alg_02_train_wyj._25_day_贪心算法二;

/**
 * @Author Wuyj
 * @DateTime 2023-04-13 23:40
 * @Version 1.0
 */
public class _03_738_monotone_increasing_digits {
    public int monotoneIncreasingDigits(int n) {
        char[] chars = String.valueOf(n).toCharArray();
        int i = 1;
        while (i < chars.length && chars[i - 1] <= chars[i]) i++;
        if (i < chars.length) {
            while (i > 0 && chars[i - 1] > chars[i]) {
                chars[i - 1]--;
                i--;
            }
            i++;
            while (i < chars.length) {
                chars[i++] = '9';
            }
            return Integer.parseInt(new String(chars));
        } else {
            return n;
        }
    }
}
