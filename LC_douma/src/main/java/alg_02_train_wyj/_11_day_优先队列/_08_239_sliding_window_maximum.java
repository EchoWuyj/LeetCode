package alg_02_train_wyj._11_day_优先队列;

import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * @Author Wuyj
 * @DateTime 2023-05-24 12:44
 * @Version 1.0
 */
public class _08_239_sliding_window_maximum {
    public int[] maxSlidingWindow1(int[] nums, int k) {
        if (nums == null) return nums;
        int n = nums.length;
        int[] res = new int[n - k + 1];
        for (int i = 0; i < n - k + 1; i++) {
            int maxValue = Integer.MIN_VALUE;
            for (int j = i; j < i + k; j++) {
                maxValue = Math.max(maxValue, nums[j]);
            }
            res[i] = maxValue;
        }
        return res;
    }

    public int[] maxSlidingWindow2(int[] nums, int k) {
        if (nums == null) return nums;
        PriorityQueue<int[]> pq
                = new PriorityQueue<>((a, b) -> b[0] - a[0] == 0 ? (b[1] - a[1]) : (b[0] - a[0]));

        for (int i = 0; i < k; i++) {
            pq.add(new int[]{nums[i], i});
        }

        int n = nums.length;
        int[] res = new int[n - k + 1];
        res[0] = pq.peek()[0];

        for (int i = k; i < n; i++) {
            pq.add(new int[]{nums[i], i});
            while (pq.peek()[1] <= i - k) {
                pq.remove();
            }
            res[i - k + 1] = pq.peek()[0];
        }
        return res;
    }

    public int[] maxSlidingWindow3(int[] nums, int k) {
        if (nums == null) return null;
        int n = nums.length;
        int[] res = new int[n - k + 1];
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            while (!deque.isEmpty() && deque.peek() <= i - k) {
                deque.poll();
            }
            while (!deque.isEmpty() && nums[i] > nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offer(i);

            if (i >= k - 1) res[i - k + 1] = nums[deque.peek()];
        }
        return res;
    }
}
