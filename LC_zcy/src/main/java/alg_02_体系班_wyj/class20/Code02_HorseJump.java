package alg_02_体系班_wyj.class20;

import javax.lang.model.type.ErrorType;
import java.nio.file.Watchable;

/**
 * @Author Wuyj
 * @DateTime 2023-03-05 16:37
 * @Version 1.0
 */
public class Code02_HorseJump {
    public static int jump(int a, int b, int k) {
        return process(0, 0, k, a, b);
    }

    public static int process(int x, int y, int rest, int a, int b) {
        if (x < 0 || x > 9 || y < 0 || y > 8) {
            return 0;
        }

        if (rest == 0) {
            return (x == a && y == b) ? 1 : 0;
        }

        int ways = process(x + 1, y + 2, rest - 1, a, b);
        ways += process(x + 2, y + 1, rest - 1, a, b);
        ways += process(x + 2, y - 1, rest - 1, a, b);
        ways += process(x + 1, y - 2, rest - 1, a, b);

        ways += process(x - 1, y - 2, rest - 1, a, b);
        ways += process(x - 2, y - 1, rest - 1, a, b);
        ways += process(x - 2, y + 1, rest - 1, a, b);
        ways += process(x - 1, y + 2, rest - 1, a, b);

        return ways;
    }

    public static int dp(int a, int b, int k) {
        int[][][] dp = new int[10][9][k + 1];
        dp[a][b][0] = 1;
        for (int rest = 1; rest <= k; rest++) {
            for (int x = 0; x < 10; x++) {
                for (int y = 0; y < 9; y++) {
                    int ways = pick(dp, x + 1, y + 2, rest - 1);
                    ways += pick(dp, x + 2, y + 1, rest - 1);
                    ways += pick(dp, x + 2, y - 1, rest - 1);
                    ways += pick(dp, x + 1, y - 2, rest - 1);
                    ways += pick(dp, x - 1, y - 2, rest - 1);
                    ways += pick(dp, x - 2, y - 1, rest - 1);
                    ways += pick(dp, x - 2, y + 1, rest - 1);
                    ways += pick(dp, x - 1, y + 2, rest - 1);
                    dp[x][y][rest] = ways;
                }
            }
        }
        return dp[0][0][k];
    }

    public static int pick(int[][][] dp, int x, int y, int rest) {
        if (x < 0 || x > 9 || y < 0 || y > 8) {
            return 0;
        }
        return dp[x][y][rest];
    }

    public static void main(String[] args) {
        int x = 7;
        int y = 7;
        int step = 10;
        System.out.println(jump(x, y, step));
        System.out.println(dp(x, y, step));
    }
}
