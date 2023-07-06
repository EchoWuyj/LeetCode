package alg_02_train_wyj._06_day_位运算;

/**
 * @Author Wuyj
 * @DateTime 2023-04-21 20:53
 * @Version 1.0
 */
public class _02_461_hamming_distance {
    public int hammingDistance(int x, int y) {
        int diff = x ^ y;
        int res = 0;
        while (diff != 0) {
            diff = (diff) & (diff - 1);
            res++;
        }
        return res;
    }
}
