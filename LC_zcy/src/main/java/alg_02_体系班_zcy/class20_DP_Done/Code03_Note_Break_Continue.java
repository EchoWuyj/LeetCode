package alg_02_体系班_zcy.class20_DP_Done;

/**
 * @Author Wuyj
 * @DateTime 2023-03-06 17:10
 * @Version 1.0
 */
public class Code03_Note_Break_Continue {
    /*
        双层 for 循环，在内层循环中当满足某些条件时使用了 break，只结束内层循环(就近原则)

        continue 语句的作用：跳过本次循环体中剩下尚未执行的语句，立即进行下一次的循环条件判定
                 可以理解为只是中止(跳过)本次循环，接着开始下一次循环。

        continue 在 dfs 中用于剪枝


     */

    public void test1() {
        for (int i = 0; i < 3; i++) {
            System.out.println("第" + i + "次外循环");
            for (int j = 0; j < 3; j++) {
                if (j == 1) {
                    System.out.println("中断");
                    // 当 break 用在内部循环中，遇到 break 时，会跳出内部循环，进行下一次外部循环.
                    break;
                }

                System.out.println("第" + j + "次内循环");
            }
        }
    }

    public void test2() {
        for (int i = 0; i < 3; i++) {
            System.out.println("第" + i + "次外循环");
            for (int j = 0; j < 3; j++) {
                if (j == 1) {
                    System.out.println("跳过");
                    // 当 continue 用在内部循环中，遇到 continue 时，会跳出当前内部循环，进行下一次内部循环.
                    continue;
                }

                System.out.println("第" + j + "次内循环");
            }
        }
    }

    public void test3() {
        int[] coins = new int[2];
        int amount = 10;
        int minCoins = 0;

        for (int i = 0; i < coins.length; i++) {
            // for 循环里，若条件不满足，剪枝，不执行后面代码段，执行下轮循环
            if (amount - coins[i] < 0) continue;
            int subMinCoins = dfs(amount - coins[i], coins);
            if (subMinCoins == -1) continue;
            minCoins = Math.min(minCoins, subMinCoins + 1);
        }
    }

    public int dfs(int amount, int[] coins) {
        return -1;
    }
}
