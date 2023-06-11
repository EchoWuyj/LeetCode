package alg_02_体系班_wyj.class18;

import javax.lang.model.element.AnnotationMirror;

/**
 * @Author Wuyj
 * @DateTime 2023-03-04 13:56
 * @Version 1.0
 */
public class Code01_RobotWalk_2T {

    public static int ways1(int N, int start, int aim, int K) {
        if (start < 1 || start > N || aim < 1 || aim > N || K < 1) {
            return -1;
        }
        return process(start, K, aim, N);
    }

    public static int process(int cur, int rest, int aim, int N) {

        if (rest == 0) {
            return cur == aim ? 1 : 0;
        }
        if (cur == 1) {
            return process(2, rest - 1, aim, N);
        }
        if (cur == N) {
            return process(N - 1, rest - 1, aim, N);
        }

        return process(cur - 1, rest - 1, aim, N)
                + process(cur + 1, rest - 1, aim, N);
    }

    public static int ways2(int N, int start, int aim, int K) {
        if (start < 1 || start > N || aim < 1 || aim > N || K < 1) {
            return -1;
        }
        int[][] dp = new int[N + 1][K + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= K; j++) {
                dp[i][j] = -1;
            }
        }
        return process1(start, K, aim, N, dp);
    }

    public static int process1(int cur, int rest, int aim, int N, int[][] dp) {
        if (dp[cur][rest] != -1) {
            return dp[cur][rest];
        }
        int ans = 0;
        if (rest == 0) {
            ans = cur == aim ? 1 : 0;
        } else if (cur == 1) {
            ans = process1(2, rest - 1, aim, N, dp);
        } else if (cur == N) {
            ans = process1(N - 1, rest - 1, aim, N, dp);
        } else {
            ans = process1(cur - 1, rest - 1, aim, N, dp)
                    + process1(cur + 1, rest - 1, aim, N, dp);
        }
        dp[cur][rest] = ans;
        return ans;
    }

    public static int ways3(int N, int start, int aim, int K) {
        if (N < 2 || start < 1 || start > N || aim < 1 || aim > N || K < 1) {
            return -1;
        }
        // int[cur][rest]
        int[][] dp = new int[N + 1][K + 1];
        dp[aim][0] = 1;
        for (int rest = 1; rest <= K; rest++) {
            dp[1][rest] = dp[2][rest - 1];
            for (int cur = 2; cur < N; cur++) {
                dp[cur][rest] = dp[cur - 1][rest - 1] + dp[cur + 1][rest - 1];
            }
            dp[N][rest] = dp[N - 1][rest - 1];
        }
        return dp[start][K];
    }

    public static void main(String[] args) {
        System.out.println(ways1(5, 2, 4, 6));
        System.out.println(ways2(5, 2, 4, 6));
        System.out.println(ways3(5, 2, 4, 6));
    }
}
