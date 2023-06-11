package alg_02_train_wyj._26_day_动态规划一;

/**
 * @Author Wuyj
 * @DateTime 2023-06-03 13:11
 * @Version 1.0
 */
public class _01_509_Fibonacci4 {

    public int fib(int n) {
        if (n <= 1) return n;
        int[] arr = new int[n + 1];

        arr[0] = 0;
        arr[1] = 1;
        for (int i = 2; i <= n; i++) {
            arr[i] = arr[i - 1] + arr[i - 2];
        }
        return arr[n];
    }
}
