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
        if (nums == null || nums.length == 0) return null;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    public int[] twoSum2(int[] nums, int target) {
        if (nums == null || nums.length == 0) return null;
        Map<Integer, ArrayList<Integer>> map = new HashMap<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            ArrayList<Integer> indexList;
            if (map.containsKey(nums[i])) {
                indexList = map.get(nums[i]);
            } else {
                indexList = new ArrayList<>();
            }
            indexList.add(i);
            map.put(nums[i], indexList);
        }

        Arrays.sort(nums);
        for (int i = 0; i < n; i++) {
            int num1 = nums[i];
            int num2 = binarySearch(nums, i + 1, n - 1, target - num1);
            if (num2 != Integer.MIN_VALUE) {
                if (num1 == num2) {
                    return new int[]{map.get(num1).get(0), map.get(num2).get(1)};
                } else {
                    return new int[]{map.get(num1).get(0), map.get(num2).get(0)};
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

    public int[] twoSum3_1(int[] nums, int target) {
        if (nums == null || nums.length == 0) return nums;
        int n = nums.length;
        Arrays.sort(nums);
        int i = 0, j = n - 1;
        while (i < j) {
            int sum = nums[i] + nums[j];
            if (sum == target) {
                return new int[]{i, j};
            } else if (sum < target) {
                i++;
            } else { // sum > target
                j--;
            }
        }
        return null;
    }

    public int[] twoSum3(int[] nums, int target) {
        if (nums == null || nums.length == 0) return nums;
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(nums[i], i);
        }

        for (int i = 0; i < n; i++) {
            int num1 = nums[i];
            int num2 = target - num1;
            if (map.containsKey(num2)) {
                int index = map.get(num2);
                if (i != index) return new int[]{i, index};
            }
        }
        return null;
    }

    public int[] twoSum4(int[] nums, int target) {
        if (nums == null || nums.length == 0) return nums;
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int num1 = nums[i];
            int num2 = target - num1;
            if (map.containsKey(num2)) {
                int index = map.get(num2);
                return new int[]{i, index};
            }
            map.put(num1, i);
        }
        return null;
    }
}
