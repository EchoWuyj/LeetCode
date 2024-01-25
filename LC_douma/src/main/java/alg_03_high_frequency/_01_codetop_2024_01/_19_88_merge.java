package alg_03_high_frequency._01_codetop_2024_01;

/**
 * @Author Wuyj
 * @DateTime 2024-01-13 13:19
 * @Version 1.0
 */
public class _19_88_merge {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = n - 1;
        int k = (m + n - 1);

        // 判断 num2 对应的索引指针 j
        while (j >= 0) {
            // i >= 0 保证索引不越界，和 i -- 对应
            // 索引 i 和 j 是可以取等的，不能是 i > 0 和 j > 0
            if (i >= 0 && nums1[i] >= nums2[j]) {
                nums1[k] = nums1[i];
                // 移动指针
                i--;
                k--;
            } else if (j >= 0) {
                // i 和 nums1 对应，j 和 nums2 对应，两者不要搞反了
                nums1[k] = nums2[j];
                j--;
                k--;
            }
        }
    }
}
