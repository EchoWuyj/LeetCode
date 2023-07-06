package alg_02_train_wyj._06_day_位运算;

/**
 * @Author Wuyj
 * @DateTime 2023-04-21 20:11
 * @Version 1.0
 */
public class _01_191_number_of_1_bits {
    public static int hammingWeight1(int n) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            if ((n & (1 << i)) != 0) {
                res++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(hammingWeight1(7));
    }

    public int hammingWeight2(int n) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            if ((n & 1) == 1) res++;
            n >>= 1;
        }
        return res;
    }

    public int hammingWeight(int n) {
        int res = 0;
        while (n != 0) {
            n = (n) & (n - 1);
            res++;
        }
        return res;
    }
}
