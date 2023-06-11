package alg_02_train_dm._13_day_综合应用一;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2023-03-17 19:49
 * @Version 1.0
 */
public class _08_350_IntersectionOfTwoArraysII {
     /*
        350. 两个数组的交集 II
        给定两个数组，编写一个函数来计算它们的交集。

        输入：nums1 = [1,2,2,2], nums2 = [2,2]
        输出：[2,2]

        输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
        输出：[4,9]

        输出结果中每个元素出现的次数，应与元素在两个数组中出现次数的最小值一致。
        我们可以不考虑输出结果的顺序。

        进阶：
        1. 如果给定的数组已经排好序呢？你将如何优化你的算法？ -> 双指针
        2. 如果 nums1 的大小比 nums2 小很多，哪种方法更优？
            -> 哈希查找，nums1 作为哈希表，保证哈希查找非常快，数据量少也避免哈希冲突，
            -> 虽然nums2 多，但是只要遍历一遍
        3. 如果 nums2 的元素存储在磁盘上，内存是有限的，
           并且你不能一次加载所有的元素到内存中，你该怎么办？
            -> nums2 在磁盘外部排序，nums1 在内存排序
            -> 双指针，一个指针将磁盘中数据一条一条读出，再和另一个指针读取内存中数据，进行逐一比较
     */

    // KeyPoint 哈希查找
    // 时间复杂度：O(m + n)
    // 空间复杂度：O(min(m, n) + n)
    public int[] intersect1(int[] nums1, int[] nums2) {
        // key -> 元素
        // value -> 出现次数
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : nums2) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        int[] res = new int[Math.min(nums1.length, nums2.length)];
        int index = 0;
        for (int num : nums1) {
            // 出现次数：以两个数组中出现次数最小值为准
            if (countMap.containsKey(num) && countMap.get(num) > 0) {
                res[index++] = num;
                countMap.put(num, countMap.get(num) - 1);
            }
        }
        // 不是直接将 res 返回，截取一部分
        return Arrays.copyOfRange(res, 0, index);
    }

    // KeyPoint 进阶：如果给定的数组已经排好序呢？你将如何优化你的算法？ => 二分查找
    // 时间复杂度：O(mlogm + nlogn) => 排序所消耗时间复杂度
    // 空间复杂度：O(m + n)
    public int[] intersect2(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int[] res = new int[Math.min(nums1.length, nums2.length)];
        int index = 0;
        int i = 0;
        // 时间复杂度
        // 1.没有重复元素：O(mlogn)
        // 2.全部是重复元素：O(logn + m + n)
        while (i < nums1.length) { // i 右移没有规律，满足某种条件，可以一直右移，使用 while 灵活处理

            // 确定 firstTargetIndex
            // O(logn)
            int firstTargetIndex = searchFirstTargetIndex(nums2, nums1[i]);
            if (firstTargetIndex == -1) {
                // i 右移，直接跳过
                i++;
                //
                continue;
            }

            // 处理相同的元素
            // 在 nums2 中找到和 nums1[i] 相等元素的个数
            int count = 0;
            while (firstTargetIndex < nums2.length && nums2[firstTargetIndex] == nums1[i]) {
                count++;
                firstTargetIndex++;
            }

            // 尽量在 nums1 中找到 count 个与 nums1[i] 相等元素的个数
            // 注意：有可能 nums1 中数值为 nums1[i] 不足 count 个
            int j = i;
            while (j < nums1.length && nums1[j] == nums1[i]) {
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
    // 使用：思路二 => 在循环体内排除没有目标值的区间
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
        if (nums[left] == target) return left;
        return -1;
    }

    // KeyPoint 进阶：如果给定的数组已经排好序呢？你将如何优化你的算法？ => 双指针
    // 时间复杂度：O(mlogm + nlogn)
    // 空间复杂度：O(m + n)
    public int[] intersect3(int[] nums1, int[] nums2) {

        if (nums1 == null || nums2 == null) return null;
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int[] res = new int[Math.min(nums1.length, nums2.length)];
        int index = 0;
        // 使用双指针去重
        int i = 0, j = 0;
        // 时间复杂度：O(m + n)
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                // 没有去重的逻辑
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
