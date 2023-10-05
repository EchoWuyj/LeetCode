package alg_02_train_dm._11_day_优先队列_二刷;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2023-05-22 11:16
 * @Version 1.0
 */
public class _03_347_top_k_frequent_elements1_推荐 {

     /*
        347 号算法题：前 K 个高频元素
        给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。
        你可以按 任意顺序 返回答案。

        输入: nums = [1,1,1,2,2,3], k = 2
        输出: [2, 1]

        输入: nums = [1], k = 1
        输出: [1]

        提示：
        1 <= nums.length <= 10^5
        k 的取值范围是 [1, 数组中不相同的元素的个数]
        题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的

        进阶：你所设计算法的时间复杂度 必须 优于 O(nlogn) ，其中 n 是数组大小。
        => 优于 O(nlogn)，说明不能直接排序，不能全局排序，但是可以考虑局部排序，或者某个元素排序

     */

    // KeyPoint 方法一 小顶堆
    // 时间复杂度：O(nlogk)
    // 空间复杂度：O(n) => 数组中没有重复元素，空间大小 n
    // => 如果我们只关心与输入规模，即为数组 nums 大小，空间复杂度是 O(n)
    public int[] topKFrequent1(int[] nums, int k) {
        // 统计词频
        Map<Integer, Integer> count = new HashMap<>();
        for (int num : nums) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }

        // 空间复杂度 O(k) < O(n)
        PriorityQueue<Integer> pq
                // 按照出现次数，升序排列
                // Comparator => 直接使用 Lambda 表达式 (a,b) -> 比较逻辑
                // new PriorityQueue<>(k + 1, (a, b) -> count.get(a) - count.get(b));
                // PriorityQueue 会自动扩容管理，可以不设置 initialCapacity
                = new PriorityQueue<>((a, b) -> count.get(a) - count.get(b));

        // 小堆顶，堆顶出现次数为最小值
        for (int key : count.keySet()) { // O(n)
            // KeyPoint 特别注意
            // 1.该种实现，不需要手动比较，只需要判断个数即可
            // 2.中间过程是 k+1 个元素，通过词频多少进行比较，最终小堆顶只会留下 k 个元素
            pq.add(key); // O(logk)
            if (pq.size() > k) pq.remove();
        }

        int[] res = new int[k];
        int index = 0;
        // 小顶堆中存储的就是出现了前 k 个高频的元素，将其复制到 res 中即可
        while (!pq.isEmpty()) {
            res[index++] = pq.remove();
        }
        return res;
    }
}
