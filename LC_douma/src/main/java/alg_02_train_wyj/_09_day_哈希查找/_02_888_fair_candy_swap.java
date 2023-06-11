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
        int sumA = 0;
        for (int a : A) sumA += a;
        int sumB = 0;
        for (int b : B) sumB += b;

        int delta = (sumA - sumB) / 2;
        Set<Integer> set = new HashSet<>();
        for (int num : A) set.add(num);

        int[] ans = new int[2];
        for (int y : B) {
            int x = y + delta;
            if (set.contains(x)) {
                ans[0] = x;
                ans[1] = y;
            }
        }
        return ans;
    }
}
