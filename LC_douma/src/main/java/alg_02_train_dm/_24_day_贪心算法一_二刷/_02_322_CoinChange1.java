package alg_02_train_dm._24_day_贪心算法一_二刷;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-04-11 15:28
 * @Version 1.0
 */
public class _02_322_CoinChange1 {

    /*
        322. 零钱兑换
        给你一个整数数组 coins，表示不同面额的硬币；
        以及一个整数 amount，表示总金额。
        计算并返回可以凑成总金额所需的 最少 的硬币个数
        如果没有任何一种硬币组合能组成总金额，返回-1
        你可以认为每种硬币的数量是 无限 的。

        输入：coins = [1, 2, 5], amount = 11
        输出：3
        解释：11 = 5 + 5 + 1

        提示
        1 <= coins.length <= 12
        1 <= coins[i] <= 231 - 1
        0 <= amount <= 104

     */

    // KeyPoint 贪心 => 存在 bug
    // 贪心策略：每次拿面值最大的硬币
    // 局部最优解  =/=> 全局最优解
    public int coinChange(int[] coins, int amount) {
        // 升序排列
        Arrays.sort(coins);
        // 定义变量时，尽量保证变量名不相同，而 rest 和 res 就很容易混淆，代码容易出现 bug
        int rest = amount;
        // 最少硬币个数
        int res = 0;
        int n = coins.length;
        // 每次都拿最大硬币，从后往前遍历
        for (int i = n - 1; i >= 0; i--) {
            // 需要当前面值多少个
            int curCount = rest / coins[i];
            // 还剩多少
            rest -= curCount * coins[i];
            // 统计硬币个数
            res += curCount;
            // 剩余为 0，表示全都找到了
            if (rest == 0) {
                return res;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        test1(); // 3
        test2(); // -1 解答错误

        // KeyPoint 解题经验
        // 当贪心失效时，使用回溯来解
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
