package alg_02_train_wyj._11_day_优先队列;

/**
 * @Author Wuyj
 * @DateTime 2023-07-23 18:41
 * @Version 1.0
 */
public class _07_04_median_of_two_sorted_arrays4 {

    public double findMedianSortedArrays4(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;

        int leftK = (m + n + 1) / 2;
        int rightK = (m + n + 2) / 2;

        int leftNum = getKth(nums1, nums2, leftK);
        int rightNum = getKth(nums1, nums2, rightK);

        return (leftNum + rightNum) * 0.5;
    }

    public int getKth(int[] nums1, int[] nums2, int k) {
        int m = nums1.length;
        int n = nums2.length;
        int i = 0, j = 0;
        while (true) {
            if (i == m) return nums2[j + k - 1];
            if (j == n) return nums1[i + k - 1];
            if (k == 1) return Math.min(nums1[i], nums2[j]);

            int nexti = Math.min(m, i + k / 2) - 1;
            int nextj = Math.min(n, j + k / 2) - 1;

            if (nums1[nexti] <= nums2[nextj]) {
                k -= (nexti - i + 1);
                i = nexti + 1;
            } else {
                k -= (nextj - j + 1);
                j = nextj + 1;
            }
        }
    }
}
