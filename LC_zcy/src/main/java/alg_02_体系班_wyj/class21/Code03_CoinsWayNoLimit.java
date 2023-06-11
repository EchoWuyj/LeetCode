package alg_02_体系班_wyj.class21;

/**
 * @Author Wuyj
 * @DateTime 2023-03-07 18:46
 * @Version 1.0
 */
public class Code03_CoinsWayNoLimit {

    // 暴力递归
    public static int coinsWay(int[] arr, int aim) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        return process(arr, 0, aim);
    }

    public static int process(int[] arr, int index, int rest) {
        if (index == arr.length) {
            return rest == 0 ? 1 : 0;
        }
        int ways = 0;
        for (int zhang = 0; zhang * arr[index] <= rest; zhang++) {
            ways += process(arr, index + 1, rest - zhang * arr[index]);
        }
        return ways;
    }

    // KeyPoint 补充:缓存 => 存在问题
    public static int dp1(int[] arr, int aim) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int n = arr.length;
        int m = aim;
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                dp[i][j] = -1;
            }
        }
        process1(arr, 0, aim, dp);
        return dp[0][aim];
    }

    public static int process1(int[] arr, int index, int rest, int[][] dp) {
        if (dp[index][rest] != -1) {
            return dp[index][rest];
        }

        // KeyPoint 注意点
        //  1 修改递归 => 缓存dp,不要遗漏if判断, if (index == arr.length)
        //  2 因为定义了结果变量ways,原来多个if逻辑就有问题了i(递归版本存在return可以结束方法)
        //    这里需要将原来if结构修改成if else结构,这样所有分支只会走一个
        int ways = 0;
        if (index == arr.length) {
            return rest == 0 ? 1 : 0;
            //  rest == 0 ? 1 : 0; 需要将所有的return语句替换成dp表格赋值操作
            //  dp[index][0] = 1;
        } else {
            for (int zhang = 0; zhang * arr[index] <= rest; zhang++) {
                ways = process1(arr, index + 1, rest - zhang * arr[index], dp);
            }
            dp[index][rest] = ways;
        }
        return ways;
    }

    public static int dp2(int[] arr, int aim) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int n = arr.length;
        int m = aim;
        int[][] dp = new int[n + 1][m + 1];
        dp[n][0] = 1;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= m; j++) {
                int ways = 0;
                for (int zhang = 0; zhang * arr[i] <= j; zhang++) {
                    ways += process(arr, i + 1, j - zhang * arr[i]);
                }
                dp[i][j] = ways;
            }
        }
        return -1;
    }

    // for test
    public static int[] randomArray(int maxLen, int maxValue) {
        int N = (int) (Math.random() * maxLen);
        int[] arr = new int[N];
        boolean[] has = new boolean[maxValue + 1];
        for (int i = 0; i < N; i++) {
            do {
                arr[i] = (int) (Math.random() * maxValue) + 1;
            } while (has[arr[i]]);
            has[arr[i]] = true;
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
        int maxLen = 10;
        int maxValue = 30;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(maxLen, maxValue);
            int aim = (int) (Math.random() * maxValue);
            int ans1 = coinsWay(arr, aim);
            int ans2 = dp1(arr, aim);
            int ans3 = dp2(arr, aim);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(aim);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(ans3);
                break;
            }
        }
        System.out.println("测试结束");
    }
}
