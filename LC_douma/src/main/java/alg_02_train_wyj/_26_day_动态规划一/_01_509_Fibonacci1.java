package alg_02_train_wyj._26_day_动态规划一;

/**
 * @Author Wuyj
 * @DateTime 2023-06-03 11:54
 * @Version 1.0
 */
public class _01_509_Fibonacci1 {

    public int fib(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        return fib(n - 1) + fib(n - 2);
    }

    public int fib1(int n) {
        return dfs(n);
    }

    public int dfs(int n) {
        if (n == 1) return 1;
        if (n == 0) return 0;

        int leftFib = dfs(n - 1);
        int rightFib = dfs(n - 2);
        return leftFib + rightFib;
    }
}
