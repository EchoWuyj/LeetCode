package algorithm._11_dynamic_programming;

/**
 * @Author Wuyj
 * @DateTime 2022-03-28 21:19
 * @Version 1.0
 */
public class LeetCode_70_ClimbStairs {
    //方法一:动态规划
    public int climbStairs01(int n) {
        //定义两个临时变量,保存前两个状态
        //pre2=f(0),pre1=f(1)从最底层开始递推
        int pre2 = 1, pre1 = 1;
        int cur;

        for (int i = 1; i < n; i++) {
            //定义一个临时变量,保存当前的状态
            /*
            加深对递推公式的理解
            1       1       2       3
            f(0)    f(1)    f(2)    f(3)
            pre2    pre1    cur  -> f(0) + f(1) = (2)
                    pre2    pre1    cur -> f(1) + f(2) = (3)
             */
            cur = pre1 + pre2;
            pre2 = pre1;
            pre1 = cur;
        }
        return pre1;
    }

    //方法二:数学公式法
    public int climbStairs02(int n) {
        double sqrt_5 = Math.sqrt(5);
        double fib = (Math.pow((1 + sqrt_5) / 2, n + 1) - (Math.pow((1 - sqrt_5) / 2, n + 1))) / sqrt_5;
        //返回值需要强制类型转换
        return (int) fib;
    }

    public static void main(String[] args) {
        LeetCode_70_ClimbStairs climbStairs = new LeetCode_70_ClimbStairs();
        System.out.println(climbStairs.climbStairs02(1));
    }
}
