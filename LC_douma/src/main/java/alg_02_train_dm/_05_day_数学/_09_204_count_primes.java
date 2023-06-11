package alg_02_train_dm._05_day_数学;

/**
 * @Author Wuyj
 * @DateTime 2023-04-20 20:42
 * @Version 1.0
 */
public class _09_204_count_primes {

    /*
        204. 计数质数
        给定整数 n，返回 所有小于非负整数 n 的质数的数量

        质数是指在大于1的自然数中，除了1和它本身以外不再有其他因数的自然数。

        提示：
        0 <= n <= 5 * 10^6

        示例 1：
        输入：n = 10
        输出：4
        解释：小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。

        示例 2：
        输入：n = 0
        输出：0

        示例 3：
        输入：n = 1
        输出：0
     */

    // KeyPoint 方法一 暴力解法 => 超出时间限制
    // 时间复杂度 O(n^2)
    public int countPrimes1(int n) {
        int ans = 0;
        // 1 不是质数
        for (int x = 2; x < n; x++) {
            ans += isPrime(x) ? 1 : 0;
        }
        return ans;
    }

    // 判断 x 是否为质数
    private boolean isPrime(int x) {
        // 从 2 开始判断，且小于 x
        for (int i = 2; i < x; i++) {
            // 现在将 x 除以 y，如果余数为 0，那么就可以说 x 能够被 y 整除。
            if (x % i == 0) return false;
        }
        return true;
    }

    // KeyPoint 方法二 埃氏筛
    // 优化：一个质数的倍数肯定不是质数 => 减少重复计算
    // 时间复杂度 O(nlog(logn))
    public int countPrimes(int n) {
        int ans = 0;
        // 标识不是质数
        boolean[] notPrimes = new boolean[n];
        for (int x = 2; x < n; x++) {
            if (notPrimes[x]) continue;
            ans++;
            // 如果 x 是质数，那么 2x、3x、4x...肯定不是质数
            // 这里 i 是从 x + x 开始，因为 x 有可能是质数
            for (int i = x + x; i < n; i += x) {
                // KeyPoint for 循环的注意点
                // 1. ( ) 循环条件中，循环变量定义
                // 2. { } 代码块中，循环变量使用
                notPrimes[i] = true;
            }
        }
        return ans;
    }
}
