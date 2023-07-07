package alg_02_train_wyj._09_day_哈希查找;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author Wuyj
 * @DateTime 2023-04-15 14:14
 * @Version 1.0
 */
public class _02_888_fair_candy_swap {

    public int[] fairCandySwap(int[] A, int[] B) {
        int sumA = 0, sumB = 0;
        for (int a : A) {
            sumA += a;
        }

        for (int b : B) {
            sumB += b;
        }

        int delta = (sumA - sumB) / 2;

        Set<Integer> set = new HashSet<>();
        for (int a : A) set.add(a);

        int[] res = new int[2];

        for (int y : B) {
            int x = y + delta;
            if (set.contains(x)) {
                res[0] = x;
                res[1] = y;
            }
        }
        return res;
    }
}
