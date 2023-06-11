package alg_02_体系班_zcy.class20_DP_Done;

/**
 * @Author Wuyj
 * @DateTime 2023-03-03 11:02
 * @Version 1.0
 */
public class Code02_HorseJump {

    /*
        请同学们自行搜索或者想象一个象棋的棋盘,然后把整个棋盘放入第一象限,棋盘的最左下角是(0,0)位置
        那么整个棋盘就是横坐标上10条线,纵坐标上9条线(10*9棋盘)给你三个参数x,y,k返回"马"从(0,0)位置出发
        必须走k步,最后落在(x,y)上的方法数有多少种?(大公司笔试原题)

        注意:"马"在横坐标和纵坐标的交点上,不是在方格里面
     */

    // KeyPoint 方法一:暴力递归
    //      时间复杂度:走一步有8种可能性,一共k个位置,递归全展开即O(8^k)
    public static int jump(int a, int b, int k) {
        return process(0, 0, k, a, b);
    }

    // 当前来到的位置是(x,y)
    // 还剩下rest步需要跳
    // 跳完rest步,正好跳到(a,b)的方法数是多少？
    // KeyPoint 本质:样本对应模型
    public static int process(int x, int y, int rest, int a, int b) {
        // 边界问题考虑,如果越过了边界,其结果一定为0
        // 10*9棋盘,10行对应0~9,9列对应0~8
        if (x < 0 || x > 9 || y < 0 || y > 8) {
            return 0;
        }
        if (rest == 0) {
            // 如果此时走完了所有步,那么就要判断当的这个(x,y)位置是否是(a,b)位置,如果是则返回1
            return (x == a && y == b) ? 1 : 0;
        }

        // 分析可能性:以(x,y)为原点,以每个1*2对角线的位置为终点,每条路径都是一种可能性,旋转一周即有8种可能
        //           ways相加是将每种可能性相加,因为不确定后续具体是哪一种可能性

        // KeyPoint 注意:坐标系是以左下角(0,0)为原点,不是(x,y)为原点,
        //           坐标变换,关键看是水平还是垂直方向(先水平,再垂直),对应进行加减即可
        // rest需要-1,不要遗忘了
        int ways = process(x + 2, y + 1, rest - 1, a, b);
        ways += process(x + 1, y + 2, rest - 1, a, b);
        ways += process(x - 1, y + 2, rest - 1, a, b);
        ways += process(x - 2, y + 1, rest - 1, a, b);
        ways += process(x - 2, y - 1, rest - 1, a, b);
        ways += process(x - 1, y - 2, rest - 1, a, b);
        ways += process(x + 1, y - 2, rest - 1, a, b);
        ways += process(x + 2, y - 1, rest - 1, a, b);
        return ways;
    }

    // KeyPoint 方法二:dp
    // 注意:若3维dp若是简单依赖关系,则可以改成严格表依赖的dp,否则直接记忆化搜索
    //     时间复杂度:三维标规模:10*9*k,时间复杂度O(K)
    public static int dp(int a, int b, int k) {
        // x,y,k都是可变形参
        // 剩余步数0~k步,所以申请k+1
        int[][][] dp = new int[10][9][k + 1];

        // rest=0,建立第0层,同时x=a,y=b时,其值为1;
        dp[a][b][0] = 1;

        // 因为递归函数是三个可变参数a,b,k,所以建立一个三维表表格,根据x,y,z三维坐标系将其与之对应
        // dp[x][y][rest] -> dp[..][..][rest-1],故每一层都是依赖它的下一层,同一层之间是不相互依赖的
        // rest-1已经决定他们是不在同一层的,没有必要分析x,y的依赖关系了,所以可以从下(0层)往上(9层)填
        for (int rest = 1; rest <= k; rest++) {
            // 同层之间不相互依赖,直接两个for循环,枚举所有情况
            for (int x = 0; x < 10; x++) {
                for (int y = 0; y < 9; y++) {
                    // KeyPoint 最初在递归中越界会返回0,而在dp中越界就直接报错了,需要处理一下pick
                    int ways = pick(dp, x + 2, y + 1, rest - 1);
                    ways += pick(dp, x + 1, y + 2, rest - 1);
                    ways += pick(dp, x - 1, y + 2, rest - 1);
                    ways += pick(dp, x - 2, y + 1, rest - 1);
                    ways += pick(dp, x - 2, y - 1, rest - 1);
                    ways += pick(dp, x - 1, y - 2, rest - 1);
                    ways += pick(dp, x + 1, y - 2, rest - 1);
                    ways += pick(dp, x + 2, y - 1, rest - 1);
                    // 最后赋值操作不能忘了!
                    dp[x][y][rest] = ways;
                }
            }
        }
        // 返回值
        return dp[0][0][k];
    }

    // 在dp表中,得到dp[i][j][step]的值,但如果(i,j)位置越界的话,返回0；
    public static int pick(int[][][] dp, int x, int y, int rest) {
        if (x < 0 || x > 9 || y < 0 || y > 8) {
            // 越界返回0,没有越界直接获取
            return 0;
        }
        // 正常情况返回值
        return dp[x][y][rest];
    }

    public static void main(String[] args) {
        int x = 7;
        int y = 7;
        int step = 10;
        System.out.println(dp(x, y, step));
        System.out.println(jump(x, y, step));
    }
}
