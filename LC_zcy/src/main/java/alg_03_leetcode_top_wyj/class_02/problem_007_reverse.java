package alg_03_leetcode_top_wyj.class_02;

/**
 * @Author Wuyj
 * @DateTime 2023-02-20 18:14
 * @Version 1.0
 */
public class problem_007_reverse {
    public int reverse(int x) {
        boolean neg = ((x >> 31) & 1) == 1;
        x = neg ? x : -x;
        int res = 0;

        int minq = Integer.MIN_VALUE / 10;
        int minr = Integer.MIN_VALUE % 10;

        while (x != 0) {
            // 这里不是x,而是res
            if (res < minq || res == minq && (x % 10) < minr) {
                return 0;
            }

            res = res * 10 + x % 10;
            x /= 10;
        }
        return res = neg ? res : -res;
    }
}
