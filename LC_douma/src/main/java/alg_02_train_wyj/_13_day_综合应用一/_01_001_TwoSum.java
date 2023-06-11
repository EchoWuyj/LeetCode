package alg_02_train_wyj._13_day_综合应用一;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-05-08 20:15
 * @Version 1.0
 */
public class _01_001_TwoSum {

    public int[] twoSum1(int[] nums, int target) {
        if (nums == null || nums.length == 0) return nums;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            for (int j = i + 1; j < n; j++) {
                if (nums[j] == target - x) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    public int[] twoSum2(int[] nums, int target) {
        if (nums == null || nums.length == 0) return nums;
        int n = nums.length;
        Map<Integer, ArrayList<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            ArrayList<Integer> index;
            if (!map.containsKey(nums[i])) {
                index = new ArrayList<Integer>();
            } else {
                index = map.get(nums[i]);
            }
            index.add(i);
            map.put(nums[i], index);
        }

        Arrays.sort(nums);
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            int num = binarySearch(nums, i + 1, n - 1, target - x);
            if (num != Integer.MIN_VALUE) {
                if (num != x) {
                    return new int[]{map.get(x).get(0), map.get(num).get(0)};
                } else {
                    return new int[]{map.get(x).get(0), map.get(x).get(1)};
                }
            }
        }
        return null;
    }

    private int binarySearch(int[] nums, int left, int right, int target) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return nums[mid];
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return Integer.MIN_VALUE;
    }

    public int[] twoSum3(int[] nums, int target) {
        if (nums == null || nums.length == 0) return nums;
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(nums[i], i);
        }

        for (int i = 0; i < n; i++) {
            int x = nums[i];
            if (map.containsKey(target - x)) {
                int index = map.get(target - x);
                if (i != index) {
                    return new int[]{i, index};
                }
            }
        }
        return null;
    }

    public int[] twoSum4(int[] nums, int target) {
        if (nums == null || nums.length == 0) return nums;
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            int y = target - x;
            if (map.containsKey(y)) {
                int index = map.get(y);
                return new int[]{i, index};
            }
            map.put(x, i);
        }
        return null;
    }
}
