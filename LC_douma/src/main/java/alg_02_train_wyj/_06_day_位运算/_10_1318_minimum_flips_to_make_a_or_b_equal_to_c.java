package alg_02_train_wyj._06_day_位运算;

/**
 * @Author Wuyj
 * @DateTime 2023-04-23 11:26
 * @Version 1.0
 */
public class _10_1318_minimum_flips_to_make_a_or_b_equal_to_c {

    public static int minFlips(int a, int b, int c) {
        int aOrb = a | b;
        int equal = aOrb ^ c;
        if (equal == 0) return 0;
        int res = 0;
        for (int i = 0; i < 32; i++) {
            int mask = 1 << i;
            if ((equal & mask) > 0) {
                if ((c & mask) == 0 && ((a & mask) == (b & mask))) {
                    res += 2;
                } else {
                    res++;
                }
            }
        }
        return res;
    }
}
