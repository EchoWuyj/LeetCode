package alg_02_train_wyj._11_day_优先队列;

import java.util.PriorityQueue;

/**
 * @Author Wuyj
 * @DateTime 2023-05-23 15:35
 * @Version 1.0
 */
public class _07_04_median_of_two_sorted_arrays {

    private PriorityQueue<Integer> maxHeap;
    private PriorityQueue<Integer> minHeap;

    public double findMedianSortedArrays1(int[] nums1, int[] nums2) {
        maxHeap = new PriorityQueue<>((a, b) -> b - a);
        minHeap = new PriorityQueue<>();

        for (int num : nums1) addNum(num);
        for (int num : nums2) addNum(num);

        if (maxHeap.size() > minHeap.size()) {
            return maxHeap.peek();
        } else {
            return (maxHeap.peek() + minHeap.peek()) * 0.5;
        }
    }

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

    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int[] tmp = new int[m + n];

        int i = 0, j = 0, k = 0;
        while (i < m && j < n) {
            if (nums1[i] < nums2[j]) {
                tmp[k++] = nums1[i++];
            } else {
                tmp[k++] = nums2[j++];
            }
        }

        while (i < m) tmp[k++] = nums1[i++];
        while (j < n) tmp[k++] = nums2[j++];

        if ((m + n) % 2 == 1) {
            return tmp[(m + n) / 2];
        } else {
            return (tmp[(m + n) / 2] + tmp[(m + n - 1) / 2]) * 0.5;
        }
    }

    public double findMedianSortedArrays3(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int len = m + n;
        int lower = -1, upper = -1;
        int start1 = 0, start2 = 0;

        for (int i = 0; i <= len / 2; i++) {
            lower = upper;
            if (start1 < m && (start2 >= n || nums1[start1] < nums2[start2])) {
                upper = nums1[start1++];
            } else {
                upper = nums2[start2++];
            }
        }
        return len % 2 == 0 ? (lower + upper) * 0.5 : upper;
    }

    public double findMedianSortedArrays4(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;

        int leftK = (m + n + 1) / 2;
        int rightK = (m + n + 2) / 2;

        int leftNum = getKth(nums1, nums2, leftK);
        int rightNum = getKth(nums1, nums2, rightK);

        return (leftNum + rightNum) * 0.5;
    }

    private int getKth(int[] nums1, int[] nums2, int k) {
        int m = nums1.length;
        int n = nums2.length;

        int i = 0, j = 0;

        while (true) {
            if (i == m) return nums2[j + k - 1];
            if (j == n) return nums1[i + k - 1];

            if (k == 1) return Math.min(nums1[i], nums2[j]);

            int newi = Math.min(i + (k / 2), m) - 1;
            int newj = Math.min(j + (k / 2), n) - 1;

            if (nums1[newi] < nums2[newj]) {
                k -= (newi - i + 1);
                i = newi + 1;
            } else {
                k -= (newj - j + 1);
                j = newj + 1;
            }
        }
    }
}
