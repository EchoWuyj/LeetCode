package alg_03_leetcode_top_wyj.class_03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-02-20 19:05
 * @Version 1.0
 */
public class problem_015_threeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return ans;
        }
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            if (i == 0 || nums[i - 1] == nums[i]) {
                List<List<Integer>> nexts = twoSum(nums, i + 1, -nums[i]);
                for (List<Integer> next : nexts) {
                    next.add(0, nums[i]);
                    ans.add(next);
                }
            }
        }
        return ans;
    }

    public List<List<Integer>> twoSum(int[] nums, int begin, int target) {
        int l = begin;
        int r = nums.length - 1;
        List<List<Integer>> ans = new ArrayList<>();
        while (l < r) {
            if (nums[l] + nums[r] < target) {
                l++;
            } else if (nums[l] + nums[r] > target) {
                r--;
            } else {
                if (l == begin || nums[l - 1] != nums[l]) {
                    List<Integer> cur = new ArrayList<>();
                    cur.add(nums[l]);
                    cur.add(nums[r]);
                    ans.add(cur);
                }
                l++;
            }
        }
        return ans;
    }
}
