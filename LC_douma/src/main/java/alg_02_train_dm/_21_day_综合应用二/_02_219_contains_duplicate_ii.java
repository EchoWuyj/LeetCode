package alg_02_train_dm._21_day_综合应用二;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Author Wuyj
 * @DateTime 2023-05-26 10:47
 * @Version 1.0
 */
public class _02_219_contains_duplicate_ii {
    
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
        for (int i = 0; i < nums.length; i++) {
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
        // key -> 元素
        // value -> 索引
        // KeyPoint 涉及索引，无法使用排序，因为排序后，索引会发生变化
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            // 在 [0, i) 区间内哈希查找 nums[i]
            if (indexMap.containsKey(nums[i])) {
                int j = indexMap.get(nums[i]);
                if (i - j <= k) return true;
            }
            indexMap.put(nums[i], i);
        }
        return false;
    }

    // KeyPoint 方法三 滑动窗口
    // 使用窗口维护 k+1 个元素的即可，不需要使用哈希表维护 n 个元素 => 节省空间
    // 时间复杂度：O(n*min(n, k)) => 注意：k 有可能大于 n
    // 空间复杂度：O(1)
    public boolean containsNearbyDuplicate3(int[] nums, int k) {
        if (nums == null) return false;
        int left = 0;
        int right = 0;
        while (right < nums.length) {
            left = Math.max(0, right - k);
            // 窗口内 => 线性查找
            while (left < right) {
                if (nums[left] == nums[right]) return true;
                // 多次循环，left 最后移动到 right -1位置
                // 执行 left = Math.max(0, right - k) 后 left 又往回退
                left++;
            }
            right++;
        }
        return false;
    }

    // KeyPoint 方法四 滑动窗口
    // 时间复杂度：O(n)
    // 空间复杂度：O(min(n, k))
    public boolean containsNearbyDuplicate4(int[] nums, int k) {
        if (nums == null) return false;
        int left = 0;
        int right = 0;
        Set<Integer> window = new HashSet<>();
        while (right < nums.length) {
            // 窗口内 => 哈希查找
            if (window.contains(nums[right])) return true;
            // 右侧新遍历到元素，将其加入窗口中
            window.add(nums[right]);

            if (window.size() > k) {
                // 左侧最久的元素，将其从窗口中移除
                window.remove(nums[left]);
                left++;
            }
            right++;
        }
        return false;
    }
}
