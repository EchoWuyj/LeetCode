package alg_02_train_dm._13_day_综合应用一;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author Wuyj
 * @DateTime 2023-03-17 18:23
 * @Version 1.0
 */
public class _07_349_IntersectionOfTwoArrays1 {

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
        Set<Integer> set = new HashSet<>();
        // O(m)
        for (int num2 : nums2) {
            // 线性查找
            for (int num1 : nums1) { //O(n)
                // HashSet 的 add 操作，时间复杂度 O(1)
                if (num2 == num1) set.add(num2);
            }
        }
        int[] res = new int[set.size()];
        int i = 0;
        for (int num : set) {
            res[i++] = num;
        }
        return res;
    }
}
