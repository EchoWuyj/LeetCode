package alg_02_train_dm._13_day_综合应用一_二刷;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author Wuyj
 * @DateTime 2023-07-31 20:01
 * @Version 1.0
 */
public class _07_349_IntersectionOfTwoArrays3 {

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

        int[] res = new int[resultSet.size()];
        int i = 0;
        for (int num : resultSet) {
            res[i++] = num;
        }
        return res;
    }
}
