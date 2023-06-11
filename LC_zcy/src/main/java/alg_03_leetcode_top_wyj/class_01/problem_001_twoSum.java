package alg_03_leetcode_top_wyj.class_01;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @Author Wuyj
 * @DateTime 2023-02-17 18:51
 * @Version 1.0
 */
public class problem_001_twoSum {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{i, map.get(target - nums[i])};
            }
            map.put(nums[i], i);
        }
        return new int[]{-1, -1};
    }

    public int[] twoSum1(int[] nums, int target) {
        // 不能排序,影响原来索引
        // 即排完序索引!=原始索引
        Arrays.sort(nums);
        int l = 0;
        int r = nums.length - 1;
        while (l < r) {
            if (nums[l] + nums[r] < target) {
                l++;
            } else if (nums[l] + nums[r] > target) {
                r--;
            } else {

                return new int[]{l, r};
            }
        }
        return new int[]{-1, -1};
    }
}
