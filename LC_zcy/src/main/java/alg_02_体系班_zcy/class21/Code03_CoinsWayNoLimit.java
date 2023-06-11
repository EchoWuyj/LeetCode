package alg_02_体系班_zcy.class21;

/**
 * @Author Wuyj
 * @DateTime 2023-03-03 15:43
 * @Version 1.0
 */
public class Code03_CoinsWayNoLimit {
    /*
        arr是面值数组,其中的值都是正数且没有重复,再给定一个正数aim,
        每个值都认为是一种面值,且认为张数是无限的,返回组成aim的方法数

        例如:arr = {1,2},aim = 4
        方法如下:1+1+1+1,1+1+2,2+2 一共就3种方法,所以返回3
        [每张货币不一样,且有无数张]

        => 从左往右尝试模型
     */

    // KeyPoint 方法一:暴力递归
    public static int coinsWay(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        return process(arr, 0, aim);
    }

    // arr[index..]所有的面值,每一个面值都可以任意选择张数,组成正好rest这么多钱,方法数多少？
    public static int process(int[] arr, int index, int rest) {

        // if (rest < 0) 没有写这个边界条件是因为在for循环中的循环条件
        // zhang * arr[index] <= rest 已经对rest进行了控制,不会出现rest<0的情况
        // 换句换说:在递归调用之前已经保证了 rest - (zhang * arr[index]) >= 0

        if (index == arr.length) { // 没钱了
            return rest == 0 ? 1 : 0;
        }
        int ways = 0;
        // KeyPoint 从左往右尝试模型不一定只是限于包含于不包含的两种情况(二叉树)
        //      同样适用 => 从左往右,依次选择,从0到index,使用0张,1张..最大张数,即所有分支都走(多叉树)
        for (int zhang = 0; zhang * arr[index] <= rest; zhang++) {
            // 将所有的方法数进行累加
            ways += process(arr, index + 1, rest - (zhang * arr[index]));
        }
        return ways;
    }

    // KeyPoint 补充:缓存 => 存在问题
    public static int dp(int[] arr, int aim) {
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
        //  1 修改递归 => 缓存dp,不要遗漏 if 判断, if (index == arr.length)
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

    // KeyPoint 方法二:dp(严格表依赖)
    public static int dp1(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int N = arr.length;
        // 0~aim
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;
        // N行值已知,for循环直接从N-1行(N不用重复计算),从下往上填
        for (int index = N - 1; index >= 0; index--) {
            // KeyPoint rest是可以取等的,不可大意
            for (int rest = 0; rest <= aim; rest++) {
                int ways = 0;
                // KeyPoint 之前所有的题目,求一个dp格子仅仅是依赖周围有限个格子,因此时间复杂度都是O(1)
                //      而本题求一个dp格子是需要for循环的,需要搞出严格表结构dp后继续优化
                for (int zhang = 0; zhang * arr[index] <= rest; zhang++) {
                    ways += dp[index + 1][rest - (zhang * arr[index])];

                    // KeyPoint 严格表结构dp后继续优化 => 设法将for循环的枚举行为使用邻近位置代替
                    // 假设(index,rest)为(3,14),即index=3,根据for循环枚举出(3,14)所有依赖的位置
                    //                        (3,11)  (3,14)
                    //   (4,2)  (4,5)  (4,8)  (4,11)  (4,14)  => 求解 (3,14)=(4,2)+(4,5)+(4,8)+(4,11)+(4,14)
                    //    4张    3张    2张     1张     0张
                    //                                        =>    (3,11) = (4,2)+(4,5)+(4,8)+(4,11)
                    //                                        =>      (3,14)=(3,11)+(4,14)

                }
                dp[index][rest] = ways;
            }
        }
        return dp[0][aim];

        // 记忆化搜索 => 暴力所有过程存在dp表中,dp表不关心位置依赖,没有计算过,则直接计算;之前已经计算过,从dp数组中获取值
        // 严格表结构dp => 比记忆化搜索进一步梳理了表的依赖关系,从dp表的简单位置填到复杂位置,进而求解所有dp所有位置的过程

        // 记忆化搜索和严格表结构dp都是dp表有多少格子就计算多少格子,将格子计算完就结束了
        // 若单独一个格子没有枚举行为(for循环),即该格子只是依赖有限的位置,记忆化搜索和严格表结构dp一样的优秀
        // 若单独一个格子有枚举行为,则需要搞出严格表结构dp后继续优化 => 即再建立空间感后,设法将for循环的枚举行为使用邻近位置代替

    }

    //  KeyPoint 方法三:dp=>优化
    public static int dp2(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                // for循环中,zhang=0时,该等式恒成立,所以必然有该等式
                dp[index][rest] = dp[index + 1][rest];
                // rest剩余的钱大于等于index行的面值
                if (rest - arr[index] >= 0) {
                    dp[index][rest] += dp[index][rest - arr[index]];
                }
            }
        }
        return dp[0][aim];
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
