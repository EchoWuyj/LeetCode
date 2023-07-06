package alg_02_train_wyj._06_day_位运算;

/**
 * @Author Wuyj
 * @DateTime 2023-04-23 12:36
 * @Version 1.0
 */
public class _11_201_bitwise_and_of_numbers_range {

    public int rangeBitwiseAnd1(int left, int right) {
        int res = left;
        for (int i = left + 1; i <= right; i++) {
            res ^= i;
        }
        return res;
    }

    public int rangeBitwiseAnd2(int left, int right) {
        int shift = 0;
        while (left < right) {
            left >>= 1;
            right >>= 1;
            shift++;
        }
        left <<= shift;
        return left;
    }

    public int rangeBitwiseAnd3(int left, int right) {
        while (left < right) {
            right = right & (right - 1);
        }
        return right;
    }
}
