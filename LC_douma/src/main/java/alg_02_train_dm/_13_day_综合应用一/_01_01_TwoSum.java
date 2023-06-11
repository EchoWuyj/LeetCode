package alg_02_train_dm._13_day_综合应用一;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-03-17 20:08
 * @Version 1.0
 */
public class _01_01_TwoSum {

    /*
        1. 两数之和
        给定一个整数数组 nums 和一个整数目标值 target，
        请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。

        你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
        你可以按任意顺序返回答案。

        示例 1：
        输入：nums = [2,7,11,15], target = 9
        输出：[0,1]
        解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。

        提示：
        2 <= nums.length <= 10^4
        -10^9 <= nums[i] <= 10^9
        -10^9 <= target <= 10^9
        只会存在一个有效答案
     */

    // 面对算法题，一开始一般都是使用最简单，最暴力解法
    // 然后再去不断优化，所以不要想着一步就想出最优解，除非之前你之前遇到过 [doge]

    // KeyPoint 方法一 线性查找
    // 时间复杂度：O(n^2)
    // 空间复杂度：O(1)
    public int[] twoSum1(int[] nums, int target) {
        if (nums == null || nums.length == 0) return null;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            // 固定一个元素
            int x = nums[i];
            // KeyPoint 线性查找 => 性能瓶颈 => 思考别的查找方式(二分，哈希，堆)
            // 同一个元素在答案里不能重复出现 => j 必然是从 i+1 开始
            for (int j = i + 1; j < n; j++) {
                if (nums[j] == target - x) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    // KeyPoint 方法二 二分查找(存在 bug)
    // 时间复杂度：O(nlogn)
    // 空间复杂度：O(1)
    public int[] twoSum2(int[] nums, int target) {
        if (nums == null || nums.length == 0) return null;
        // KeyPoint 二分查找，存在 bug
        // 不能对原数组进行排序，因为排序会改变原数组中元素的相对位置(索引)，这样返回索引就不对了
        // 解决方法：使用 Map 记录：'元素'和'索引'映射
        Arrays.sort(nums); // O(nlogn)
        int n = nums.length;
        // O(nlogn)
        for (int i = 0; i < n; i++) { // O(n)
            int x = nums[i];
            // 二分查找 O(logn)
            int index = binarySearch(nums, i + 1, n - 1, target - x);
            if (index != -1) {
                return new int[]{i, index};
            }
        }
        return null;
    }

    // 二分查找
    private int binarySearch(int[] nums, int left, int right, int target) {
        return -1;
    }

    // KeyPoint 修复 bug
    public int[] twoSum21(int[] nums, int target) {
        if (nums == null || nums.length == 0) return nums;
        int n = nums.length;
        // key -> num
        // value -> 索引集合(num 重复，对应多个索引)
        Map<Integer, ArrayList<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            ArrayList<Integer> index;
            if (!map.containsKey(nums[i])) {
                index = new ArrayList<>();
            } else {
                index = map.get(nums[i]);
            }
            index.add(i);
            map.put(nums[i], index);
        }

        Arrays.sort(nums);
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            int num = bs(nums, i + 1, n - 1, target - x);
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

    // KeyPoint 修复 bug -> 简化 Map -> 失败，因为排序缘故
    public static int[] twoSum22(int[] nums, int target) {
        if (nums == null || nums.length == 0) return nums;
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(nums[i], i);
        }
        Arrays.sort(nums);
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            int num = bs(nums, i + 1, n - 1, target - x);
            if (num != Integer.MIN_VALUE) {
                // 根据 num 和 x 是否相等，分情况讨论
                if (num != x) {
                    return new int[]{map.get(x), map.get(num)};
                } else {
                    // 相等，跳过
                    if (i == map.get(num)) continue;
                    // 若数组 nums 中存在相同元素，不同索引的解，则返回 int[]，否则返回 null
                    // KeyPoint i 还是存在 bug，此时 i 是排完序的 i，不是之前的 i，索引的获取，只能通过 map
                    return new int[]{i, map.get(num)};
                }
            }
        }
        return null;
    }

    // 二分查找
    private static int bs(int[] nums, int left, int right, int target) {
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

    public static void main(String[] args) {
        int[] arr1 = {3, 2, 4};
        int target = 6;
        System.out.println(Arrays.toString(twoSum22(arr1, 6))); // [0, 2]
    }

    // KeyPoint 方法三 双指针
    // 时间复杂度：O(nlogn)
    // 空间复杂度：O(1)
    public int[] twoSum3(int[] nums, int target) {
        if (nums == null || nums.length == 0) return null;
        // KeyPoint 存在个 bug
        // 不能对原数组进行排序，因为排序会改变原数组中元素的相对位置(索引)，这样返回索引就不对了
        Arrays.sort(nums); // O(nlogn)
        int n = nums.length;
        int i = 0;
        int j = n - 1;
        while (i < j) { // O(n/2)
            int sum = nums[i] + nums[j];
            if (sum == target) {
                return new int[]{i, j};
            } else if (sum < target) {
                i++;
            } else {
                j--;
            }
        }
        return null;
    }

    // KeyPoint 方法四 哈希查找 + 两次遍历 => 空间换时间
    // 空间换时间，绝大多数情况，时间和空间是不可兼得的
    // 时间复杂度：O(2n)
    // 空间复杂度：O(n)
    public int[] twoSum4(int[] nums, int target) {
        if (nums == null || nums.length == 0) return null;
        int n = nums.length;
        // 数据预处理 => 建立映射表
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) { // O(n)
            // num 相同，但是 i 是不同的
            map.put(nums[i], i);
        }
        for (int i = 0; i < n; i++) { // O(n)
            int x = nums[i];
            // 哈希查找 -> O(1)
            if (map.containsKey(target - x)) {
                int index = map.get(target - x);
                // 因为有两次独立 for 循环，此时获取 index 可能是 i
                // 但题目要求：同一个元素不能使用两次
                // i != index => x 和 target - x 不是同一个元素
                if (i != index) return new int[]{i, index};
            }
        }
        return null;
    }

    // KeyPoint 方法五 哈希查找 + 一次遍历 => 空间换时间 => 最优解
    // 空间换时间，绝大多数情况，时间和空间是不可兼得的
    // 时间复杂度：O(n)
    // 空间复杂度：O(n)
    public int[] twoSum(int[] nums, int target) {
        // KeyPoint 判空先后逻辑有讲究，先是 null，再是 nums.length，避免空指针异常
        if (nums == null || nums.length == 0) return null;
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) { // O(n)
            int x = nums[i];
            // 哈希查找
            if (map.containsKey(target - x)) {
                int index = map.get(target - x);
                // 同一个 for 循环，此时 map 还没有 put(x, i)，故不需要 i != index
                return new int[]{i, index};
            }
            // 不在也要将其放进 map 中，后面别的元素可能要使用到
            map.put(x, i);
        }
        return null;
    }
}
