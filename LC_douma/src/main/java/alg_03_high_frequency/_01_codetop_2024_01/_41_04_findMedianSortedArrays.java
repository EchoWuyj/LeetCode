package alg_03_high_frequency._01_codetop_2024_01;

/**
 * @Author Wuyj
 * @DateTime 2024-01-14 0:19
 * @Version 1.0
 */
public class _41_04_findMedianSortedArrays {
    // 寻找两个正序数组的中位数
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        // 定位 m+n 个元素中，中位数对应的位置
        // 通过 +1 和 +2 将奇偶性统一
        int leftK = (m + n + 1) / 2;
        int rightK = (m + n + 2) / 2;
        // 中位数位置上对应数字
        int leftNum = getKthNum(nums1, nums2, leftK);
        int rightNum = getKthNum(nums1, nums2, rightK);
        // 计算中位数
        return (leftNum + rightNum) * 0.5;
    }

    public static int getKthNum(int[] nums1, int[] nums2, int k) {
        int m = nums1.length;
        int n = nums2.length;
        int i = 0, j = 0;

        // 不断使用二分，直到不能使用为止
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
