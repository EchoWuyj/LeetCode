package alg_02_train_dm._13_day_综合应用一;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author Wuyj
 * @DateTime 2023-07-31 20:01
 * @Version 1.0
 */
public class _07_349_IntersectionOfTwoArrays4 {

    // KeyPoint 方法四 双指针 + set 去重
    // 时间复杂度：O(mlogm + nlogn)
    // 空间复杂度：O(min(m, n))
    public int[] intersection4(int[] nums1, int[] nums2) {

        if (nums1 == null || nums2 == null) return null;
        // KeyPoint 注意事项
        // 1.若两个数组原来不需要排序，则使用这种方式效果最好，可以考虑使用双指针的方式来解
        // 2.对数据进行预处理，预处理时间复杂度很高没有关系，因为只需要执行一次即可
        //   但是后面查询等其他的操作执行次数很多，需要将执行次数越多的操作，时间复杂度降到最低
        Arrays.sort(nums1); // O(mlogm)
        Arrays.sort(nums2); // O(nlogn)

        // 使用 HashSet 去重
        Set<Integer> set = new HashSet<>();
        int i = 0, j = 0;
        int n = nums1.length, m = nums2.length;
        // 最坏情况，两个数组中元素全都遍历一遍，故时间复杂度 O(m + n)
        while (i < n && j < m) {
            if (nums1[i] == nums2[j]) {
                set.add(nums1[i]);
                // i 和 j 指针同时向右移动
                i++;
                j++;
                // 若 nums1[i] != nums2[j]，则始终将数组中值小的元素 nums1[i] 或 nums2[j] 对应的指针右移
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                j++;
            }
        }
        int[] res = new int[set.size()];
        int index = 0;
        for (int num : set) {
            res[index++] = num;
        }
        return res;
    }

    // KeyPoint 方法五 双指针 + 手动去重
    // 时间复杂度：O(mlogm + nlogn)
    // 空间复杂度：O(min(m, n))
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) return null;
        // KeyPoint 手动去重
        // 除了使用 HashSet 去重，使用排序去重 => 排序后相同元素紧挨着，利用这种特性，从而进行手动去重
        Arrays.sort(nums1); // O(mlogm)
        Arrays.sort(nums2); // O(nlogn)

        int n = nums1.length, m = nums2.length;

        int[] res = new int[Math.min(n, m)];
        int index = 0;
        int i = 0, j = 0;

        while (i < n && j < m) {
            // 保证加入元素的唯一性
            if (nums1[i] == nums2[j]) {

                // 手动去重，对于加入结果集 res，进行限制
                // res 最后一个元素 != 交集元素，才加入 res 中，否则直接跳过
                // => res[index-1] != nums1[i]
                // KeyPoint 注意：index 时刻在越界位置，所以 res 最后一个元素，index 需要减 1
                if (index == 0 || res[index - 1] != nums1[i]) {
                    res[index++] = nums1[i];
                }
                // i 和 j 指针同时向右移动
                i++;
                j++;
                // 若 nums1[i] != nums2[j]，则始终将值小元素对应的指针右移
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                j++;
            }
        }
        // 从 res 拷贝一部分，从 0 到 index，注意 index 取不到的 => [)
        return Arrays.copyOfRange(res, 0, index);
    }
}
