package alg_02_体系班_zcy.class21;

/**
 * @Author Wuyj
 * @DateTime 2023-03-03 15:43
 * @Version 1.0
 */
public class Code02_CoinsWayEveryPaperDifferent {
    /*
        arr是货币数组,其中的值都是正数,每个值都认为是一张货币
        即便是值相同的货币也认为每一张都是不同的,再给定一个正数aim,返回组成aim的方法数

        例如：arr = {1,1,1},aim = 2
        第0个和第1个能组成2
        第1个和第2个能组成2
        第0个和第2个能组成2
        一共就3种方法,所以返回3

        [即使面值相同,每张货币也是不一样的,且只有一张]

        => 从左往右的尝试模型
     */
    public static int coinWays(int[] arr, int aim) {
        return process(arr, 0, aim);
    }

    // arr[index....] 组成正好rest这么多的钱,有几种方法
    public static int process(int[] arr, int index, int rest) {
        if (rest < 0) {
            return 0;
        }
        // index越界且rest=0,才是有效解
        if (index == arr.length) { // 没钱了！
            // KeyPoint ==是否相等,=是赋值
            //      => = 相等 = 等价 A == B
            //      => A <- B 等价 A = B
            return rest == 0 ? 1 : 0;
        } else {

            // KeyPoint 不要 + 要 => 组成的方法数(二叉树)
            return process(arr, index + 1, rest) + process(arr, index + 1, rest - arr[index]);
        }
    }

    public static int dp(int[] arr, int aim) {
        if (aim == 0) {
            return 1;
        }
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;
        // 行优先,从下往上,填表
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                // KeyPoint 递归中 if (rest < 0)  return 0; 也是需要在dp中体现的,不能直接将
                //      if 判断逻辑直接省略了,体现在dp中,则是需要判断dp中索引下标是否越界
                // KeyPoint 凡是复杂的逻辑运算都是需要使用(),显示标记其运算逻辑
                dp[index][rest] = dp[index + 1][rest] + (rest - arr[index] >= 0 ? dp[index + 1][rest - arr[index]] : 0);
            }
        }
        return dp[0][aim];
    }

    // for test
    public static int[] randomArray(int maxLen, int maxValue) {
        int N = (int) (Math.random() * maxLen);
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = (int) (Math.random() * maxValue) + 1;
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int maxLen = 20;
        int maxValue = 30;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(maxLen, maxValue);
            int aim = (int) (Math.random() * maxValue);
            int ans1 = coinWays(arr, aim);
            int ans2 = dp(arr, aim);
            if (ans1 != ans2) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(aim);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("测试结束");
    }
}
