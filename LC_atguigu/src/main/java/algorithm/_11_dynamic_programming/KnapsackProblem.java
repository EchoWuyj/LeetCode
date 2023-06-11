package algorithm._11_dynamic_programming;

/**
 * @Author Wuyj
 * @DateTime 2022-03-27 15:04
 * @Version 1.0
 */
public class KnapsackProblem {
    //动态规划实现
    public int maxValue01(int capacity, int[] weights, int[] values) {
        int n = weights.length;

        //定义状态,因为0<=i<=N,0<=j<=W限制,所以定义数组需要加1,capacity表示容量
        int[][] dp = new int[n + 1][capacity + 1];

        //遍历所有子问题,依次计算状态
        //初始没有定义即默认初始值都是为0,同时第0行值本来就应该为0,所以第0行不用去处理
        for (int i = 1; i <= n; i++) { //行号从第1行处理
            for (int j = 0; j <= capacity; j++) {
                //判断当前的背包容量j是否能放下物品i
                //物品i是从0开始的,范围是0<=i<=N,但是weights数组的索引是从0开始的,所以i需要减1
                if (j >= weights[i - 1]) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weights[i - 1]] + values[i - 1]);
                } else {
                    //放不下的情况,即放物品i时上一行的dp状态
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n][capacity];
    }

    //动态规划空间优化
    //优化最重要的点:看状态转移
    public int maxValue02(int capacity, int[] weights, int[] values) {
        int n = weights.length;

        //定义状态,只保存一行的数据,和行号没有关系,所以保留的是capacity+1
        //滚动数组,即数组不停地滚动,代表当前行的上一行,从而保存所有的状态即可
        //因为接下做处理时,只是依赖于之前的一行,再往前的数据没有用
        int[] dp = new int[capacity + 1];

        //遍历所有的子问题,依次计算状态
        for (int i = 1; i <= n; i++) {
            //使用逆向遍历,从后往前进行比较
            for (int j = capacity; j > 0; j--) {
                //判断当前的背包容量j是否能放下物品i
                if (j >= weights[i - 1]) {
                    //此时数组只有一维,所以只需要保留dp[j]即可
                    dp[j] = Math.max(dp[j], dp[j - weights[i - 1]] + values[i - 1]);
                }
            }
        }
        return dp[capacity];
    }

    public static void main(String[] args) {
        int W = 150;
        int[] w = {35, 30, 60, 50, 40, 10, 25};
        int[] v = {10, 40, 30, 50, 35, 40, 30};

        int[] w2 = {25, 20, 10};
        int[] v2 = {28, 20, 10};
        KnapsackProblem knapsackProblem = new KnapsackProblem();

        System.out.println(knapsackProblem.maxValue01(W, w, v)); //170
        System.out.println(knapsackProblem.maxValue01(30, w2, v2)); //30
    }
}
