package alg_02_train_dm._26_day_动态规划一_二刷._01_509_Fibonacci;

/**
 * @Author Wuyj
 * @DateTime 2023-06-03 13:08
 * @Version 1.0
 */
public class _01_509_Fibonacci4 {

    // 迭代(自底而上) => 经典动态规划解题步骤
    public int fib(int n) {
        if (n <= 1) return n;
        // 1. 定义数组
        // arr[i] 表示的是数字 i 的斐波那契数
        int[] arr = new int[n + 1];

        // 2. 初始化数组元素
        arr[0] = 0;
        arr[1] = 1;

        // 3. 计算数组的元素
        // 涉及数组 i 的坐标变换，即 i-1，i-2，特别注意避免数组越界
        for (int i = 2; i <= n; i++) {
            // 已知状态 推导 未知状态
            arr[i] = arr[i - 1] + arr[i - 2];
        }

        // 4. 返回最终结果
        return arr[n];
    }
}
