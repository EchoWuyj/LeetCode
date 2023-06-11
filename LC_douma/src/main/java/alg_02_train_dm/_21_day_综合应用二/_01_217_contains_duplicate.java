package alg_02_train_dm._21_day_综合应用二;

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

    // KeyPoint 方法一 等值查找 => 线性查找(超时)
    // 时间复杂度：O(n^2)
    // 空间复杂度：O(1)
    public boolean containsDuplicate1(int[] nums) {
        if (nums == null) return false;
        for (int i = 0; i < nums.length; i++) {
            // 在 [i+1, nums.length-1] 区间内线性查找 nums[i]
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) return true;
            }
        }
        return false;
    }

    // KeyPoint 方法二 线性查找(超时)
    // 时间复杂度：O(n^2)
    // 空间复杂度：O(1)
    public boolean containsDuplicate(int[] nums) {
        if (nums == null) return false;
        for (int i = 0; i < nums.length; i++) {
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
        Set<Integer> visited = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            // 在 [0, i-1] 区间内哈希查找 nums[i]
            // 将已将遍历元素存在哈希表中，对于新来的元素去哈希表中判断是否存在
            if (visited.contains(nums[i])) {
                return true;
            }
            visited.add(nums[i]);
        }

        return false;

        // KeyPoint 简化形式 => 推荐使用
//        for (int num : nums) {
//            if (set.contains(num)) return true;
//            set.add(num);
//        }

    }

    // KeyPoint 方法四 排序优化
    // 时间复杂度：O(nlogn)
    // 空间复杂度：O(logn) 或者 O(n)
    public boolean containsDuplicate4(int[] nums) {
        if (nums == null) return false;
        // 只要判断数组是否存在重复数字，其位置可以随意改变，故可以排序
        Arrays.sort(nums);
        // 涉及 [i-1]，故 i 从 1 开始
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) return true;
        }
        return false;
    }
}
