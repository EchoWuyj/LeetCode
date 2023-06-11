package alg_02_train_wyj._21_day_综合应用二;

/**
 * @Author Wuyj
 * @DateTime 2023-05-26 15:49
 * @Version 1.0
 */
public class _04_258_add_digits {

    public int addDigits1(int num) {
        while (num >= 10) {
            num = totalSum(num);
        }
        return num;
    }

    public int totalSum(int num) {
        int total = 0;
        while (num != 0) {
            total += num % 10;
            num /= 10;
        }
        return total;
    }
}
