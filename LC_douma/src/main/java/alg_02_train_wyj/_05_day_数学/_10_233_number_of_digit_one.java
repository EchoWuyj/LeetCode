package alg_02_train_wyj._05_day_数学;

/**
 * @Author Wuyj
 * @DateTime 2023-04-21 10:40
 * @Version 1.0
 */
public class _10_233_number_of_digit_one {

    public int countDigitOne1(int n) {
        int res = 0;
        int count = 0;
        for (int i = 1; i <= n; i++) {
            String str = String.valueOf(i);
            for (char c : str.toCharArray()) {
                if (c == '1') count++;
            }
        }
        return count;
    }

    public int countDigitOne(int n) {
        return -1;
    }
}
