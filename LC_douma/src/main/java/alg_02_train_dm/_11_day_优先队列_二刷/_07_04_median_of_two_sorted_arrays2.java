package alg_02_train_dm._11_day_优先队列_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-07-22 19:41
 * @Version 1.0
 */
public class _07_04_median_of_two_sorted_arrays2 {

    // KeyPoint 方法二 合并排序 + 使用额外空间
    // 充分利用题目给的信息
    // => nums1 和 nums2 已经有序，联想归并排序，最后通过索引计算出中位数
    // 时间复杂度：O(m + n)
    // 空间复杂度：O(m + n)
    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int[] tmpArr = new int[m + n];

        int i = 0, j = 0, index = 0;
        while (i < m && j < n) {
            if (nums1[i] < nums2[j]) {
                tmpArr[index++] = nums1[i++];
            } else {
                tmpArr[index++] = nums2[j++];
            }
        }

        while (i < m) tmpArr[index++] = nums1[i++];
        while (j < n) tmpArr[index++] = nums2[j++];

        // 奇数
        if ((m + n) % 2 == 1) {
            return tmpArr[(m + n) / 2];
            // 偶数
        } else {
            // Java 代码中，不能使用 [] 当做中括号。想使用括号，只能使用 ()
            return (tmpArr[(m + n) / 2] + tmpArr[(m + n - 1) / 2]) / 2.0;
        }
    }
}
