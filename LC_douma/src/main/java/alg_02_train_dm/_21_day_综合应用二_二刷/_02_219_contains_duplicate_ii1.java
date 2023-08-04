package alg_02_train_dm._21_day_综合应用二_二刷;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-05-26 10:47
 * @Version 1.0
 */
public class _02_219_contains_duplicate_ii1 {
    
     /* 
        219. 存在重复元素 II
        给定一个整数数组和一个整数 k，判断数组中是否存在两个不同的索引 i 和 j，
        使得 nums [i] = nums [j]，并且 i 和 j 的差的 绝对值 至多为 k。
    
        示例1:
        输入: nums = [1,2,3,1], k = 3
        输出: true
    
        示例 2:
        输入: nums = [1,0,1,1], k = 1
        输出: true
    
        示例 3:
        输入: nums = [1,2,3,1,2,3], k = 2
        输出: false

        提示：
        1 <= nums.length <= 10^5
        -10^9 <= nums[i] <= 10^9
        0 <= k <= 10^5
     */

    // KeyPoint 方法一 线性查找(超时)
    // 时间复杂度：O(n^2)
    // 空间复杂度：O(1)
    public boolean containsNearbyDuplicate1(int[] nums, int k) {
        if (nums == null) return false;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            // 在 [0, i-1] 区间内线性查找 nums[i]
            for (int j = 0; j < i; j++) {
                // j 在 [0, i-1] 范围内，故 i > j
                if (nums[i] == nums[j] && i - j <= k) return true;
            }
        }
        return false;
    }

    //  KeyPoint 方法二 哈希表
    // 时间复杂度：O(n)
    // 空间复杂度：O(n)
    public boolean containsNearbyDuplicate2(int[] nums, int k) {
        if (nums == null) return false;
        // Map 映射
        // key -> 元素
        // value -> 索引
        Map<Integer, Integer> map = new HashMap<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            // 在 [0, i) 区间内哈希查找和 nums[i] 相等的 nums[j]，必然是 i > j
            if (map.containsKey(nums[i])) {
                int j = map.get(nums[i]);
                if (i - j <= k) return true;
                // 若是实在搞不清 i 和 j 谁大
                // if (Math.abs(i - j) <= k) return true;
            }
            map.put(nums[i], i);
        }
        return false;
    }

    // KeyPoint 注意事项
    // 本题涉及索引，无法使用排序，因为排序后，索引会发生变化
}
