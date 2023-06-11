package alg_02_体系班_zcy.class22;

/**
 * @Author Wuyj
 * @DateTime 2023-03-04 13:10
 * @Version 1.0
 */
public class Code01_KillMonster {

    /*
        给定3个参数,N,M,K
        怪兽有N滴血,等着英雄来砍自己
        英雄每一次打击,都会让怪兽流失[0~M]的血量
        到底流失多少？每一次在[0~M]上等概率的获得一个值
        求K次打击之后,英雄把怪兽砍死的概率
     */
    public static double right(int N, int M, int K) {
        if (N < 1 || M < 1 || K < 1) {
            return 0;
        }
        // 第一砍,流失范围[0,M],即M+1层展开 => 所有可能性:M^k
        long all = (long) Math.pow(M + 1, K);
        // kill死后,收集kill死情况数
        long kill = process(K, M, N);
        // 砍死概率 = kill / all
        // 因为求的是概率,所以使用double类型,若是int,则3/5必然为0
        return (double) ((double) kill / (double) all);
    }

    // 怪兽还剩hp点血
    // 每次的伤害在[0~M]范围上
    // 还有times次可以砍
    // 返回砍死的情况数
    public static long process(int times, int M, int hp) {
        // 砍到times=0次,不剪枝
        if (times == 0) {
            return hp <= 0 ? 1 : 0;
        }

        if (hp <= 0) {
            return (long) Math.pow(M + 1, times);
        }
        // 没有到times次,还能砍
        long ways = 0;
        // 遍历砍血范围M,依次尝试,0滴血,1滴血,2滴血...,获取所有砍死的情况数累加
        // dp格子存在枚举
        for (int i = 0; i <= M; i++) {
            ways += process(times - 1, M, hp - i);
        }
        return ways;
    }

    public static double dp1(int N, int M, int K) {
        if (N < 1 || M < 1 || K < 1) {
            return 0;
        }
        long all = (long) Math.pow(M + 1, K);
        // dp的行和列可以转换 => 将有明显的依赖关系times-1作为行,这样比较好分析(存疑
        // 一般位置依赖上一行的值,故先将上面行填好,再去填下面的行
        long[][] dp = new long[K + 1][N + 1];
        dp[0][0] = 1; // dp[0][..]都是0
        for (int times = 1; times <= K; times++) { // 从1行到K行
            dp[times][0] = (long) Math.pow(M + 1, times);
            for (int hp = 1; hp <= N; hp++) { // 每行
                long ways = 0;
                for (int i = 0; i <= M; i++) {
                    //
                    if (hp - i >= 0) {
                        ways += dp[times - 1][hp - i];
                    } else {
                        ways += (long) Math.pow(M + 1, times - 1);
                    }
                }
                dp[times][hp] = ways;
            }
        }
        long kill = dp[K][N];
        return (double) ((double) kill / (double) all);
    }

    public static double dp2(int N, int M, int K) {
        if (N < 1 || M < 1 || K < 1) {
            return 0;
        }
        long all = (long) Math.pow(M + 1, K);
        long[][] dp = new long[K + 1][N + 1];
        dp[0][0] = 1;
        for (int times = 1; times <= K; times++) {
            dp[times][0] = (long) Math.pow(M + 1, times);
            for (int hp = 1; hp <= N; hp++) {
                dp[times][hp] = dp[times][hp - 1] + dp[times - 1][hp];
                if (hp - 1 - M >= 0) {
                    dp[times][hp] -= dp[times - 1][hp - 1 - M];
                } else {
                    dp[times][hp] -= Math.pow(M + 1, times - 1);
                }
            }
        }
        long kill = dp[K][N];
        return (double) ((double) kill / (double) all);
    }

    public static void main(String[] args) {
        int NMax = 10;
        int MMax = 10;
        int KMax = 10;
        int testTime = 200;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int N = (int) (Math.random() * NMax);
            int M = (int) (Math.random() * MMax);
            int K = (int) (Math.random() * KMax);
            double ans1 = right(N, M, K);
            double ans2 = dp1(N, M, K);
            double ans3 = dp2(N, M, K);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("测试结束");
    }
}
