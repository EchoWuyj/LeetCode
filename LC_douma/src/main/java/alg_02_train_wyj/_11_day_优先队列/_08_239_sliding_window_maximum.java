package alg_02_train_wyj._11_day_优先队列;

import java.util.PriorityQueue;

/**
 * @Author Wuyj
 * @DateTime 2023-05-24 12:44
 * @Version 1.0
 */
public class _08_239_sliding_window_maximum {
    public int[] maxSlidingWindow1(int[] nums, int k) {
        int n = nums.length;
        int[] res = new int[n - k + 1];
        for (int i = 0; i < n - k + 1; i++) {
            int maxNum = Integer.MIN_VALUE;
            for (int j = i; j < i + k; j++) {
                maxNum = Math.max(maxNum, nums[j]);
            }
            res[i] = maxNum;
        }
        return res;
    }

    public int[] maxSlidingWindow2(int[] nums, int k) {
        PriorityQueue<int[]> maxHeap
                = new PriorityQueue<>((a, b) -> a[0] != b[0] ? b[0] - a[0] : b[1] - b[0]);
        for (int i = 0; i < k; i++) {
            maxHeap.add(new int[]{nums[i], i});
        }
        int n = nums.length;
        int[] res = new int[n - k + 1];
        res[0] = maxHeap.peek()[0];

        for (int i = k; i < n; i++) {
            maxHeap.add(new int[]{nums[i], i});
            while (maxHeap.peek()[1] <= i - k) {
                maxHeap.remove();
            }

            res[i - k + 1] = maxHeap.peek()[0];
        }
        return res;
    }

    public int[] maxSlidingWindow3(int[] nums, int k) {
        PriorityQueue<int[]> maxHeap
                = new PriorityQueue<>((a, b) -> a[0] != b[0] ? b[0] - a[0] : b[1] - a[1]);
        for (int i = 0; i < k; i++) {
            maxHeap.add(new int[]{nums[i], i});
        }

        int n = nums.length;
        int[] res = new int[n - k + 1];
        res[0] = maxHeap.peek()[0];

        for (int i = k; i < n; i++) {
            maxHeap.add(new int[]{nums[i], i});
            while (maxHeap.peek()[1] <= i - k) {
                maxHeap.remove();
            }
            res[i - k + 1] = maxHeap.peek()[0];
        }
        return res;
    }
}
