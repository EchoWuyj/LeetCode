package alg_02_train_dm._11_day_优先队列_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-07-22 19:42
 * @Version 1.0
 */
public class _07_04_median_of_two_sorted_arrays3 {

    // KeyPoint 方法三 合并排序 + 不使用额外空间
    // 时间复杂度：O(m + n)
    // 空间复杂度：O(1)
    public double findMedianSortedArrays3(int[] nums1, int[] nums2) {

        // 优化思路：
        // 本题求解中位数，只需要获取数组中两个元素值，并不需要将所有元素排好序放到 tmpArr 数组中
        // 只是需要两个变量 lower，upper，记录数组中间元素即可，故不使用额外空间

        int m = nums1.length;
        int n = nums2.length;
        int len = m + n;
        int lower = -1, upper = -1;
        int start1 = 0, start2 = 0;

        // 2 7 8    4 5 6
        //   ↑          ↑
        // start1     start2

        // 中位数在排序后数组的中间位置，故只要迭代到中间位置就可以了，故不需要循环整个 len
        // 以 upper 指针正确位置为标准，执行 len/2+1 次循环即可
        for (int i = 0; i <= len / 2; i++) {
            // 每轮循环一开始，先移动本轮 lower 到 上一轮 upper 位置，抱着 lower 紧跟着 upper
            // 从而保证 lower 和 upper 是紧挨着的
            lower = upper;
            // 因为 upper 是中间靠右位置，所以先移动 upper，即执行下面 if 判断
            // 移动 upper 两种情况
            // 1. start1 越界 或者 start2 越界 => 不要遗漏了
            // 2. [start1] < [start2]
            if (start1 < m && (start2 >= n || nums1[start1] < nums2[start2])) {
                upper = nums1[start1++];
            } else {
                upper = nums2[start2++];
            }
        }
        return len % 2 == 0 ? (lower + upper) / 2.0 : upper;
    }
}
