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
        int n = coins.length;
        for (int i = n - 1; i >= 0; i--) {
            int curCount = rest / coins[i];
            rest -= curCount * coins[i];
            res += curCount;
            if (rest == 0) return res;
        }
        return -1;
    }

    public static void main(String[] args) {
        test1(); // 3
        test2(); // -1 解答错误，当贪心失效时，使用回溯
    }

    private static void test1() {
        int[] c = {1, 2, 5};
        System.out.println(new _02_322_CoinChange1().coinChange(c, 11));
    }

    private static void test2() {
        int[] c = {3, 5};
        System.out.println(new _02_322_CoinChange1().coinChange(c, 11));
    }
}
