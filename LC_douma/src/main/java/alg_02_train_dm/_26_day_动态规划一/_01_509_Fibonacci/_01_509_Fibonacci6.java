package alg_02_train_dm._26_day_动态规划一._01_509_Fibonacci;

/**
 * @Author Wuyj
 * @DateTime 2023-06-03 13:36
 * @Version 1.0
 */
public class _01_509_Fibonacci6 {

    // 状态数组 => 空间压缩
    public int fib(int n) {
        if (n <= 1) return n;
        // 只存储前两个状态
        int prev = 0;
        int curr = 1;
        // 当前状态只和之前两个状态有关，只需要保存前两个状态即可，不需要数组存储所有的状态
        for (int i = 2; i <= n; i++) {
            int sum = prev + curr;
            // 注意赋值的先后顺序
            // 1.先将 cur 赋值给 prev
            // 2.再将 sum 赋值给 cur
            prev = curr;
            curr = sum;
        }
        // for 循环，边界条件，i = n，将 sum 赋值给 cur，将 cur 返回
        return curr;
    }
}
