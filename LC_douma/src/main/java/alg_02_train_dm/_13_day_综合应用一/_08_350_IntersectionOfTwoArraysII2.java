package alg_02_train_dm._13_day_综合应用一;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-08-01 11:06
 * @Version 1.0
 */
public class _08_350_IntersectionOfTwoArraysII2 {
    /*
        KeyPoint 进阶
        1. 如果给定的数组已经排好序呢？你将如何优化你的算法？ => 双指针
        2. 如果 nums1 的大小比 nums2 小很多，哪种方法更优？
            => 哈希查找，nums1 作为哈希表，保证哈希查找非常快，数据量少也避免哈希冲突，
            => 虽然nums2 多，但是只要遍历一遍
        3. 如果 nums2 的元素存储在磁盘上，内存是有限的，
           并且你不能一次加载所有的元素到内存中，你该怎么办？
            => nums2 在磁盘外部排序，nums1 在内存排序
            => 双指针，一个指针将磁盘中数据一条一条读出，再和另一个指针读取内存中数据，进行逐一比较
     */

    // KeyPoint 进阶：如果给定的数组已经排好序呢？你将如何优化你的算法？
    // 实现一：数组有序 => 二分查找
    // 时间复杂度：O(mlogm + nlogn) => 排序所消耗时间复杂度
    // 空间复杂度：O(m + n)
    public int[] intersect2(int[] nums1, int[] nums2) {

        // num1：1 2 2 2 2
        //         ↑
        //         i
        // num2：0 0 2 2 8 8 8

        // 遍历 num1 中每个元素，使用二分查找在 num2 中查找是否有 num1[i]

        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int n = nums1.length, m = nums2.length;

        int[] res = new int[Math.min(n, m)];
        int index = 0;
        int i = 0;

        // KeyPoint 时间复杂度分析
        // 1.没有重复元素：O(nlogm)
        // 2.全部是重复元素：O(logm + n + m)
        // i 指针右移没有规律，满足某种条件，可以一直右移，使用 while 灵活处理
        while (i < n) { // O(n)

            // 确定 lower 指针
            // O(logm)
            int lower = searchFirstTargetIndex(nums2, nums1[i]);
            if (lower == -1) {
                // 没有找到，i 右移，直接跳过
                i++;
                continue;
            }

            // 处理 nums1 和 nums2 相同的元素
            // 在 nums2 中找到和 nums1[i] 相等元素的个数
            int count = 0;
            while (lower < m && nums2[lower] == nums1[i]) {
                count++;
                lower++;
            }

            // 尽量在 nums1 中找到 count 个与 nums1[i] 相等元素的个数
            // 注意：有可能 nums1 中数值为 nums1[i] 不足 count 个
            int j = i;
            // 使用 j 指针代替 i 指针，只要 nums1[j] == nums1[i] 则 j 一直右移
            // 不能直接使用 i 指针右移，因为 nums1[i] 中涉及 i 指针
            while (j < n && nums1[j] == nums1[i]) {
                j++;
                if (count > 0) {
                    res[index++] = nums1[i];
                    count--;
                }
            }
            i = j;
        }
        return Arrays.copyOfRange(res, 0, index);
    }

    // 二分查找：在数组中，找到第一值为 target 的索引
    // 思路二 => 在循环体内排除没有目标值的区间
    private int searchFirstTargetIndex(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (target > nums[mid]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        // while 循环结束，left 指针位置，nums[left] >= target
        // 附加判断：nums[left] == target => 找到第一值为 target 的索引，没有则返回 -1
        if (nums[left] == target) return left;
        return -1;
    }

    // KeyPoint 进阶：如果给定的数组已经排好序呢？你将如何优化你的算法？
    // 实现二：双指针
    // 时间复杂度：O(mlogm + nlogn)
    // 空间复杂度：O(m + n)
    public int[] intersect3(int[] nums1, int[] nums2) {

        if (nums1 == null || nums2 == null) return null;
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int n = nums1.length, m = nums2.length;

        int[] res = new int[Math.min(n, m)];
        int index = 0;
        // 双指针
        int i = 0, j = 0;
        // 时间复杂度：O(m + n)
        while (i < n && j < m) {
            if (nums1[i] == nums2[j]) {
                // 没有去重的逻辑 => 只要相等，就将其加入到结果集中
                res[index++] = nums1[i];
                i++;
                j++;
                // nums1[i] != nums2[j]，移动值小的指针
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                j++;
            }
        }
        return Arrays.copyOfRange(res, 0, index);
    }
}
