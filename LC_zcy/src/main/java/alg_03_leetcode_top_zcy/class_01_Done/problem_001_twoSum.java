package alg_03_leetcode_top_zcy.class_01_Done;

import java.util.HashMap;

/**
 * @Author Wuyj
 * @DateTime 2023-02-13 11:03
 * @Version 1.0
 */

// 两数之和
public class problem_001_twoSum {
    public int[] twoSum(int[] nums, int target) {
        // HashMap -> key(num) value(index)
        // key 某个之前的数
        // value 这个数出现的位置索引
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            // 找i位置数值,对应target的剩余值
            if (map.containsKey(target - nums[i])) {
                // 返回两个数的索引
                return new int[]{i, map.get(target - nums[i])};
            }
            map.put(nums[i], i);
        }

        return new int[]{-1, -1};
    }
}

