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
        for (int i = k; i < n; i++) {
            if (nums[i] > pq.peek()) {
                pq.remove();
                pq.add(nums[i]);
            }
        }
        return pq.peek();
    }

    public int findKthLargest3(int[] nums, int k) {
        int n = nums.length;
        PriorityQueue<Integer> pq = new PriorityQueue<>(k + 1);
        for (int i = 0; i < n; i++) {
            pq.add(nums[i]);
            if (pq.size() > k) pq.remove();
        }

        return pq.peek();
    }

    public int findKthLargest4(int[] nums, int k) {
        int n = nums.length;
        PriorityQueue<Integer> pq;
        int capacity;

        if (k < n - k) {
            capacity = k;
            pq = new PriorityQueue<>(capacity + 1);
        } else {
            capacity = n - k + 1;
            pq = new PriorityQueue<>(capacity + 1, (a, b) -> b - a);
        }

        for (int i = 0; i < n; i++) {
            pq.add(nums[i]);
            if (pq.size() > capacity) pq.remove();
        }

        return pq.peek();
    }

    public int findKthLargest5(int[] nums, int k) {
        int left = 0, right = nums.length - 1;
        int target = nums.length - k;
        while (true) {
            int pivotIndex = partition(nums, left, right);
            if (target == pivotIndex) {
                return nums[pivotIndex];
            } else if (pivotIndex < target) {
                left = pivotIndex + 1;
            } else {
                right = pivotIndex - 1;
            }
        }
    }

    private Random random = new Random(System.currentTimeMillis());

    public int partition(int[] nums, int left, int right) {
        if (right > left) {
            int pivotIndex = random.nextInt(right - left + 1) + left;
            swap(nums, pivotIndex, right);
        }

        int pivot = nums[right];
        int less = left, great = left;
        for (; great < right; great++) {
            if (nums[great] < pivot) {
                swap(nums, great, less);
                less++;
            }
        }
        swap(nums, less, right);
        return less;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}


