package alg_02_train_wyj._24_day_贪心算法一;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-04-12 10:51
 * @Version 1.0
 */
public class _02_322_CoinChange1 {
    public int coinChange(int[] coins, int amount) {
        Arrays.sort(coins);
        int rest = amount;
        int res = 0;
        for (int i = coins.length - 1; i >= 0; i--) {
            int curCount = rest / coins[i];
            rest -= curCount * coins[i];
            res += curCount;
            if (rest == 0) {
                return res;
            }
        }
        return -1;
    }
}
