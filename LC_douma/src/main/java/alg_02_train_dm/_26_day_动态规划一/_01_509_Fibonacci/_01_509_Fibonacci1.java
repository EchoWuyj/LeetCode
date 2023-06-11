package alg_02_train_dm._26_day_动态规划一._01_509_Fibonacci;

/**
 * @Author Wuyj
 * @DateTime 2023-06-03 11:41
 * @Version 1.0
 */
public class _01_509_Fibonacci1 {
    
     /* 
        509. 斐波那契数
        斐波那契数，通常用 F(n) 表示，形成的序列称为 斐波那契数列 。
        该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：
    
        F(0) = 0，F(1)= 1
        F(n) = F(n - 1) + F(n - 2)，其中 n > 1
        给你 n ，请计算 F(n) 。
    
        示例 1：
        输入：2
        输出：1
        解释：F(2) = F(1) + F(0) = 1 + 0 = 1
    
        示例 2：
        输入：3
        输出：2
        解释：F(3) = F(2) + F(1) = 1 + 1 = 2
    
        示例 3：
        输入：4
        输出：3
        解释：F(4) = F(3) + F(2) = 2 + 1 = 3
        
        提示：
        0 <= n <= 30

     */

    // KeyPoint 方法一 递归实现 (自顶向下)
    private int fib1(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        return fib(n - 1) + fib(n - 2);
    }

    // KeyPoint 方法二 回溯 => 枚举 => 抽象成树形结构 (自顶向下)
    // 时间复杂度：O(2^n)
    // 因为 n 最大为 30，比较小，所以不会超时，O(2^n) 存在许多重复子问题，导致时间复杂度很高
    // 解释：O(2^n)
    // 1.dfs 调用次数取决于有多少节点，二叉树节点个数最多是 2^n
    // 2.二叉树，第 i 层最多有 2^(i-1) 个节点，一共有的节点 2^0 + 2^1 + ... + 2^(i-1) = 2^i - 1，故最多是 2^n
    // 空间复杂度：O(logn) 递归系统栈需要的空间开销
    public int fib(int n) {
        return dfs(n);
    }

    // 时间复杂度：O(2^n)
    // 树形结构 => 后续遍历
    private int dfs(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;

        int leftFib = dfs(n - 1);
        int rightFib = dfs(n - 2);

        return leftFib + rightFib;
    }
}
