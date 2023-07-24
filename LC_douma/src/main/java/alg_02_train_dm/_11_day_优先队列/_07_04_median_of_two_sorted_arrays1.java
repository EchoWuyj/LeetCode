package alg_02_train_dm._11_day_优先队列;

import java.util.PriorityQueue;

/**
 * @Author Wuyj
 * @DateTime 2023-05-23 13:42
 * @Version 1.0
 */
public class _07_04_median_of_two_sorted_arrays1 {

    /*
        4 号算法题：寻找两个正序数组的中位数

        给定两个大小分别为 m 和 n 的正序(从小到大)数组 nums1 和 nums2
        请你找出并返回这两个正序数组的 中位数

        输入：nums1 = [1,3], nums2 = [2]
        输出：2.00000
        解释：合并数组 = [1,2,3] ，中位数 2

        输入：nums1 = [1,2], nums2 = [3,4]
        输出：2.50000
        解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5

        提示
        1. nums1.length == m
        2. nums2.length == n
        3. 0 <= m <= 1000、0 <= n <= 1000、1 <= m + n <= 2000
        4. -10^6 <= nums1[i], nums2[i] <= 10^6

        进阶：你能设计一个时间复杂度为 O(log (m+n)) 的算法解决此问题吗？
     */

    // 大顶堆 => 存储较小的一半元素
    PriorityQueue<Integer> maxHeap;
    // 小顶堆 => 存储较大的一半元素
    PriorityQueue<Integer> minHeap;

    // KeyPoint 方法一 大顶堆 + 小顶堆 => 题解能通过，但是击败了 9.34% 的用户，性能比较差
    // 将 nums1 和 nums2 中所有元素都放入到 maxHeap 和 minHeap 中，这样就可以直接获取中位数
    // 时间复杂度：O(mlogm + nlog(m + n))
    // 空间复杂度：O(m + n)
    public double findMedianSortedArrays1(int[] nums1, int[] nums2) {
        maxHeap = new PriorityQueue<>((a, b) -> b - a);
        minHeap = new PriorityQueue<>();

        // O(mlogm)
        for (int num : nums1) addNum(num);
        // O(nlog(m + n))
        for (int num : nums2) addNum(num);

        if (maxHeap.size() > minHeap.size()) {
            // 说明有奇数个元素，那么大顶堆堆顶元素就是中位数
            return maxHeap.peek();
        } else {
            return (maxHeap.peek() + minHeap.peek()) * 0.5;
        }
    }

    // 从数据流中添加一个整数到数据结构中
    // 时间复杂度：log(n)
    public void addNum(int num) {
        if (maxHeap.isEmpty()) {
            maxHeap.add(num);
            return;
        }

        if (num <= maxHeap.peek()) {
            maxHeap.add(num);
        } else {
            minHeap.add(num);
        }

        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.add(maxHeap.remove());
        }

        if (maxHeap.size() < minHeap.size()) {
            maxHeap.add(minHeap.remove());
        }
    }

    // KeyPoint 总结
    // 1.对于已经有序数组，再使用堆就有点绕远了，使用堆主要是用来排序，本题中已经是正序数组
    // 2.对于无序数组，使用大顶堆和小顶堆，寻找第 k 大值，中位数，其性能还是很好的
}
