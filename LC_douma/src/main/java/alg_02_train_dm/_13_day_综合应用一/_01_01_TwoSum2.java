package alg_02_train_dm._13_day_综合应用一;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-07-31 11:47
 * @Version 1.0
 */
public class _01_01_TwoSum2 {

    // KeyPoint 方法二 二分查找 => 存在 bug
    // 时间复杂度：O(nlogn)
    // 空间复杂度：O(1)
    public int[] twoSum2(int[] nums, int target) {
        if (nums == null || nums.length == 0) return null;
        // KeyPoint 二分查找 => 存在 bug
        // 不能对原数组进行排序，因为排序会改变原数组中元素的相对位置(索引)，这样返回索引就不对了
        // 解决方法：使用 Map 记录：数组元素值 和 索引 映射
        Arrays.sort(nums); // O(nlogn)
        int n = nums.length;
        // O(nlogn)
        for (int i = 0; i < n; i++) { // O(n)
            int num1 = nums[i];
            // 二分查找 O(logn)
            // start：i+1
            // end：n-1
            // target：target - num1
            int index = binarySearch(nums, i + 1, n - 1, target - num1);
            if (index != -1) {
                return new int[]{i, index};
            }
        }
        return null;
    }

    // 二分查找
    private static int binarySearch(int[] nums, int left, int right, int target) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                // 返回数组元素值，不是索引
                return nums[mid];
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return Integer.MIN_VALUE;
    }

    // KeyPoint 修复 bug
    public int[] twoSum2_1(int[] nums, int target) {
        if (nums == null || nums.length == 0) return nums;
        int n = nums.length;
        // 映射
        // key -> num
        // value -> 索引集合(nums 数组中 num 可能重复，对应多个索引，使用 List 来存储)
        Map<Integer, ArrayList<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            ArrayList<Integer> indexList;
            // 这里 if 无法使用三元运算符简化
            if (!map.containsKey(nums[i])) {
                indexList = new ArrayList<>();
            } else {
                indexList = map.get(nums[i]);
            }
            indexList.add(i);
            map.put(nums[i], indexList);
        }

        Arrays.sort(nums);
        for (int i = 0; i < n; i++) {
            int num1 = nums[i];
            int num2 = binarySearch(nums, i + 1, n - 1, target - num1);
            if (num2 != Integer.MIN_VALUE) {
                if (num2 != num1) {
                    return new int[]{map.get(num1).get(0), map.get(num2).get(0)};
                } else {
                    // num2 == num1
                    return new int[]{map.get(num1).get(0), map.get(num1).get(1)};
                }
            }
        }
        return null;
    }

    // KeyPoint 简化 Map 失败
    // 因为数组 nums 可能存在相同元素，若 Map <Integer,Integer>
    // 导致多个相同元素 num，但只存储一个索引，之前索引被覆盖
    public static int[] twoSum22(int[] nums, int target) {
        if (nums == null || nums.length == 0) return nums;
        int n = nums.length;

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(nums[i], i);
        }
        Arrays.sort(nums);
        for (int i = 0; i < n; i++) {
            int num1 = nums[i];
            int num2 = binarySearch(nums, i + 1, n - 1, target - num1);
            if (num2 != Integer.MIN_VALUE) {
                // 根据 num2 和 num1 是否相等，分情况讨论
                if (num2 != num1) {
                    return new int[]{map.get(num1), map.get(num2)};
                } else {
                    // KeyPoint num1 和 num2 相等 => 同一个元素
                    // 因为Map <Integer,Integer> 导致多个相同元素 num，只存储一个索引，之前索引被覆盖
                    if (map.get(num1).equals(map.get(num2))) continue;
                    return new int[]{i, map.get(num2)};
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        int[] arr1 = {3, 2, 4};
        int target = 6;
        System.out.println(Arrays.toString(twoSum22(arr1, 6))); // [0, 2]
    }
}
