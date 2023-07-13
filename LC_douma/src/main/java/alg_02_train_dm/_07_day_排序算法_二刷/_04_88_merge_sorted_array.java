package alg_02_train_dm._07_day_排序算法_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-05-13 19:53
 * @Version 1.0
 */

// KeyPoint 归并排序应用
public class _04_88_merge_sorted_array {


    /*
        88. 合并两个有序数组
        给你两个按 非递减顺序 排列的整数数组 nums1 和 nums2，
        另有两个整数 m 和 n ，分别表示 nums1 和 nums2 中的元素数目。
        请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。

        KeyPoint 明确：非递减顺序 含义
                 0,1,2,3,4 是非递减，也是递增
                 0,0,2,2,4 是非递减，但不是递增

        注意：最终，合并后数组不应由函数返回，而是存储在数组 nums1 中。
        为了应对这种情况，nums1 的初始长度为 m + n，其中前 m 个元素表示应合并的元素，
        后 n 个元素为 0 ，应忽略。nums2 的长度为 n 。

        示例 1：
        输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
        输出：[1,2,2,3,5,6]
        解释：需要合并 [1,2,3] 和 [2,5,6] 。
        合并结果是 [1,2,2,3,5,6] ，其中斜体加粗标注的为 nums1 中的元素。

        示例 2：
        输入：nums1 = [1], m = 1, nums2 = [], n = 0
        输出：[1]
        解释：需要合并 [1] 和 [] 。
        合并结果是 [1] 。

        提示：
        nums1.length == m + n
        nums2.length == n
        0 <= m, n <= 200
        1 <= m + n <= 200
        -109 <= nums1[i], nums2[j] <= 109

     */

    // KeyPoint 方法一 归并 => 使用额外数组 tmp
    public void merge1(int[] nums1, int m, int[] nums2, int n) {
        int[] tmp = new int[m + n];
        // i 和 j 分别表示 num1 和 num2 起始位置索引
        int i = 0, j = 0;
        for (int index = 0; index < (m + n); index++) {
            if (i == m) {
                tmp[index] = nums2[j++];
            } else if (j == n) {
                tmp[index] = nums1[i++];
            } else {
                if (nums1[i] <= nums2[j]) {
                    tmp[index] = nums1[i++];
                } else {
                    tmp[index] = nums2[j++];
                }
            }
        }

        // for 循环中，循环变量一定需要统一，都是 index
        // 不要 for 循环中 index，循环体中又变成 i，不一致
        for (int index = 0; index < (m + n); index++) {
            nums1[index] = tmp[index];
        }
    }

    // KeyPoint 方法二 归并 => 不使用额外数组空间 tmp
    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = n - 1;
        // KeyPoint 指针技巧，有时从后往前遍历，节省空间
        // 将 nums1 和 nums2 比较结果，放到 num1 尾部，从而避免申请 tmp 数组
        int k = m + n - 1;

        // k 不是循环终止变量，因为将 j 遍历完，i 之前必然有序
        // 故 while 循环停止，k 也就停止移动了
        while (j >= 0) {
            // if 成立分支操作中，存在 i--，故必须保证 i>=0，不越界
            if (i >= 0 && nums1[i] > nums2[j]) {
                nums1[k] = nums1[i];
                i--;
                // 最好 else if 中也得加上 j >= 0
            } else if (j >= 0) {
                nums1[k] = nums2[j];
                j--;
            }
            k--;
        }
    }
}
