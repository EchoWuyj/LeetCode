package alg_02_train_dm._13_day_综合应用一;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author Wuyj
 * @DateTime 2023-07-31 20:01
 * @Version 1.0
 */
public class _07_349_IntersectionOfTwoArrays2 {

    // KeyPoint 方法二 排序 + 二分查找
    // 时间复杂度：O((m + n) * logm)
    // 空间复杂度：O(min(m, n))
    public int[] intersection2(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) return null;

        // 对 nums1 进行排序，在 nums1 中使用二分查找
        Arrays.sort(nums1); // O(m*logm)
        Set<Integer> set = new HashSet<>();

        // O(n*logm)
        for (int num2 : nums2) { // O(n)
            // 二分查找
            if (binarySearch(nums1, num2)) { // O(logm)
                set.add(num2);
            }
        }

        int[] ans = new int[set.size()];
        int i = 0;
        for (int num : set) {
            ans[i++] = num;
        }

        return ans;

        // KeyPoint 时间复杂度计算
        // 1.若两个时间复杂度数据规模相同，即都是关于是 m 的，且数量级相同，两者时间复杂度相加
        //   如：O(m*logm) 和 O(n*logm)，相加 O((m+n) * logm)
        //   => 本质：数量级相同，没法忽略其中一个，故只能相加
        // 2.若两个时间复杂度数据规模相同，即都是关于是 n 的，且数量级不同，时间复杂度取较大值
        //   如：O(n) 和 O(n^2) 相比较，选择 O(n^2)，
        //   => 本质：忽略较小数量级，保留较大数据级
    }

    // 二分查找
    private boolean binarySearch(int[] nums, int target) {
        // 若方法形参中没有定义变量 left，right，则需要在方法中定义
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target)
                return true;
            if (nums[mid] > target)
                right = mid - 1;
            else
                left = mid + 1;
        }
        return false;
    }
}
