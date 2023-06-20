package alg_02_train_dm._26_day_动态规划一_2刷._01_509_Fibonacci;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-06-03 12:29
 * @Version 1.0
 */
public class _01_509_Fibonacci2 {
    // 数据规模不清楚，不能使用数组，只能使用 Map
    private Map<Integer, Integer> map;

    public int fib(int n) {
        map = new HashMap<>();
        return dfs(n);
    }

    // 记忆化搜索 (自顶向下)
    // 时间复杂度：O(n)
    private int dfs(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        // 先从 Map 中检索子问题的解，如果已经计算，则直接返回即可
        // 通过 Map 消除重复子问题，提高算法性能，降低时间复杂度
        if (map.containsKey(n)) {
            return map.get(n);
        }
        int leftFib = dfs(n - 1);
        int rightFib = dfs(n - 2);
        // 将计算好的结果放入到 Map 中，编译后续检索
        map.put(n, leftFib + rightFib);

        return leftFib + rightFib;
    }
}
