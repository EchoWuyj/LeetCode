package alg_02_体系班_zcy.class14;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Code04_IPO {

    // 给定两个数组,Costs[]花费和Profits[]利润,数组大小相等,一次只能做一个项目
    // 一共最多做k个项目,初始资金W,求最大收益(钱剩下多少)?

    // 参数1,正数数组 costs[i]表示第i个项目的花费
    // 参数2,正数数组 profits[i]表示第i个项目在扣除花费之后还能挣到的钱(利润)
    // 参数3,正数k,表示你不能并行,只能串行的最多做k个项目
    //      你每做完一个项目,马上获得的收益,可以支持你去做下一个项目
    // 参数4,正数m,表示你初始的资金
    // 输出:你最后获得的最大钱数

    /**
     * @param K       最多K个项目
     * @param W       W是初始资金
     * @param Profits 利润
     * @param Costs   花费
     * @return 返回最终最大的资金
     */
    public static int findMaximizedCapital(int K, int W, int[] Profits, int[] Costs) {

        // 小根堆(花费最小)
        PriorityQueue<Program> minCostQ = new PriorityQueue<>(new MinCostComparator());
        // 大根堆(利润最大)
        PriorityQueue<Program> maxProfitQ = new PriorityQueue<>(new MaxProfitComparator());

        // 思路:
        // 1)根据W解锁,小根堆->大根堆
        // 2)从大顶堆中获取顶部,执行该项目,W↑,周而复始

        // 建立每个项目,并放入小根堆中
        for (int i = 0; i < Profits.length; i++) {
            minCostQ.add(new Program(Profits[i], Costs[i]));
        }

        // 执行K轮,K个项目
        for (int i = 0; i < K; i++) {
            // 根据W解锁,小根堆->大根堆
            while (!minCostQ.isEmpty() && minCostQ.peek().cost <= W) {
                maxProfitQ.add(minCostQ.poll());
            }
            // 小根堆中解锁不出任何项目,则提前结束
            if (maxProfitQ.isEmpty()) {
                return W;
            }
            W += maxProfitQ.poll().profit;
        }
        return W;
    }

    public static class Program {
        public int profit;
        public int cost;

        public Program(int profit, int cost) {
            this.profit = profit;
            this.cost = cost;
        }
    }

    public static class MinCostComparator implements Comparator<Program> {

        @Override
        // 最小花费
        public int compare(Program o1, Program o2) {
            return o1.cost - o2.cost;
        }
    }

    public static class MaxProfitComparator implements Comparator<Program> {

        @Override
        // 最大利润
        public int compare(Program o1, Program o2) {
            return o2.profit - o1.profit;
        }
    }
}
