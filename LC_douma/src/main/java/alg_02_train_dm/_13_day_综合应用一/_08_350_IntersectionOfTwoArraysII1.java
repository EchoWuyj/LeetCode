package alg_02_train_dm._13_day_综合应用一;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2023-03-17 19:49
 * @Version 1.0
 */
public class _08_350_IntersectionOfTwoArraysII1 {
     /*
        350. 两个数组的交集 II
        给定两个数组，编写一个函数来计算它们的交集。

        输入：nums1 = [1,2,2,2], nums2 = [2,2]
        输出：[2,2]

        输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
        输出：[4,9]

        输出结果中每个元素出现的次数，应与元素在两个数组中出现次数的最小值一致。
        我们可以不考虑输出结果的顺序。

        提示：
        1 <= nums1.length, nums2.length <= 1000
        0 <= nums1[i], nums2[i] <= 1000

     */

    // KeyPoint 哈希查找 => Map 实现
    // 时间复杂度：O(m + n)
    // 空间复杂度：O(min(m, n) + n)
    public int[] intersect1(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) return null;
        // key => 元素
        // value => 出现次数
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : nums2) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }
        int n = nums1.length, m = nums2.length;
        int[] res = new int[Math.min(n, m)];
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

    // KeyPoint 哈希查找 => 数组实现 => 推荐使用
    // 时间复杂度：O(m + n)
    // 空间复杂度：O(1001) => O(1)
    public int[] intersect2(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) return null;
        // 根据提示：0 <= nums1[i], nums2[i] <= 1000
        // 直接定义大小为 1001 数组即可，数字范围 [0,1000]，而不是 int[1000]
        int[] count = new int[1001];
        for (int num : nums1) {
            count[num]++;
            // 统计数字词频，直接使用 count[num]
            // 不用 count[num-'0']++ 这是字母字符使用的方式
        }
        int n = nums1.length, m = nums2.length;
        int index = 0;
        int[] res = new int[Math.min(m, n)];
        for (int num : nums2) {
            if (count[num] > 0) {
                res[index++] = num;
                count[num]--;
            }
        }
        return Arrays.copyOfRange(res, 0, index);
    }
}
