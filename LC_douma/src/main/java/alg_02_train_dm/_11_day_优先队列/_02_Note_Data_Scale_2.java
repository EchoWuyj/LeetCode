package alg_02_train_dm._11_day_优先队列;

/**
 * @Author Wuyj
 * @DateTime 2023-05-22 21:08
 * @Version 1.0
 */
public class _02_Note_Data_Scale_2 {
    /*

        一般ACM或者笔试题，或者力扣上的题目的时间限制是1秒或2秒。
        在这种情况下，C++代码中的操作次数控制在 1e7 为最佳。

        我们做题时可以根据不同数据范围，知道代码的时间复杂度和算法该如何选择
        n越小说明我们可以使用越暴力、时间复杂度越高的解法

        n ≤ 30, 指数级别, dfs + 剪枝，状态压缩 dp
        n ≤ 100 => O(n3)，floyd，dp
        n ≤ 1000 => O(n2)，O(n2logn)，dp，二分

        分界点 => 一旦 n 到达 1e4，就不适合 n2 的暴力解法

        n ≤ 10^4 => O(n∗√n)，块状链表
        n ≤ 10^5 => O(nlogn) => 各种 sort，线段树、树状数组、set/map、heap、
                                dijkstra + heap、spfa(最短路径算法)、求凸包、求半平面交、二分

        n ≤ 10^6 => O(n) 以及常数较小的 O(nlogn) 算法
                 => O(n)做法：hash、双指针扫描、kmp、AC自动机
                 => 常数比较小的 O(nlogn) 的做法：sort、树状数组、heap、dijkstra、spfa

        n ≤ 10^8 => O(n)，双指针扫描、kmp、AC自动机、线性筛素数
        n ≤ 10^9 => O(√n)，判断质数
        n ≤ 10^18 => O(logn)，最大公约数

     */
}
