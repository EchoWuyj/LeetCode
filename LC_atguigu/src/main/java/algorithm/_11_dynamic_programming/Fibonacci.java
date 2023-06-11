package algorithm._11_dynamic_programming;

/**
 * @Author Wuyj
 * @DateTime 2022-03-26 20:18
 * @Version 1.0
 */
public class Fibonacci {
    //方法一:利用数学递推式,直接递归
    public int fib01(int n) {
        //基准情况
        if (n == 0) return 0;
        if (n == 1) return 1;

        //递归调用
        return fib01(n - 2) + fib01(n - 1);
    }

    //方法二:动态规划
    public int fib02(int n) {
        //返回基准情况
        if (n == 1 || n == 2) return 1;

        //定义一个状态数组,保存就是fib(n)的计算结果
        int[] dp = new int[n];

        //fib(1)和fib(2) 初始条件
        dp[0] = dp[1] = 1;

        //状态转移递推
        for (int i = 2; i < n; i++) {
            dp[i] = dp[i - 2] + dp[i - 1];
        }

        return dp[n - 1];
    }

    //方法三:动态规划空间优化
    public int fib03(int n) {
        //返回基准情况
        if (n == 1 || n == 2) return 1;

        //定义两个临时变量,保存前两个状态
        //pre2=fib(1) pre1=fib(2)
        int pre2 = 1, pre1 = 1;

        for (int i = 2; i < n; i++) {
            //定义一个临时变量,保存当前的状态,并且通过迭代计算实现
            int cur = pre1 + pre2;  // cur:f(3)=f(2)+f(1)  下一轮 f(4)=f(3)+f(2)
            pre2 = pre1; // pre2=f(2)
            pre1 = cur;  // pre1=f(3)
        }
        return pre1;
    }

    public static void main(String[] args) {
        Fibonacci fibonacci = new Fibonacci();
        System.out.println(fibonacci.fib03(5));
    }
}
