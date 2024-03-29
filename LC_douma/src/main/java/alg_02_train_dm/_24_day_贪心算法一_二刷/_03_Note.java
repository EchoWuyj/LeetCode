package alg_02_train_dm._24_day_贪心算法一_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-04-12 14:18
 * @Version 1.0
 */
public class _03_Note {
    /*

        贪心的局限
        1.并不是所有的问题都可以通过局部最优解得到整体最优解的
          硬币找零问题，贪心失效，就回溯
        2.有向有权图，最短路径选择问题 => 每次局部选择，有可能得不到整体最优解，贪心失效
          具体问题中：若前面的选择，会影响后面的选择，说明选择具备后效性，不能使用贪心来解

        具备贪心思想的问题
        1.每一步都具有局部最优解
        2.前面的选择，不会影响后面的选择 => 无后效性
        => 局部最优解 + 无后效性 => 全局最优解

        分发饼干
        硬币找零问题 => 贪心策略：每次拿面值最大的硬币 => 可能贪心不成功

     */
}
