package alg_02_train_wyj._11_day_优先队列;

/**
 * @Author Wuyj
 * @DateTime 2023-07-23 17:01
 * @Version 1.0
 */
public class _07_04_median_of_two_sorted_arrays3 {

    public double findMedianSortedArrays3(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int len = m + n;
        int lower = -1, upper = -1;
        int start1 = 0, start2 = 0;

        for (int i = 0; i <= len / 2; i++) {
            lower = upper;
            if (start1 < m && (start2 >= n || nums1[start1] <= nums2[start2])) {
                upper = nums1[start1++];
            } else {
                upper = nums2[start2++];
            }
        }
        return len % 2 == 0 ? (lower + upper) * 0.5 : upper;
    }
}
