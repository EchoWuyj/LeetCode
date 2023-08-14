package alg_02_train_wyj._25_day_贪心算法二;

/**
 * @Author Wuyj
 * @DateTime 2023-04-13 23:40
 * @Version 1.0
 */
public class _03_738_monotone_increasing_digits {
    public int monotoneIncreasingDigits(int n) {
        char[] charsN = String.valueOf(n).toCharArray();
        int i = 1;
        int len = charsN.length;
        while (i < len && charsN[i - 1] <= charsN[i]) i++;
        if (i < len) {
            while (i > 0 && charsN[i - 1] > charsN[i]) {
                charsN[i-1] -= 1;
                i--;
            }
            i++;
            while (i < len) {
                charsN[i] = '9';
                i++;
            }
            return Integer.parseInt(new String(charsN));
        } else {
            return n;
        }
    }
}
