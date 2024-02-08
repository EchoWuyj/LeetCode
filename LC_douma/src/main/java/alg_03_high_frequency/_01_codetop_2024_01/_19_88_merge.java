package alg_03_high_frequency._01_codetop_2024_01;

/**
 * @Author Wuyj
 * @DateTime 2024-01-13 13:19
 * @Version 1.0
 */
public class _19_88_merge {

    // 合并两个有序数组
    // 逆序遍历
    public void merge(int[] nums1, int m, int[] nums2, int n) {

        // nums1 的初始长度为 m + n，其中前 m 个元素表示应合并的元素，后 n 个元素为 0
        // nums2 的长度为 n

        // 定义变量顺序规范：i j 和 m n
        int i = m - 1;
        int j = n - 1;
        int k = (m + n - 1);

        // 一定需要将 num2 数组遍历完，故判断 num2 对应的索引指针 j >= 0
        // nums1 [ 1 2 3 _ _ _ ] 和 nums2 [ 4 5 6 ]
        // 判断条件是对称结构：j >= 0；i >= 0；j >= 0
        while (j >= 0) {
            // i >= 0 保证索引不越界，和 i -- 对应
            // 索引 i 和 j 是可以取等的，不能是 i > 0 和 j > 0
            if (i >= 0 && nums1[i] >= nums2[j]) {
                // 从后往前遍历，故选择大的值进行赋值
                nums1[k] = nums1[i];
                // 移动指针
                i--;
                k--;
            } else if (j >= 0) {
                // i 和 nums1 对应，j 和 nums2 对应，两者不要搞反了
                // KeyPoint 索引 j 和 数组名 nums2 对应，不能写成 nums1[j]
                nums1[k] = nums2[j];
                j--;
                k--;
            }
        }
    }
}
