package alg_02_train_dm._11_day_优先队列;

/**
 * @Author Wuyj
 * @DateTime 2023-05-23 20:00
 * @Version 1.0
 */
public class _07_04_median_of_two_sorted_arrays5 {
    /*

        KeyPoint 划分数组，数学思维，太过复杂，直接跳过

        ------------------------------ 划分数组 ----------------------------------
        中位数：
        将一个集合划分为两个长度相等的子集，其中一个子集中的元素总是大于另一个子集中元素
        (补充说明：两个子集长度不一定严格相等，若集合为奇数，则存在一个子集比另一个子集个数多 1 的情况)

        一个升序数组：
        1,2,3,4,5          --> [1,2,3] 和 [4,5]
        1,2,3,4,5,6,7,8    --> [1,2,3,4] 和 [5,6,7,8]

        两个升序的数组： A + B => 奇数
        A：[1,2,3,4,5]
        B：[1,2,3,4,5,6,7,8]

           left_part | right_part
        A：[1,2,3]   | [4,5]
        B：[1,2,3,4] | [5,6,7,8]
        中位数：left_part A 和 B 中最大值 4

        两个升序的数组： A + B => 偶数
        A：[1,2,3,4,5,6]
        B：[1,2,3,4,5,6,7,8]

           left_part | right_part
        A：[1,2,3]   | [4,5,6]
        B：[1,2,3,4] | [5,6,7,8]
        中位数：left_part，A 和 B 中最大值 4 => left
               right_part，A 和 B 中最小值 4 => right
        ---------------------------------------------------------------------
        升序数组 A 和 B，长度分别是 m 和 n
        A[0], A[1], ..., A[i-1], A[i], A[i+1], ..., A[m-1]
        B[0], B[1], ..., B[j-1], B[j], B[j+1], ..., B[n-1]

        对于 A，在索引为 i 的位置切割
        对于 B，在索引为 j 的位置切割

                 left_part       |       right_part
        A[0], A[1], ..., A[i-1], | A[i], A[i+1], ..., A[m-1]
        B[0], B[1], ..., B[j-1], | B[j], B[j+1], ..., B[n-1]

        如果 A 和 B 的总长度为偶数：
        1. len(left_part) == len(right_part) ==> i + j = m - i + n -j
        2. max(left_part) <= min(right_part)
        中位数 = (max(left_part) + min(right_part)) / 2

        如果 A 和 B 的总长度为奇数：
        1. len(left_part) == len(right_part) + 1 ==> i + j = m - i + n -j + 1
        2. max(left_part) <= min(right_part)
        中位数 = max(left_part)

        i + j = m - i + n -j   => i + j = (m + n) / 2
        i + j = m - i + n -j + 1  => i + j = (m + n + 1) / 2

        又因为：当 m + n 为偶数：(m + n) / 2 == (m + n + 1) / 2
        所以，我们可以统一这两种形式：i + j = (m + n) / 2 和 i + j = (m + n + 1) / 2
        统一成：i + j = (m + n + 1) / 2  => j = ((m + n + 1) / 2) - i
        => 有 i 和 j 等式关系，若 i 和 j 中其中一个固定了，另外一个也就固定了

        max(left_part) <= min(right_part) => B[j - 1] <= A[i] 且 A[i - 1] <= B[j]
        问题变为：在有序数组 A 中找到一个 i，使得 B[j - 1] <= A[i] 且 A[i - 1] <= B[j]
        因为 i 确定，j 通过 该公式 j = ((m + n + 1) / 2) - i 也能确定

        二分查找 => 在有序数组中，直到符合某个条件的值

                 left_part       |       right_part
        A[0], A[1], ..., A[i-1], | A[i], A[i+1], ..., A[m-1]
        B[0], B[1], ..., B[j-1], | B[j], B[j+1], ..., B[n-1]

        如果 B[j - 1] > A[i]，说明 A[i] 太小了，i 需要往右走
        如果 A[i - 1] > B[j]，说明 A[i - 1] 太大了，i 需要往左走

        -------------------------------------------------------------------------
        补充说明：

        在 bug，j < 0，索引越界
        j = ((m + n + 1) / 2) - i
        i -> [0,m]  => i 相当于'切割线'，可以在 0 和 m 位置进行切割，将数组划分成 left_part 和 right_part
        j -> [0,n]
        m > n
        假设 i = m
        j = ((m + n + 1) / 2) - m < 0

        修复 bug， m < n
        j = ((m + n + 1) / 2) - i
        i -> [0,m]
        j -> [0,n]
        m < n
        假设 i = m
        j = ((m + n + 1) / 2) - m > 0

     */

    // KeyPoint 方法五 划分数组 => 锻炼自己思维
    // 时间复杂度：O(log(m + n))
    // 空间复杂度：O(1)
    public double findMedianSortedArrays5(int[] nums1, int[] nums2) {
        // nums1 => A
        // nums2 => B
        int m = nums1.length, n = nums2.length;
        // 交换两个数组引用
        if (m > n) {
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
            int tmp = m;
            m = n;
            n = tmp;
        }
        int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;
        while (iMin <= iMax) {
            int i = iMin + (iMax - iMin) / 2;
            int j = halfLen - i;
            // 如果 B[j - 1] > A[i]，说明 A[i] 太小了，i 需要往右走
            if (i < iMax && nums2[j - 1] > nums1[i]) {
                iMin = i + 1;
                // 如果 A[i - 1] > B[j]，说明 A[i - 1] 太大了，i 需要往左走
            } else if (i > iMin && nums1[i - 1] > nums2[j]) {
                iMax = i - 1;
            } else {
                // 如果 B[j - 1] <= A[i] && A[i - 1] <= B[j]
                // 1.求左边最大值
                int maxLeft;
                if (i == 0) maxLeft = nums2[j - 1];
                else if (j == 0) maxLeft = nums1[i - 1];
                else maxLeft = Math.max(nums1[i - 1], nums2[j - 1]);
                // m + n 奇数，直接返回 maxLeft，即为中位数
                if ((m + n) % 2 == 1) return maxLeft;
                // 2.求右边最小值
                int minRight;
                if (i == m) minRight = nums2[j];
                else if (j == n) minRight = nums1[i];
                else minRight = Math.min(nums1[i], nums2[j]);
                return (maxLeft + minRight) * 0.5;
            }
        }
        // 没有，则返回 0.0
        return 0.0;
    }
}
