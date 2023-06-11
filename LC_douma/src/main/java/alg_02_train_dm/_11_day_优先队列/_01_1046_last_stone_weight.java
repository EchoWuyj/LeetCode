package alg_02_train_dm._11_day_优先队列;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @Author Wuyj
 * @DateTime 2023-04-28 23:41
 * @Version 1.0
 */
public class _01_1046_last_stone_weight {

    /*
           1046 号算法题：最后一块石头的重量
           有一堆石头，每块石头的重量都是正整数。
           每一回合，从中选出两块 最重的 石头，然后将它们一起粉碎。
           假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：
               1. 如果 x == y，那么两块石头都会被完全粉碎；
               2. 如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
           最后，最多只会剩下一块石头。返回此石头的重量。如果没有石头剩下，就返回 0。

           输入：[2,7,4,1,8,1]
                 x = 7, y = 8  --> [2,0,4,1,1,1]
                 x = 2, y = 4  --> [0,0,2,1,1,1]
                 x = 1, y = 2  --> [0,0,1,0,1,1]
                 x = 1, y = 1  --> [0,0,0,0,0,1]
           输出： 1

           1 <= stones.length <= 30
           1 <= stones[i] <= 1000

           KeyPoint 思路：给定具体场景，根据给定的规则，通过模拟方式求解，最后通过代码实现
    */

    // KeyPoint 方法一 排序解法
    // O(n^2logn) => 数据规模 30，该时间复杂度完全够了
    public int lastStoneWeight1(int[] stones) {
        int n = stones.length;
        if (n == 1) return stones[0];
        // 最多比较次数 => 考虑最坏的情况
        for (int i = 0; i < n - 1; i++) {
            // 默认升序，
            // 每次 for 循环之后都进行排序，为了方便获取最大值和第二大值
            Arrays.sort(stones);
            int y = stones[n - 1];
            int x = stones[n - 2];
            // 说明最多只剩一块石头，提前退出 for 循环
            // 此时数组有序，返回 stones[n - 1] 即可
            if (x == 0) break;
            stones[n - 1] = y - x;
            stones[n - 2] = 0;
        }
        return stones[n - 1];
    }

    // KeyPoint 方法二 大顶堆
    // 性能瓶颈在于排序，堆可以快速拿到最大值和第二大值
    // 时间复杂度：O(nlogn)
    // 注意：虽然时间复杂度提高，但是由于数据规模太小了，排序解法因为操作简单，执行的时间更短，大顶堆相对需要的时间更长
    // 空间复杂度：O(n)
    public int lastStoneWeight(int[] stones) {
        int n = stones.length;
        if (n == 1) return stones[0];

        // 大顶堆
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        // 维护大顶堆
        for (int stone : stones) pq.add(stone);

        // pq.size() = 1，将元素返回
        while (pq.size() > 1) { // O(n)
            int y = pq.remove(); // O(logn)
            int x = pq.remove();
            int diff = y - x;
            // 粉碎结果 > 0，才将其放入堆中，进行后续粉碎
            if (diff > 0) pq.add(diff);
        }
        return pq.isEmpty() ? 0 : pq.peek();
    }
}
