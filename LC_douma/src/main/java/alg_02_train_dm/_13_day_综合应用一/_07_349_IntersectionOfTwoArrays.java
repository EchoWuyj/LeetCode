package alg_02_train_dm._13_day_综合应用一;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author Wuyj
 * @DateTime 2023-03-17 18:23
 * @Version 1.0
 */
public class _07_349_IntersectionOfTwoArrays {

      /*
        349. 两个数组的交集
        给定两个数组，编写一个函数来计算它们的交集。

        输入：nums1 = [1,2,2,1], nums2 = [2,2]
        输出：[2]

        输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
        输出：[9,4]

        输出结果中的每个元素一定是唯一的。
        我们可以不考虑输出结果的顺序。

        提示：
        1 <= nums1.length, nums2.length <= 1000
        0 <= nums1[i], nums2[i] <= 1000
     */

    // KeyPoint 方法一 线性查找
    // 时间复杂度：O(n*m)
    // 空间复杂度：O(min(m, n))
    public int[] intersection1(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) return null;
        Set<Integer> resultSet = new HashSet<>();
        // O(m)
        for (int num2 : nums2) {
            // 线性查找
            for (int num1 : nums1) { //O(n)
                // HashSet 的 add 操作，时间复杂度 O(1)
                if (num2 == num1) resultSet.add(num2);
            }
        }
        int[] ans = new int[resultSet.size()];
        int i = 0;
        for (int num : resultSet) {
            ans[i++] = num;
        }
        return ans;
    }

    // KeyPoint 方法二 排序 + 二分查找
    // 时间复杂度：O((m + n) * logm)
    // 空间复杂度：O(min(m, n))
    public int[] intersection2(int[] nums1, int[] nums2) {

        if (nums1 == null || nums2 == null) return null;

        // KeyPoint 注意
        // 1.若两个时间复杂度数据规模相同，即都是关于是 m 的，且数量级相同，两者时间复杂度相加
        //   如：O(m*logm) 和 O(n*logm)，相加 O((m + n) * logm)
        //   本质：数量级相同，没法忽略其中一个，故只能相加
        // 2.若两个时间复杂度数据规模相同，即都是关于是 n 的，且数量级不同，时间复杂度取较大值
        //   如：O(n) 和 O(n^2) 相比较，选择 O(n^2)，
        //   本质：忽略较小数量级，保留较大数据级

        // O(m*logm)
        Arrays.sort(nums1);
        Set<Integer> resultSet = new HashSet<>();

        // O(n*logm)
        for (int num2 : nums2) { // O(n)
            // 二分查找
            if (binarySearch(nums1, num2)) { // O(logm)
                resultSet.add(num2);
            }
        }

        int[] ans = new int[resultSet.size()];
        int i = 0;
        for (int num : resultSet) {
            ans[i++] = num;
        }

        return ans;
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

    // KeyPoint 方法三 两次遍历 + Set
    // 时间复杂度：O(m+n)
    // 空间复杂度：O(m+n)
    public int[] intersection3(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) return null;
        // 空间复杂度 O(m)
        Set<Integer> set = new HashSet<>();
        // O(m)
        for (int num : nums1) set.add(num);

        // 空间复杂度 O(min(m,n))
        Set<Integer> resultSet = new HashSet<>();
        // O(n)
        for (int num2 : nums2) { // O(n)
            // 哈希查找
            if (set.contains(num2)) { // O(1)
                resultSet.add(num2);
            }
        }

        int[] ans = new int[resultSet.size()];
        int i = 0;
        for (int num : resultSet) {
            ans[i++] = num;
        }

        return ans;
    }

    // KeyPoint 方法四 双指针 + set 去重
    // 时间复杂度：O(mlogm + nlogn)
    // 空间复杂度：O(min(m, n))
    public int[] intersection4(int[] nums1, int[] nums2) {

        // KeyPoint 注意事项
        // 1.若两个数组原来不需要排序，则使用这种方式效果最好，可以考虑使用双指针的方式来解
        // 2.对数据进行预处理，预处理很大没有关系，因为只需要执行一次即可
        //   但是后面查询等其他的操作执行次数很多，需要将执行次数越多的操作，时间复杂度降到最低

        if (nums1 == null || nums2 == null) return null;
        Arrays.sort(nums1); // O(mlogm)
        Arrays.sort(nums2); // O(nlogn)

        // 使用 HashSet 去重
        Set<Integer> resultSet = new HashSet<>();
        int i = 0, j = 0;
        // 最坏情况，两个数组中元素全都遍历一遍，故时间复杂度 O(m + n)
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                resultSet.add(nums1[i]);
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
        int[] res = new int[resultSet.size()];
        int k = 0;
        for (int num : resultSet) {
            res[k++] = num;
        }
        return res;
    }

    // KeyPoint 方法五 双指针 + 手动去重
    // 时间复杂度：O(mlogm + nlogn)
    // 空间复杂度：O(min(m, n))
    public int[] intersection(int[] nums1, int[] nums2) {

        if (nums1 == null || nums2 == null) return null;
        // KeyPoint 手动去重
        // 除了使用 HashSet 去重，使用排序去重 => 排序后相同元素紧挨着，从而进行去重
        Arrays.sort(nums1); // O(mlogm)
        Arrays.sort(nums2); // O(nlogn)

        int[] res = new int[Math.min(nums1.length, nums2.length)];
        int index = 0;
        int i = 0, j = 0;

        while (i < nums1.length && j < nums2.length) {
            // 保证加入元素的唯一性
            if (nums1[i] == nums2[j]) {
                // 手动去重：res 最后一个元素 != 交集元素 nums1[i]，才加入 res 中，否则直接跳过
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
        // 从 res 拷贝一部分，从 0 到 index(取不到)，[)
        return Arrays.copyOfRange(res, 0, index);
    }
}
