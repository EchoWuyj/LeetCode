package alg_02_体系班_zcy.class18_DP_Done;

public class Code01_RobotWalk {

    /*
         动态规划:将重复调用过程,在计算过一次之后,将计算结果记录下来
                 下次再遇到重复过程直接调用即可

         动态规划从尝试方法(暴力递归)入手,后续再去将暴力递归修改成动态规划
          => 动态规划是结果不是原因
          => 暴力递归范围大于动态规划范围

         暴力递归设计 => 契约设计:基于契约精神,向下绝对信任,不要追究是怎么来的
                                 向上忠实兑现承诺,不要管是怎么被使用的

         在面试场上遇到所有的动态规划,都是可以通过这种方式搞出来

     */


    /*

         假设有排成一行的N个位置记为1~N,N一定大于或等于2
         开始时机器人在其中的start位置上(start一定是1~N中的一个)
         如果机器人来到1位置,那么下一步只能往右来到2位置
         如果机器人来到N位置,那么下一步只能往左来到N-1位置
         如果机器人来到中间位置,那么下一步可以往左走或者往右走
         规定机器人必须走K步,最终能来到aim位置(aim也是1~N中的一个)的方法有多少种
         给定四个参数N,start,K,aim,返回方法数

          | 1 | 2 | 3 | ...  | aim | ... | N-1 | | N |
              start
              k步
             返回方法数
     */

    public static int ways1(int N, int start, int aim, int K) {
        if (N < 2 || start < 1 || start > N || aim < 1 || aim > N || K < 1) {
            // 返回-1,表示无效条件
            return -1;
        }
        return process1(start, K, aim, N);
    }

    /**
     * KeyPoint 方法一:尝试方法(暴力递归) => 符合自然智慧
     * 功能:机器人从cur出发,走过rest步之后,最终停在aim的方法数是多少？
     *
     * @param cur  当前来到的位置是cur
     * @param rest 还有rest步需要去走
     * @param aim  最终的目标是aim
     * @param N    有哪些位置？1~N
     * @return 达标的方法数
     */
    public static int process1(int cur, int rest, int aim, int N) {

        // 如果已经不需要走了,走完了！
        if (rest == 0) {
            // 最终在aim上返回1种方法,否则返回0
            return cur == aim ? 1 : 0;
        }

        // KeyPoint if条件判断代码之后,剩余代码隐含的逻辑:rest>0,还有步数要走

        // cur来到边界的情况
        // 1) 1->2
        if (cur == 1) {
            return process1(2, rest - 1, aim, N);
        }

        // 2) N->N-1
        if (cur == N) {
            return process1(N - 1, rest - 1, aim, N);
        }

        // 中间位置(左右尝试->总方法数)
        // 当前位置cur,在2-N-1中任意一个位置,左走(cur-1)+右走(cur+1)
        return process1(cur - 1, rest - 1, aim, N) + process1(cur + 1, rest - 1, aim, N);

        /*
            int ways = 0;
            if (cur == 1) {
                ways += process(2, rest - 1, aim, N);
            }

            不能这样写,因为并不需要将其进行累加,cur只是在1-N中一个位置,在此基础上走过rest步之后,最终停在aim的方法数
            if每个情况都是相互独立的,直接调用process1方法,再去return即可,根据递归含义来写代码,和别的dp代码搞混淆了
         */

    }

    /**
     * KeyPoint 方法二:暴力递归的优化 => 记忆化搜索dp
     *
     * 优化之前,思考什么样的暴力递归可以优化? 出现重复解的暴力递归可以优化
     * 若一个暴力递归每个子状态都是不一样的,此时该暴力递归不用优化也不能优化,同时动态规划是无法优化这样的暴力递归过程
     *
     * 递归优化:从顶向下动态规划(记忆化搜索) 本质:缓存方法
     * => 不关心位置依赖,没有计算过,则直接计算;之前已经计算过,从dp数组中获取值
     * => 所有重复过程只是计算一次,后续再遇到重复过程直接从dp数组中获取值
     *
     * 思考递归返回值由谁决定
     * => 因为N和aim一直都没有变化,故递归返回值是由cur和rest唯一决定的
     * => 即从当前位置cur触发,在剩余步数rest要走,最终停在aim,该方法数是不变的(存在重复操作)
     *
     * @param N
     * @param start
     * @param aim
     * @param K
     * @return
     */
    public static int ways2(int N, int start, int aim, int K) {
        if (N < 2 || start < 1 || start > N || aim < 1 || aim > N || K < 1) {
            return -1;
        }
        // cur范围:1~N,rest范围:0~K
        // => 设计DP表,保证每组(cur,rest)返回值都能在dp保存
        // => 行和列选择最好和递归函数cur,rest一致
        // 1~N用int[N]即可,for循环从i=1开始,不影响最终结果
        int[][] dp = new int[N + 1][K + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= K; j++) {
                dp[i][j] = -1;
            }
        }
        // dp就是缓存表
        // dp[cur][rest]=-1 -> process1(cur,rest)之前没算过
        // dp[cur][rest] != -1 -> process1(cur,rest)之前算过,返回值保存到dp[cur][rest]
        return process2(start, K, aim, N, dp);
    }

    // cur范围:1~N
    // rest范围:0~K
    public static int process2(int cur, int rest, int aim, int N, int[][] dp) {

        // KeyPoint 之前已经计算过,直接返回dp值,缓存法必须添加的判断
        if (dp[cur][rest] != -1) {
            return dp[cur][rest];
        }
        // 之前没算过后逻辑

        // 定义结果变量,用来存结果
        int ans;

        // KeyPoint 注意点
        //  因为定义了结果变量ans,原来多个if逻辑就有问题了
        //  需要将原来多个if修改成if else结构,这样所有分支只会走一个

        if (rest == 0) {
            ans = cur == aim ? 1 : 0;
        } else if (cur == 1) {
            // KeyPoint 注意点
            //  缓存法,中间还是存在递归调用,只有严格dp才是没有递归调用
            //  调用过程需要加dp表,修改方法名process2
            ans = process2(2, rest - 1, aim, N, dp);
        } else if (cur == N) {
            ans = process2(N - 1, rest - 1, aim, N, dp);
        } else {
            ans = process2(cur - 1, rest - 1, aim, N, dp)
                    + process2(cur + 1, rest - 1, aim, N, dp);
        }

        // 返回之前将其保存到缓存中
        dp[cur][rest] = ans;
        return ans;
    }

    // KeyPoint 方法三:严格表依赖dp
    // 暴力递归 => dp 步骤
    //   1)将dp表格列出来,先根据递归边界确定dp值(假定N,K为具体的值)
    //   2)根据主函数的形参start和k明确最终要求的dp值
    //   3)分析一般位置的依赖关系,看暴力递归中的代码
    //   4)根据已经递归边界值+依赖关系=>未知位置dp值
    public static int ways3(int N, int start, int aim, int K) {
        if (N < 2 || start < 1 || start > N || aim < 1 || aim > N || K < 1) {
            return -1;
        }

        // 明确行列含义 => int[cur][rest]
        int[][] dp = new int[N + 1][K + 1];

//        if (rest == 0) {
//            return cur == aim ? 1 : 0;
//        }

        // rest==0,cur==aim,此时dp=1,其余位置都是0,即dp[...][0]=0
        // 恰好java初始dp本身就是0,故只需要填写dp[aim][0]即可
        dp[aim][0] = 1;

//        if (cur == 1) {
//            return process1(2, rest - 1, aim, N);
//        }
//        依赖关系:左下角 ↙

//        if (cur == N) {
//            return process1(N - 1, rest - 1, aim, N);
//        }
//        依赖关系:左上交 ↖

//        return process1(cur - 1, rest - 1, aim, N)
//                + process1(cur + 1, rest - 1, aim, N);

//        针对不确定的(cur,rest),可以自己假设一个(cur,rest)来确定依赖关系
//        依赖关系:左下角↙ 和 左上角↖

        // 以上分析为了确定dp空间依赖关系,从而明确如何填表
        // 填表顺序原则:已知位置 =>未知位置
        // 0列已经确定,所以从1列开始填表,整体填表顺序,从左往右列优先
        for (int rest = 1; rest <= K; rest++) { // 列
            // 1行单独处理,dp依赖关系和递归代码中保持一致
            dp[1][rest] = dp[2][rest - 1];

            // 2-(N-1)行,既左上又有左下
            for (int cur = 2; cur < N; cur++) { // 行
                dp[cur][rest] = dp[cur - 1][rest - 1] + dp[cur + 1][rest - 1];
            }

            // N行单独处理
            dp[N][rest] = dp[N - 1][rest - 1];
        }

        // 最终所求值
        return dp[start][K];
    }

    public static void main(String[] args) {
        System.out.println(ways1(5, 2, 4, 6));
        System.out.println(ways2(5, 2, 4, 6));
        System.out.println(ways3(5, 2, 4, 6));
    }
}
