package alg_02_train_wyj._11_day_优先队列;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * @Author Wuyj
 * @DateTime 2023-04-29 11:09
 * @Version 1.0
 */
public class _02_215_kth_largest_element_in_an_array {

    public int findKthLargest1(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);
        return nums[n - k];
    }

    public int findKthLargest2(int[] nums, int k) {
        int n = nums.length;
        PriorityQueue<Integer> pq = new PriorityQueue<>(k);
        for (int i = 0; i < k; i++) {
            pq.add(nums[i]);
        }
        for (int i = k; i < nums.length; i++) {
            if (nums[i] > pq.peek()) {
                pq.remove();
                pq.add(nums[i]);
            }
        }
        return pq.peek();
    }

    public int findKthLargest3(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(k + 1);
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            pq.add(nums[i]);
            if (pq.size() > k) pq.remove();
        }
        return pq.peek();
    }

    public int findKthLargest4(int[] nums, int k) {
        int n = nums.length;
        int capacity;
        PriorityQueue<Integer> pq;
        if (k < n - k) {
            capacity = k;
            pq = new PriorityQueue<>(k + 1);
        } else {
            capacity = n + 1 - k;
            pq = new PriorityQueue<>(capacity + 1, (a, b) -> b - a);
        }

        for (int i = 0; i < n; i++) {
            pq.add(nums[i]);
            if (pq.size() > capacity) pq.remove();
        }
        return pq.peek();
    }

    public int findKthLargest5(int[] nums, int k) {

    }

    public int partition(int[] nums, int left, int right) {

    }
}


