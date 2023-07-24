package alg_02_train_wyj._11_day_优先队列;

/**
 * @Author Wuyj
 * @DateTime 2023-07-23 17:01
 * @Version 1.0
 */
public class _07_04_median_of_two_sorted_arrays2 {

    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int[] tmpArr = new int[n + m];
        int i = 0, j = 0, index = 0;
        while (i < m && j < n) {
            if (nums1[i] <= nums2[j]) {
                tmpArr[index++] = nums1[i++];
            } else {
                tmpArr[index++] = nums2[j++];
            }
        }

        while (i < m) tmpArr[index++] = nums1[i++];
        while (j < n) tmpArr[index++] = nums2[j++];

        if ((m + n) % 2 == 1) {
            return tmpArr[(m + n) / 2];
        } else {
            return (tmpArr[(m + n) / 2] + tmpArr[(m + n) / 2 - 1]) * 0.5;
        }
    }
}
