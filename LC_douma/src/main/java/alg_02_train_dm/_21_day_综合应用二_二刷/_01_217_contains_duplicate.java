package alg_02_train_dm._21_day_综合应用二_二刷;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author Wuyj
 * @DateTime 2023-05-26 10:22
 * @Version 1.0
 */
public class _01_217_contains_duplicate {

     /*
        217. 存在重复元素
        给定一个整数数组，判断是否存在重复元素
        如果存在一个值在数组中出现至少两次，函数返回 true
        如果数组中每个元素都不相同，则返回 false

        示例 1:
        输入: [1,2,3,1]
        输出: true

        示例 2:
        输入: [1,2,3,4]
        输出: false

        示例 3:
        输入: [1,1,1,3,3,4,3,2,4,2]
        输出: true

        提示：
        1 <= nums.length <= 10^5
        -10^9 <= nums[i] <= 10^9

     */

    // KeyPoint 方法一 等值查找 => 线性查找 => 超时
    // 时间复杂度：O(n^2)
    // 空间复杂度：O(1)
    public boolean containsDuplicate1(int[] nums) {
        if (nums == null) return false;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            // KeyPoint 从前往后查找
            // 在 [i+1, n-1] 区间内线性查找 nums[i]
            for (int j = i + 1; j < n; j++) {
                if (nums[i] == nums[j]) return true;
            }
        }
        return false;
    }

    // KeyPoint 方法二 线性查找 => 超时
    // 时间复杂度：O(n^2)
    // 空间复杂度：O(1)
    public boolean containsDuplicate(int[] nums) {
        if (nums == null) return false;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            // KeyPoint 从后往前查找
            // 在 [0, i-1] 区间内线性查找 nums[i]
            for (int j = 0; j < i; j++) {
                if (nums[i] == nums[j]) return true;
            }
        }
        return false;
    }

    // KeyPoint 方法三 哈希查找
    // 时间复杂度：O(n)
    // 空间复杂度：O(n)
    public boolean containsDuplicate3(int[] nums) {
        if (nums == null) return false;
        Set<Integer> set = new HashSet<>();
        int n = nums.length;
        // 在 [0, i-1] 区间内哈希查找 nums[i]
        for (int i = 0; i < n; i++) {
            // 若 nums[i] 已经存在 set 中，直接返回 true
            if (set.contains(nums[i])) {
                return true;
            }
            // 若 nums[i] 不存在 set 中，将其加入 set 中
            set.add(nums[i]);
        }

        return false;

        // KeyPoint 简化形式 => 推荐使用
//        for (int num : nums) {
//            if (set.contains(num)) return true;
//            set.add(num);
//        }

    }

    // KeyPoint 方法四 排序，手动去重
    // 时间复杂度：O(nlogn)
    // 空间复杂度：O(logn) 或者 O(n)
    public boolean containsDuplicate4(int[] nums) {
        if (nums == null) return false;
        // 只要判断数组是否存在重复数字，其位置可以随意改变，故可以排序
        Arrays.sort(nums);
        int n = nums.length;
        // 涉及 [i-1]，故 i 从 1 开始，避免索引越界
        for (int i = 1; i < n; i++) {
            if (nums[i] == nums[i - 1]) return true;
        }
        return false;
    }
}
