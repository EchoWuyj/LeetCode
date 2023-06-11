package alg_02_train_wyj._26_day_动态规划一;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-06-03 12:46
 * @Version 1.0
 */
public class _01_509_Fibonacci2 {

    private Map<Integer, Integer> map;

    public int fib(int n) {
        map = new HashMap<>();
        return dfs(n);
    }

    public int dfs(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;

        if (map.containsKey(n)) {
            return map.get(n);
        }

        int leftFib = dfs(n - 1);
        int rightFib = dfs(n - 2);
        map.put(n, leftFib + rightFib);
        return leftFib + rightFib;
    }
}
