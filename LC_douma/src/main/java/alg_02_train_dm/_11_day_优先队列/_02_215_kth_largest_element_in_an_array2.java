package alg_02_train_dm._11_day_优先队列;

import java.util.PriorityQueue;

/**
 * @Author Wuyj
 * @DateTime 2023-07-21 20:21
 * @Version 1.0
 */
public class _02_215_kth_largest_element_in_an_array2 {

    // KeyPoint 方法二 小顶堆
    // 优化思路：排序解法时间复杂度高的原因：对所有的元素进行全量排序
    //          但实际上，只需要确定 k 个最大元素即可，其他元素可以不关心
    // 关于'大顶堆'和'小顶堆'选择
    // => 画出堆结构，看清堆顶和堆下面元素的大小关系，从而明确选择'大顶堆'还是'小顶堆'
    // => 小顶堆：堆顶是最小元素，堆下面元素有 k-1 个比其大，故堆顶 => 数组中的第 K 个最大元素
    // 时间复杂度：O(nlogk)
    // 空间复杂度：O(k)
    public int findKthLargest2(int[] nums, int k) {

        // 数组中的第 K 个最大元素
        // => 小顶堆，保证顶堆是最小元素

        // 构造方法中，设置 pq 元素个数为 k
        PriorityQueue<Integer> pq = new PriorityQueue<>(k);
        for (int i = 0; i < k; i++) {
            pq.add(nums[i]);
        }
        // 比较数组剩余元素
        for (int i = k; i < nums.length; i++) {
            // 有更大元素 nums[i]，则加入小顶堆中
            if (nums[i] > pq.peek()) {
                // 先删，再加
                pq.remove();
                pq.add(nums[i]);
            }
        }
        // 小顶堆一共 k 个元素，其中堆顶为第 k 个最大元素
        return pq.peek();
    }

    // KeyPoint 方法三 小顶堆 => 另一种实现
    // 时间复杂度：O(nlogk)
    // 空间复杂度：O(k)
    public int findKthLargest3(int[] nums, int k) {

        PriorityQueue<Integer> pq = new PriorityQueue<>(k + 1);
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            // k+1 个元素加入 pq 后
            // => 留个一个位置给比较的元素
            pq.add(nums[i]);
            // pq 维护 k 个元素，删除多个多余的 1 个元素
            if (pq.size() > k) pq.remove();
        }
        return pq.peek();
    }
}
