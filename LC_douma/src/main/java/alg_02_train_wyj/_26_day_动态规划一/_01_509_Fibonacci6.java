package alg_02_train_wyj._26_day_动态规划一;

/**
 * @Author Wuyj
 * @DateTime 2023-06-03 13:46
 * @Version 1.0
 */
public class _01_509_Fibonacci6 {

    public int fib(int n) {
        if (n <= 1) return n;
        int prev = 0;
        int curr = 1;
        for (int i = 2; i <= n; i++) {
            int sum = curr + prev;
            prev = curr;
            curr = sum;
        }

        return curr;
    }
}
